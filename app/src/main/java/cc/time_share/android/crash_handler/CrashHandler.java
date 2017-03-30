package cc.time_share.android.crash_handler;

import android.util.Log;

import com.squareup.leakcanary.LeakCanary;

import cc.time_share.android.crash_handler.handlers.AppCrashHandler;
import cc.time_share.android.crash_handler.timber.Timber;

import static cc.time_share.android.crash_handler.timber.TimberTreeType.*;


public class CrashHandler {

    public static final String IS_REPEATED_CRASH = "IS_REPEATED_CRASH";

    private static volatile CrashHandler instance;

    private CrashHandlerConfiguration configuration;

    protected CrashHandler() {
        // empty constructor
    }

    /**
     * Initialize this instance with provided configuration.
     *
     * @param configuration CrashHandler configuration which is used in instance.
     */
    public synchronized void init(CrashHandlerConfiguration configuration) {
        if (configuration == null) {
            throw new IllegalArgumentException();
        }
        if (this.configuration == null) {
            this.configuration = configuration;
        }

        initDependencies();
    }

    /**
     * Returns singleton class instance.
     */
    public static CrashHandler getInstance() {
        if (instance == null) {
            synchronized (CrashHandler.class) {
                if (instance == null) {
                    instance = new CrashHandler();
                }
            }
        }
        return instance;
    }

    private void initDependencies() {

        // Check if we should enable AppCrashHandler
        if (configuration.isAppCrashHandlerEnabled()) {
            Thread.setDefaultUncaughtExceptionHandler(new AppCrashHandler(configuration.getContext()));
        }

        // Check if we should enable LeakCanary
        if (configuration.isLeakCanaryEnabled()) {
            LeakCanary.install(configuration.getContext());
        }

        // Plant correct Timber tree
        switch (configuration.getTimberTreeType()) {
            case DEBUG:
                Timber.plant(new Timber.DebugTree());
                break;
            default:
                Timber.plant(new Timber.DebugTree());
        }
    }


    public CrashHandlerConfiguration getConfiguration() {
        return configuration;
    }
}
