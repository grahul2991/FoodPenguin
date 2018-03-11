package com.a3dmorpher;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.a3dmorpher.login.LoginActivity;

import java.util.HashMap;

import static com.a3dmorpher.Utils.APP_PREF_NAME;
import static com.a3dmorpher.Utils.IS_LOGGED_IN;
import static com.a3dmorpher.Utils.IS_REGISTERED;
import static com.a3dmorpher.Utils.KEY_EMAIL;
import static com.a3dmorpher.Utils.KEY_FIRST_NAME;
import static com.a3dmorpher.Utils.KEY_LAST_NAME;
import static com.a3dmorpher.Utils.KEY_PASSWORD;
import static com.a3dmorpher.Utils.KEY_PHONE_NUMBER;
import static com.a3dmorpher.Utils.KEY_PROFILE_PIC;
import static com.a3dmorpher.Utils.SHARED_PREF_MODE;

/**
 * Created by ahextech on 9/3/18.
 */

public class SessionManager {
    private Context context;
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;

    public SessionManager(Context context) {
        this.context = context;
        sharedPref = context.getSharedPreferences(APP_PREF_NAME, SHARED_PREF_MODE);
        editor = sharedPref.edit();
    }

    public void createSession(String email, String password) {
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_PASSWORD, password);
        editor.putBoolean(IS_LOGGED_IN, true);
        editor.commit();
    }

    public void checkLogin() {
        if (!this.isLoggedIn()) {
            // user is not logged in redirect him to Login Activity
            redirectUser();
        }
    }




    public void logOutUser() {
        editor.clear();
        editor.commit();
        redirectUser();

    }

    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<>();
        // user emailID
        user.put(KEY_EMAIL, sharedPref.getString(KEY_EMAIL, null));

        // user password
        user.put(KEY_PASSWORD, sharedPref.getString(KEY_PASSWORD, null));

        user.put(KEY_FIRST_NAME, sharedPref.getString(KEY_FIRST_NAME, null));
        user.put(KEY_LAST_NAME, sharedPref.getString(KEY_LAST_NAME, null));
        user.put(KEY_PROFILE_PIC, sharedPref.getString(KEY_PROFILE_PIC, null));
        user.put(KEY_PHONE_NUMBER, sharedPref.getString(KEY_PHONE_NUMBER, null));

        // return user
        return user;
    }

    private void redirectUser() {

        Intent i = new Intent(context, LoginActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(i);
    }

    public boolean isLoggedIn() {
        return sharedPref.getBoolean(IS_LOGGED_IN, false);
    }

}
