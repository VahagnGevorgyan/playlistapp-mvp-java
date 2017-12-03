package com.playlistapp.ui.home.settings;


import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.playlistapp.R;
import com.playlistapp.eventbus.SingletonBus;
import com.playlistapp.eventbus.event.RefreshTracksEvent;
import com.playlistapp.ui.adapter.CustomSpinnerAdapter;
import com.playlistapp.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.Arrays;
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
    @BindView(R.id.dividerSpinnerCountry)
    View mDividerSpinnerCountry;
    @BindView(R.id.textViewSpinnerCountryError)
    TextView mTextViewSpinnerCountryError;
    @BindView(R.id.spinnerLimit)
    Spinner mSpinnerLimit;
    @BindView(R.id.dividerSpinnerLimit)
    View mDividerSpinnerLimit;
    @BindView(R.id.textViewSpinnerLimitError)
    TextView mTextViewSpinnerLimitError;

    @BindString(R.string.str_select_country)
    String mCountryDefValue;
    @BindString(R.string.str_select_limit)
    String mLimitDefValue;
    @BindString(R.string.str_error_empty_text)
    String mErrorEmptyText;

    CustomSpinnerAdapter mCountryAdapter;
    CustomSpinnerAdapter mLimitAdapter;

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
        prepareLimitSpinner();
        mPresenter.loadCountries();
    }

    /**
     * Prepares country spinner adapter
     */
    private void prepareCountrySpinner() {
        Timber.d("Preparing country spinner adapter");
        List<String> countryItems = new ArrayList<>();
        countryItems.add(0, mCountryDefValue);
        mCountryAdapter = new CustomSpinnerAdapter(getBaseActivity(), R.layout.spinner_selection_item, countryItems);
        mCountryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerCountry.setAdapter(mCountryAdapter);
        mSpinnerCountry.setSelection(0);
        mSpinnerCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mTextViewSpinnerCountryError.setVisibility(View.GONE);
                mDividerSpinnerCountry.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.black));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Nothing selected.
            }
        });
    }

    /**
     * Prepares limit spinner adapter
     */
    private void prepareLimitSpinner() {
        Timber.d("Preparing limit spinner adapter");
        String[] limitResArray = getResources().getStringArray(R.array.limit_string_array);
        List<String> limitItemsList = Arrays.asList(limitResArray);
        List<String> limitItems = new ArrayList<>(limitItemsList);
        limitItems.add(0, mLimitDefValue);
        mLimitAdapter = new CustomSpinnerAdapter(getBaseActivity(), R.layout.spinner_selection_item, limitItems);
        mLimitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerLimit.setAdapter(mLimitAdapter);
        mSpinnerLimit.setSelection(0);
        mSpinnerLimit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mTextViewSpinnerLimitError.setVisibility(View.GONE);
                mDividerSpinnerLimit.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.black));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Nothing selected.
            }
        });
        mPresenter.loadLimitIndex(limitItems);
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
        mTextViewSpinnerCountryError.setVisibility(View.GONE);
        mDividerSpinnerCountry.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.black));
    }

    @Override
    public void showCountryNotSelectedError() {
        mTextViewSpinnerCountryError.setVisibility(View.VISIBLE);
        mDividerSpinnerCountry.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.error_color));
    }

    @Override
    public void clearLimitNotSelectedError() {
        mTextViewSpinnerLimitError.setVisibility(View.GONE);
        mDividerSpinnerLimit.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.black));
    }

    @Override
    public void showLimitNotSelectedError() {
        mTextViewSpinnerLimitError.setVisibility(View.VISIBLE);
        mDividerSpinnerLimit.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.error_color));
    }

    @Override
    public void setSelectedCountry(int index) {
        Timber.d("Setting selected country " + index);
        mSpinnerCountry.setSelection(index);
    }

    @Override
    public void setSelectedLimit(int index) {
        Timber.d("Setting selected limit " + index);
        mSpinnerLimit.setSelection(index);
    }

    @Override
    public void backToTracks() {
        Timber.d("Back to tracks and refresh list");
        SingletonBus.getInstance().post(new RefreshTracksEvent());
        getBaseActivity().onFragmentDetached(TAG);
    }

    @OnClick(R.id.nav_back_btn)
    void onNavBackClick() {
        getBaseActivity().onFragmentDetached(TAG);
    }

    @OnClick(R.id.save_btn)
    void onCheckClick() {
        Timber.d("Save button is clicked");
        String selectedCountry = "";
        if (mSpinnerCountry.getSelectedItem() != null) {
            selectedCountry = (String) mSpinnerCountry.getSelectedItem();
        }
        String selectedLimit = "";
        if (mSpinnerLimit.getSelectedItem() != null) {
            selectedLimit = (String) mSpinnerLimit.getSelectedItem();
        }
        mPresenter.onSaveClicked(
                selectedCountry,
                mCountryDefValue,
                selectedLimit,
                mLimitDefValue
        );
    }

    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
    }
}
