package com.a3dmorpher;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

import static com.a3dmorpher.Utils.IS_REGISTERED;
import static com.a3dmorpher.Utils.KEY_EMAIL;
import static com.a3dmorpher.Utils.KEY_FIRST_NAME;
import static com.a3dmorpher.Utils.KEY_LAST_NAME;
import static com.a3dmorpher.Utils.KEY_PASSWORD;
import static com.a3dmorpher.Utils.KEY_PHONE_NUMBER;
import static com.a3dmorpher.Utils.KEY_PROFILE_PIC;
import static com.a3dmorpher.Utils.REG_PREF_NAME;
import static com.a3dmorpher.Utils.SHARED_PREF_MODE;

/**
 * Created by Asus on 3/10/2018.
 */

public class RegistrationManager {
    private SharedPreferences.Editor regEditor;
    private SharedPreferences registrationSharedPref;

    public RegistrationManager(Context context) {
        registrationSharedPref = context.getSharedPreferences(REG_PREF_NAME, SHARED_PREF_MODE);
        regEditor = registrationSharedPref.edit();
    }

    public void registerUser(String email, String password, String first_name,
                             String last_name, String phone_number, String profile_pic) {
        regEditor.putString(KEY_EMAIL, email);
        regEditor.putString(KEY_PASSWORD, password);
        regEditor.putString(KEY_FIRST_NAME, first_name);
        regEditor.putString(KEY_LAST_NAME, last_name);
        regEditor.putString(KEY_PHONE_NUMBER, phone_number);
        regEditor.putString(KEY_PROFILE_PIC, profile_pic);
        regEditor.putBoolean(IS_REGISTERED, true);
        regEditor.commit();
    }

    public HashMap<String, String> getRegistrationDetails() {
        HashMap<String, String> map = new HashMap<>();
        map.put(KEY_EMAIL, registrationSharedPref.getString(KEY_EMAIL, null));
        map.put(KEY_PASSWORD, registrationSharedPref.getString(KEY_PASSWORD, null));
        return map;
    }

    public boolean isRegistered() {
        return registrationSharedPref.getBoolean(IS_REGISTERED, false);
    }
}
