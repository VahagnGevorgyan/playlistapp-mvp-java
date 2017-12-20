package com.playlistapp.data.settings;

import static java.lang.Integer.MIN_VALUE;

/**
 * Storing app general information.
 */
public class GeneralSettings extends BaseSettings {

    public GeneralSettings(AppPreferences prefs) {
        super(prefs);
    }

    public String getSplashUrl() {
        return getPrefs().getString(AppPreferences.Settings.SPLASH_URL.key(), "");
    }

    public void setSplashUrl(String splashUrl) {
        getPrefs().setSetting(AppPreferences.Settings.SPLASH_URL, splashUrl);
    }

    public int getAppVersion() {
        return getPrefs().getInt(AppPreferences.Settings.PROPERTY_APP_VERSION.key(), MIN_VALUE);
    }

    public void setAppVersion(int appVersion) {
        getPrefs().setSetting(AppPreferences.Settings.PROPERTY_APP_VERSION, appVersion);
    }

}
