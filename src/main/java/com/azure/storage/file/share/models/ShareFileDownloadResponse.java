// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.storage.file.share.models;

import com.azure.core.http.rest.ResponseBase;
import com.azure.storage.file.share.models.ShareFileDownloadAsyncResponse;
import com.azure.storage.file.share.models.ShareFileDownloadHeaders;

/**
 * This class contains the response information returned from the server when downloading a file.
 */
public class ShareFileDownloadResponse extends ResponseBase<ShareFileDownloadHeaders, Void> {
    /**
     * Constructs a {@link com.azure.storage.file.share.models.ShareFileDownloadResponse}.
     *
     * @param response Response returned from the service.
     */
    public ShareFileDownloadResponse(ShareFileDownloadAsyncResponse response) {
        super(response.getRequest(), response.getStatusCode(), response.getHeaders(), null,
            response.getDeserializedHeaders());
    }
}
