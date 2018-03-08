package com.a3dmorpher;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.theartofdev.edmodo.cropper.CropImage;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

    final int REQUEST_CODE_FOR_PICK_IMAGE = 100, CAMERA_REQUEST = 101;
    FloatingActionButton fab_select_profile_pic;
    CircleImageView img_profile_profile_pic;
    AlertDialog dialog, editDialog;
    TextView tvUserName, tvUserStatus, tvUserPhoneNumber;
    ImageView ivEditButton;
    CropImage.ActivityResult result;
    String IMAGE_USER = "users";
    String updatedProfilePic, userStatus;
    String profileImageUrl = null;
    private String userID;
    private EditText etUserName;
    private ProgressBar profileUpdateProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_profile);
//        getSupportActionBar().setTitle(R.string.profile);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeButtonEnabled(true);
//        init();
//        Bundle bundle = getIntent().getExtras();
//        String userPhoneNumber = null, userName = null;
//        if (bundle != null) {
//            profileImageUrl = bundle.getString("profilePic");
//            userName = bundle.getString("userName");
//            userStatus = bundle.getString("status");
//            userPhoneNumber = bundle.getString("phoneNumber");
//        }
//
//        Glide.with(this).load(profileImageUrl).
//                apply(RequestOptions.errorOf(R.drawable.no_image)).
//                into(img_profile_profile_pic);
//
//        new Handler().postDelayed(new Runnable() {
//                                      @Override
//                                      public void run() {
//                                          fab_select_profile_pic.show();
//                                      }
//                                  }, 700
//
//        );
//        tvUserName.setText(userName);
//        tvUserStatus.setText(userStatus);
//        tvUserPhoneNumber.setText(userPhoneNumber);
//
//    }
//
//    private void init() {
//        appUserReference = MyApplication.getInstance().getDatabaseReference().child("users");
//        fab_select_profile_pic = findViewById(R.id.fab_select_profile_pic);
//        fab_select_profile_pic.setOnClickListener(this);
//        img_profile_profile_pic = findViewById(R.id.img_profile_profile_pic);
//        tvUserName = findViewById(R.id.tv_UserName);
//        tvUserStatus = findViewById(R.id.tv_profile_status);
//        tvUserStatus.setOnClickListener(this);
//        tvUserPhoneNumber = findViewById(R.id.tv_phone_number);
//        ivEditButton = findViewById(R.id.iv_edit_button);
//        ivEditButton.setOnClickListener(this);
//        storageReference = FirebaseStorage.getInstance().getReference("profile_Images");
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        assert user != null;
//        userID = user.getUid();
//        profileUpdateProgress = findViewById(R.id.profile_progress_bar);
//
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                onBackPressed();
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    @Override
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.fab_select_profile_pic:
//                showCustomAlertDialog(this, profileImageUrl);
//                break;
//            case R.id.linear_custom_view_gallery:
//                Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                startActivityForResult(pickIntent, REQUEST_CODE_FOR_PICK_IMAGE);
//                break;
//            case R.id.linear_custom_view_camera:
//                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(cameraIntent, CAMERA_REQUEST);
//                break;
//            case R.id.linear_custom_view_remove_photo:
//                appUserReference.child(userID).child("profileImageUrl").setValue(null);
//                setProfilePic("", "Profile Picture Deleted");
//                dialog.cancel();
//                break;
//            case R.id.iv_edit_button:
//                showEditUserNameDialog(this);
//                break;
//
//            case R.id.btn_ok:
//                String userName = etUserName.getText().toString();
//                if (userName.trim().length() > 0) {
//                    tvUserName.setText(userName);
//                    appUserReference.child(userID).child("name").setValue(userName);
//                } else {
//                    Toast.makeText(this, "User name cannot be left empty", Toast.LENGTH_SHORT).show();
//                }
//                editDialog.cancel();
//                break;
//            case R.id.tv_profile_status:
//                Intent intent = new Intent(this, ChangeStatusActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                intent.putExtra("status", userStatus);
//                startActivity(intent);
//                finish();
//                break;
//            default:
//                break;
//
//        }
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (data != null) {
//            if (resultCode == RESULT_OK) {
//                switch (requestCode) {
//                    case REQUEST_CODE_FOR_PICK_IMAGE:
//                        String uri = String.valueOf(data.getData());
//                        CropImage.activity(Uri.parse(uri))
//                                .setAspectRatio(1, 1)
//                                .start(this);
//                        dialog.cancel();
//                        break;
//                    case CAMERA_REQUEST:
//                        Bitmap photo = (Bitmap) data.getExtras().get("data");
//                        // CALL THIS METHOD TO GET THE URI FROM THE BITMAP
//                        String cameraUri = String.valueOf(getImageUri(getApplicationContext(), photo));
//                        CropImage.activity(Uri.parse(cameraUri))
//                                .setAspectRatio(1, 1)
//                                .start(this);
//                        dialog.cancel();
//                        break;
//                    case CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE:
//                        result = CropImage.getActivityResult(data);
//                        Uri resultUri = result.getUri();
//                        updateProfilePic(resultUri);
//                        break;
//                }
//            }
//        }
//
//    }
//
//    public Uri getImageUri(Context inContext, Bitmap inImage) {
//        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
//        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
//        return Uri.parse(path);
//    }
//
//
//    private void updateProfilePic(Uri uri) {
//        if (uri != null) {
//            profileUpdateProgress.setVisibility(View.VISIBLE);
//            StorageReference reference = storageReference.child(IMAGE_USER + System.currentTimeMillis() + "." + String.valueOf(uri));
//            reference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                @SuppressLint("CheckResult")
//                @Override
//                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                    if (taskSnapshot.getDownloadUrl() != null) {
//                        updatedProfilePic = taskSnapshot.getDownloadUrl().toString();
//                        HashMap map = new HashMap();
//                        map.put(UserConstants.profileImageUrl, updatedProfilePic);
//                        appUserReference.child(userID).child("profileImageUrl").setValue(updatedProfilePic);
//                        profileUpdateProgress.setVisibility(View.GONE);
//                        setProfilePic(updatedProfilePic, "Profile picture changed");
//                    }
//                }
//            });
//        }
//
//    }
//
//    void setProfilePic(String picUrl, String message) {
//        Glide.with(this)
//                .load(picUrl)
//                .apply(RequestOptions.placeholderOf(R.drawable.no_image))
//                .into(img_profile_profile_pic);
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
//    }
//
//
//    public void showCustomAlertDialog(Context context, String profileImageUrl) {
//        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
//                context);
//        LayoutInflater inflater = (LayoutInflater) context
//                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        assert inflater != null;
//        View view = inflater.inflate(R.layout.custom_view_for_choose_image_dialog, null);
//        LinearLayout linear_custom_view_gallery = view.findViewById(R.id.linear_custom_view_gallery);
//        LinearLayout linear_custom_view_camera = view.findViewById(R.id.linear_custom_view_camera);
//        LinearLayout linear_custom_view_remove_photo = view.findViewById(R.id.linear_custom_view_remove_photo);
//        if (profileImageUrl == null || profileImageUrl.equals("")) {
//            linear_custom_view_remove_photo.setVisibility(View.GONE);
//        }
//        linear_custom_view_gallery.setOnClickListener(this);
//        linear_custom_view_camera.setOnClickListener(this);
//        linear_custom_view_remove_photo.setOnClickListener(this);
//        alertDialogBuilder.setView(view);
//        dialog = alertDialogBuilder.create();
//        dialog.show();
//    }
//
//    private void showEditUserNameDialog(Context context) {
//        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
//                context);
//        LayoutInflater inflater = (LayoutInflater) context
//                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        assert inflater != null;
//        View view = inflater.inflate(R.layout.custom_edit_user_name, null);
//        Button changeUserNameBtn = view.findViewById(R.id.btn_ok);
//        etUserName = view.findViewById(R.id.et_userName);
//        changeUserNameBtn.setOnClickListener(this);
//        alertDialogBuilder.setView(view);
//        editDialog = alertDialogBuilder.create();
//        editDialog.show();
//
//    }
//
//    @Override
//    public void onBackPressed() {
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                ProfileActivity.super.onBackPressed();
//            }
//        }, 200);
//        fab_select_profile_pic.hide();
    }
}
