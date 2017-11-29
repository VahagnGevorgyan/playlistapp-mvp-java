package com.playlistapp.ui.home.settings;


import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.playlistapp.R;
import com.playlistapp.ui.adapter.CountrySpinnerAdapter;
import com.playlistapp.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

/**
 * Settings fragment class.
 */
public class SettingsFragment extends BaseFragment implements SettingsMvpView {

    public static final String TAG = SettingsFragment.class.getSimpleName();

    @Inject
    SettingsMvpPresenter<SettingsMvpView> mPresenter;

    @BindView(R.id.spinnerCountry)
    Spinner mSpinnerCountry;
    @BindView(R.id.dividerSpinner)
    View mDividerSpinner;
    @BindView(R.id.textViewSpinnerError)
    TextView mTextViewSpinnerError;

    @BindString(R.string.str_select_country)
    String mCountryDefValue;
    @BindString(R.string.str_error_empty_text)
    String mErrorEmptyText;

    CountrySpinnerAdapter mCountryAdapter;

    public static SettingsFragment newInstance() {
        Bundle args = new Bundle();
        SettingsFragment fragment = new SettingsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getRootViewId() {
        return R.layout.fragment_settings;
    }

    @Override
    protected void initInjector(View view) {
        Timber.d("Injecting \"Settings\" fragment");
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this, view));
        mPresenter.onAttach(this);
    }

    @Override
    protected void prepareView(View rootView) {
        Timber.d("Preparing fragment elements");
        prepareCountrySpinner();
        mPresenter.loadCountries();
    }

    /**
     * Prepares country spinner adapter
     */
    private void prepareCountrySpinner() {
        Timber.d("Preparing country spinner adapter");
        List<String> countryItems = new ArrayList<>();
        countryItems.add(0, mCountryDefValue);
        mCountryAdapter = new CountrySpinnerAdapter(getBaseActivity(), R.layout.spinner_selection_item, countryItems);
        mCountryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerCountry.setAdapter(mCountryAdapter);
        mSpinnerCountry.setSelection(0);
        mSpinnerCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mTextViewSpinnerError.setVisibility(View.GONE);
                mDividerSpinner.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.black));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Nothing selected.
            }
        });
    }

    @Override
    public void updateCountries(List<String> countries) {
        Timber.d("Updating countries spinner items " + countries);
        countries.add(0, mCountryDefValue);
        mCountryAdapter.updateItems(countries);
        mPresenter.loadCountryIndex(countries);
    }

    @Override
    public void clearCountryNotSelectedError() {
        mTextViewSpinnerError.setVisibility(View.GONE);
        mDividerSpinner.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.black));
    }

    @Override
    public void showCountryNotSelectedError() {
        mTextViewSpinnerError.setVisibility(View.VISIBLE);
        mDividerSpinner.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.error_color));
    }

    @Override
    public void setSelectedCountry(int index) {
        Timber.d("Setting selected country " + index);
        mSpinnerCountry.setSelection(index);
    }

    @OnClick(R.id.nav_back_btn)
    void onNavBackClick() {
        getBaseActivity().onFragmentDetached(TAG);
    }

    @OnClick(R.id.save_btn)
    void onCheckClick() {
        getBaseActivity().onFragmentDetached(TAG);
    }

    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
    }
}
