package com.a3dmorpher.login;


import android.content.Context;

import com.a3dmorpher.SessionManager;

import java.util.HashMap;

import static com.a3dmorpher.Utils.KEY_EMAIL;
import static com.a3dmorpher.Utils.KEY_PASSWORD;

/**
 * Created by ahextech on 7/3/18.
 */

public class LoginInteractorImpl implements LoginInteractor {
    private onAuthCompletedListener listener;
    private SessionManager sessionManager;
    private Context context;
    private String emailID, userPassword;

    @Override
    public void authenticateUser(onAuthCompletedListener listener, String email, String password, Context context) {
        this.listener = listener;
        this.context = context;
        sessionManager = new SessionManager(context);
        HashMap<String, String> userDetails = sessionManager.getUserDetails();
        if (userDetails != null) {
            emailID = userDetails.get(KEY_EMAIL);
            userPassword = userDetails.get(KEY_PASSWORD);
        }
        if (!emailID.equals("") && !userPassword.equals("")) {
            if (email.matches(emailID) && password.matches(userPassword)) {
                sessionManager.createSession(email, password);
                listener.onAuthSuccess();
            }

        } else {
            listener.onAuthFailure("User is not registered");
        }

    }

    @Override
    public void validateFields(String email, String password, onValidateFieldListener validateFieldListener) {
        if (email.equals("") || password.equals("")) {
            validateFieldListener.onValidationFailure();
        } else {
            validateFieldListener.onValidationSuccess();
        }
    }
}
