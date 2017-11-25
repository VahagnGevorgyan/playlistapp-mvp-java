package com.playlistapp.data.settings;

import android.content.Context;
import android.content.SharedPreferences;

import com.playlistapp.di.ApplicationContext;
import com.playlistapp.di.PreferenceInfo;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Various methods for storing data in shared preferences.
 */
@Singleton
public class AppPreferences implements SharedPreferences {

    /**
     * Settings keys.
     */
    public enum Settings {
        LOGGED_IN_MODE("LOGGED_IN_MODE", Integer.class),

        IS_FIRST_LAUNCH("IS_FIRST_LAUNCH", Boolean.class),
        SPLASH_URL("SPLASH_URL", String.class),
        AUTH_TOKEN("AUTH_TOKEN", String.class),
        REG_KEY("REG_KEY", String.class),
        REGISTER_COMPLETED("REGISTER_COMPLETED", Boolean.class),
        CITIES_LIST("CITIES_LIST", String.class),
        RECIPIENT_LIST("RECIPIENT_LIST", String.class),
        ORDER_DATA("ORDER_DATA", String.class),
        USER_ID("USER_ID", Integer.class),
        USER_FIRST_NAME("USER_FIRST_NAME", String.class),
        USER_LAST_NAME("USER_LAST_NAME", String.class),
        USER_ARM_CODE("USER_ARM_CODE", String.class),
        USER_COMPANY_NAME("USER_COMPANY_NAME", String.class),
        USER_BONUS_IN("USER_BONUS_IN", Integer.class),
        USER_BONUS_OUT("USER_BONUS_OUT", Integer.class),
        USER_EMAIL("USER_EMAIL", String.class),
        GCM_TOKEN("GCM_TOKEN", String.class),
        PROPERTY_APP_VERSION("PROPERTY_APP_VERSION", Integer.class),
        PUSH_COUNT("PUSH_COUNT", Integer.class),
        BADGE_COUNT("BADGE_COUNT", Integer.class),
        CURRENCY_LIST("CURRENCY_LIST", String.class),

        TRACK_LIMIT_COUNT("TRACK_LIMIT_COUNT", Integer.class)
        ;

        private final String key;
        private final Class<?> type;

        private Settings(final String key, final Class<?> type) {
            this.key = key;
            this.type = type;
        }

        public String key() {
            return this.key;
        }

        Class<?> type() {
            return this.type;
        }
    }

    private final SharedPreferences sPreferences;

    @Inject
    public AppPreferences(@ApplicationContext Context context,
                          @PreferenceInfo String prefFileName) {
        sPreferences = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
       
        if (!contains(Settings.IS_FIRST_LAUNCH)) {
            setSetting(Settings.IS_FIRST_LAUNCH, true);
        }
    }


    public boolean contains(final Settings settingsKey) {
        return contains(settingsKey.key());
    }

    @Override
    public boolean contains(final String key) {
        boolean retval = false;

        if (null != sPreferences) {
            retval = sPreferences.contains(key);
        }

        return retval;
    }

    public synchronized void setSetting(final Settings settingsKey,
                                        final Object value) {

        final Editor settingsEditor = edit();
        boolean bChanged = false;
        final String key = settingsKey.key();
        final Class<?> type = settingsKey.type();

        if (null != settingsEditor) {
            if (String.class == type) {
                settingsEditor.putString(key, (String) value);
                bChanged = true;
            } else if (Boolean.class == type) {
                settingsEditor.putBoolean(key, (Boolean) value);
                bChanged = true;
            } else if (Integer.class == type) {
                settingsEditor.putInt(key, (Integer) value);
                bChanged = true;
            } else if (Long.class == type) {
                settingsEditor.putLong(key, (Long) value);
                bChanged = true;
            } else if (Float.class == type) {
                settingsEditor.putFloat(key, (Float) value);
                bChanged = true;
            }

            if (bChanged) {
                settingsEditor.commit();
            }
        }
    }

    public synchronized void setSetting(final Settings settingsKey,
                                        final String keyAdd,
                                        final Object value) {

        final Editor settingsEditor = edit();
        boolean bChanged = false;
        final String key = settingsKey.key();
        final Class<?> type = settingsKey.type();

        if (null != settingsEditor) {
            if (String.class == type) {
                settingsEditor.putString(key+keyAdd, (String) value);
                bChanged = true;
            } else if (Boolean.class == type) {
                settingsEditor.putBoolean(key+keyAdd, (Boolean) value);
                bChanged = true;
            } else if (Integer.class == type) {
                settingsEditor.putInt(key+keyAdd, (Integer) value);
                bChanged = true;
            } else if (Long.class == type) {
                settingsEditor.putLong(key+keyAdd, (Long) value);
                bChanged = true;
            } else if (Float.class == type) {
                settingsEditor.putFloat(key+keyAdd, (Float) value);
                bChanged = true;
            }

            if (bChanged) {
                settingsEditor.commit();
            }
        }
    }

    @Override
    public Editor edit() {
        Editor editor = null;

        if (null != sPreferences) {
            editor = sPreferences.edit();
        }

        return editor;
    }

    @Override
    public Map<String, ?> getAll() {
        Map<String, ?> map = Collections.emptyMap();

        if (null != sPreferences) {
            map = sPreferences.getAll();
        }

        return map;
    }

    public boolean getBoolean(final Settings settingsKey) {
        return getBoolean(settingsKey.key(), false);
    }

    @Override
    public boolean getBoolean(final String key, final boolean defValue) {
        boolean retval = defValue;

        if (null != sPreferences) {
            retval = sPreferences.getBoolean(key, defValue);
        }

        return retval;
    }

    public float getFloat(final Settings settingsKey) {
        return getFloat(settingsKey.key(), 0);
    }

    @Override
    public float getFloat(final String key, final float defValue) {
        float retval = defValue;

        if (null != sPreferences) {
            retval = sPreferences.getFloat(key, defValue);
        }

        return retval;
    }

    public int getInt(final Settings settingsKey) {
        return getInt(settingsKey.key(), -1);
    }

    @Override
    public int getInt(final String key, final int defValue) {
        int retval = defValue;

        if (null != sPreferences) {
            retval = sPreferences.getInt(key, defValue);
        }

        return retval;
    }

    public long getLong(final Settings settingsKey) {
        return getLong(settingsKey.key(), -1);
    }

    @Override
    public long getLong(final String key, final long defValue) {
        long retval = defValue;

        if (null != sPreferences) {
            retval = sPreferences.getLong(key, defValue);
        }

        return retval;
    }

    public String getString(final Settings settingsKey) {
        return getString(settingsKey.key(), null);
    }

    @Override
    public String getString(final String key, final String defValue) {
        String retval = defValue;

        if (null != sPreferences) {
            retval = sPreferences.getString(key, defValue);
        }

        return retval;
    }

    @Override
    public void registerOnSharedPreferenceChangeListener(
            final OnSharedPreferenceChangeListener listener) {

        if (null != sPreferences) {
            sPreferences.registerOnSharedPreferenceChangeListener(listener);
        }
    }

    @Override
    public void unregisterOnSharedPreferenceChangeListener(
            final OnSharedPreferenceChangeListener listener) {

        if (null != sPreferences) {
            sPreferences.unregisterOnSharedPreferenceChangeListener(listener);
        }
    }

    @Override
    public Set<String> getStringSet(String key, Set<String> defValue) {
        Set<String> retval = defValue;

        if (null != sPreferences) {
            retval = sPreferences.getStringSet(key, defValue);
        }

        return retval;
    }

}
