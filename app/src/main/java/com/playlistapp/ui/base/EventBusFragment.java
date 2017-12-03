package com.playlistapp.ui.base;

import android.support.v4.app.Fragment;

import com.playlistapp.eventbus.SingletonBus;

import timber.log.Timber;

/**
 * Base event bus fragment class, for registering event bus.
 */
public class EventBusFragment extends Fragment {

    @Override
    public void onStart() {
        super.onStart();
        SingletonBus.getInstance().register(this);
    }

    @Override
    public void onStop() {
        try {
            SingletonBus.getInstance().unregister(this);
        } catch (IllegalStateException e) {
            Timber.e(e.getLocalizedMessage());
        }
        super.onStop();
    }
}
