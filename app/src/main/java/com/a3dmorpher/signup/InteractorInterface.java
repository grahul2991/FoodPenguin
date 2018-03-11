package com.a3dmorpher.signup;

import android.content.Context;

/**
 * Created by ahextech on 8/3/18.
 */

public interface InteractorInterface {

    void checkUserNameAvailability(onUserNameAvailability listener,
                                   String email, Context context);

    interface onUserNameAvailability {
        void onUserNameAvailable();

        void onUserNameUnavailable(String username);

        void onConnectionStatus(String status);
    }
}
