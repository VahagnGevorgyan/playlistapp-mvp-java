package com.playlistapp.ui.base;

import android.support.v7.app.AppCompatActivity;

import com.playlistapp.eventbus.SingletonBus;

import timber.log.Timber;

/**
 * Base event bus activity class, for registering event bus.
 */
public abstract class EventBusActivity extends AppCompatActivity {

    @Override
    protected void onStart() {
        super.onStart();
        SingletonBus.getInstance().register(this);
    }

    @Override
    protected void onStop() {
        try {
            SingletonBus.getInstance().unregister(this);
        } catch (IllegalStateException e) {
            Timber.e(e.getLocalizedMessage());
        }
        super.onStop();
    }

    @Override
    public void onBackPressed() {
        try {
            super.onBackPressed();
        } catch (IllegalStateException e) {
            Timber.e(e.getLocalizedMessage());
        }
    }
}
