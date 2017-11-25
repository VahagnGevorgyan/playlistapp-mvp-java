package com.playlistapp.data.settings;

import static java.lang.Integer.MIN_VALUE;

/**
 * Storing user profile information.
 */
public class GeneralSettings extends BaseSettings {

    public GeneralSettings(AppPreferences prefs) {
        super(prefs);
    }

    public int getTrackLimitCount() {
        return getPrefs().getInt(AppPreferences.Settings.TRACK_LIMIT_COUNT.key(), 10);
    }

    public void setTrackLimitCount(int limitCount) {
        getPrefs().setSetting(AppPreferences.Settings.TRACK_LIMIT_COUNT, limitCount);
    }

    public String getSplashUrl() {
        return getPrefs().getString(AppPreferences.Settings.SPLASH_URL.key(), "");
    }

    public void setSplashUrl(String splashUrl) {
        getPrefs().setSetting(AppPreferences.Settings.SPLASH_URL, splashUrl);
    }

    public String getGcmToken() {
        return getPrefs().getString(AppPreferences.Settings.GCM_TOKEN.key(), "");
    }

    public void setGcmToken(String gcmToken) {
        getPrefs().setSetting(AppPreferences.Settings.GCM_TOKEN, gcmToken);
    }

    public int getAppVersion() {
        return getPrefs().getInt(AppPreferences.Settings.PROPERTY_APP_VERSION.key(), MIN_VALUE);
    }

    public void setAppVersion(int appVersion) {
        getPrefs().setSetting(AppPreferences.Settings.PROPERTY_APP_VERSION, appVersion);
    }

    public int getPushCount() {
        return getPrefs().getInt(AppPreferences.Settings.PUSH_COUNT.key(), 0);
    }

    public void setPushCount(int pushCount) {
        getPrefs().setSetting(AppPreferences.Settings.PUSH_COUNT, pushCount);
    }

    public int getBadgeCount() {
        return getPrefs().getInt(AppPreferences.Settings.BADGE_COUNT.key(), 0);
    }

    public void setBadgeCount(int badgeCount) {
        getPrefs().setSetting(AppPreferences.Settings.BADGE_COUNT, badgeCount);
    }

}
