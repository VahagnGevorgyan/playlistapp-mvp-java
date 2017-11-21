package com.playlistapp.data.settings;


/**
 * Base settings.
 */
public class BaseSettings {
    private AppPreferences prefs = null;

    public BaseSettings(AppPreferences prefs) {
        this.prefs = prefs;
    }

    protected AppPreferences getPrefs() { return prefs; }
}
