package com.omnia.docclassifier.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ConfigurationProperties(prefix="app")
@Validated
@Data
@Slf4j
public class AppConfig {

    @NotNull
    @NotEmpty
    private String name;

    @Size(max = 14, min = 3)
    private String version;

    public String getVersionJson() {
        return getJson("version", version);
    }

    public static String getJson(String key, String value) {
        return String.format("{\"%s\":\"%s\"}", key, value);
    }

    @NotNull
    @NotEmpty
    private String apiRestBase;

    @NotNull
    @NotEmpty
    private String azureFileShareName;

    @NotNull
    @NotEmpty
    private String azureFileShareEndpoint;

    @NotNull
    @NotEmpty
    private String azureFileShareConnStr;

    @NotNull
    @NotEmpty
    private String blobSourceDocsKey;

}