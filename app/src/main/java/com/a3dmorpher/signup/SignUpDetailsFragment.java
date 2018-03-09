package com.a3dmorpher.signup;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.a3dmorpher.foodpenguin.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ahextech on 8/3/18.
 */

public class SignUpDetailsFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.et_first_name)
    EditText etFirstName;
    @BindView(R.id.et_last_name)
    EditText etLastName;
    @BindView(R.id.et_phone_number)
    EditText etPhoneNumber;
    @BindView(R.id.btn_complete)
    Button btnCompleteReg;
    private String username, password, firstName, lastName, phoneNumber;
    private SignUpCompleteInterface signUpCompleteInterface;
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            validateFields();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        ButterKnife.bind(this, view);
        signUpCompleteInterface = (SignUpCompleteInterface) getContext();
        Bundle bundle = getArguments();
        if (bundle != null) {
            username = bundle.getString("username");
            password = bundle.getString("password");
        }
        etFirstName.addTextChangedListener(textWatcher);
        etLastName.addTextChangedListener(textWatcher);
        validateFields();
        btnCompleteReg.setOnClickListener(this);
        return view;
    }

    private void validateFields() {
        firstName = etFirstName.getText().toString();
        lastName = etLastName.getText().toString();
        phoneNumber = etPhoneNumber.getText().toString();
        if (firstName.equals("") || lastName.equals("") || phoneNumber.equals("")) {
            btnCompleteReg.setEnabled(false);
            btnCompleteReg.setTextColor(getResources().
                    getColor(R.color.LoginDisabledTextColor));
        } else {
            btnCompleteReg.setEnabled(true);
            btnCompleteReg.setTextColor(getResources().
                    getColor(R.color.LoginEnabledTextColor));

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_complete:
                signUpCompleteInterface.signUpCompleted();
                break;
        }
    }
}
