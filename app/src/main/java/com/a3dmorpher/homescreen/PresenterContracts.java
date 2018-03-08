package com.a3dmorpher.homescreen;

/**
 * Created by ahextech on 6/3/18.
 */

public interface PresenterContracts {
    void loadFirstPage(int startIndex, int count);

    void loadNextPage(int startIndex, int count);

    void onRestaurantClicked(String resID);

    void showError(String message);
}

