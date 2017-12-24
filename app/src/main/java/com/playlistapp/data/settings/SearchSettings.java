package com.playlistapp.data.settings;

import static com.playlistapp.Constants.DEFAULT_TRACK_COUNTRY;
import static com.playlistapp.Constants.DEFAULT_TRACK_LIMIT_COUNT;

/**
 * Storing search filters.
 */
public class SearchSettings extends BaseSettings {

    public SearchSettings(AppPreferences prefs) {
        super(prefs);
    }

    public int getLimitCount() {
        return getPrefs().getInt(AppPreferences.Settings.TRACK_LIMIT_COUNT.key(), DEFAULT_TRACK_LIMIT_COUNT);
    }

    public void setLimitCount(int limitCount) {
        getPrefs().setSetting(AppPreferences.Settings.TRACK_LIMIT_COUNT, limitCount);
    }

    public String getCountry() {
        return getPrefs().getString(AppPreferences.Settings.TRACK_COUNTRY.key(), DEFAULT_TRACK_COUNTRY);
    }

    public void setCountry(String country) {
        getPrefs().setSetting(AppPreferences.Settings.TRACK_COUNTRY, country);
    }
}
