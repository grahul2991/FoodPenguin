package com.a3dmorpher.signup;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.a3dmorpher.foodpenguin.R;


/**
 * Created by ahextech on 8/3/18.
 */

public class SignUpCompleteFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup_complete, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final ChangeFragments myInterface = (ChangeFragments) getContext();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                assert myInterface != null;
                myInterface.startHomeActivity();
            }
        }, 2000);
    }
}
