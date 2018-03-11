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

public class SignUpActivity extends AppCompatActivity implements ChangeFragments {
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
    public void loadProfileActivity(String email, String password) {
        intent = new Intent(this, SignUpDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("email", email);
        bundle.putString("password", password);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();

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
}
