package com.a3dmorpher.login;


/**
 * Created by ahextech on 7/3/18.
 */

public class LoginInteractorImpl implements LoginInteractor {
    private onAuthCompletedListener listener;

    @Override
    public void authenticateUser(onAuthCompletedListener listener, String email, String password) {
        this.listener = listener;

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
