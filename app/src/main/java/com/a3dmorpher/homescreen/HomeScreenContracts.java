package com.a3dmorpher.homescreen;


import com.a3dmorpher.POJO.RestaurantModelClass;

import java.util.List;

/**
 * Created by ahextech on 6/3/18.
 */

public interface HomeScreenContracts {
    void showLoading();

    void refreshViews(List<RestaurantModelClass> restaurantList);

    void initializeRecyclerView();

    void showError(String message);

    void onRestaurantSelected(RestaurantModelClass modelClass);
}
