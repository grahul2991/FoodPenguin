package com.a3dmorpher.signup;

/**
 * Created by ahextech on 8/3/18.
 */

public interface InteractorInterface {

    void checkUserNameAvailability(onUserNameAvailability listener,
                                   String email);

    interface onUserNameAvailability {
        void onUserNameAvailable();

        void onUserNameUnavailable(String username);

        void onConnectionStatus(String status);
    }
}
