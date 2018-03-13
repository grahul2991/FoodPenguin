package com.a3dmorpher.resDetails;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.a3dmorpher.MenuActivity;
import com.a3dmorpher.POJO.RestaurantModelClass;
import com.a3dmorpher.foodpenguin.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity {
    private static final String TAG = "DetailsActivity";
    @BindView(R.id.collapsingToolbar)
    CollapsingToolbarLayout ctLayout;
    @BindView(R.id.iv_res_pic)
    ImageView ivResPic;
    @BindView(R.id.res_name)
    TextView tvResName;
    @BindView(R.id.res_address)
    TextView tvAddress;
    @BindView(R.id.res_cuisines)
    TextView tvCuisines;
    @BindView(R.id.res_ratings)
    TextView tvRatings;
    @BindView(R.id.tv_menu)
    Button btnMenu;
    private String resID;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        RestaurantModelClass restaurantModelClass = (RestaurantModelClass) getIntent().
                getSerializableExtra("resDetails");
        String resName = restaurantModelClass.getResName();
        ctLayout.setTitle("");
        String picURL = restaurantModelClass.getResFeaturedPic();
        resID = restaurantModelClass.getResID();
        if (picURL != null) {
            Glide.with(this)
                    .load(picURL)
                    .apply(RequestOptions.errorOf(R.drawable.ic_launcher_background))
                    .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)).
                    into(ivResPic);
        }
        tvResName.setText(restaurantModelClass.getResName());
        tvAddress.setText(restaurantModelClass.getResLocation());
        tvCuisines.setText(restaurantModelClass.getResCuisines());
        tvRatings.setText(restaurantModelClass.getResRating());
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MenuActivity.class));
            }
        });


    }

}
