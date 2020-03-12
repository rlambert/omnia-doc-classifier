// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.storage.file.share.models;

import com.azure.core.annotation.Fluent;
import com.azure.storage.file.share.models.ShareCorsRule;
import com.azure.storage.file.share.models.ShareMetrics;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Storage service properties.
 */
@JacksonXmlRootElement(localName = "StorageServiceProperties")
@Fluent
public final class ShareServiceProperties {
    /*
     * A summary of request statistics grouped by API in hourly aggregates for
     * files.
     */
    @JsonProperty(value = "HourMetrics")
    private ShareMetrics hourMetrics;

    /*
     * A summary of request statistics grouped by API in minute aggregates for
     * files.
     */
    @JsonProperty(value = "MinuteMetrics")
    private ShareMetrics minuteMetrics;

    private static final class CorsWrapper {
        @JacksonXmlProperty(localName = "CorsRule")
        private final List<ShareCorsRule> items;

        @JsonCreator
        private CorsWrapper(@JacksonXmlProperty(localName = "CorsRule") List<ShareCorsRule> items) {
            this.items = items;
        }
    }

    /*
     * The set of CORS rules.
     */
    @JsonProperty(value = "Cors")
    private CorsWrapper cors;

    /**
     * Get the hourMetrics property: A summary of request statistics grouped by
     * API in hourly aggregates for files.
     *
     * @return the hourMetrics value.
     */
    public ShareMetrics getHourMetrics() {
        return this.hourMetrics;
    }

    /**
     * Set the hourMetrics property: A summary of request statistics grouped by
     * API in hourly aggregates for files.
     *
     * @param hourMetrics the hourMetrics value to set.
     * @return the ShareServiceProperties object itself.
     */
    public com.azure.storage.file.share.models.ShareServiceProperties setHourMetrics(ShareMetrics hourMetrics) {
        this.hourMetrics = hourMetrics;
        return this;
    }

    /**
     * Get the minuteMetrics property: A summary of request statistics grouped
     * by API in minute aggregates for files.
     *
     * @return the minuteMetrics value.
     */
    public ShareMetrics getMinuteMetrics() {
        return this.minuteMetrics;
    }

    /**
     * Set the minuteMetrics property: A summary of request statistics grouped
     * by API in minute aggregates for files.
     *
     * @param minuteMetrics the minuteMetrics value to set.
     * @return the ShareServiceProperties object itself.
     */
    public com.azure.storage.file.share.models.ShareServiceProperties setMinuteMetrics(ShareMetrics minuteMetrics) {
        this.minuteMetrics = minuteMetrics;
        return this;
    }

    /**
     * Get the cors property: The set of CORS rules.
     *
     * @return the cors value.
     */
    public List<ShareCorsRule> getCors() {
        if (this.cors == null) {
            this.cors = new CorsWrapper(new ArrayList<ShareCorsRule>());
        }
        return this.cors.items;
    }

    /**
     * Set the cors property: The set of CORS rules.
     *
     * @param cors the cors value to set.
     * @return the ShareServiceProperties object itself.
     */
    public com.azure.storage.file.share.models.ShareServiceProperties setCors(List<ShareCorsRule> cors) {
        this.cors = new CorsWrapper(cors);
        return this;
    }
}