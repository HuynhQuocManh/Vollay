package com.ebizword.bigcosm.utils;

/**
 * Created by Mahesh on 8/5/2016.
 */
public class Const {
    public static final String LOCATION_API_BASE = "https://maps.googleapis.com/maps/api/geocode/json?address=";
    public static String PREF_NAME = "allder123";
    public static final int GET = 0;
    public static final int HEADERS = 4;
    public static final int POST = 1;
    public static final int NO_REQUEST = -1;
    public static final int TIMEOUT = 30000;
    public static final int MAX_RETRY = 5;
    public static final int CHOOSE_PHOTO = 100;

    public static final int TAKE_PHOTO = 101;
    public static final float DEFAULT_BACKOFF_MULT = 1f;
    public static final String GOOGLE_API_KEY = "AIzaSyCXfwKyBx0gKZKqqbqezIQzcylFHVluPA0";
    public static final String GOOGLE_MATRIX_URL = "https://maps.googleapis.com/maps/api/distancematrix/json?";

    // no request

    public static final String DEVICE_TYPE = "android";
    public static final String DEVICE_TYPE_ANDROID = "android";
    public static final String SOCIAL_FACEBOOK = "facebook";
    public static final String SOCIAL_GOOGLE = "google";
    public static final String MANUAL = "manual";
    public static final String SOCIAL = "social";
    public static final String REQUEST_DETAIL = "requestDetails";
    public static final String PLACES_API_BASE = "https://maps.googleapis.com/maps/api/place";
    public static final String TYPE_AUTOCOMPLETE = "/autocomplete";
    public static final String OUT_JSON = "/json";

    public class Params {
        public static final String ID = "id";
        public static final String TOKEN = "token";
        public static final String SOCIAL_ID = "social_unique_id";
        public static final String URL = "url";
        public static final String PICTURE = "picture";
        public static final String EMAIL = "email";
        public static final String PASSWORD = "password";
        public static final String confirm_password = "confirm_password";
        public static final String REPASSWORD = "confirm_password";
        public static final String FIRSTNAME = "first_name";
        public static final String LAST_NAME = "last_name";
        public static final String PHONE = "mobile";
        public static final String OTP = "otp";
        public static final String SSN = "ssn";
        public static final String DEVICE_TOKEN = "device_token";
        public static final String OLD_PASSWORD = "old_password";
        public static final String DOC_URL = "document_url";
        public static final String CAR_IMAGE = "cover_picture";
        public static final String ICON = "icon";
        public static final String DEVICE_TYPE = "device_type";
        public static final String LOGIN_BY = "login_by";
        public static final String CURRENCEY = "currency_code";
        public static final String LANGUAGE = "language";
        public static final String REQUEST_ID = "request_id";
        public static final String GENDER = "gender";
        public static final String COUNTRY = "country";
        public static final String TIMEZONE = "timezone";
        public static final String LATITUDE = "latitude";
        public static final String LONGITUDE = "longitude";
        public static final String TAXI_TYPE = "service_id";
        public static final String SERVICE_TYPE = "service_type";
        public static final String ORIGINS = "origins";
        public static final String DESTINATION = "destinations";
        public static final String SENSOR = "sensor";
        public static final String MODE = "mode";
        public static final String DISTANCE = "distance";
        public static final String TIME = "time";
        public static final String S_LATITUDE = "s_latitude";
        public static final String S_LONGITUDE = "s_longitude";
        public static final String D_LATITUDE = "d_latitude";
        public static final String D_LONGITUDE = "d_longitude";
        public static final String S_ADDRESS = "s_address";
        public static final String D_ADDRESS = "d_address";
        public static final String PAYMENT_MODE = "payment_mode";
        public static final String IS_PAID = "is_paid";
        public static final String COMMENT = "comment";
        public static final String RATING = "rating";
        public static final String PAYMENT_METHOD_NONCE = "payment_method_nonce";
        public static final String LAST_FOUR = "last_four";
        public static final String CARD_ID = "card_id";
        public static final String NO_HOUR = "number_hours";
        public static final String REQ_STATUS_TYPE = "request_status_type";
        public static final String HOURLY_PACKAGE_ID = "hourly_package_id";
        public static final String AIRPORT_PACKAGE_ID = "aifrport_price_id";
        public static final String PROMOCODE = "promo_code";
        public static final String REFERRAL_CODE = "referral_code";
        public static final String FAV_ID = "fav_id";
        public static final String ADDRESS = "address";
        public static final String FAVOURITE_NAME = "favourite_name";
        public static final String IS_ADSTOP = "is_adstop";
        public static final String ADSTOP_LONGITUDE = "adstop_longitude";
        public static final String ADSTOP_LATITUDE = "adstop_latitude";
        public static final String ADSTOP_ADDRESS = "adstop_address";
        public static final String CHANGE_TYPE = "change_type";

    }

    public class ServiceType {
        public static final String HOST_URL = "http://allder.qooservices.cf/";
        //consumer
        public static final String BASE_URL = HOST_URL + "userApi/";
        public static final String FOODAPI = HOST_URL + "managetmentFoodApi/";

        //provider  http://allder.qooservices.cf/managetmentFoodApi/txt_search=a

        public static final String BASE_URL2 = HOST_URL + "providerApi/";
        public static final String POSTORDER = BASE_URL + "post_order";
        public static final String LOGINCONSUMER = BASE_URL + "login";
        public static final String GETNOTIFIVATION = BASE_URL + "listNotifications?";
        public static final String GETNOTIFIVATIONPROVIDER = BASE_URL2 + "listNotifications?";
        public static final String LOGINPROVIDER = BASE_URL2 + "login";
        public static final String NOT_APPROVE_ORDER = BASE_URL2 + "listOrders?";
        public static final String NOT_APPROVE_ORDER_USER = BASE_URL + "listOrders?";
        public static final String HANDLEORDER = BASE_URL2 + "handleOrder";
        public static final String UPDATESTORE = BASE_URL2 + "updateDetailStore";
        public static final String CHANGEPASSWORDCONSUMER = BASE_URL + "changePassword";
        public static final String CHANGEPASSWORDPROVIDER = BASE_URL2 + "changePassword";
        public static final String REGISTERCONSUMWE = BASE_URL + "register";
        public static final String REGISTERPROVIDER = BASE_URL2 + "register";
        public static final String UPDATE_PROFILE = BASE_URL + "updateProfile";
        public static final String UPDATE_PROFILEPROVIDER = BASE_URL2 + "updateProfile";
        public static final String FORGOT_PASSWORD = BASE_URL + "forgotpassword";
        public static final String FORGOT_PASSWORDPROVIDER = BASE_URL2 + "forgotpassword";
        public static final String TAXI_TYPE = HOST_URL + "serviceList";
        public static final String UPDATE_LOCATION_URL = BASE_URL + "locationUpdate";
        public static final String INCOMING_REQUEST_IN_PROGRESS_URL = BASE_URL + "incomingRequest";
        public static final String CHECK_REQUEST_STATUS_URL = BASE_URL + "requestStatusCheck";
        public static final String PROVIDER_ACCEPTED_URL = BASE_URL + "serviceAccept";
        public static final String PROVIDER_REJECTED_URL = BASE_URL + "serviceReject";
        public static final String PROVIDER_STARTED_URL = BASE_URL + "providerStarted";
        public static final String PROVIDER_ARRIVED_URL = BASE_URL + "arrived";
        public static final String PROVIDER_SERVICE_STARTED_URL = BASE_URL + "serviceStarted";
        public static final String PROVIDER_SERVICE_COMPLETED_URL = BASE_URL + "serviceCompleted";
        public static final String RATE_USER_URL = BASE_URL + "rateUser";
        public static final String COD_CONFIRM_URL = BASE_URL + "codPaidConfirmation";
        public static final String GET_CHECK_AVAILABLE_STATUS_URL = BASE_URL + "checkAvailableStatus?";
        public static final String POST_AVAILABILITY_STATUS_URL = BASE_URL + "availableUpdate";
        public static final String POST_CANCEL_TRIP_URL = BASE_URL + "cancelrequest";
        public static final String POST_HISTORY_URL = BASE_URL + "history";
        public static final String GET_DOC = BASE_URL + "documents?";
        public static final String GET_OTP = BASE_URL + "sendOtp?";
        public static final String UPLOAD_DOC = BASE_URL + "upload_documents";
        public static final String USER_MESSAGE_NOTIFY = BASE_URL + "message_notification?";
        public static final String GETCATEGORY = FOODAPI + "all_cuisine_categories?";
        public static final String GETFOODS = FOODAPI + "foods?";
        public static final String GETDETAILFOOD = FOODAPI + "detail_food?";
        public static final String GETSTORENEARBY = FOODAPI + "foods?";
        public static final String GETSTORENEARBY1 = FOODAPI + "food?";
        public static final String GETSTORE = FOODAPI + "foods_of_provider?";
        public static final String UPDATETIME = BASE_URL + "update_timezone";
        public static final String ADVERTISEMENTS = BASE_URL + "adsManagement";
        public static final String EARNINGS = BASE_URL + "earnings";
        public static final String LOGOUT = BASE_URL + "logout";
        public static final String LOGOUTPOVIDER = BASE_URL2 + "logout";
        public static final String GET_VERSION = HOST_URL + "get_version";
        public static final String MESSAGE_GET = BASE_URL + "message/get";
    }

    // service codes
    public class ServiceCode {
        //food
        public static final int GETCATEGORY = 39;
        public static final int GETFOODS = 43;
        public static final int GETSTOER = 44;
        public static final int GETDATACOMMENTS = 45;
        public static final int GETDATAPICTURES = 46;
        public static final int UPDATESTORE = 48;
        public static final int GETSTORENEARBY = 53;
        public static final int GETSRECOMMENDED = 54;
        //provider
        public static final int LOCATION_API_BASE_SOURCE = 47;
        public static final int LOGINPROVIDER = 35;
        public static final int ORDER = 211;
        public static final int REGISTERPROVIDER = 36;
        public static final int LOGOUTPOVIDER = 37;
        public static final int FORGOT_PASSWORDPROVIDER = 38;
        public static final int UPDATE_PROFILEPROVIDER = 40;
        public static final int CHANGE_PASSWORD_CONSUMER = 41;
        public static final int CHANGE_PASSWORD_PROVIDER = 42;
        public static final int NOT_APPROVE_ORDER = 49;
        public static final int APPROVE_ORDER = 50;
        public static final int DETAIL_ORDER = 51;
        public static final int HANDLEORDER = 52;


        //consumer
        public static final int REGISTERCONSUMER = 1;
        public static final int LOGINCONSUMER = 2;
        public static final int GET_OTP = 38;
        public static final int UPDATE_PROFILE = 3;
        public static final int FORGOT_PASSWORD = 4;
        public static final int GOOGLE_DIRECTION_API = 5;
        public static final int TAXI_TYPE = 6;
        public static final int UPDATE_LOCATION = 7;
        public static final int INCOMING_REQUEST = 8;
        public static final int CHECK_REQUEST_STATUS = 9;
        public static final int DRIVER_ACCEPTED = 10;
        public static final int DRIVER_REJECTED = 11;
        public static final int PROVIDER_STARTED = 12;
        public static final int PROVIDER_ARRIVED = 13;
        public static final int PROVIDER_SERVICE_STARTED = 14;
        public static final int PROVIDER_SERVICE_COMPLETED = 15;
        public static final int RATE_USER = 16;
        public static final int COD_CONFIRM = 17;
        public static final int GET_CHECK_AVAILABLE_STATUS = 18;
        public static final int POST_AVAILABILITY_STATUS = 19;
        public static final int GOOGLE_MATRIX = 20;
        public static final int POST_CANCEL_TRIP = 21;
        public static final int POST_HISTORY = 22;
        public static final int GET_DOC = 23;
        public static final int UPLOAD_DOC = 24;
        public static final int USER_MESSAGE_NOTIFY = 25;
        public static final int GOOGLE_DIRECTION_forcar_API = 26;
        public static final int UPDATETIME = 27;
        public static final int ADVERTISEMENTS = 28;
        public static final int EARNINGS = 29;
        public static final int LOGOUT = 30;

        public static final int GET_VERSION = 31;
        public static final int MESSAGE_GET = 32;
    }
}
