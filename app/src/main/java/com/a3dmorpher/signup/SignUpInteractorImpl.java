package com.a3dmorpher.signup;


import android.content.Context;

import com.a3dmorpher.DatabaseHelper;

/**
 * Created by ahextech on 8/3/18.
 */

public class SignUpInteractorImpl implements InteractorInterface {
    private onUserNameAvailability listener;
    private DatabaseHelper databaseHelper;


    @Override
    public void checkUserNameAvailability(onUserNameAvailability listener, String email, Context context) {
        this.listener = listener;
        databaseHelper = new DatabaseHelper(context);
        if (databaseHelper.checkUser(email)) {
            listener.onUserNameUnavailable(email);
        } else {
            listener.onUserNameAvailable();
        }

    }
}
