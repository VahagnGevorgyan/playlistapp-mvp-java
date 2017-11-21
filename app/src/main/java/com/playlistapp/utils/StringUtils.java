package com.playlistapp.utils;

import android.content.Context;
import android.text.Html;
import android.text.Spanned;

import timber.log.Timber;

/**
 * Utils for strings stuff - check emptiness etc.
 */
public final class StringUtils {

    private StringUtils() { }

    /**
     * Method for combining String arrays to String object
     * @param strings - String array
     * @return - combined String
     */
    public static String combineStrings(String... strings) {
        Timber.d(":: StringUtils.combineStrings ");

        StringBuilder stringBuilder = new StringBuilder();
        if(strings != null && strings.length > 0) {
            for (String itemString :
                    strings) {
                stringBuilder.append(itemString);
            }
        }

        return stringBuilder.toString();
    }

    public static boolean isNotEmptySafe(String string) {
        return string != null && !string.isEmpty();
    }

    /**
     * Method for showing content as HTML
     * @param source - HTML source
     * @return Content as HTML
     */
    public static Spanned fromHtml(String source) {

        Spanned result;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result = Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY);
        } else {
            //noinspection deprecation
            result = Html.fromHtml(source);
        }

        return result;
    }

    /**
     * Method for getting string resource by name
     * @param context - current context
     * @param aString - string name
     * @return - string resource
     */
    public static String getStringResourceByName(Context context, String aString) {
        String packageName = context.getPackageName();
        int resId = context.getResources().getIdentifier(aString, "string", packageName);
        return context.getResources().getString(resId);
    }
}
