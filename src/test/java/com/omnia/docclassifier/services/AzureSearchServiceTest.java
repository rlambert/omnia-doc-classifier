package com.omnia.docclassifier.services;

// Include the following imports to use blob APIs.

// Include the following imports to use blob APIs.

import com.omnia.docclassifier.config.AppConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertFalse;

//@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("unittest")
public class AzureSearchServiceTest {

    @Autowired
    private AppConfig _config;

    @Autowired
    private AzureSearchService azureSearchService;

    @Test
    public void testIndexOperations() throws InterruptedException, IOException, URISyntaxException {
        final String TEST_NDX = "test-search-index";

        // delete the index if it exists
        boolean exists = this.azureSearchService.indexExists(_config.getAzureSearchName(), TEST_NDX, _config.getAzureSearchApiVersion());

        if (exists) {
            this.azureSearchService.deleteIndex(_config.getAzureSearchName(), TEST_NDX, _config.getAzureSearchApiVersion());
        }

        // see if we can create the test index
        boolean created = this.azureSearchService.createIndex(_config.getAzureSearchIndexDefinition(),
                _config.getAzureSearchName(),TEST_NDX, _config.getAzureSearchApiVersion());

        if (created) {
            this.azureSearchService.deleteIndex(_config.getAzureSearchName(), TEST_NDX, _config.getAzureSearchApiVersion());
        }

        exists = this.azureSearchService.indexExists(_config.getAzureSearchName(),
                TEST_NDX, _config.getAzureSearchApiVersion());

        assertFalse(exists);

    }
}
