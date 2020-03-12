package com.omnia.docclassifier.services;

import com.azure.storage.file.share.ShareClient;
import com.azure.storage.file.share.ShareClientBuilder;
import com.azure.storage.file.share.ShareDirectoryClient;
import com.azure.storage.file.share.ShareFileClient;
import com.azure.storage.file.share.models.ShareFileItem;
import com.azure.storage.file.share.models.ShareFileProperties;
import com.microsoft.azure.storage.StorageException;
import com.omnia.docclassifier.config.AppConfig;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Service
public class AzureFileShareService {

    private AppConfig _config;

    /**
     * constructor, Spring's DI gets our config
     * @param config
     */
    public AzureFileShareService(AppConfig config) {
        _config = config;
    }

    /**
     * getModified walks the share and picks up new and modified files since
     * the last scan
     * @param shareName - String, name of the file share
     * @param connectionStr - String, connection string
     * @param endpoint - String, URL of end point
     * @param lastScanDt - OffsetDateTime, the last datetime of the last scan
     * @return List&lt;CloudFileClient&gt; - the list of changed files
     * @throws URISyntaxException
     * @throws InvalidKeyException
     * @throws StorageException
     */
    public List<ShareFileClient> getModified(String shareName, String connectionStr, String endpoint,
                                             OffsetDateTime lastScanDt) throws URISyntaxException, InvalidKeyException,
                                                                    StorageException {
        // create the list for the results
        List<ShareFileClient> resultList = new ArrayList<>();

        // get a client that connects to the share itself
        ShareClient shareClient = new ShareClientBuilder()
                .shareName(_config.getAzureFileShareName())
                .connectionString(_config.getAzureFileShareConnStr())
                .endpoint(_config.getAzureFileShareEndpoint())
                .buildClient();

        // create and initialize our queue of directories to scan
        Queue<ShareDirectoryClient> remaining = new LinkedList<>();
        remaining.add(shareClient.getRootDirectoryClient());

        // keep reading each directory until we're done
        while(remaining.size() > 0) {

            // pull a directory client off the queue
            ShareDirectoryClient directoryClient = remaining.poll();
            System.out.println(String.format("Directory URL: %s, path: %s", directoryClient.getDirectoryUrl(), directoryClient.getDirectoryPath()));

            // iterate the list of items within the directory
            for (ShareFileItem item : directoryClient.listFilesAndDirectories()) {
                if (item.isDirectory()) {
                    // don't dive into hidden folders
                    if (item.getName().startsWith(".")) {
                        System.out.println(String.format("%s is a hidden directory.", item.getName()));
                    }
                    else {
                        // we enqueue any directories we find to read them later
                        System.out.println(String.format("%s is a directory.", item.getName()));
                        remaining.add(directoryClient.getSubdirectoryClient(item.getName()));
                    }
                }
                else {
                    System.out.println(String.format("%s is a file.", item.getName()));
                    // check the properties of the file via a ShareFileClient instance
                    ShareFileClient client = directoryClient.getFileClient(item.getName());
                    ShareFileProperties props = client.getProperties();
                    // modified since our last scan?
                    if (props.getLastModified().isAfter(lastScanDt)) {
                        resultList.add(client);
                    }
                }
            }

        }
        return resultList;
    }

}
