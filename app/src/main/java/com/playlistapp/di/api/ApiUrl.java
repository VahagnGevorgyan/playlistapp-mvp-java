package com.playlistapp.di.api;

import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Allows for Dagger to precisely identify a Class.
 */
@Retention(RUNTIME)
@Qualifier
public @interface ApiUrl {
}
