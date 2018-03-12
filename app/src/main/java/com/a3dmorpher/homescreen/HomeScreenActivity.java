package com.a3dmorpher.homescreen;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.a3dmorpher.POJO.RestaurantModelClass;
import com.a3dmorpher.SessionManager;
import com.a3dmorpher.foodpenguin.R;
import com.a3dmorpher.resDetails.DetailsActivity;

import java.io.Serializable;
import java.util.List;

public class HomeScreenActivity extends AppCompatActivity implements HomeScreenContracts, NavigationView.OnNavigationItemSelectedListener {
    private static final int count = 20;
    private RecyclerView recyclerView;
    private ProgressBar mProgressbar;
    private RestaurantsListAdapter adapter;
    private HomeScreenPresenter presenter;
    private boolean isLoading = false;
    private int startIndex = 0;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("");
        TextView tvToolbarTitle = findViewById(R.id.tv_toolbar_title);
        Typeface typeface = Typeface.createFromAsset(getResources().getAssets(), "fonts/coco_biker.ttf");
        tvToolbarTitle.setTypeface(typeface);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
        recyclerView = findViewById(R.id.main_home_recycler);
        mProgressbar = findViewById(R.id.main_home_progress);
        presenter = new HomeScreenPresenter(this);
        initializeRecyclerView();
        presenter.loadFirstPage(startIndex, count);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void showLoading() {
        mProgressbar.setVisibility(View.VISIBLE);

    }

    @Override
    public void refreshViews(List<RestaurantModelClass> restaurantList) {
        mProgressbar.setVisibility(View.GONE);
        adapter.refreshAdapter(restaurantList);
    }


    @Override
    public void initializeRecyclerView() {
        adapter = new RestaurantsListAdapter(this, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new PaginationScrollListener(layoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                startIndex = startIndex + 20;
                adapter.addLoadingFooter();
                // mocking network delay for API call
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        presenter.loadNextPage(startIndex, count);
                        isLoading = false;
                        adapter.removeLoadingFooter();

                    }
                }, 1000);
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });


    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRestaurantSelected(RestaurantModelClass modelClass) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("resDetails", (Serializable) modelClass);
        startActivity(intent);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
