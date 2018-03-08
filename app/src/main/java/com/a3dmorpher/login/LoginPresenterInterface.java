package com.a3dmorpher.login;

/**
 * Created by ahextech on 7/3/18.
 */

public interface LoginPresenterInterface {
    void onLoginButtonClicked(String email, String password);

    void validateLoginFields(String email, String password);

}
