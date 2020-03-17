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
import java.time.Instant;
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

        // this should grab all files since they were recently uploaded
        OffsetDateTime lastScanDt = OffsetDateTime.now().minusYears(1);

        Instant dtStart = Instant.now();
        List<ShareFileClient> sfcList = azureFileShareService.getModified(_config.getAzureFileShareName(),
                                                                          _config.getAzureFileShareConnStr(),
                                                                          _config.getAzureFileShareEndpoint(),
                                                                          lastScanDt);

        Instant dtEnd = Instant.now();
        long allFilesMillis = dtEnd.toEpochMilli() - dtStart.toEpochMilli();

        long allFilesCount = sfcList.size();
        assertTrue(allFilesCount > 0);

        lastScanDt = OffsetDateTime.now();

        dtStart = Instant.now();
        sfcList = azureFileShareService.getModified(_config.getAzureFileShareName(),
                _config.getAzureFileShareConnStr(),
                _config.getAzureFileShareEndpoint(),
                lastScanDt);

        dtEnd = Instant.now();
        long noFileCount = sfcList.size();
        assertTrue(allFilesCount > noFileCount);

        long noFilesMillis = dtEnd.toEpochMilli() - dtStart.toEpochMilli();
        assertTrue(allFilesMillis > noFilesMillis);

        System.out.println("------------------");
        System.out.println("AFS Scan Results");
        System.out.println("------------------");
        System.out.println("");
        System.out.println(String.format("All Files Count: %s", allFilesCount));
        System.out.println(String.format("All Files Duration: %s ms",allFilesMillis));
        System.out.println("");
        System.out.println(String.format("No Files Count: %s", noFileCount));
        System.out.println(String.format("No Files Duration: %s ms", noFilesMillis));

    }

}
