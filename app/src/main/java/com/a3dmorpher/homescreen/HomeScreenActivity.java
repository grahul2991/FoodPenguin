package com.a3dmorpher.homescreen;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.a3dmorpher.POJO.RestaurantModelClass;
import com.a3dmorpher.SessionManager;
import com.a3dmorpher.foodpenguin.R;

import java.util.List;

public class HomeScreenActivity extends AppCompatActivity implements HomeScreenContracts {
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
        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
        recyclerView = findViewById(R.id.main_home_recycler);
        mProgressbar = findViewById(R.id.main_home_progress);
        presenter = new HomeScreenPresenter(this);
        initializeRecyclerView();
        presenter.loadFirstPage(startIndex, count);
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
    public void onRestaurantSelected(String resID) {
        Toast.makeText(this, resID, Toast.LENGTH_SHORT).show();
    }
}
