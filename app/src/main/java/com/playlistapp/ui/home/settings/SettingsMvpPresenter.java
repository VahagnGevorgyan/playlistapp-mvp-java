package com.playlistapp.ui.home.settings;


import com.playlistapp.ui.base.MvpPresenter;

import java.util.List;

public interface SettingsMvpPresenter<V extends SettingsMvpView> extends MvpPresenter<V> {

    void loadCountries();

    void loadCountryIndex(List<String> countries);

    void loadLimitIndex(List<String> limitItems);

    void onSaveClicked(String selectedCountry, String countryDefValue, String selectedLimit, String limitDefValue);
}
