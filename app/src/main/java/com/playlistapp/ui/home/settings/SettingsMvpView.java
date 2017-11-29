package com.playlistapp.ui.home.settings;


import com.playlistapp.ui.base.MvpView;

import java.util.List;

public interface SettingsMvpView extends MvpView {
    void updateCountries(List<String> countries);

    void clearCountryNotSelectedError();

    void showCountryNotSelectedError();

    void setSelectedCountry(int i);
}
