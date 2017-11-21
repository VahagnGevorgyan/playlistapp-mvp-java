package com.playlistapp.logging;


import android.os.AsyncTask;
import android.util.Log;

import com.playlistapp.utils.LoggingUtils;

import timber.log.Timber;

/**
 *  Logging tree for development (debugging) tasks.
 */
public class DevelopmentTree extends Timber.DebugTree {

    /**
     *  Logging message.
     *
     * @param priority
     * @param tag
     * @param message
     * @param t
     */
    @Override
    protected void log(int priority, String tag, final String message, Throwable t) {
        switch (priority) {
            case Log.DEBUG:
            case Log.INFO:
            case Log.WARN:
                LoggingUtils.logAtCrashlytics(message);
                break;
            case Log.ERROR:
                final Exception crashException = new Exception(message);
                // Without AsyncTask Crashlytics crash production in some cases freezes UI for almost 1s
                AsyncTask<Void, Void, Void> crashReportingTask = new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... params) {
                        LoggingUtils.forceCrashlyticsCrashWithMessage(message, crashException, LoggingUtils.TIMBER_CRASH_LINE_POSITION);
                        return null;
                    }
                };
                crashReportingTask.execute();
                break;
            default:
                break;
        }
        super.log(priority, tag, message, t);
    }
}
