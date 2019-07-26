package com.fxkj.publicframework.tool.RecordVoice;

/**
 * Created by wxmij on 2017/3/29.
 */

public class EMError {

    public static final int EM_NO_ERROR = 0;
    public static final int GENERAL_ERROR = 1;
    public static final int NETWORK_ERROR = 2;
    public static final int INVALID_APP_KEY = 100;
    public static final int INVALID_USER_NAME = 101;
    public static final int INVALID_PASSWORD = 102;
    public static final int INVALID_URL = 103;
    public static final int USER_ALREADY_LOGIN = 200;
    public static final int USER_NOT_LOGIN = 201;
    public static final int USER_AUTHENTICATION_FAILED = 202;
    public static final int USER_ALREADY_EXIST = 203;
    public static final int USER_NOT_FOUND = 204;
    public static final int USER_ILLEGAL_ARGUMENT = 205;
    public static final int USER_LOGIN_ANOTHER_DEVICE = 206;
    public static final int USER_REMOVED = 207;
    public static final int USER_REG_FAILED = 208;
    public static final int USER_UPDATEINFO_FAILED = 209;
    public static final int USER_PERMISSION_DENIED = 210;
    public static final int USER_BINDDEVICETOKEN_FAILED = 211;
    public static final int USER_UNBIND_DEVICETOKEN_FAILED = 212;
    public static final int SERVER_NOT_REACHABLE = 300;
    public static final int SERVER_TIMEOUT = 301;
    public static final int SERVER_BUSY = 302;
    public static final int SERVER_UNKNOWN_ERROR = 303;
    public static final int SERVER_GET_DNSLIST_FAILED = 304;
    public static final int SERVER_SERVICE_RESTRICTED = 305;
    public static final int FILE_NOT_FOUND = 400;
    public static final int FILE_INVALID = 401;
    public static final int FILE_UPLOAD_FAILED = 402;
    public static final int FILE_DOWNLOAD_FAILED = 403;
    public static final int MESSAGE_INVALID = 500;
    public static final int MESSAGE_INCLUDE_ILLEGAL_CONTENT = 501;
    public static final int MESSAGE_SEND_TRAFFIC_LIMIT = 502;
    public static final int MESSAGE_ENCRYPTION_ERROR = 503;
    public static final int GROUP_INVALID_ID = 600;
    public static final int GROUP_ALREADY_JOINED = 601;
    public static final int GROUP_NOT_JOINED = 602;
    public static final int GROUP_PERMISSION_DENIED = 603;
    public static final int GROUP_MEMBERS_FULL = 604;
    public static final int GROUP_NOT_EXIST = 605;
    public static final int CHATROOM_INVALID_ID = 700;
    public static final int CHATROOM_ALREADY_JOINED = 701;
    public static final int CHATROOM_NOT_JOINED = 702;
    public static final int CHATROOM_PERMISSION_DENIED = 703;
    public static final int CHATROOM_MEMBERS_FULL = 704;
    public static final int CHATROOM_NOT_EXIST = 705;
    public static final int CALL_INVALID_ID = 800;
    public static final int CALL_BUSY = 801;
    public static final int CALL_REMOTE_OFFLINE = 802;
    public static final int CALL_CONNECTION_ERROR = 803;
    public static final int CALL_INVALID_CAMERA_INDEX = 804;

    public EMError() {
    }
}
