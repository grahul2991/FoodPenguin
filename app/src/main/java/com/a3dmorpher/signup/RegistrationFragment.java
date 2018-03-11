package com.a3dmorpher.signup;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.a3dmorpher.foodpenguin.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ahextech on 8/3/18.
 */

public class RegistrationFragment extends Fragment implements SignUpView, View.OnClickListener {
    @BindView(R.id.username_layout)
    LinearLayout userNameLayout;
    @BindView(R.id.et_username)
    EditText etUserName;
    @BindView(R.id.password_layout)
    LinearLayout passwordLayout;
    @BindView(R.id.et_password)
    EditText etCreatePwd;
    @BindView(R.id.iv_show_password)
    ImageView ivShowPassword;
    @BindView(R.id.tv_username_error)
    TextView tvUserNameError;
    @BindView(R.id.layout_btn_next)
    RelativeLayout btnNext;
    @BindView(R.id.tv_btn_text)
    TextView tvButtonText;
    @BindView(R.id.tv_log_in)
    TextView tvLogIn;
    @BindView(R.id.tv_choose_username)
    TextView tvPageTitle;
    @BindView(R.id.tv_fragment_icon)
    TextView tvAppTitle;
    @BindView(R.id.btn_proceed)
    Button btnProceed;
    private Context context;
    private String email, password;
    private SignUpPresenterImpl signUpPresenter;
    private ChangeFragments myInterface;

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            tvUserNameError.setVisibility(View.INVISIBLE);
            userNameLayout.setBackground(getResources()
                    .getDrawable(R.drawable.pwd_creation_bg));
        }

        @Override
        public void afterTextChanged(Editable s) {
            validateUserName();
            validatePassword();
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registration, container, false);
        ButterKnife.bind(this, view);
        Typeface typeface = Typeface.createFromAsset(getResources().getAssets(), "fonts/coco_biker.ttf");
        tvAppTitle.setTypeface(typeface);

        context = getContext();
        myInterface = (ChangeFragments) getContext();
        this.context = getContext();
        signUpPresenter = new SignUpPresenterImpl(this);
        etUserName.addTextChangedListener(textWatcher);
        etCreatePwd.addTextChangedListener(textWatcher);
        tvLogIn.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        btnProceed.setOnClickListener(this);
        validateUserName();
        validatePassword();
        return view;
    }

    private void validatePassword() {
        password = etCreatePwd.getText().toString();
        if (password.equals("")) {
            btnProceed.setEnabled(false);
            btnProceed.
                    setTextColor(getResources().getColor(R.color.LoginDisabledTextColor));
        } else {
            btnProceed.setEnabled(true);
            btnProceed.
                    setTextColor(getResources().getColor(R.color.LoginEnabledTextColor));

        }
    }

    private void validateUserName() {
        email = etUserName.getText().toString();
        if (email.equals("")) {
            btnNext.setEnabled(false);
            tvButtonText.setTextColor(getResources().getColor(R.color.LoginDisabledTextColor));
        } else {
            btnNext.setEnabled(true);
            tvButtonText.setTextColor(getResources().getColor(R.color.LoginEnabledTextColor));

        }
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void showUserNameError(String username) {
        String errorMessage = username + " " + getResources().getString(R.string.username_error);
        tvUserNameError.setText(errorMessage);
        tvUserNameError.setVisibility(View.VISIBLE);
        userNameLayout
                .setBackground(getResources().getDrawable(R.drawable.username_background));
        btnNext.setEnabled(false);
        tvButtonText.setTextColor(getResources().getColor(R.color.LoginDisabledTextColor));
    }

    @Override
    public void showNextButton() {

    }

    @Override
    public void onValidUserName() {
        tvUserNameError.setVisibility(View.INVISIBLE);
        passwordLayout.setVisibility(View.VISIBLE);
        userNameLayout.setVisibility(View.GONE);
        btnNext.setVisibility(View.GONE);
        btnProceed.setVisibility(View.VISIBLE);
        tvPageTitle.setText(getResources().getText(R.string.create_password));

    }

    @Override
    public void showConnectionErrorMsg(String status) {

    }

    @Override
    public void showDialog() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_log_in:
                myInterface.startLoginActivity();
                break;
            case R.id.layout_btn_next:
                signUpPresenter.validateUserName(email, getActivity());
                break;
            case R.id.btn_proceed:
                myInterface.loadProfileActivity(email, password);
//                signUpPresenter.validateUserName(email, password, username);
                break;
        }
    }
}
