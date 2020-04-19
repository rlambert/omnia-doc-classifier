package com.omnia.docclassifier.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.omnia.docclassifier.config.AppConfig;
import com.omnia.docclassifier.utils.StringTemplateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Slf4j
public class AzureSearchSkillService {

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
    public AzureSearchSkillService(AppConfig config) {
        _config = config;
    }

    // KnowledgeStoreStorageAccountName


}
