package com.a3dmorpher.signup;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.a3dmorpher.DatabaseHelper;
import com.a3dmorpher.POJO.User;
import com.a3dmorpher.foodpenguin.R;
import com.a3dmorpher.homescreen.HomeScreenActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.ByteArrayOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class SignUpDetailsActivity extends AppCompatActivity implements View.OnClickListener {
    final int REQUEST_CODE_FOR_PICK_IMAGE = 100, CAMERA_REQUEST = 101;
    @BindView(R.id.et_first_name)
    EditText etFirstName;
    @BindView(R.id.et_last_name)
    EditText etLastName;
    @BindView(R.id.et_phone_number)
    EditText etPhoneNumber;
    @BindView(R.id.btn_complete)
    Button btnCompleteReg;
    @BindView(R.id.fab_select_profile_pic)
    FloatingActionButton fab_select_profile_pic;
    @BindView(R.id.iv_profile_pic)
    CircleImageView img_profile_profile_pic;
    AlertDialog dialog;
    CropImage.ActivityResult result;
    DatabaseHelper dbHelper;
    private String email, password, firstName, lastName, phoneNumber, profilePicPath;
    private SignUpCompleteInterface signUpCompleteInterface;
    private User user;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_sign_up);
        ButterKnife.bind(this);
        new Handler().postDelayed(new Runnable() {
                                      @Override
                                      public void run() {
                                          fab_select_profile_pic.show();
                                      }
                                  }, 700

        );
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            email = bundle.getString("email");
            password = bundle.getString("password");
        }
        etFirstName.addTextChangedListener(textWatcher);
        etLastName.addTextChangedListener(textWatcher);
        etPhoneNumber.addTextChangedListener(textWatcher);
        validateFields();
        btnCompleteReg.setOnClickListener(this);
        fab_select_profile_pic.setOnClickListener(this);
        dbHelper = new DatabaseHelper(this);
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
                registerUser();
                Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show();
                startHomeActivity();
                break;
            case R.id.fab_select_profile_pic:
                showCustomAlertDialog(this, profilePicPath);
                break;
            case R.id.linear_custom_view_gallery:
                Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickIntent, REQUEST_CODE_FOR_PICK_IMAGE);
                break;
            case R.id.linear_custom_view_camera:
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
                break;
            case R.id.linear_custom_view_remove_photo:
                setProfilePic("");
                Toast.makeText(this, "Photo Removed", Toast.LENGTH_SHORT).show();
                dialog.cancel();
                break;
        }
    }

    private void startHomeActivity() {
        startActivity(new Intent(this, HomeScreenActivity.class));
        finish();
    }

    private void registerUser() {
        if (!dbHelper.checkUser(email, password)) {
            user = new User();
            user.setEmail(email);
            user.setPassword(password);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPhoneNumber(phoneNumber);
            user.setProfilePicPath(profilePicPath);
            dbHelper.addUser(user);
        }
    }

    public void showCustomAlertDialog(Context context, String profileImageUrl) {
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert inflater != null;
        View view = inflater.inflate(R.layout.custom_view_for_choose_image_dialog, null);
        LinearLayout linear_custom_view_gallery = view.findViewById(R.id.linear_custom_view_gallery);
        LinearLayout linear_custom_view_camera = view.findViewById(R.id.linear_custom_view_camera);
        LinearLayout linear_custom_view_remove_photo = view.findViewById(R.id.linear_custom_view_remove_photo);
        if (profileImageUrl == null || profileImageUrl.equals("")) {
            linear_custom_view_remove_photo.setVisibility(View.GONE);
        }
        linear_custom_view_gallery.setOnClickListener(this);
        linear_custom_view_camera.setOnClickListener(this);
        linear_custom_view_remove_photo.setOnClickListener(this);
        alertDialogBuilder.setView(view);
        dialog = alertDialogBuilder.create();
        dialog.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (resultCode == RESULT_OK) {
                switch (requestCode) {
                    case REQUEST_CODE_FOR_PICK_IMAGE:
                        String uri = String.valueOf(data.getData());
                        CropImage.activity(Uri.parse(uri))
                                .setAspectRatio(1, 1)
                                .start(this);
                        dialog.cancel();
                        break;
                    case CAMERA_REQUEST:
                        Bitmap photo = (Bitmap) data.getExtras().get("data");
                        // CALL THIS METHOD TO GET THE URI FROM THE BITMAP
                        String cameraUri = String.valueOf(getImagePath(getApplicationContext(), photo));
                        CropImage.activity(Uri.parse(cameraUri))
                                .setAspectRatio(1, 1)
                                .start(this);
                        dialog.cancel();
                        break;
                    case CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE:
                        result = CropImage.getActivityResult(data);
                        Uri resultUri = result.getUri();
                        setProfilePic(resultUri.toString());
                        break;
                }
            }
        }

    }

    private void setProfilePic(String path) {
        this.profilePicPath = path;
        Glide.with(this)
                .load(profilePicPath)
                .apply(RequestOptions.errorOf(R.drawable.no_image))
                .into(img_profile_profile_pic);
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
    }

    public String getImagePath(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return path;
    }
}
