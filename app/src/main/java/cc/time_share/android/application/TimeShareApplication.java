package cc.time_share.android.application;

import android.app.Application;

import cc.time_share.android.activities.MainActivity;
import cc.time_share.android.crash_handler.CrashHandler;
import cc.time_share.android.crash_handler.CrashHandlerConfiguration;
import cc.time_share.android.utilites.GlobalSharedPreferences;

/**
 * Created by gilgoldzweig on 30/03/2017.
 */

public class TimeShareApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        GlobalSharedPreferences.getInstance().initialize(this);
//        CrashHandlerConfiguration configuration =
//                new CrashHandlerConfiguration.Builder(this)
//                        .setHomeActivity(MainActivity.class)
//                        .enableAppCrashHandler(true)
//                        .enableLeakCanary(true)
//                        .build();
//        CrashHandler.getInstance().init(configuration);
    }
}
