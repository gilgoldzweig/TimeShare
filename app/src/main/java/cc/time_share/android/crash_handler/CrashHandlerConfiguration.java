package cc.time_share.android.crash_handler;


import android.app.Activity;
import android.app.Application;

import cc.time_share.android.crash_handler.timber.TimberTreeType;

/**
 * Created by Željko Plesac on 18/11/15.
 */
public class CrashHandlerConfiguration {

    private static final int DEFAULT_CRASH_THRESHOLD = 1000;

    private long crashThreshold;

    private Class<?> homeActivity;

    private Application context;

    private boolean enableLeakCanary;


    private boolean enableAppCrashHandler;

    private TimberTreeType timberTreeType;

    private CrashHandlerConfiguration(Builder builder) {
        this.crashThreshold = builder.defaultCrashThreshold;
        this.context = builder.application;
        this.homeActivity = builder.homeActivity;
        this.enableLeakCanary = builder.enableLeakCanary;
        this.timberTreeType = builder.timberTreeType;
        this.enableAppCrashHandler = builder.enableAppCrashHandler;
    }

    public long getCrashThreshold() {
        return crashThreshold;
    }

    public Application getContext() {
        return context;
    }

    public Class<?> getHomeActivity() {
        return homeActivity;
    }

    public boolean isLeakCanaryEnabled() {
        return enableLeakCanary;
    }

    public boolean isAppCrashHandlerEnabled() {
        return enableAppCrashHandler;
    }

    public TimberTreeType getTimberTreeType() {
        return timberTreeType;
    }

    public static class Builder {

        /**
         * Application context.
         */
        private Application application;

        /**
         * Threshold value which is used in AppCrashHandler for detecting crash loops. Default value is 1000.
         */
        private long defaultCrashThreshold = DEFAULT_CRASH_THRESHOLD;

        /**
         * Home activity of your application.
         */
        private Class<?> homeActivity;

        /**
         * Should we enable LeakCanary for CrashHandler instance. Default value is false.
         */
        private boolean enableLeakCanary = false;

        /**
         * Define Timber's log tree type for CrashHandler instance. Default value is DEBUG.
         */
        private TimberTreeType timberTreeType = TimberTreeType.DEBUG;

        /**
         * Should we use AppCrashHandler from library for handling crashes. Default value is true.
         */
        private boolean enableAppCrashHandler = true;

        public Builder(Application application) {
            this.application = application;
        }

        public Builder setCrashThreshold(int threshold) {
            this.defaultCrashThreshold = threshold;
            return this;
        }

        public Builder setHomeActivity(Class<? extends Activity> activity) {
            this.homeActivity = activity;
            return this;
        }

        public Builder setTimberTreeType(TimberTreeType timberTreeType) {
            this.timberTreeType = timberTreeType;
            return this;
        }

        public Builder enableLeakCanary(boolean enableLeakCanary) {
            this.enableLeakCanary = enableLeakCanary;
            return this;
        }


        public Builder enableAppCrashHandler(boolean enableAppCrashHandler) {
            this.enableAppCrashHandler = enableAppCrashHandler;
            return this;
        }

        public CrashHandlerConfiguration build() {
            return new CrashHandlerConfiguration(this);
        }
    }
}
