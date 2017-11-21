package com.playlistapp.utils;

import android.content.res.Resources;

/**
 * Utils class with common methods using with screen.
 */
public final class ScreenUtils {

    private ScreenUtils() { }

    /**
     * Method for getting dp by pixel
     * @param dp - Dpi
     * @return Pixel
     */
    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    /**
     * Method for getting px by dp
     * @param px - Pixel
     * @return Dpi
     */
    public static int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

}
