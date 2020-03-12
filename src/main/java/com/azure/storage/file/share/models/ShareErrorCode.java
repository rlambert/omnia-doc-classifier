// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.storage.file.share.models;

import com.azure.core.util.ExpandableStringEnum;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Collection;

/**
 * Defines values for ShareErrorCode.
 */
public final class ShareErrorCode extends ExpandableStringEnum<com.azure.storage.file.share.models.ShareErrorCode> {
    /**
     * Static value AccountAlreadyExists for ShareErrorCode.
     */
    public static final com.azure.storage.file.share.models.ShareErrorCode ACCOUNT_ALREADY_EXISTS = fromString("AccountAlreadyExists");

    /**
     * Static value AccountBeingCreated for ShareErrorCode.
     */
    public static final com.azure.storage.file.share.models.ShareErrorCode ACCOUNT_BEING_CREATED = fromString("AccountBeingCreated");

    /**
     * Static value AccountIsDisabled for ShareErrorCode.
     */
    public static final com.azure.storage.file.share.models.ShareErrorCode ACCOUNT_IS_DISABLED = fromString("AccountIsDisabled");

    /**
     * Static value AuthenticationFailed for ShareErrorCode.
     */
    public static final com.azure.storage.file.share.models.ShareErrorCode AUTHENTICATION_FAILED = fromString("AuthenticationFailed");

    /**
     * Static value AuthorizationFailure for ShareErrorCode.
     */
    public static final com.azure.storage.file.share.models.ShareErrorCode AUTHORIZATION_FAILURE = fromString("AuthorizationFailure");

    /**
     * Static value ConditionHeadersNotSupported for ShareErrorCode.
     */
    public static final com.azure.storage.file.share.models.ShareErrorCode CONDITION_HEADERS_NOT_SUPPORTED = fromString("ConditionHeadersNotSupported");

    /**
     * Static value ConditionNotMet for ShareErrorCode.
     */
    public static final com.azure.storage.file.share.models.ShareErrorCode CONDITION_NOT_MET = fromString("ConditionNotMet");

    /**
     * Static value EmptyMetadataKey for ShareErrorCode.
     */
    public static final com.azure.storage.file.share.models.ShareErrorCode EMPTY_METADATA_KEY = fromString("EmptyMetadataKey");

    /**
     * Static value InsufficientAccountPermissions for ShareErrorCode.
     */
    public static final com.azure.storage.file.share.models.ShareErrorCode INSUFFICIENT_ACCOUNT_PERMISSIONS = fromString("InsufficientAccountPermissions");

    /**
     * Static value InternalError for ShareErrorCode.
     */
    public static final com.azure.storage.file.share.models.ShareErrorCode INTERNAL_ERROR = fromString("InternalError");

    /**
     * Static value InvalidAuthenticationInfo for ShareErrorCode.
     */
    public static final com.azure.storage.file.share.models.ShareErrorCode INVALID_AUTHENTICATION_INFO = fromString("InvalidAuthenticationInfo");

    /**
     * Static value InvalidHeaderValue for ShareErrorCode.
     */
    public static final com.azure.storage.file.share.models.ShareErrorCode INVALID_HEADER_VALUE = fromString("InvalidHeaderValue");

    /**
     * Static value InvalidHttpVerb for ShareErrorCode.
     */
    public static final com.azure.storage.file.share.models.ShareErrorCode INVALID_HTTP_VERB = fromString("InvalidHttpVerb");

    /**
     * Static value InvalidInput for ShareErrorCode.
     */
    public static final com.azure.storage.file.share.models.ShareErrorCode INVALID_INPUT = fromString("InvalidInput");

    /**
     * Static value InvalidMd5 for ShareErrorCode.
     */
    public static final com.azure.storage.file.share.models.ShareErrorCode INVALID_MD5 = fromString("InvalidMd5");

    /**
     * Static value InvalidMetadata for ShareErrorCode.
     */
    public static final com.azure.storage.file.share.models.ShareErrorCode INVALID_METADATA = fromString("InvalidMetadata");

    /**
     * Static value InvalidQueryParameterValue for ShareErrorCode.
     */
    public static final com.azure.storage.file.share.models.ShareErrorCode INVALID_QUERY_PARAMETER_VALUE = fromString("InvalidQueryParameterValue");

    /**
     * Static value InvalidRange for ShareErrorCode.
     */
    public static final com.azure.storage.file.share.models.ShareErrorCode INVALID_RANGE = fromString("InvalidRange");

    /**
     * Static value InvalidResourceName for ShareErrorCode.
     */
    public static final com.azure.storage.file.share.models.ShareErrorCode INVALID_RESOURCE_NAME = fromString("InvalidResourceName");

    /**
     * Static value InvalidUri for ShareErrorCode.
     */
    public static final com.azure.storage.file.share.models.ShareErrorCode INVALID_URI = fromString("InvalidUri");

    /**
     * Static value InvalidXmlDocument for ShareErrorCode.
     */
    public static final com.azure.storage.file.share.models.ShareErrorCode INVALID_XML_DOCUMENT = fromString("InvalidXmlDocument");

    /**
     * Static value InvalidXmlNodeValue for ShareErrorCode.
     */
    public static final com.azure.storage.file.share.models.ShareErrorCode INVALID_XML_NODE_VALUE = fromString("InvalidXmlNodeValue");

    /**
     * Static value Md5Mismatch for ShareErrorCode.
     */
    public static final com.azure.storage.file.share.models.ShareErrorCode MD5MISMATCH = fromString("Md5Mismatch");

    /**
     * Static value MetadataTooLarge for ShareErrorCode.
     */
    public static final com.azure.storage.file.share.models.ShareErrorCode METADATA_TOO_LARGE = fromString("MetadataTooLarge");

    /**
     * Static value MissingContentLengthHeader for ShareErrorCode.
     */
    public static final com.azure.storage.file.share.models.ShareErrorCode MISSING_CONTENT_LENGTH_HEADER = fromString("MissingContentLengthHeader");

    /**
     * Static value MissingRequiredQueryParameter for ShareErrorCode.
     */
    public static final com.azure.storage.file.share.models.ShareErrorCode MISSING_REQUIRED_QUERY_PARAMETER = fromString("MissingRequiredQueryParameter");

    /**
     * Static value MissingRequiredHeader for ShareErrorCode.
     */
    public static final com.azure.storage.file.share.models.ShareErrorCode MISSING_REQUIRED_HEADER = fromString("MissingRequiredHeader");

    /**
     * Static value MissingRequiredXmlNode for ShareErrorCode.
     */
    public static final com.azure.storage.file.share.models.ShareErrorCode MISSING_REQUIRED_XML_NODE = fromString("MissingRequiredXmlNode");

    /**
     * Static value MultipleConditionHeadersNotSupported for ShareErrorCode.
     */
    public static final com.azure.storage.file.share.models.ShareErrorCode MULTIPLE_CONDITION_HEADERS_NOT_SUPPORTED = fromString("MultipleConditionHeadersNotSupported");

    /**
     * Static value OperationTimedOut for ShareErrorCode.
     */
    public static final com.azure.storage.file.share.models.ShareErrorCode OPERATION_TIMED_OUT = fromString("OperationTimedOut");

    /**
     * Static value OutOfRangeInput for ShareErrorCode.
     */
    public static final com.azure.storage.file.share.models.ShareErrorCode OUT_OF_RANGE_INPUT = fromString("OutOfRangeInput");

    /**
     * Static value OutOfRangeQueryParameterValue for ShareErrorCode.
     */
    public static final com.azure.storage.file.share.models.ShareErrorCode OUT_OF_RANGE_QUERY_PARAMETER_VALUE = fromString("OutOfRangeQueryParameterValue");

    /**
     * Static value RequestBodyTooLarge for ShareErrorCode.
     */
    public static final com.azure.storage.file.share.models.ShareErrorCode REQUEST_BODY_TOO_LARGE = fromString("RequestBodyTooLarge");

    /**
     * Static value ResourceTypeMismatch for ShareErrorCode.
     */
    public static final com.azure.storage.file.share.models.ShareErrorCode RESOURCE_TYPE_MISMATCH = fromString("ResourceTypeMismatch");

    /**
     * Static value RequestUrlFailedToParse for ShareErrorCode.
     */
    public static final com.azure.storage.file.share.models.ShareErrorCode REQUEST_URL_FAILED_TO_PARSE = fromString("RequestUrlFailedToParse");

    /**
     * Static value ResourceAlreadyExists for ShareErrorCode.
     */
    public static final com.azure.storage.file.share.models.ShareErrorCode RESOURCE_ALREADY_EXISTS = fromString("ResourceAlreadyExists");

    /**
     * Static value ResourceNotFound for ShareErrorCode.
     */
    public static final com.azure.storage.file.share.models.ShareErrorCode RESOURCE_NOT_FOUND = fromString("ResourceNotFound");

    /**
     * Static value ServerBusy for ShareErrorCode.
     */
    public static final com.azure.storage.file.share.models.ShareErrorCode SERVER_BUSY = fromString("ServerBusy");

    /**
     * Static value UnsupportedHeader for ShareErrorCode.
     */
    public static final com.azure.storage.file.share.models.ShareErrorCode UNSUPPORTED_HEADER = fromString("UnsupportedHeader");

    /**
     * Static value UnsupportedXmlNode for ShareErrorCode.
     */
    public static final com.azure.storage.file.share.models.ShareErrorCode UNSUPPORTED_XML_NODE = fromString("UnsupportedXmlNode");

    /**
     * Static value UnsupportedQueryParameter for ShareErrorCode.
     */
    public static final com.azure.storage.file.share.models.ShareErrorCode UNSUPPORTED_QUERY_PARAMETER = fromString("UnsupportedQueryParameter");

    /**
     * Static value UnsupportedHttpVerb for ShareErrorCode.
     */
    public static final com.azure.storage.file.share.models.ShareErrorCode UNSUPPORTED_HTTP_VERB = fromString("UnsupportedHttpVerb");

    /**
     * Static value CannotDeleteFileOrDirectory for ShareErrorCode.
     */
    public static final com.azure.storage.file.share.models.ShareErrorCode CANNOT_DELETE_FILE_OR_DIRECTORY = fromString("CannotDeleteFileOrDirectory");

    /**
     * Static value ClientCacheFlushDelay for ShareErrorCode.
     */
    public static final com.azure.storage.file.share.models.ShareErrorCode CLIENT_CACHE_FLUSH_DELAY = fromString("ClientCacheFlushDelay");

    /**
     * Static value DeletePending for ShareErrorCode.
     */
    public static final com.azure.storage.file.share.models.ShareErrorCode DELETE_PENDING = fromString("DeletePending");

    /**
     * Static value DirectoryNotEmpty for ShareErrorCode.
     */
    public static final com.azure.storage.file.share.models.ShareErrorCode DIRECTORY_NOT_EMPTY = fromString("DirectoryNotEmpty");

    /**
     * Static value FileLockConflict for ShareErrorCode.
     */
    public static final com.azure.storage.file.share.models.ShareErrorCode FILE_LOCK_CONFLICT = fromString("FileLockConflict");

    /**
     * Static value InvalidFileOrDirectoryPathName for ShareErrorCode.
     */
    public static final com.azure.storage.file.share.models.ShareErrorCode INVALID_FILE_OR_DIRECTORY_PATH_NAME = fromString("InvalidFileOrDirectoryPathName");

    /**
     * Static value ParentNotFound for ShareErrorCode.
     */
    public static final com.azure.storage.file.share.models.ShareErrorCode PARENT_NOT_FOUND = fromString("ParentNotFound");

    /**
     * Static value ReadOnlyAttribute for ShareErrorCode.
     */
    public static final com.azure.storage.file.share.models.ShareErrorCode READ_ONLY_ATTRIBUTE = fromString("ReadOnlyAttribute");

    /**
     * Static value ShareAlreadyExists for ShareErrorCode.
     */
    public static final com.azure.storage.file.share.models.ShareErrorCode SHARE_ALREADY_EXISTS = fromString("ShareAlreadyExists");

    /**
     * Static value ShareBeingDeleted for ShareErrorCode.
     */
    public static final com.azure.storage.file.share.models.ShareErrorCode SHARE_BEING_DELETED = fromString("ShareBeingDeleted");

    /**
     * Static value ShareDisabled for ShareErrorCode.
     */
    public static final com.azure.storage.file.share.models.ShareErrorCode SHARE_DISABLED = fromString("ShareDisabled");

    /**
     * Static value ShareNotFound for ShareErrorCode.
     */
    public static final com.azure.storage.file.share.models.ShareErrorCode SHARE_NOT_FOUND = fromString("ShareNotFound");

    /**
     * Static value SharingViolation for ShareErrorCode.
     */
    public static final com.azure.storage.file.share.models.ShareErrorCode SHARING_VIOLATION = fromString("SharingViolation");

    /**
     * Static value ShareSnapshotInProgress for ShareErrorCode.
     */
    public static final com.azure.storage.file.share.models.ShareErrorCode SHARE_SNAPSHOT_IN_PROGRESS = fromString("ShareSnapshotInProgress");

    /**
     * Static value ShareSnapshotCountExceeded for ShareErrorCode.
     */
    public static final com.azure.storage.file.share.models.ShareErrorCode SHARE_SNAPSHOT_COUNT_EXCEEDED = fromString("ShareSnapshotCountExceeded");

    /**
     * Static value ShareSnapshotOperationNotSupported for ShareErrorCode.
     */
    public static final com.azure.storage.file.share.models.ShareErrorCode SHARE_SNAPSHOT_OPERATION_NOT_SUPPORTED = fromString("ShareSnapshotOperationNotSupported");

    /**
     * Static value ShareHasSnapshots for ShareErrorCode.
     */
    public static final com.azure.storage.file.share.models.ShareErrorCode SHARE_HAS_SNAPSHOTS = fromString("ShareHasSnapshots");

    /**
     * Static value ContainerQuotaDowngradeNotAllowed for ShareErrorCode.
     */
    public static final com.azure.storage.file.share.models.ShareErrorCode CONTAINER_QUOTA_DOWNGRADE_NOT_ALLOWED = fromString("ContainerQuotaDowngradeNotAllowed");

    /**
     * Static value AuthorizationSourceIPMismatch for ShareErrorCode.
     */
    public static final com.azure.storage.file.share.models.ShareErrorCode AUTHORIZATION_SOURCE_IPMISMATCH = fromString("AuthorizationSourceIPMismatch");

    /**
     * Static value AuthorizationProtocolMismatch for ShareErrorCode.
     */
    public static final com.azure.storage.file.share.models.ShareErrorCode AUTHORIZATION_PROTOCOL_MISMATCH = fromString("AuthorizationProtocolMismatch");

    /**
     * Static value AuthorizationPermissionMismatch for ShareErrorCode.
     */
    public static final com.azure.storage.file.share.models.ShareErrorCode AUTHORIZATION_PERMISSION_MISMATCH = fromString("AuthorizationPermissionMismatch");

    /**
     * Static value AuthorizationServiceMismatch for ShareErrorCode.
     */
    public static final com.azure.storage.file.share.models.ShareErrorCode AUTHORIZATION_SERVICE_MISMATCH = fromString("AuthorizationServiceMismatch");

    /**
     * Static value AuthorizationResourceTypeMismatch for ShareErrorCode.
     */
    public static final com.azure.storage.file.share.models.ShareErrorCode AUTHORIZATION_RESOURCE_TYPE_MISMATCH = fromString("AuthorizationResourceTypeMismatch");

    /**
     * Static value FeatureVersionMismatch for ShareErrorCode.
     */
    public static final com.azure.storage.file.share.models.ShareErrorCode FEATURE_VERSION_MISMATCH = fromString("FeatureVersionMismatch");

    /**
     * Creates or finds a ShareErrorCode from its string representation.
     *
     * @param name a name to look for.
     * @return the corresponding ShareErrorCode.
     */
    @JsonCreator
    public static com.azure.storage.file.share.models.ShareErrorCode fromString(String name) {
        return fromString(name, com.azure.storage.file.share.models.ShareErrorCode.class);
    }

    /**
     * @return known ShareErrorCode values.
     */
    public static Collection<com.azure.storage.file.share.models.ShareErrorCode> values() {
        return values(com.azure.storage.file.share.models.ShareErrorCode.class);
    }
}