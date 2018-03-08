package com.a3dmorpher.signup;

/**
 * Created by ahextech on 8/3/18.
 */

public interface ChangeFragments {
    void loadFragments(String username, String password);

    void startLoginActivity();

    void startHomeActivity();
}
