package com.a3dmorpher.homescreen;

import com.a3dmorpher.POJO.RestaurantModelClass;

/**
 * Created by ahextech on 6/3/18.
 */

public interface PresenterContracts {
    void loadFirstPage(int startIndex, int count);

    void loadNextPage(int startIndex, int count);

    void onRestaurantClicked(RestaurantModelClass modelClass);

    void showError(String message);
}

