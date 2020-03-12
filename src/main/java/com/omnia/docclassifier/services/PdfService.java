package com.omnia.docclassifier.services;

import com.omnia.docclassifier.config.AppConfig;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;

import com.azure.storage.common.StorageSharedKeyCredential;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;

@Service
public class PdfService {

    //private BlobServiceClient _storageClient;

    private AppConfig _config;

    /**
     * constructor, Spring's DI gets our config
     * @param config
     */
    public PdfService(AppConfig config) {
        _config = config;
    }

    public String getPdfTextFromBlob() {
//        BlobContainerClient client = _storageClient.getBlobContainerClient("training");
//        client.
//
//        /*
//         * Create a BlockBlobClient object that wraps a blob's endpoint and a default pipeline, the blockBlobClient give us access to upload the file.
//         */
//        String filename = "BigFile.bin";
//        BlobClient blobClient = blobContainerClient.getBlobClient(filename);
//        _storageClient.ge
        return "42";
    }

    public String getPdfText(String fName) throws IOException, IllegalAccessException {
        PDDocument document = PDDocument.load(new File(fName));
        PDFTextStripper textStripper = new PDFTextStripper();
        document.getClass();

        if (!document.isEncrypted()) {
            return textStripper.getText(document);
        }
        else {
            throw new IllegalAccessException(String.format("Document %s is encrypted", fName));
        }
    }

    @PostConstruct
    public void init() {
        /*
         * Use your Storage account's name and key to create a credential object; this is used to access your account.
         */
       // StorageSharedKeyCredential credential = new StorageSharedKeyCredential(_config.getBlobSourceDocsName(), _config.getBlobSourceDocsKey());

        // ours: https://sourcedocs.file.core.windows.net/
       // String endpoint = String.format(Locale.ROOT, "https://%s.file.core.windows.net/", _config.getBlobSourceDocsName());
        //_storageClient = new BlobServiceClientBuilder().endpoint(endpoint).credential(credential).buildClient();
    }

}
