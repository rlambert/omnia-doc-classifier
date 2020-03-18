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
import static org.junit.jupiter.api.Assertions.assertTrue;

//@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("unittest")
public class AzureSearchServiceTest {

    @Autowired
    private AppConfig _config;

    @Autowired
    private AzureSearchService azureSearchService;

    @Test
    public void testDataSourceOperations() throws URISyntaxException, IOException, InterruptedException {
        final String TEST_DS_NAME = "test-search-datasource";
        final String TEST_DS_TEMPLATE = "afs-crawler-data-source.tmpl";
        final String TEST_DS_DESC = "This is a unit test data source.";
        final String TEST_DS_CONTAINER = "sourcefiles";
        final String TEST_DS_QUERY = "data/";
        final String TEST_DS_TYPE = "azurefile";

        // clean up and prep environment
        boolean exists = this.azureSearchService.searchDataSourceExists(_config.getAzureSearchName(), TEST_DS_NAME, _config.getAzureSearchApiVersion());
        if (exists) {
            this.azureSearchService.deleteDataSource(_config.getAzureSearchName(), TEST_DS_NAME, _config.getAzureSearchApiVersion());
        }

        // test create of data source
        boolean created = this.azureSearchService.createDataSource(TEST_DS_TEMPLATE, _config.getAzureSearchName(),
                TEST_DS_NAME, TEST_DS_DESC, TEST_DS_TYPE, _config.getAzureFileShareConnStr(), TEST_DS_CONTAINER, TEST_DS_QUERY, _config.getAzureSearchApiVersion());
        assertTrue(created);

        // verify existence
        exists = this.azureSearchService.searchDataSourceExists(_config.getAzureSearchName(), TEST_DS_NAME, _config.getAzureSearchApiVersion());
        assertTrue(exists);

        // test delete
        this.azureSearchService.deleteDataSource(_config.getAzureSearchName(), TEST_DS_NAME, _config.getAzureSearchApiVersion());
        exists = this.azureSearchService.searchDataSourceExists(_config.getAzureSearchName(), TEST_DS_NAME, _config.getAzureSearchApiVersion());
        assertFalse(exists);

    }

    @Test
    public void testIndexOperations() throws InterruptedException, IOException, URISyntaxException {
        final String TEST_NDX = "test-search-index";

        // clean up environment: delete the index if it exists
        boolean exists = this.azureSearchService.indexExists(_config.getAzureSearchName(), TEST_NDX, _config.getAzureSearchApiVersion());
        if (exists) {
            this.azureSearchService.deleteIndex(_config.getAzureSearchName(), TEST_NDX, _config.getAzureSearchApiVersion());
        }

        // see if we can create the test index
        boolean created = this.azureSearchService.createIndex(_config.getAzureSearchIndexDefinition(),
                _config.getAzureSearchName(),TEST_NDX, _config.getAzureSearchApiVersion());
        assertTrue(created);

        // make sure we can verify it exists
        exists = this.azureSearchService.indexExists(_config.getAzureSearchName(),
                TEST_NDX, _config.getAzureSearchApiVersion());
        assertTrue(exists);

        // test delete
        this.azureSearchService.deleteIndex(_config.getAzureSearchName(), TEST_NDX, _config.getAzureSearchApiVersion());

        exists = this.azureSearchService.indexExists(_config.getAzureSearchName(),
                TEST_NDX, _config.getAzureSearchApiVersion());

        assertFalse(exists);

    }
}
