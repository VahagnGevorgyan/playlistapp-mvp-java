package com.playlistapp.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import timber.log.Timber;

/**
 * A bunch of useful static methods for working with fullsceen activities.
 */

public final class FullscreenUtils {

    private FullscreenUtils() { }

    /**
     * Returns the pixel height of status bar is such is present.
     */
    public static int getStatusBarHeight(Context context, boolean isDialog) {
        int result = 0;
        if (!isDialog && Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return result;
        }
        Resources res = context.getResources();

        int resourceId = res.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = res.getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static int getActionBarHeight(Context context) {
        int actionBarHeight = 0;
        TypedValue tv = new TypedValue();
        if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, context.getResources().getDisplayMetrics());
        }
        return actionBarHeight;
    }

    /**
     * Getting the width and height of navigation bar. A bit complex chain, but it's the only
     * reliable solution which should work on all of the deivices.
     */
    public static Point getNavigationBarSize(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            // There's no transparent nav bar for old versions
            return new Point();
        }

        Point appUsableSize = getAppUsableScreenSize(context);
        Point realScreenSize = getRealScreenSize(context);

        // navigation bar on the right
        if (appUsableSize.x < realScreenSize.x) {
            return new Point(realScreenSize.x - appUsableSize.x, appUsableSize.y);
        }

        // navigation bar at the bottom
        if (appUsableSize.y < realScreenSize.y) {
            return new Point(appUsableSize.x, realScreenSize.y - appUsableSize.y);
        }

        // navigation bar is not present
        return new Point();
    }

    /**
     * Getting the area reserved for the application.
     */
    public static Point getAppUsableScreenSize(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size;
    }

    /**
     * Getting the actual devices screen size.
     */
    public static Point getRealScreenSize(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point size = new Point();

        if (Build.VERSION.SDK_INT >= 17) {
            display.getRealSize(size);
        } else {
            try {
                size.x = (Integer) Display.class.getMethod("getRawWidth").invoke(display);
                size.y = (Integer) Display.class.getMethod("getRawHeight").invoke(display);
            } catch (Exception e) { // NOSONAR
                Timber.e(e.getLocalizedMessage());
            }
        }

        return size;
    }

    public static void setWindowUiVisibility(Window window) {
        int uiFlag = View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            uiFlag |= View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
        }
        window.getDecorView().setSystemUiVisibility(uiFlag);
    }
}
