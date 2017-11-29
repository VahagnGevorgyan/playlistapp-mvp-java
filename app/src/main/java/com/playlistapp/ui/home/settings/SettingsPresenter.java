package com.playlistapp.ui.home.settings;

import com.playlistapp.data.DataManager;
import com.playlistapp.data.scheduler.SchedulerProvider;
import com.playlistapp.ui.base.BasePresenter;

import java.util.ArrayList;
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
        for( Locale loc : locale ){
            country = loc.getDisplayCountry();
            if( country.length() > 0 && !countries.contains(country) ){
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
        for(int i = 0; i < countries.size(); i++) {
            if(countries.get(i).equals("Spain")) { // TODO: Change with data from Settings
                getMvpView().setSelectedCountry(i);
                return;
            }
        }
    }
}
