package com.a3dmorpher.login;

import android.content.Context;

/**
 * Created by ahextech on 7/3/18.
 */

public interface LoginInteractor {

    void authenticateUser(onAuthCompletedListener listener, String email, String password, Context context);

    void validateFields(String email, String password, onValidateFieldListener validateFieldListener);

    interface onAuthCompletedListener {
        void onAuthSuccess();

        void onAuthFailure(String status);

    }

    interface onValidateFieldListener {
        void onValidationSuccess();

        void onValidationFailure();
    }
}
