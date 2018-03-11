package com.a3dmorpher.signup;

/**
 * Created by ahextech on 8/3/18.
 */

public interface ChangeFragments {
    void loadProfileActivity(String email, String password);

    void startLoginActivity();

    void startHomeActivity();
}
