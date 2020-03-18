package com.omnia.docclassifier.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.omnia.docclassifier.config.AppConfig;
import com.omnia.docclassifier.utils.HttpUtils;
import com.omnia.docclassifier.utils.StringTemplateUtils;
import com.omnia.docclassifier.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class AzureSearchService {

    // static class variables
    private static final String APIKEY = "api-key";
    private static StringTemplateUtils _templateUtils = new StringTemplateUtils();

    // instance variables
    private AppConfig _config;
    private WebClient _client = WebClient.builder().build();
    private ObjectMapper _mapper = new ObjectMapper().findAndRegisterModules();

    @Value("classpath:templates/*")
    private Resource[] resources;

    /**
     * constructor, Spring's DI gets our config
     * @param config
     */
    public AzureSearchService(AppConfig config) {
        _config = config;
    }

    /**
     *   "name": "~Name~",
     *   "description": "",
     *   "dataSourceName": "~DataSourceName~",
     *   "skillsetName": "~SkillsetName~",
     *   "targetIndexName": "~IndexName~",
     */

    /**
     * creates a data source
     * @param serviceName - String, name of search service
     * @param indexerName - String, name of data source
     * @param description - String
     * @param apiVersion - String, api version
     * @return boolean, true if index exists
     * @throws IOException, on connection error
     * @throws InterruptedException - should never happen
     * @throws URISyntaxException - when the URL is malformed
     */
    public boolean createIndexer(String templateName, String serviceName, String indexerName, String description,
                                    String dataSourceName, String skillsetName, String indexName, String apiVersion)
            throws IOException, InterruptedException, URISyntaxException {

        AzureSearchService.log.info(String.format("Creating search data source %s...", dsName));

        // azureSearchBaseUrl: https://%s.search.windows.net/%s?api-version=%s
        String url = String.format(_config.getAzureSearchBaseUrl(), serviceName, "datasources/", apiVersion);

        // build map of dynamic values to insert into template
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("Name", dsName);
        dataMap.put("Description", dsDescription);
        dataMap.put("Type", dsType);
        dataMap.put("ConnectionString", dsConnStr);
        dataMap.put("ContainerName", dsContainerName);
        dataMap.put("Query", dsContainerName);

        String json = _templateUtils.transform(dataMap, _config.getDataSourceTemplate());

        String resultStr = HttpUtils.postWithHeader(_client, url, json, APIKEY, _config.getAzureSearchAdminKey(), String.class);
        return (!StringUtils.isNullOrEmpty(resultStr));
    }

    /**
     * queries just to make sure the data source exists
     * @param serviceName - String, name of search service
     * @param indexerName - String, name index
     * @param apiVersion - String, api version
     * @return boolean, true if index exists
     * @throws URISyntaxException - when the URL is malformed
     */
    public boolean searchIndexerExists(String serviceName, String indexerName, String apiVersion) throws URISyntaxException {
        AzureSearchService.log.info(String.format("Checking if datasource %s exists...", indexerName));

        // azureSearchBaseUrl: https://%s.search.windows.net/%s?api-version=%s
        var url = String.format(_config.getAzureSearchBaseUrl(), serviceName, "indexers/" + indexerName, apiVersion);

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
     * deletes a data source
     * @param serviceName - String, name of search service
     * @param dsName - String, name of data source
     * @param apiVersion - String, api version
     * @throws IOException, on connection error
     * @throws InterruptedException - should never happen
     * @throws URISyntaxException - when the URL is malformed
     */
    public void deleteDataSource(String serviceName, String dsName, String apiVersion) throws IOException, InterruptedException, URISyntaxException {
        AzureSearchService.log.info(String.format("Deleting data source %s...", dsName));
        // azureSearchBaseUrl: https://%s.search.windows.net/%s?api-version=%s
        String url = String.format(_config.getAzureSearchBaseUrl(), serviceName, "datasources/" + dsName, apiVersion);
        HttpUtils.deleteWithHeader(_client, url, APIKEY, _config.getAzureSearchAdminKey(), String.class);
    }

    /**
     * creates a data source
     * @param serviceName - String, name of search service
     * @param dsName - String, name of data source
     * @param apiVersion - String, api version
     * @return boolean, true if index exists
     * @throws IOException, on connection error
     * @throws InterruptedException - should never happen
     * @throws URISyntaxException - when the URL is malformed
     */
    public boolean createDataSource(String templateName, String serviceName, String dsName, String dsDescription,
                                    String dsType, String dsConnStr, String dsContainerName, String dsQuery,
                                    String apiVersion)
            throws IOException, InterruptedException, URISyntaxException {

        AzureSearchService.log.info(String.format("Creating search data source %s...", dsName));

        // azureSearchBaseUrl: https://%s.search.windows.net/%s?api-version=%s
        String url = String.format(_config.getAzureSearchBaseUrl(), serviceName, "datasources/", apiVersion);

        // build map of dynamic values to insert into template
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("Name", dsName);
        dataMap.put("Description", dsDescription);
        dataMap.put("Type", dsType);
        dataMap.put("ConnectionString", dsConnStr);
        dataMap.put("ContainerName", dsContainerName);
        dataMap.put("Query", dsContainerName);

        String json = _templateUtils.transform(dataMap, _config.getDataSourceTemplate());

        String resultStr = HttpUtils.postWithHeader(_client, url, json, APIKEY, _config.getAzureSearchAdminKey(), String.class);
        return (!StringUtils.isNullOrEmpty(resultStr));
    }

    /**
     * queries just to make sure the data source exists
     * @param serviceName - String, name of search service
     * @param indexName - String, name index
     * @param apiVersion - String, api version
     * @return boolean, true if index exists
     * @throws URISyntaxException - when the URL is malformed
     */
    public boolean searchDataSourceExists(String serviceName, String indexName, String apiVersion) throws URISyntaxException {
        AzureSearchService.log.info(String.format("Checking if datasource %s exists...", indexName));

        // azureSearchBaseUrl: https://%s.search.windows.net/%s?api-version=%s
        var url = String.format(_config.getAzureSearchBaseUrl(), serviceName, "datasources/" + indexName, apiVersion) + "&search=*&$top=1";

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

        // azureSearchBaseUrl: https://%s.search.windows.net/%s?api-version=%s
        var url = String.format(_config.getAzureSearchBaseUrl(), serviceName, "indexes/" + indexName, apiVersion) + "&search=*&$top=1";

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
     * @throws IOException, on connection error
     * @throws InterruptedException - should never happen
     * @throws URISyntaxException - when the URL is malformed
     */
    public void deleteIndex(String serviceName, String indexName, String apiVersion) throws IOException, InterruptedException, URISyntaxException {
        AzureSearchService.log.info(String.format("Deleting index %s...", indexName));
        // azureSearchBaseUrl: https://%s.search.windows.net/%s?api-version=%s
        String url = String.format(_config.getAzureSearchBaseUrl(), serviceName, "indexes/" + indexName, apiVersion);
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

        // azureSearchBaseUrl: https://%s.search.windows.net/%s?api-version=%s
        String url = String.format(_config.getAzureSearchBaseUrl(), serviceName, "indexes/" + indexName, apiVersion);

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
        return true;
    }

    @PostConstruct
    public void init() throws IOException {
        // initialize templates if needed
        if (_templateUtils.getTemplateCacheMap().size() == 0) {
            List<String> templateList = new ArrayList<>();

            for (final Resource res : this.resources) {
                templateList.add(res.getFilename());
            }
            _templateUtils.buildTemplateCache(templateList);
        }
    }
}
