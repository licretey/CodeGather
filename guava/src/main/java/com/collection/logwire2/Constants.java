package com.collection.logwire2;


public interface Constants {
    String DEFAULT_APP_NAME = "logwire";

    String COOKIE_SESSION_ID = "__session_id";
    String COOKIE_DEVICE_ID = "__device_id";
    String COOKIE_DESIGNER_SESSION_ID = "__designer_session_id";
    String COOKIE_CSRF_TOKEN = "__csrf_token";
    String PARAMS_ARCHIVE_DATASOURCE = "__archive_datasource";
    String PRODUCTS_PACKAGE = "logwire.products.";
    String RECORDS_PACKAGE = "logwire.records.";
    String LOGWIRE_PROCESSES_PACKAGE = "logwire.process.";
    String LOGWIRE_CONFIG_IN_CLASSPATH = "resource";
    String PRODUCTS_CONFIG_IN_CLASSPATH = "products";
    String TENANT_ID_PLATFORM = "__platform";
    String MQ_TOPIC_NAME_WEB_SOCKET = "logwire_web_socket";
    String ACTION_TYPE_ACTION = "action";
    String ACTION_TYPE_OPEN_API = "open";
    String ACTION_TYPE_PLATFORM = "platform";
    String ACTION_TYPE_INTERNAL_ACTION = "internal_action";
    String PROPERTY_NODE_ID = "logwirecloud.nodeId";
    String PROPERTY_NODE_ADDRESS = "logwirecloud.nodeAddress";
    String PROPERTY_GATEWAY_SPEC = "logwirecloud.gatewaySpec";
    String PROPERTY_TRACE_ID = "logwirecloud.traceId";
    String DEFAULT_DESIGNER_TOKEN = "designer";
    String DYNAMIC_QUERY_NAME = "__dynamic";
    String TRANSACTION_ID = "transaction_id";
    String HEADER_X_DEVICE_INFO = "X-Device-Info";
    String HEADER_X_BREAK_DEBUG_REQUIRED = "X-Break-Debug-Required";

    String GATEWAY_SPEC = System.getProperty(PROPERTY_GATEWAY_SPEC, "GS2");
    String ZK_PREFIX = "/" + GATEWAY_SPEC;
    String ZK_LOCK_ROOT_PATH = ZK_PREFIX + "/logwire_locks";
    String ZK_NODE_ROOT_PATH = ZK_PREFIX + "/logwire_nodes";
    String ZK_SNOW_FLAKE_ROOT_PATH = ZK_PREFIX + "/logwire_snow_flake";
    String ZK_TASK_CONSUMER_QUEUES = ZK_PREFIX + "/logwire_task/consumer_queues";
    String ZK_LEADER_PATH = ZK_PREFIX + "/logwire_leader";

    String NAMESPACE_DESIGNER = "designer";

    String ACTION_NAME_LOGIN = "core.login";
    String ACTION_NAME_NEW_DEVICE_LOGIN = "core.new-device-login";
    String ACTION_NAME_BIND = "core.bind";
    String ACTION_NAME_EXTENSION_DEVICE_EXPIRE = "core.extension-device-expire";
    String ACTION_NAME_GET_FRONTEND_FILE = "designer.get-frontend-file";
    String ACTION_NAME_GET_USER_THIRD_PARTY_BIND_INFO = "core.get-user-third-party-bind-info";
    String ACTION_NAME_GET_I18N = "core.get-i18n";
    String ACTION_NAME_CORS_CHECK = "core.cors-check";
    String ACTION_NAME_GET_CLIENT_RESOURCE = "core.get-client-resource";
    String ACTION_NAME_GET_CLIENT_RESOURCE_LIST = "core.get-client-resource-list";
    String ACTION_NAME_GET_ASSET = "core.get-asset";
    String ACTION_NAME_GET_LOGIN_ENTRY = "core.get-login-entry";
    String ACTION_NAME_QUERY_BY_FILTER = "core.query-by-filter";
    String ACTION_NAME_COUNT_BY_FILTER = "core.count-by-filter";
    String ACTION_NAME_GET_QUERY_META_DATA = "core.get-query-metadata";
    String ACTION_NAME_SIMPLE_EXPORT_EXCEL = "core.simple-export-excel";
    String ACTION_NAME_QUERY_BY_ID = "core.query-by-id";
    String ACTION_NAME_SAVE = "core.save";
    String ACTION_NAME_DELETE = "core.delete";
    String ACTION_NAME_GET_DEVICE_INFO = "core.get-device-info";
    String ACTION_NAME_DEVICE_OFFLINE = "core.device-offline";
    String ACTION_NAME_GET_PROMETHEUS = "core.get-prometheus";
    String ACTION_NAME_BUILD_PRODUCT = "core.build-product";
    String ACTION_NAME_GET_VERSION_FOR_COOPWIRE = "core.get-version-for-coopwire";
    String ACTION_NAME_GET_NODE_LAST_ACTIVE_TIME = "core.get-node-last-active-time";
    String ACTION_NAME_TENANT_REGISTER_INFO_CHANGE = "core.tenant-register-info-change";
    String ACTION_NAME_GET_WEB_SOCKET_CHECK_INFO = "core.get-web-socket-check-info";
    String ACTION_NAME_DESIGNER_GET_WEB_SOCKET_CHECK_INFO = "designer.designer-get-web-socket-check-info";
    String ACTION_NAME_DESIGNER_LOGIN_CALLBACK = "designer.designer-login-callback";
    String ACTION_NAME_PATH_HANDLER_PROCESS = "core.path-handler-process";
    String ACTION_NAME_GENERATE_DATA_KEY = "core.generate-data-key";
    String ACTION_NAME_UPDATE_DATA_KEY = "core.update-data-key";
    String ACTION_NAME_LOG_GATEWAY_SECURITY_LOG = "core.log-gateway-security-log";

    String ACTION_NAME_RELOAD_TENANT = "core.reload-tenant";
    String ACTION_NAME_UNLOAD_TENANT = "core.unload-tenant";
    String ACTION_NAME_DESIGNER_RELOAD_TENANT = "designer.designer-reload-tenant";

    String LOCK_PATH_SEQUENCE_NO = "core.lock_sequence_no";

    String DEFAULT_TARGET_NAME = "core.default";

    String HOST_NAME_GATEWAY = "gateway";

    String WS_CLOSE_PAYLOAD = "#disconnect_client";

    String DEFAULT_TASK_QUEUE_NAME = "default";

    int KEY_LENGTH = 32;
    int SALT_LENGTH = 16;
    int IV_LENGTH = 12;
    int API_USER_PASSWORD_LENGTH = 20;

    long PROCESS_COST_LOG_THRESHOLD = 1000;

    int ACTIVATION_CODE_LENGTH = 20;
    int SET_NEW_PASSWORD_TOKEN_LENGTH = 30;
    int NEW_DEVICE_LOGIN_TOKEN_LENGTH = 30;

    long ONE_MINUTE = 60 * 1000L;
    long TEN_MINUTE = 10 * ONE_MINUTE;
    long FIFTEEN_MINUTE = 15 * ONE_MINUTE;
    long ONE_HOUR = 60 * ONE_MINUTE;
    long ONE_DAY = 24 * ONE_HOUR;

    int LIMIT_MAX_TAGS_COUNT = 2000;
    int LIMIT_SIZABLE_ITERABLE_TO_LIST = 10000;

    /**
     * 向输出端迭代 RecordDataSource 时的数据行号名称
     */
    String ROWNUM_KEY = "_rownum";

}
