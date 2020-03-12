package com.omnia.docclassifier.services;

// Include the following imports to use blob APIs.

// Include the following imports to use blob APIs.

import com.azure.storage.file.share.ShareFileClient;
import com.microsoft.azure.storage.StorageException;
import com.omnia.docclassifier.config.AppConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.time.OffsetDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

//@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("unittest")
public class AzureFileShareServiceTest {

    @Autowired
    private AppConfig _config;

    @Autowired
    private AzureFileShareService azureFileShareService;


    @Test
    public void testFileShareWalk() throws URISyntaxException, StorageException, InvalidKeyException, MalformedURLException, UnsupportedEncodingException {

        OffsetDateTime lastScanDt = OffsetDateTime.now().minusYears(1);

        List<ShareFileClient> sfcList = azureFileShareService.getModified(_config.getAzureFileShareName(),
                                                                          _config.getAzureFileShareConnStr(),
                                                                          _config.getAzureFileShareEndpoint(),
                                                                          lastScanDt);

        assertTrue(sfcList.size() > 0);

//        CloudStorageAccount csa = CloudStorageAccount.parse(_config.getAzureFileShareConnStr());
//
//        CloudFileClient fileClient = csa.createCloudFileClient();
//        CloudFileShare share = fileClient.getShareReference(_config.getAzureFileShareName());
//        ShareClient shareClient = new ShareClientBuilder()
//                .shareName(_config.getAzureFileShareName())
//                .connectionString(_config.getAzureFileShareConnStr())
//                .endpoint(_config.getAzureFileShareEndpoint())
//                .buildClient();
//
//        Queue<ShareDirectoryClient> remaining = new LinkedList<>();
//        remaining.add(shareClient.getRootDirectoryClient());
//
//        while(remaining.size() > 0) {
//
//            ShareDirectoryClient dir = remaining.poll();
//            System.out.println(String.format("Directory URL: %s, path: %s", dir.getDirectoryUrl(), dir.getDirectoryPath()));
//
//            for (ShareFileItem item : dir.listFilesAndDirectories()) {
//                if (item.isDirectory()) {
//                    if (item.getName().startsWith(".")) {
//                        System.out.println(String.format("%s is a hidden directory.", item.getName()));
//                    }
//                    else {
//                        System.out.println(String.format("%s is a directory.", item.getName()));
//                        //remaining.add(dir.getSubdirectoryClient(UriUtils.encodePath(item.getName(), "UTF-8")));
//                        remaining.add(dir.getSubdirectoryClient(item.getName()));
//                    }
//                }
//                else {
//                    System.out.println(String.format("%s is a file.", item.getName()));
//                    ShareFileClient client = dir.getFileClient(item.getName());
//                    ShareFileProperties props = client.getProperties();
//                    if (props.getLastModified().isAfter())
//                }
//            }
//
//        }

        //Get a reference to the root directory for the share.
//        CloudFileDirectory rootDir = share.getRootDirectoryReference();
////        ShareDirectoryClient dirClient = new ShareFileClientBuilder()
////                .connectionString(_config.getAzureFileShareConnStr())
////                .endpoint(_config.getAzureFileShareEndpoint())
////                .shareName(_config.getAzureFileShareName())
////                .resourcePath("")
////                .buildDirectoryClient();
//
//        getAfsDirectory(Date.from(Instant.now()), rootDir);
//        System.out.println("*** Total File Count: " + this.resultFileList.size());
    }

}
