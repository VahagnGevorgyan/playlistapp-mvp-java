package com.playlistapp.ui.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.playlistapp.eventbus.SingletonBus;
import com.playlistapp.eventbus.event.SetMainFragmentDetailsEvent;

import timber.log.Timber;

import static com.playlistapp.Constants.EXTRA_FRAGMENT_POSITION;
import static com.playlistapp.Constants.EXTRA_MENU_ITEM_ID;

/**
 * Base fragment abstract class for Main activity fragments.
 */
public abstract class MainBaseFragment extends BaseFragment {

    protected int mFragmentPosition;
    protected int mMenuItemPosition;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initFragmentAppearanceDetails();
    }

    /**
     * Initializing previous fragment appearance details.
     */
    protected void initFragmentAppearanceDetails() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            mFragmentPosition = bundle.getInt(EXTRA_FRAGMENT_POSITION, -1);
            mMenuItemPosition = bundle.getInt(EXTRA_MENU_ITEM_ID, 0);
        } else {
            mFragmentPosition = 0;
            mMenuItemPosition = 0;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        setFragmentAppearanceDetails();
    }

    /**
     * Method for setting main fragment appearance details.
     */
    protected void setFragmentAppearanceDetails() {
        SetMainFragmentDetailsEvent event =
                new SetMainFragmentDetailsEvent(mFragmentPosition, mMenuItemPosition);
        try {
            SingletonBus.getInstance().post(event);
        } catch (Exception e) {
            // Could happen when setting fragment at a wrong time.
            Timber.e("Failed to setting main fragment appearance details " + e.getLocalizedMessage());
        }
    }
}
