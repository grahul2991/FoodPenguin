package com.a3dmorpher.resDetails;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.a3dmorpher.POJO.RestaurantModelClass;
import com.a3dmorpher.foodpenguin.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity {
    @BindView(R.id.collapsingToolbar)
    CollapsingToolbarLayout ctLayout;
    @BindView(R.id.iv_res_pic)
    ImageView ivResPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        RestaurantModelClass restaurantModelClass = (RestaurantModelClass) getIntent().
                getSerializableExtra("resDetails");
        String resName = restaurantModelClass.getResName();
        ctLayout.setTitle(resName);
        String picURL = restaurantModelClass.getResPicUrl();
        if (picURL != null) {
            Glide.with(this)
                    .load(picURL)
                    .apply(RequestOptions.errorOf(R.drawable.ic_launcher_background))
                    .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)).
                    into(ivResPic);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
