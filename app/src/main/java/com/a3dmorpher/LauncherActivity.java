package com.a3dmorpher;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.a3dmorpher.foodpenguin.R;
import com.a3dmorpher.homescreen.HomeScreenActivity;
import com.a3dmorpher.login.LoginActivity;

public class LauncherActivity extends AppCompatActivity {
    SessionManager sessionManager;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        sessionManager = new SessionManager(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (sessionManager.isLoggedIn()) {
                    intent = new Intent(getApplicationContext(), HomeScreenActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                } else {
                    intent = new Intent(getApplicationContext(), LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }
            }
        }, 200);
    }
}
