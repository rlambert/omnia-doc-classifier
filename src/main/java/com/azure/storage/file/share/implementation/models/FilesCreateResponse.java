// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.storage.file.share.implementation.models;

import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.rest.ResponseBase;
import com.azure.storage.file.share.implementation.models.FileCreateHeaders;

/**
 * Contains all response data for the create operation.
 */
public final class FilesCreateResponse extends ResponseBase<FileCreateHeaders, Void> {
    /**
     * Creates an instance of FilesCreateResponse.
     *
     * @param request the request which resulted in this FilesCreateResponse.
     * @param statusCode the status code of the HTTP response.
     * @param rawHeaders the raw headers of the HTTP response.
     * @param value the deserialized value of the HTTP response.
     * @param headers the deserialized headers of the HTTP response.
     */
    public FilesCreateResponse(HttpRequest request, int statusCode, HttpHeaders rawHeaders, Void value, FileCreateHeaders headers) {
        super(request, statusCode, rawHeaders, value, headers);
    }
}
