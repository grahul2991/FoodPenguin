package com.a3dmorpher.signup;


/**
 * Created by ahextech on 8/3/18.
 */

public class SignUpInteractorImpl implements InteractorInterface {
    private onUserNameAvailability listener;


    @Override
    public void checkUserNameAvailability(onUserNameAvailability listener, String email) {
        this.listener = listener;
        if (email.trim().length() > 0) {
            if (email.equals("rahul@woohoo.com")) {
                listener.onUserNameAvailable();
            } else {
                listener.onUserNameUnavailable(email);
            }
        }

    }


}
