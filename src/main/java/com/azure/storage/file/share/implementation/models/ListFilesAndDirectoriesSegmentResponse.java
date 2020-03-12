// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.storage.file.share.implementation.models;

import com.azure.core.annotation.Fluent;
import com.azure.storage.file.share.implementation.models.FilesAndDirectoriesListSegment;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * An enumeration of directories and files.
 */
@JacksonXmlRootElement(localName = "EnumerationResults")
@Fluent
public final class ListFilesAndDirectoriesSegmentResponse {
    /*
     * The serviceEndpoint property.
     */
    @JacksonXmlProperty(localName = "ServiceEndpoint", isAttribute = true)
    private String serviceEndpoint;

    /*
     * The shareName property.
     */
    @JacksonXmlProperty(localName = "ShareName", isAttribute = true)
    private String shareName;

    /*
     * The shareSnapshot property.
     */
    @JacksonXmlProperty(localName = "ShareSnapshot", isAttribute = true)
    private String shareSnapshot;

    /*
     * The directoryPath property.
     */
    @JacksonXmlProperty(localName = "DirectoryPath", isAttribute = true)
    private String directoryPath;

    /*
     * The prefix property.
     */
    @JsonProperty(value = "Prefix", required = true)
    private String prefix;

    /*
     * The marker property.
     */
    @JsonProperty(value = "Marker")
    private String marker;

    /*
     * The maxResults property.
     */
    @JsonProperty(value = "MaxResults")
    private Integer maxResults;

    /*
     * The segment property.
     */
    @JsonProperty(value = "Entries", required = true)
    private FilesAndDirectoriesListSegment segment;

    /*
     * The nextMarker property.
     */
    @JsonProperty(value = "NextMarker", required = true)
    private String nextMarker;

    /**
     * Get the serviceEndpoint property: The serviceEndpoint property.
     *
     * @return the serviceEndpoint value.
     */
    public String getServiceEndpoint() {
        return this.serviceEndpoint;
    }

    /**
     * Set the serviceEndpoint property: The serviceEndpoint property.
     *
     * @param serviceEndpoint the serviceEndpoint value to set.
     * @return the ListFilesAndDirectoriesSegmentResponse object itself.
     */
    public com.azure.storage.file.share.implementation.models.ListFilesAndDirectoriesSegmentResponse setServiceEndpoint(String serviceEndpoint) {
        this.serviceEndpoint = serviceEndpoint;
        return this;
    }

    /**
     * Get the shareName property: The shareName property.
     *
     * @return the shareName value.
     */
    public String getShareName() {
        return this.shareName;
    }

    /**
     * Set the shareName property: The shareName property.
     *
     * @param shareName the shareName value to set.
     * @return the ListFilesAndDirectoriesSegmentResponse object itself.
     */
    public com.azure.storage.file.share.implementation.models.ListFilesAndDirectoriesSegmentResponse setShareName(String shareName) {
        this.shareName = shareName;
        return this;
    }

    /**
     * Get the shareSnapshot property: The shareSnapshot property.
     *
     * @return the shareSnapshot value.
     */
    public String getShareSnapshot() {
        return this.shareSnapshot;
    }

    /**
     * Set the shareSnapshot property: The shareSnapshot property.
     *
     * @param shareSnapshot the shareSnapshot value to set.
     * @return the ListFilesAndDirectoriesSegmentResponse object itself.
     */
    public com.azure.storage.file.share.implementation.models.ListFilesAndDirectoriesSegmentResponse setShareSnapshot(String shareSnapshot) {
        this.shareSnapshot = shareSnapshot;
        return this;
    }

    /**
     * Get the directoryPath property: The directoryPath property.
     *
     * @return the directoryPath value.
     */
    public String getDirectoryPath() {
        return this.directoryPath;
    }

    /**
     * Set the directoryPath property: The directoryPath property.
     *
     * @param directoryPath the directoryPath value to set.
     * @return the ListFilesAndDirectoriesSegmentResponse object itself.
     */
    public com.azure.storage.file.share.implementation.models.ListFilesAndDirectoriesSegmentResponse setDirectoryPath(String directoryPath) {
        this.directoryPath = directoryPath;
        return this;
    }

    /**
     * Get the prefix property: The prefix property.
     *
     * @return the prefix value.
     */
    public String getPrefix() {
        return this.prefix;
    }

    /**
     * Set the prefix property: The prefix property.
     *
     * @param prefix the prefix value to set.
     * @return the ListFilesAndDirectoriesSegmentResponse object itself.
     */
    public com.azure.storage.file.share.implementation.models.ListFilesAndDirectoriesSegmentResponse setPrefix(String prefix) {
        this.prefix = prefix;
        return this;
    }

    /**
     * Get the marker property: The marker property.
     *
     * @return the marker value.
     */
    public String getMarker() {
        return this.marker;
    }

    /**
     * Set the marker property: The marker property.
     *
     * @param marker the marker value to set.
     * @return the ListFilesAndDirectoriesSegmentResponse object itself.
     */
    public com.azure.storage.file.share.implementation.models.ListFilesAndDirectoriesSegmentResponse setMarker(String marker) {
        this.marker = marker;
        return this;
    }

    /**
     * Get the maxResults property: The maxResults property.
     *
     * @return the maxResults value.
     */
    public Integer getMaxResults() {
        return this.maxResults;
    }

    /**
     * Set the maxResults property: The maxResults property.
     *
     * @param maxResults the maxResults value to set.
     * @return the ListFilesAndDirectoriesSegmentResponse object itself.
     */
    public com.azure.storage.file.share.implementation.models.ListFilesAndDirectoriesSegmentResponse setMaxResults(Integer maxResults) {
        this.maxResults = maxResults;
        return this;
    }

    /**
     * Get the segment property: The segment property.
     *
     * @return the segment value.
     */
    public FilesAndDirectoriesListSegment getSegment() {
        return this.segment;
    }

    /**
     * Set the segment property: The segment property.
     *
     * @param segment the segment value to set.
     * @return the ListFilesAndDirectoriesSegmentResponse object itself.
     */
    public com.azure.storage.file.share.implementation.models.ListFilesAndDirectoriesSegmentResponse setSegment(FilesAndDirectoriesListSegment segment) {
        this.segment = segment;
        return this;
    }

    /**
     * Get the nextMarker property: The nextMarker property.
     *
     * @return the nextMarker value.
     */
    public String getNextMarker() {
        return this.nextMarker;
    }

    /**
     * Set the nextMarker property: The nextMarker property.
     *
     * @param nextMarker the nextMarker value to set.
     * @return the ListFilesAndDirectoriesSegmentResponse object itself.
     */
    public com.azure.storage.file.share.implementation.models.ListFilesAndDirectoriesSegmentResponse setNextMarker(String nextMarker) {
        this.nextMarker = nextMarker;
        return this;
    }
}
