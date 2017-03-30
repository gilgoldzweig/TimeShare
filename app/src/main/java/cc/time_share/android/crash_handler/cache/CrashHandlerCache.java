package cc.time_share.android.crash_handler.cache;


import cc.time_share.android.utilites.GlobalSharedPreferences;

/**
 * Created by Željko Plesac on 18/11/15.
 */
public final class CrashHandlerCache {
    private static GlobalSharedPreferences globalSharedPreferences;
    private CrashHandlerCache() {
        globalSharedPreferences = GlobalSharedPreferences.getInstance();
        // private constructor
    }

    private static final String LAST_CRASH_DATE = "LAST_CRASH_DATE";

    public static void setLastCrashDate(long date) {
     globalSharedPreferences.put(LAST_CRASH_DATE, date).commit();
    }

    public static long getLastCrashDate() {
        return globalSharedPreferences.getLong(LAST_CRASH_DATE, 0);
    }
}
