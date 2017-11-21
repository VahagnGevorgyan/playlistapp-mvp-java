package com.playlistapp.data.settings;


import com.playlistapp.utils.CollectionUtils;
import com.playlistapp.utils.StringUtils;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;


public class CitySettings extends BaseSettings {

    public CitySettings(AppPreferences prefs) {
        super(prefs);
    }

    /**
     * Method for getting cities list
     * @return Cities list
     */
    public List<String> getCities() {
        Timber.d("Getting list of cities from Preferences " + getCitiesList());

        List<String> list = new ArrayList<>();
        String jsonCities = getCitiesList();
        if (StringUtils.isNotEmptySafe(jsonCities)) {
            JSONArray jsonArray;
            try {
                // TODO: Do this better way?!
                jsonArray = new JSONArray(jsonCities);
                for (int i=0; i<jsonArray.length(); i++) {
                    list.add(jsonArray.getJSONObject(i).getString("name"));
                }
            } catch (JSONException e) {
                Timber.e(e.getMessage());
            }
        }
        return list;
    }

    public String getCitiesList() {
        return getPrefs().getString(AppPreferences.Settings.CITIES_LIST.key(), "");
    }

    public void setCitiesList(String citiesList) {
        getPrefs().setSetting(AppPreferences.Settings.CITIES_LIST, citiesList);
    }

    public boolean isCityEmpty() {
        Timber.d("Checking city list from Preferences " + getCitiesList());

        return StringUtils.isNotEmptySafe(getCitiesList());
    }

    /**
     * Method for getting selected city position
     * @param cityName - Selected city
     * @return - Selected city position
     */
    public int getSelectedCityPosition(String cityName) {
        List<String> cityList = getCities();
        if (cityName != null && !CollectionUtils.isEmpty(cityList)) {
            for (int i = 0; i < cityList.size(); i++) {
                if (cityName.equals(cityList.get(i))) {
                    return i;
                }
            }
        }
        return -1;
    }
}
