package cc.time_share.android.utilites;

@SuppressWarnings({"WeakerAccess"})
public class SharedPrefKeys {
    private SharedPrefKeys() {} // non instantiable class

    private static final String TAG = SharedPrefKeys.class.getSimpleName();

    public static final String FILE_KEY = "TimeShareSharedPreferencesKey";

    public static final String USER_KEY = "userKey";
    public static final String USER_NAME = "userName";
    public static final String USER_PHONE = "userPhone";
    public static final String USER_LAT = "userLat";
    public static final String USER_LON = "userLon";
    public static final String USER_SKILLS = "userSkills";

    public static final String FIRST_LOGIN = "first_login";
    public static final String CONNECT_TRIES_KEY = "connection tries key";
    public static final String LOGIN_KEY = "login key";
    public static final String ID_KEY = "id token key";
    public static final String MAIL_KEY = "mail key";
    public static final String PHOTO_KEY = "photo key";
    public static final String PHONE_KEY = "phone key";
    public static final String SHARED_CALENDAR_ALREADY= "shared calendar before";
    public static final String GCM_KEY = "InstanceID Token";
    public static final String AUTH_KEY = "SharedPreferencesAuthKey";
}
