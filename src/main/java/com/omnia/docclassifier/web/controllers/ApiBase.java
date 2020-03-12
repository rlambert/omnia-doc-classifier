/*
 -----------------------------------------
   ApiBase
   Copyright (c) 2020
   Digital Provisioners
   All Right Reserved
 -----------------------------------------
 */

package com.omnia.docclassifier.web.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.omnia.docclassifier.config.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

public abstract class ApiBase {

    /** Logger for this class. */
    private static final Logger _logger = LoggerFactory.getLogger(ApiBase.class.getName());

    // a Jackson mapper for serialization/deserialization
    private ObjectMapper _mapper = new ObjectMapper().findAndRegisterModules().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    protected ObjectMapper getMapper() {
        return _mapper;
    }

    // a helper method for serialization
    protected String serialize(Object obj) throws JsonProcessingException {
        return _mapper.writeValueAsString(obj);
    }

    // a helper method for deserializtion
    protected <T extends Object> T deserialize(String json, Class<T> theClass) throws IOException {
        return theClass.cast(_mapper.readValue(json, theClass));
    }

    private AppConfig config;
    protected AppConfig getAppConfig() {
        return this.config;
    }

    private UUID currentAcountId;
    protected UUID getCurrentAccountId() {
        return currentAcountId;
    }
    protected void setCurrentAccountId(UUID currentAcountId) {
        this.currentAcountId = currentAcountId;
    }

    private String currentToken;
    protected String getCurrentToken() {
        return currentToken;
    }
    protected void setCurrentToken(String currentToken) {
        this.currentToken = currentToken;
    }


    /**
     * constructor
     * @param config - AppConfig
     */
    public ApiBase(AppConfig config) {
        this.config = config;
    }


    /**
     * all of our JSON-emitting APIs need to support an OPTIONS call for CORS
     * @param response - HttpServletResponse
     * @return null
     */
    @RequestMapping(value={"*"}, method= RequestMethod.OPTIONS)
    public View getOptions(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE");
        return null;
    }

}
