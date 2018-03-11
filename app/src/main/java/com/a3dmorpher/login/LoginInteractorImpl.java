package com.a3dmorpher.login;


import android.content.Context;

import com.a3dmorpher.DatabaseHelper;
import com.a3dmorpher.POJO.User;
import com.a3dmorpher.SessionManager;

import java.util.HashMap;
import java.util.List;

/**
 * Created by ahextech on 7/3/18.
 */

public class LoginInteractorImpl implements LoginInteractor {
    private onAuthCompletedListener listener;
    private SessionManager sessionManager;
    private Context context;
    private String emailID, userPassword;
    private HashMap<String, String> userCredentials;

    @Override
    public void authenticateUser(onAuthCompletedListener listener, String email, String password, Context context) {
        this.listener = listener;
        this.context = context;
        sessionManager = new SessionManager(context);
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        if (dbHelper.checkUser(email)) {
            if (dbHelper.checkUser(email, password)){
                sessionManager.isLoggedIn();
                listener.onAuthSuccess();
            }else{
                listener.onAuthFailure("Wrong Credentials");
            }
        } else {
//            List<User> list=dbHelper.getAllUser();
            listener.onUserNotRegistered();
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
