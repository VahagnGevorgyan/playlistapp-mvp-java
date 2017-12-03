package com.playlistapp.ui.home.settings;

import com.playlistapp.data.DataManager;
import com.playlistapp.data.scheduler.SchedulerProvider;
import com.playlistapp.ui.base.BasePresenter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Presenter class for Tracks fragment.
 * @param <V>
 */
public class SettingsPresenter<V extends SettingsMvpView> extends BasePresenter<V>
        implements SettingsMvpPresenter<V> {

    @Inject
    public SettingsPresenter(
            DataManager dataManager,
            SchedulerProvider schedulerProvider,
            CompositeDisposable compositeDisposable
    ) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void loadCountries() {
        if (!isViewAttached()) {
            return;
        }
        getMvpView().showLoading();
        Locale[] locale = Locale.getAvailableLocales();
        List<String> countries = new ArrayList<>();
        String country;
        for (Locale loc : locale ){
            country = loc.getDisplayCountry();
            if (country.length() > 0 && !countries.contains(country) ){
                countries.add( country );
            }
        }
        Collections.sort(countries, String.CASE_INSENSITIVE_ORDER);
        getMvpView().updateCountries(countries);
        getMvpView().hideLoading();
    }

    @Override
    public void loadCountryIndex(List<String> countries) {
        if (!isViewAttached()) {
            return;
        }
        for (int i = 0; i < countries.size(); i++) {
            if (countries.get(i).equals(
                    getDataManager().getSettingsHelper().search().getCountry())) {
                getMvpView().setSelectedCountry(i);
                return;
            }
        }
    }

    @Override
    public void loadLimitIndex(List<String> limitItems) {
        if (!isViewAttached()) {
            return;
        }
        for (int i = 0; i < limitItems.size(); i++) {
            if (limitItems.get(i).equals(
                    String.valueOf(getDataManager().getSettingsHelper().search().getLimitCount()))) {
                getMvpView().setSelectedLimit(i);
                return;
            }
        }
    }

    @Override
    public void onSaveClicked(String selectedCountry, String countryDefValue, String selectedLimit, String limitDefValue) {
        if (!isViewAttached()) {
            return;
        }
        if (isFormValid(selectedCountry, countryDefValue, selectedLimit, limitDefValue)) {
            getMvpView().showLoading();

            getDataManager().getSettingsHelper().search().setCountry(selectedCountry);
            getDataManager().getSettingsHelper().search().setLimitCount(Integer.valueOf(selectedLimit));

            getMvpView().hideLoading();
            getMvpView().backToTracks();
        }
    }

    /**
     * Validates settings screen form.
     */
    private boolean isFormValid(String city, String defCity, String limit, String defLimit) {
        boolean isValid = true;
        if (!validateCountry(city, defCity)) {
            isValid = false;
        }
        if (!validateLimit(limit, defLimit)) {
            isValid = false;
        }
        return isValid;
    }

    private boolean validateLimit(String limit, String defValue) {
        boolean isValid = true;
        if (limit != null && !limit.equals(defValue)) {
            getMvpView().clearLimitNotSelectedError();
        } else {
            isValid = false;
            getMvpView().showLimitNotSelectedError();
        }
        return isValid;
    }

    private boolean validateCountry(String country, String defValue) {
        boolean isValid = true;
        if (country != null && !country.equals(defValue)) {
            getMvpView().clearCountryNotSelectedError();
        } else {
            isValid = false;
            getMvpView().showCountryNotSelectedError();
        }
        return isValid;
    }
}
