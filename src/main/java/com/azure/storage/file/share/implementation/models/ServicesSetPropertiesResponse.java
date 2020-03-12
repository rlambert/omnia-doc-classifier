// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.storage.file.share.implementation.models;

import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.rest.ResponseBase;
import com.azure.storage.file.share.implementation.models.ServiceSetPropertiesHeaders;

/**
 * Contains all response data for the setProperties operation.
 */
public final class ServicesSetPropertiesResponse extends ResponseBase<ServiceSetPropertiesHeaders, Void> {
    /**
     * Creates an instance of ServicesSetPropertiesResponse.
     *
     * @param request the request which resulted in this ServicesSetPropertiesResponse.
     * @param statusCode the status code of the HTTP response.
     * @param rawHeaders the raw headers of the HTTP response.
     * @param value the deserialized value of the HTTP response.
     * @param headers the deserialized headers of the HTTP response.
     */
    public ServicesSetPropertiesResponse(HttpRequest request, int statusCode, HttpHeaders rawHeaders, Void value, ServiceSetPropertiesHeaders headers) {
        super(request, statusCode, rawHeaders, value, headers);
    }
}
