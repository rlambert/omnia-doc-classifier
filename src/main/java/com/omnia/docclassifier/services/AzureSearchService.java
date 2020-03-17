package com.omnia.docclassifier.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.omnia.docclassifier.config.AppConfig;
import com.omnia.docclassifier.utils.HttpUtils;
import com.omnia.docclassifier.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.net.URISyntaxException;

@Service
@Slf4j
public class AzureSearchService {

    private static final String APIKEY = "api-key";

    private AppConfig _config;
    private WebClient _client = WebClient.builder().build();
    private ObjectMapper _mapper = new ObjectMapper().findAndRegisterModules();

    /**
     * constructor, Spring's DI gets our config
     * @param config
     */
    public AzureSearchService(AppConfig config) {
        _config = config;
    }

    /**
     * queries for 1 record just to make sure the index exists
     * @param serviceName - String, name of search service
     * @param indexName - String, name index
     * @param apiVersion - String, api version
     * @return boolean, true if index exists
     * @throws IOException, on connection error
     * @throws URISyntaxException - when the URL is malformed
     */
    public boolean indexExists(String serviceName, String indexName, String apiVersion) throws URISyntaxException {
        AzureSearchService.log.info(String.format("Checking if index %s exists...", indexName));

        var url = String.format(_config.getAzureSearchIndexExistsUrl(), serviceName, indexName, apiVersion) + "&$top=1";
        try {
            String resultStr = HttpUtils.getWithHeader(_client, url, APIKEY, _config.getAzureSearchAdminKey(), String.class);
            return (!StringUtils.isNullOrEmpty(resultStr));
        }
        catch(Exception ex) {
            // 404 maps to IOException
            return false;
        }
    }

    /**
     * deletes an index
     * @param serviceName - String, name of search service
     * @param indexName - String, name index
     * @param apiVersion - String, api version
     * @return boolean, true if index exists
     * @throws IOException, on connection error
     * @throws InterruptedException - should never happen
     * @throws URISyntaxException - when the URL is malformed
     */
    public void deleteIndex(String serviceName, String indexName, String apiVersion) throws IOException, InterruptedException, URISyntaxException {
        AzureSearchService.log.info(String.format("Deleting index %s...", indexName));
        var url = String.format(_config.getAzureSearchIndexUrl(), serviceName, indexName, apiVersion);
        HttpUtils.deleteWithHeader(_client, url, APIKEY, _config.getAzureSearchAdminKey(), String.class);
    }

    /**
     * creates an index
     * @param serviceName - String, name of search service
     * @param indexName - String, name index
     * @param apiVersion - String, api version
     * @return boolean, true if index exists
     * @throws IOException, on connection error
     * @throws InterruptedException - should never happen
     * @throws URISyntaxException - when the URL is malformed
     */
    public boolean createIndex(String indexDefinitionFile, String serviceName, String indexName, String apiVersion)
            throws IOException, InterruptedException, URISyntaxException {

        AzureSearchService.log.info(String.format("Creating search index %s...", indexName));

        //Build the search service URL, like: https://%s.search.windows.net/indexers/%s?api-version=%s
        String url = String.format(_config.getAzureSearchIndexUrl(), serviceName, indexName, apiVersion);

        //Read in index definition file
        String json = StringUtils.readResource(this, indexDefinitionFile);

        String resultStr = HttpUtils.putWithHeader(_client, url, json, APIKEY, _config.getAzureSearchAdminKey(), String.class);
        return (!StringUtils.isNullOrEmpty(resultStr));
    }

    /**
     * overload that uses a configured index definition to create an index
     * @param serviceName - String, name of search service
     * @param indexName - String, name index
     * @param apiVersion - String, api version
     * @return boolean, true if index exists
     * @throws IOException, on connection error
     * @throws InterruptedException - should never happen
     * @throws URISyntaxException - when the URL is malformed
     */
    public boolean createIndex(String serviceName, String indexName, String apiVersion)
            throws IOException, InterruptedException, URISyntaxException {
        return createIndex(_config.getAzureSearchIndexDefinition(), serviceName, indexName, apiVersion);
    }


    public boolean createDocumentIndexer(String indexerName, String apiVersion) {

    }

}
