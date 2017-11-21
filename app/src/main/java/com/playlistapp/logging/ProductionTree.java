package com.playlistapp.logging;


import android.util.Log;


import com.playlistapp.utils.LoggingUtils;

import timber.log.Timber;

/**
 *  Logging tree for production (release) version.
 */
public class ProductionTree extends Timber.DebugTree {

    @Override
    protected void log(int priority, String tag, String message, Throwable t) {
        switch (priority) {
            case Log.DEBUG:
            case Log.INFO:
            case Log.WARN:
            case Log.ERROR:
                LoggingUtils.logAtCrashlytics(message);
                break;
            default:
                break;
        }
        super.log(priority, tag, message, t);
    }
}
