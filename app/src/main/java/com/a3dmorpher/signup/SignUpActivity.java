package com.a3dmorpher.signup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.a3dmorpher.foodpenguin.R;
import com.a3dmorpher.homescreen.HomeScreenActivity;
import com.a3dmorpher.login.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpActivity extends AppCompatActivity implements ChangeFragments, SignUpCompleteInterface {
    @BindView(R.id.frameLayout_container)
    FrameLayout container;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        initRegistrationPage();
    }

    private void initRegistrationPage() {
        transaction.replace(R.id.frameLayout_container, new RegistrationFragment());
        transaction.commit();
    }

    @Override
    public void loadFragments(String username, String password) {
        SignUpDetailsFragment detailsFragment = new SignUpDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("username", username);
        bundle.putString("password", password);
        detailsFragment.setArguments(bundle);
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout_container, detailsFragment);
        transaction.commit();

    }

    @Override
    public void startLoginActivity() {
        intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    public void startHomeActivity() {
        intent = new Intent(this, HomeScreenActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();

    }

    @Override
    public void signUpCompleted() {
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout_container, new SignUpCompleteFragment());
        transaction.commit();
    }
}
