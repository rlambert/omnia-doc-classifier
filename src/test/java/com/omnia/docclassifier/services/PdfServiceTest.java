package com.omnia.docclassifier.services;

import com.azure.core.util.polling.SyncPoller;
import com.azure.storage.file.share.*;
import com.azure.storage.file.share.models.ShareFileCopyInfo;
import com.azure.storage.file.share.models.ShareFileProperties;
import com.azure.storage.file.share.models.ShareStorageException;
import com.omnia.docclassifier.config.AppConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("unittest")
public class PdfServiceTest {

    @Autowired
    private PdfService _pdfService;

    @Autowired
    private AppConfig _config;

    @Test
    public void testFileShare() {

        String TOKEN = "buvG+jUrZ9iWraE3MJHaixiLmQbrid9jsGd5u3qhLvMePwRO8z1iPKHFgdLiYocQSXEZ/hocibRntxI8QMcEfQ==";
        String connStr = "DefaultEndpointsProtocol=https;AccountName=sourcedocs;AccountKey=buvG+jUrZ9iWraE3MJHaixiLmQbrid9jsGd5u3qhLvMePwRO8z1iPKHFgdLiYocQSXEZ/hocibRntxI8QMcEfQ==;EndpointSuffix=core.windows.net";
        String endpoint = "https://sourcedocs.file.core.windows.net/sourcefiles";
        String shareName = _config.getAzureFileShareName();

        ShareServiceClient fsClient = new ShareServiceClientBuilder()
                .connectionString(connStr).buildClient();
        assertNotNull(fsClient);

        fsClient.listShares().forEach(
                share -> {
                    System.out.println(share.getName());
                }
        );

//        // Create a source file client
//        String srcFileName = generateRandomName();
//        ShareFileClient srcFileClient = new ShareFileClientBuilder().endpoint(ENDPOINT).shareName(shareName)
//                .resourcePath(parentDirName + "/" + srcFileName).buildFileClient();
//
//        // Create a source file
//        try {
//            srcFileClient.create(1024);
//        } catch (ShareStorageException e) {
//            System.out.println("Failed to create source client. Reasons: " + e.getMessage());
//        }
//
//        // Upload some data bytes to the src file.
//        byte[] data = "Hello, file client sample!".getBytes(StandardCharsets.UTF_8);
//        InputStream uploadData = new ByteArrayInputStream(data);
//        try {
//            srcFileClient.upload(uploadData, data.length);
//        } catch (ShareStorageException e) {
//            System.out.println("Failed to upload the data. Reasons: " + e.getMessage());
//        }
//        // Create a destination file client.
//        String destFileName = generateRandomName();
//        ShareFileClient destFileClient = new ShareFileClientBuilder().endpoint(ENDPOINT).shareName(shareName)
//                .resourcePath(parentDirName + "/" + destFileName).buildFileClient();
//        destFileClient.create(1024);
//
//        // Copy the file from source file to destination file.
//        String clientURL = srcFileClient.getFileUrl();
//
//        String sourceURL = clientURL + "/" + shareName + "/" + parentDirName + "/" + srcFileName;
//        Duration pollInterval = Duration.ofSeconds(2);
//        SyncPoller<ShareFileCopyInfo, Void> poller = destFileClient.beginCopy(sourceURL, null, pollInterval);
//
//        try {
//            poller.waitForCompletion(Duration.ofMinutes(15));
//        } catch (RuntimeException re) {
//            if (re.getCause() != null && re.getCause() instanceof TimeoutException) {
//                try {
//                    poller.cancelOperation();
//                } catch (ShareStorageException e) {
//                    System.out.println("Failed to abort the copy. Reasons: " + e.getMessage());
//                }
//            }
//        }
//
//        // Upload a local file to the storage.
//        String filePath = "C:/resourcePath/";
//        String uploadPath = filePath + "testfiles/" + "uploadSample.txt";
//
//        try {
//            srcFileClient.uploadFromFile(uploadPath);
//        } catch (ShareStorageException e) {
//            System.out.println("Failed to upload file to storage. Reasons: " + e.getMessage());
//        }
//
//        // Download storage file to local file.
//        String downloadPath = filePath + "testfiles/" + "downloadSample.txt";
//        File downloadFile = new File(downloadPath);
//        try {
//            if (!Files.exists(downloadFile.toPath()) && !downloadFile.createNewFile()) {
//                throw new RuntimeException("Failed to create new upload file.");
//            }
//        } catch (IOException e) {
//            throw new RuntimeException("Failed to create new upload file.");
//        }
//        try {
//            srcFileClient.downloadToFile(downloadPath);
//        } catch (ShareStorageException e) {
//            System.out.println("Failed to download file from storage. Reasons: " + e.getMessage());
//        }
//
//        if (Files.exists(downloadFile.toPath()) && !downloadFile.delete()) {
//            System.out.println("Failed to delete download file.");
//        }
//
//        // Get the file properties
//        try {
//            ShareFileProperties propertiesResponse = srcFileClient.getProperties();
//            System.out.printf("This is the eTag: %s of the file. File type is : %s.", propertiesResponse.getETag(), propertiesResponse.getFileType());
//        } catch (ShareStorageException e) {
//            System.out.println("Failed to get file properties. Reasons: " + e.getMessage());
//        }
//
//        // Delete the source file.
//        try {
//            srcFileClient.delete();
//        } catch (ShareStorageException e) {
//            System.out.println("Failed to delete the src file. Reasons: " + e.getMessage());
//        }
//
//        // Delete the share
//        shareClient.delete();

    }


    private String getText(String pdfPath) throws IOException, IllegalAccessException {
        long startTm = System.currentTimeMillis();
        String text = _pdfService.getPdfText(pdfPath);
        long endTm = System.currentTimeMillis();
        System.out.println(String.format("Time to parse text from doc: %s milliseconds", (endTm - startTm)));
        System.out.println(String.format("Text length: %s characters", text.length()));
        return text;
    }

    @Test
    public void testPdf2Text() throws IOException, IllegalAccessException {

        //final String TEST_PDF1 = "classpath:/resources/source-docs/BLEVINS 2-9 Log Analysis.pdf";
        final String TEST_PDF1 = "/Users/rosslambert/Omnia-Src/doc-classifier/src/test/resources/source-docs/BLEVINS 2-9 Log Analysis.pdf";
        final String TEST_PDF2 = "https://sampledocsstorage.blob.core.windows.net/structured/Wells/3J%20ZAPATA%20RANCHES%20%231%20ST%20-%20(42505355890000)-156665/UnManaged/Drilling/Directional%20plan%20Ryan%203%20J%20ST%20Pn%202.pdf?st=2020-03-03T21%3A02%3A08Z&se=2020-03-31T21%3A02%3A00Z&sp=rl&sv=2018-03-28&sr=b&sig=CHm8csb54nDdx1urT6A4v9uQcTEYbR3R2XnG4GJmprU%3D";

        String txt1 = this.getText(TEST_PDF1);
        assertNotNull(txt1);
        assertTrue(txt1.contains("NEWFIELD"));

        String txt2 = this.getText(TEST_PDF2);
        assertNotNull(txt2);
        assertTrue(txt2.contains("NEWFIELD"));


    }
}
