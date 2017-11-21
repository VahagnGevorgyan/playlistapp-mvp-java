package com.playlistapp;


/**
 *  Class to store constants which can be used through all app.
 */
public class Constants {

    private Constants() { }

    // DATABASE
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_FILE_NAME = "playlistapp.sqlite ";

    // ENDPOINT
    public static final String ENDPOINT_CITIES_LIST = "account/listcities";
    public static final String ENDPOINT_ORDERS_LIST = "account/orders";
    public static final String ENDPOINT_LOGIN = "user/login";
    public static final String ENDPOINT_REGISTER = "user/register";
    public static final String ENDPOINT_REGISTER_PERSON = "user/registerperson";
    public static final String ENDPOINT_REGISTER_COMPANY = "user/registercompany";
    public static final String ENDPOINT_USER_INFO = "account/getuserinfo";
    public static final String ENDPOINT_REG_ID = "account/sendregisterid";
    public static final String ENDPOINT_RECIPIENT_LIST = "account/recipientlist";
    public static final String ENDPOINT_ORDER_DETAILS = "account/order";
    public static final String ENDPOINT_CURRENCIES = "account/currencies";
    public static final String ENDPOINT_DECLARE_PRICE = "account/declareprice";
    public static final String ENDPOINT_NOTIFICATIONS = "account/getnotifications";
    public static final String ENDPOINT_ADDRESS = "account/addresses";
    public static final String ENDPOINT_ADDRESS_DETAILS = "account/addressdetails";
    public static final String ENDPOINT_OFFER = "account/gethotdeals";
    public static final String ENDPOINT_OFFER_CATEGORY = "account/gethotdealscategories";
    public static final String ENDPOINT_PICKUP_POINT = "account/pickuppoints";
    public static final String ENDPOINT_RECIPIENT_EDIT = "account/recipientedit";
    public static final String ENDPOINT_RECIPIENT_ADD = "account/recipientadd";
    public static final String ENDPOINT_RECIPIENT_DELETE = "account/recipientdelete";
    public static final String ENDPOINT_CHANGE_PASS = "account/changepassword";

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

    public static final String IMAGE_URL = "http://onex.am";

    public static final String ONEX_WEB_URL = "http://onex.am/pages/5";

    public static final String SET_NOTIFICATION = "com.onex.SET_NOTIFICATION";

    public static final String ONEX_EMAIL = "contact@onex.am";
    public static final String ONEX_PHONE= "+37460750350";
    public static final String ONEX_URL= "http://onex.am/";

    public static final String PREF_NAME = "AppPreferences";

    public static final long TIMEOUT_RETRY_INITIALIZING_NETWORK_LISTENER = 500L; // 0.5s

    // MAIN
    public static final String EXTRA_FRAGMENT_POSITION = "extra_fragment_position";
    public static final String EXTRA_MENU_ITEM_POSITION = "extra_menu_item_position";

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
