package com.a3dmorpher.signup;

/**
 * Created by ahextech on 8/3/18.
 */

public interface SignUpView {
    void showUserNameError(String username);

    void showNextButton();

    void onValidUserName();

    void showConnectionErrorMsg(String status);

    void showDialog();
}
