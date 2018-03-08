package com.a3dmorpher.login;

/**
 * Created by ahextech on 7/3/18.
 */

public interface LoginInteractor {

    void authenticateUser(onAuthCompletedListener listener, String email, String password);

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
