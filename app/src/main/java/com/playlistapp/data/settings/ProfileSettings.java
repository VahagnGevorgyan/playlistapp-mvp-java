package com.playlistapp.data.settings;


import com.playlistapp.data.DataManager;
import com.playlistapp.utils.StringUtils;

/**
 * Storing user profile information.
 */
public class ProfileSettings extends BaseSettings {

    ProfileSettings(AppPreferences prefs) {
        super(prefs);
    }

    /**
     * Check if user registered.
     */
    public boolean isUserRegistered() {
        return StringUtils.isNotEmptySafe(getAuthToken()) && getUserId() > 0;
    }

    /**
     * Check if user registration completed.
     */
    public boolean isUserRegisterCompleted() {
        return getUserId() > 0 && isRegisterCompleted();
    }

    public int getCurrentUserLoggedInMode() {
        return getPrefs().getInt(AppPreferences.Settings.LOGGED_IN_MODE.key(),
                DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT.getType());
    }

    public void setCurrentUserLoggedInMode(DataManager.LoggedInMode mode) {
        getPrefs().setSetting(AppPreferences.Settings.LOGGED_IN_MODE, mode.getType());
    }

    /**
     * Clear all user data from shared preferences.
     */
    public void clearUserData() {
        setUserId(0);
        setAuthToken("");
        setRegisterCompleted(false);
    }


    public String getRegKey() {
        return getPrefs().getString(AppPreferences.Settings.REG_KEY.key(), "");
    }

    public void setRegKey(String regKey) {
        getPrefs().setSetting(AppPreferences.Settings.REG_KEY, regKey);
    }

    public String getAuthToken() {
        return getPrefs().getString(AppPreferences.Settings.AUTH_TOKEN.key(), "");
    }

    public void setAuthToken(String authToken) {
        getPrefs().setSetting(AppPreferences.Settings.AUTH_TOKEN, authToken);
    }

    private boolean isRegisterCompleted() {
        return getPrefs().getBoolean(AppPreferences.Settings.REGISTER_COMPLETED.key(), false);
    }

    public void setRegisterCompleted(boolean isRegisterCompleted) {
        getPrefs().setSetting(AppPreferences.Settings.REGISTER_COMPLETED, isRegisterCompleted);
    }

    public int getUserId() {
        return getPrefs().getInt(AppPreferences.Settings.USER_ID.key(), 0);
    }

    public void setUserId(int userId) {
        getPrefs().setSetting(AppPreferences.Settings.USER_ID, userId);
    }

    /**
     * Method for getting user email
     * @return User email
     */
    public String getEmail() {
        return getPrefs().getString(AppPreferences.Settings.USER_EMAIL.key(), "");
    }

    /**
     * Method for setting user email
     * @param email - User email
     */
    public void setEmail(String email) {
        getPrefs().setSetting(AppPreferences.Settings.USER_EMAIL, email);
    }

    /**
     * Method for getting user first name
     * @return User first name
     */
    public String getFirstName() {
        return getPrefs().getString(AppPreferences.Settings.USER_FIRST_NAME.key(), "");
    }

    /**
     * Method for setting user first name
     * @param firstName - User first name
     */
    public void setFirstName(String firstName) {
        getPrefs().setSetting(AppPreferences.Settings.USER_FIRST_NAME, firstName);
    }

    /**
     * Method for getting user last name
     * @return User last name
     */
    public String getLastName() {
        return getPrefs().getString(AppPreferences.Settings.USER_LAST_NAME.key(), "");
    }

    /**
     * Method for setting user last name
     * @param lastName - User last name
     */
    public void setLastName(String lastName) {
        getPrefs().setSetting(AppPreferences.Settings.USER_LAST_NAME, lastName);
    }

    /**
     * Method for getting user arm code
     * @return User arm code
     */
    public String getArmCode() {
        return getPrefs().getString(AppPreferences.Settings.USER_ARM_CODE.key(), "");
    }

    /**
     * Method for setting user arm code
     * @param armCode - User arm code
     */
    public void setArmCode(String armCode) {
        getPrefs().setSetting(AppPreferences.Settings.USER_ARM_CODE, armCode);
    }

    /**
     * Method for getting company name
     * @return Company name
     */
    public String getCompanyName() {
        return getPrefs().getString(AppPreferences.Settings.USER_COMPANY_NAME.key(), "");
    }

    /**
     * Method for setting company name
     * @param companyName - Company name
     */
    public void setCompanyName(String companyName) {
        getPrefs().setSetting(AppPreferences.Settings.USER_COMPANY_NAME, companyName);
    }

    /**
     * Method for getting user bonus in
     * @return - User bonus in
     */
    public int getBonusIn() {
        return getPrefs().getInt(AppPreferences.Settings.USER_BONUS_IN.key(), 0);
    }

    /**
     * Method for setting user bonus in
     * @param bonusIn - User bonus in
     */
    public void setBonusIn(int bonusIn) {
        getPrefs().setSetting(AppPreferences.Settings.USER_BONUS_IN, bonusIn);
    }

    /**
     * Method for getting user bonus out
     * @return - User bonus out
     */
    public int getBonusOut() {
        return getPrefs().getInt(AppPreferences.Settings.USER_BONUS_OUT.key(), 0);
    }

    /**
     * Method for setting user bonus out
     * @param bonusOut - User bonus out
     */
    public void setBonusOut(int bonusOut) {
        getPrefs().setSetting(AppPreferences.Settings.USER_BONUS_OUT, bonusOut);
    }
}
