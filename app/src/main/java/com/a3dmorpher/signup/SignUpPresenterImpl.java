package com.a3dmorpher.signup;

/**
 * Created by ahextech on 8/3/18.
 */

public class SignUpPresenterImpl implements PresenterInterface, SignUpInteractorImpl.onUserNameAvailability {
    private SignUpView view;
    private InteractorInterface interactor;

    public SignUpPresenterImpl(SignUpView view) {
        this.view = view;
        interactor = new SignUpInteractorImpl();
    }

    @Override
    public void validateUserName(String email) {
        interactor.checkUserNameAvailability(this, email);
    }


    @Override
    public void onUserNameAvailable() {
        view.onValidUserName();
    }

    @Override
    public void onUserNameUnavailable(String username) {
        view.showUserNameError(username);
    }

    @Override
    public void onConnectionStatus(String status) {
        view.showConnectionErrorMsg(status);
    }
}
