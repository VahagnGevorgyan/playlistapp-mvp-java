package com.playlistapp;


/**
 *  Class to store constants which can be used through all app.
 */
public class Constants {

    private Constants() { }

    // DATABASE
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_FILE_NAME = "playlistapp.sqlite ";

    public static final String FORMAT_WEB_SERVICE = "json";

    // ENDPOINT
    public static final String ENDPOINT_API_BASE = "?api_key={api_key}&format={format}";
    public static final String ENDPOINT_TRACK_LIST = "&method=geo.gettoptracks";

    // REQUEST NUMBERS
    private static final int CHANGE_IMAGE = 0;
    public static final int REQUEST_SETTINGS_ADD_NEW_RECIPIENT = CHANGE_IMAGE + 1;
    public static final int REQUEST_DECELERATE_PRICE = CHANGE_IMAGE + 2;
    public static final int REQUEST_CODE_NOTIFICATION  = CHANGE_IMAGE + 3;

    public static final String GOOGLE_APIS_PROJECT_NUMBER = "629410927983";

    public static final String PUSH_TEXT = "text";
    public static final String ORDER_DETAIL_TYPE_DECLARE = "declarate";


    public static final String DEFAULT_FONT = "fonts/arial.ttf";
    public static final String CUSTOM_ATTR_SCHEMAS = "http://schemas.android.com/apk/res-auto";

    public static final String SET_NOTIFICATION = "com.playlistapp.SET_NOTIFICATION";

    public static final String PREF_NAME = "AppPreferences";

    public static final long TIMEOUT_RETRY_INITIALIZING_NETWORK_LISTENER = 500L; // 0.5s

    public static final int DEFAULT_TRACK_LIMIT_COUNT = 10;
    public static final String DEFAULT_TRACK_COUNTRY = "Latvia";

    // MAIN
    public static final String EXTRA_FRAGMENT_POSITION = "extra_fragment_position";
    public static final String EXTRA_MENU_ITEM_ID = "extra_menu_item_id";

    // WEB
    public static final String EXTRA_WEB_URL = "extra_web_url";
    public static final String EXTRA_WEB_TITLE = "extra_web_title";

    // PUSH NOTIFICATION
    public static final String EXTRA_PUSH_TRACKING = "extra_push_tracking";
    public static final String EXTRA_PUSH_COUNTRY = "extra_push_country";
    public static final String EXTRA_PUSH_STATUS = "extra_push_status";
    public static final String EXTRA_PUSH_TYPE= "extra_push_type";

    // ARGUMENTS
    public static final String ARGUMENT_COUNTRY_TYPE = "argument_country_type";
    public static final String ARGUMENT_ORDER_ITEM_TYPE = "argument_order_item_type";
    public static final String ARGUMENT_TRACKING_ID = "argument_tracking_id";

    // ORDER
    public static final String EXTRA_ORDER_ITEM = "extra_order_item";
    public static final String EXTRA_ORDER_TITLE = "extra_order_title";
    public static final String EXTRA_ORDER_ID = "extra_order_id";

    // SETTINGS
    public static final String ARGUMENT_RECIPIENT = "argument_recipient";
}
