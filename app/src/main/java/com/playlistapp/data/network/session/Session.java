package com.playlistapp.data.network.session;


import com.playlistapp.Constants;

import java.net.MalformedURLException;
import java.net.URL;

import timber.log.Timber;

/**
 * Application Session.
 * Settings, cached data, etc.
 * When shutting down the app, this data will disappear without a trace. No persistence.
 * Class is able to reconstruct itself, if Android nullifies Singletons.
 */
public class Session {

    private String mLoginServiceURL;
    private String mRegisterServiceURL;
    private String mRegisterPersonServiceURL;
    private String mRegisterCompanyServiceURL;
    private String mCitiesListServiceURL;
    private String mOrdersListServiceURL;
    private String mUserInfoServiceURL;
    private String mRegIdServiceURL;
    private String mRecipientListServiceURL;
    private String mOrderDetailsServiceURL;
    private String mCurrenciesServiceURL;
    private String mDeclarePriceServiceURL;
    private String mNotificationsServiceURL;
    private String mAddressServiceURL;
    private String mAddressDetailsServiceURL;
    private String mOfferServiceURL;
    private String mOfferCategoryServiceURL;
    private String mPickupPointServiceURL;
    private String mRecipientEditServiceURL;
    private String mRecipientAddServiceURL;
    private String mRecipientDeleteServiceURL;
    private String mChangePasswordServiceURL;

    private boolean mIsLoggedIn;
    private Object mLock = new Object();


    /**
     * Constructor.
     *
     * @param base - url
     */
    public Session(String base) {
        Timber.d("Creating App Session");
        mLoginServiceURL = constructFullUrl(base, Constants.ENDPOINT_LOGIN);
        mRegisterServiceURL = constructFullUrl(base, Constants.ENDPOINT_REGISTER);
        mRegisterPersonServiceURL = constructFullUrl(base, Constants.ENDPOINT_REGISTER_PERSON);
        mRegisterCompanyServiceURL = constructFullUrl(base, Constants.ENDPOINT_REGISTER_COMPANY);
        mCitiesListServiceURL = constructFullUrl(base, Constants.ENDPOINT_CITIES_LIST);
        mOrdersListServiceURL = constructFullUrl(base, Constants.ENDPOINT_ORDERS_LIST);
        mUserInfoServiceURL = constructFullUrl(base, Constants.ENDPOINT_USER_INFO);
        mRegIdServiceURL = constructFullUrl(base, Constants.ENDPOINT_REG_ID);
        mRecipientListServiceURL = constructFullUrl(base, Constants.ENDPOINT_RECIPIENT_LIST);
        mOrderDetailsServiceURL = constructFullUrl(base, Constants.ENDPOINT_ORDER_DETAILS);
        mCurrenciesServiceURL = constructFullUrl(base, Constants.ENDPOINT_CURRENCIES);
        mDeclarePriceServiceURL = constructFullUrl(base, Constants.ENDPOINT_DECLARE_PRICE);
        mNotificationsServiceURL = constructFullUrl(base, Constants.ENDPOINT_NOTIFICATIONS);
        mAddressServiceURL = constructFullUrl(base, Constants.ENDPOINT_ADDRESS);
        mAddressDetailsServiceURL = constructFullUrl(base, Constants.ENDPOINT_ADDRESS_DETAILS);
        mOfferServiceURL = constructFullUrl(base, Constants.ENDPOINT_OFFER);
        mOfferCategoryServiceURL = constructFullUrl(base, Constants.ENDPOINT_OFFER_CATEGORY);
        mPickupPointServiceURL = constructFullUrl(base, Constants.ENDPOINT_PICKUP_POINT);
        mRecipientEditServiceURL = constructFullUrl(base, Constants.ENDPOINT_RECIPIENT_EDIT);
        mRecipientAddServiceURL = constructFullUrl(base, Constants.ENDPOINT_RECIPIENT_ADD);
        mRecipientDeleteServiceURL = constructFullUrl(base, Constants.ENDPOINT_RECIPIENT_DELETE);
        mChangePasswordServiceURL = constructFullUrl(base, Constants.ENDPOINT_CHANGE_PASS);
    }

    /**
     * Creates a full url for further use.
     */
    public String constructFullUrl(String base, String endpoint) {
        try {
            // Endpoint is a valid url, we should not modify it.
            // TODO Better way to do this?
            new URL(endpoint);
            return endpoint;
        } catch (MalformedURLException e) {
            // Nothing to do here, move along.
        }

        String fullUrl = base + endpoint;
        return fullUrl.replaceAll("//", "/").replace(":/", "://");
    }

    /**
     * Returns Login Web Service URL value.
     *
     * @return
     */
    public String getLoginServiceURL() {
        return mLoginServiceURL;
    }

    /**
     * Returns Register Web Service URL value.
     *
     * @return
     */
    public String getRegisterServiceURL() {
        return mRegisterServiceURL;
    }

    /**
     * Returns Register Person Web Service URL value.
     *
     * @return
     */
    public String getRegisterPersonServiceURL() {
        return mRegisterPersonServiceURL;
    }

    /**
     * Returns Register Company Web Service URL value.
     *
     * @return
     */
    public String getRegisterCompanyServiceURL() {
        return mRegisterCompanyServiceURL;
    }

    /**
     * Returns User Info Web Service URL value.
     *
     * @return
     */
    public String getUserInfoServiceURL() {
        return mUserInfoServiceURL;
    }

    /**
     * Returns RegId Web Service URL value.
     *
     * @return
     */
    public String getRegIdServiceURL() {
        return mRegIdServiceURL;
    }

    /**
     * Returns Cities List Web Service URL value.
     *
     * @return
     */
    public String getCitiesListServiceURL() {
        return mCitiesListServiceURL;
    }

    /**
     * Returns Recipient List Web Service URL value.
     *
     * @return
     */
    public String getRecipientListServiceURL() {
        return mRecipientListServiceURL;
    }

    /**
     * Returns Orders List Web Service URL value.
     *
     * @return
     */
    public String getOrdersListServiceURL() {
        return mOrdersListServiceURL;
    }

    /**
     * Returns Currencies List Web Service URL value.
     *
     * @return
     */
    public String getCurrenciesServiceURL() {
        return mCurrenciesServiceURL;
    }

    /**
     * Returns Declare Price Web Service URL value.
     *
     * @return
     */
    public String getDeclarePriceServiceURL() {
        return mDeclarePriceServiceURL;
    }

    /**
     * Returns Order Details Web Service URL value.
     *
     * @return
     */
    public String getOrderDetailsServiceURL() {
        return mOrderDetailsServiceURL;
    }

    /**
     * Returns Notifications Web Service URL value.
     *
     * @return
     */
    public String getNotificationsServiceURL() {
        return mNotificationsServiceURL;
    }

    /**
     * Returns Address Web Service URL value.
     *
     * @return
     */
    public String getAddressServiceURL() {
        return mAddressServiceURL;
    }

    /**
     * Returns Address Details Web Service URL value.
     *
     * @return
     */
    public String getAddressDetailsServiceURL() {
        return mAddressDetailsServiceURL;
    }

    /**
     * Returns Offer Web Service URL value.
     *
     * @return
     */
    public String getOfferServiceURL() {
        return mOfferServiceURL;
    }

    /**
     * Returns Offer Category Web Service URL value.
     *
     * @return
     */
    public String getOfferCategoryServiceURL() {
        return mOfferCategoryServiceURL;
    }

    /**
     * Returns Pickup Point Web Service URL value.
     *
     * @return
     */
    public String getPickupPointServiceURL() {
        return mPickupPointServiceURL;
    }

    /**
     * Returns Recipient Add Web Service URL value.
     *
     * @return
     */
    public String getRecipientAddServiceURL() {
        return mRecipientAddServiceURL;
    }

    /**
     * Returns Recipient Edit Web Service URL value.
     *
     * @return
     */
    public String getRecipientEditServiceURL() {
        return mRecipientEditServiceURL;
    }

    /**
     * Returns Recipient Delete Web Service URL value.
     *
     * @return
     */
    public String getRecipientDeleteServiceURL() {
        return mRecipientDeleteServiceURL;
    }

    /**
     * Returns Change Password Web Service URL value.
     *
     * @return
     */
    public String getChangePasswordServiceURL() {
        return mChangePasswordServiceURL;
    }

    /**
     * Tells if the User is logged in.
     *
     * @return
     */
    public boolean isLoggedIn() {
        synchronized (mLock) {
            Timber.d("Is the User logged in? " + mIsLoggedIn);
            return mIsLoggedIn;
        }
    }

    /**
     * Sets if the User is logged in.
     *
     * @param loggedIn
     */
    public void setLoggedIn(boolean loggedIn) {
        synchronized (mLock) {
            Timber.d("Setting User logged in status to " + loggedIn);
            mIsLoggedIn = loggedIn;
        }
    }

}
