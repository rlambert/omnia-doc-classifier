package com.omnia.docclassifier.services;

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

    // Test Skillset operations (create, exists, delete)
    @Test
    public void testSkillsetOperations() throws URISyntaxException, IOException, InterruptedException {
        final String TEST_SKILLSET_NAME = "test-skillset";
        final String TEST_SKILLSET_DESC = "This skillset was created by a unit test (OK to delete)";

        // clean up and prep environment
        boolean exists = this.azureSearchService.skillsetExists(_config.getAzureSearchName(), TEST_SKILLSET_NAME,
                _config.getAzureSearchApiVersion());
        if (exists) {
            this.azureSearchService.deleteSkillset(_config.getAzureSearchName(), TEST_SKILLSET_NAME, _config.getAzureSearchApiVersion());
        }

        // test create of skillset
        // String serviceName, String skillsetName, String description, String ksStoreAccount, String ksConnStr, String apiVersion)
        boolean created = this.azureSearchService.createSkillset(_config.getAzureSearchName(), TEST_SKILLSET_NAME,
                TEST_SKILLSET_DESC, null, null, _config.getAzureSearchApiVersion());
        assertTrue(created);

        // verify existence
        exists = this.azureSearchService.skillsetExists(_config.getAzureSearchName(), TEST_SKILLSET_NAME,
                _config.getAzureSearchApiVersion());
        assertTrue(exists);

        // test delete
        this.azureSearchService.deleteSkillset(_config.getAzureSearchName(), TEST_SKILLSET_NAME, _config.getAzureSearchApiVersion());

        // prove it is gone
        exists = this.azureSearchService.skillsetExists(_config.getAzureSearchName(), TEST_SKILLSET_NAME,
                _config.getAzureSearchApiVersion());

        assertFalse(exists);
    }

    @Test
    public void tempBuildDataSource() throws URISyntaxException, IOException, InterruptedException {
        final String DS_NAME = "new-azure-fileshare-source";
        final String DS_TEMPLATE = "afs-crawler-data-source.tmpl";
        final String DS_DESC = "Data source index definition";
        final String DS_CONTAINER = "sourcefiles";
        final String DS_QUERY = "data/";
        final String DS_TYPE = "azurefile";

        final String NDX_NAME = "new-afs-crawler-index";
        final String CACHE_STORAGE_CONNSTR = "DefaultEndpointsProtocol=https;AccountName=searchcacheaccount;AccountKey=dR9SumwrHzmAc2gxDCH8vNKUFgCcHxGCPm0KpUlfBrJL+E/+yDcEURndhq0nuLfiNaoLdxhSaEPFm6a8d99DXw==;EndpointSuffix=core.windows.net";

        // clean up and prep environment
        boolean exists = this.azureSearchService.dataSourceExists(_config.getAzureSearchName(), DS_NAME, _config.getAzureSearchApiVersion());
        if (!exists) {
            // test create of data source
            boolean created = this.azureSearchService.createDataSource(_config.getAzureSearchName(), DS_NAME, DS_DESC,
                    DS_TYPE, _config.getAzureFileShareConnStr(), DS_CONTAINER, DS_QUERY, _config.getAzureSearchApiVersion());
            assertTrue(created);
        }

        // verify existence
        exists = this.azureSearchService.dataSourceExists(_config.getAzureSearchName(), DS_NAME, _config.getAzureSearchApiVersion());
        assertTrue(exists);

        this.azureSearchService.indexExists(_config.getAzureSearchName(), DS_NAME, _config.getAzureSearchApiVersion());

        boolean created = this.azureSearchService.createIndex(_config.getAzureSearchIndexTemplate(),
                _config.getAzureSearchName(),NDX_NAME, _config.getAzureSearchApiVersion());
        assertTrue(created);

        // make sure we can verify it exists
        exists = this.azureSearchService.indexExists(_config.getAzureSearchName(),
                NDX_NAME, _config.getAzureSearchApiVersion());
        assertTrue(exists);


    }

    // Test DataSource CRUD
    @Test
    public void testDataSourceOperations() throws URISyntaxException, IOException, InterruptedException {
        final String TEST_DS_NAME = "test-search-datasource";
        final String TEST_DS_TEMPLATE = "afs-crawler-data-source.tmpl";
        final String TEST_DS_DESC = "This is a unit test data source (OK to delete)";
        final String TEST_DS_CONTAINER = "sourcefiles";
        final String TEST_DS_QUERY = "data/";
        final String TEST_DS_TYPE = "azurefile";

        // clean up and prep environment
        boolean exists = this.azureSearchService.dataSourceExists(_config.getAzureSearchName(), TEST_DS_NAME, _config.getAzureSearchApiVersion());
        if (exists) {
            this.azureSearchService.deleteDataSource(_config.getAzureSearchName(), TEST_DS_NAME, _config.getAzureSearchApiVersion());
        }

        // test create of data source
        boolean created = this.azureSearchService.createDataSource(_config.getAzureSearchName(), TEST_DS_NAME,
                TEST_DS_DESC, TEST_DS_TYPE, _config.getAzureFileShareConnStr(), TEST_DS_CONTAINER, TEST_DS_QUERY,
                _config.getAzureSearchApiVersion());
        assertTrue(created);

        // verify existence
        exists = this.azureSearchService.dataSourceExists(_config.getAzureSearchName(), TEST_DS_NAME, _config.getAzureSearchApiVersion());
        assertTrue(exists);

        // test delete
        this.azureSearchService.deleteDataSource(_config.getAzureSearchName(), TEST_DS_NAME, _config.getAzureSearchApiVersion());
        exists = this.azureSearchService.dataSourceExists(_config.getAzureSearchName(), TEST_DS_NAME, _config.getAzureSearchApiVersion());
        assertFalse(exists);

    }

    // Test IndexOperations CRUD
    @Test
    public void testIndexOperations() throws InterruptedException, IOException, URISyntaxException {
        final String TEST_NDX = "test-search-index";

        // clean up environment: delete the index if it exists
        boolean exists = this.azureSearchService.indexExists(_config.getAzureSearchName(), TEST_NDX, _config.getAzureSearchApiVersion());
        if (exists) {
            this.azureSearchService.deleteIndex(_config.getAzureSearchName(), TEST_NDX, _config.getAzureSearchApiVersion());
        }

        // see if we can create the test index
        boolean created = this.azureSearchService.createIndex(_config.getAzureSearchIndexTemplate(),
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

    @Test
    public void testIndexerOperations() throws URISyntaxException, IOException, InterruptedException {
        // final String TEST_INDEXER = "test-search-indexer";
        final String TEST_INDEXER = "afs-crawler";
        final String TEST_INDEXER_DESC = "This is a unit test indexer (OK to delete)";

        // make sure indexer exists
        boolean exists = this.azureSearchService.indexerExists(_config.getAzureSearchName(), TEST_INDEXER, _config.getAzureSearchApiVersion());
        if (exists) {
            this.azureSearchService.runIndexer(_config.getAzureSearchName(), TEST_INDEXER, _config.getAzureSearchApiVersion());
        }

        // clean up environment: delete the indexer if it exists
//        boolean exists = this.azureSearchService.indexerExists(_config.getAzureSearchName(), TEST_INDEXER, _config.getAzureSearchApiVersion());
//        if (exists) {
//            this.azureSearchService.deleteIndexer(_config.getAzureSearchName(), TEST_INDEXER, _config.getAzureSearchApiVersion());
//        }
//
//        // see if we can create the test indexer
//        //     public boolean createIndexer(String serviceName, String indexerName, String description,
//        //                                 String dataSourceName, String skillsetName, String indexName, String apiVersion)
//        boolean created = this.azureSearchService.createIndexer(_config.getAzureSearchIndexerTemplate(),
//                _config.getAzureSearchName(),TEST_INDEXER, TEST_INDEXER_DESC, _config.getAzureSearchApiVersion());
//        assertTrue(created);
//
//        // make sure we can verify it exists
//        exists = this.azureSearchService.indexerExists(_config.getAzureSearchName(),
//                TEST_INDEXER, _config.getAzureSearchApiVersion());
//        assertTrue(exists);
//
//        // test delete
//        this.azureSearchService.deleteIndex(_config.getAzureSearchName(), TEST_INDEXER, _config.getAzureSearchApiVersion());
//
//        exists = this.azureSearchService.indexExists(_config.getAzureSearchName(),
//                TEST_INDEXER, _config.getAzureSearchApiVersion());
//
//        assertFalse(exists);
    }
}
