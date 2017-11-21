package com.playlistapp.utils;


import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.crashlytics.android.Crashlytics;

/**
 * Utils for error logging.
 */
public final class LoggingUtils {

    private LoggingUtils() { }

    public static final int TIMBER_CRASH_LINE_POSITION = 5;

    /**
     * Add something to the Crashlytics logfile which on the crash
     * will be uploaded to the Crashlytics as additional details to the crash report.
     */
    public static void logAtCrashlytics(@NonNull String message) {
        if(!TextUtils.isEmpty(message)) {
            Crashlytics.getInstance().core.log(message);
        }
    }

    /**
     * Force Crashlytics crash upload with this message.
     */
    public static void forceCrashlyticsCrashWithMessage(@NonNull String message, @NonNull Exception exception, int crashLinePosition) {
        if (!TextUtils.isEmpty(message) && exception != null) {
            Exception e = produceException(message, exception, crashLinePosition);

            if (e != null) {
                Crashlytics.getInstance().core.logException(e);
            }
        }
    }

    /**
     * Produce exception with unique stack trace which begins with the method and line number where Timber.e() was called
     */
    private static Exception produceException(@NonNull String message, Exception e, int crashLinePosition) {
        if (e == null) {
            e = new Exception(message);
        }

        StackTraceElement[] stackTraceElements = e.getStackTrace();

        // This place is vulnerable to changes in the stack trace structure. If you will add more methods in the process, or remove some of them,
        // you will fail to get correct top line for the stack trace.
        // Remember to examine stack trace contents in order to know from which place it starts.
        if (stackTraceElements != null && stackTraceElements.length >= crashLinePosition) {
            int remainingLength = stackTraceElements.length - crashLinePosition;
            StackTraceElement[] alteredStackTraceElements = new StackTraceElement[remainingLength];
            System.arraycopy(stackTraceElements, crashLinePosition, alteredStackTraceElements, 0, remainingLength);
            StackTraceElement topLine = alteredStackTraceElements[0];
            if (topLine != null) {
                alteredStackTraceElements[0] = new StackTraceElement(topLine.getClassName(), topLine.getMethodName(), topLine.getFileName(), topLine.getLineNumber());
            }
            e.setStackTrace(alteredStackTraceElements);
            return e;
        }

        return null;
    }
}
