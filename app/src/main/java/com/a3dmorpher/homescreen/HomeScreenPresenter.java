package com.a3dmorpher.homescreen;

import android.content.Context;


import com.a3dmorpher.POJO.RestaurantModelClass;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahextech on 6/3/18.
 */

public class HomeScreenPresenter implements PresenterContracts, HomeScreenInteractor.onCompleteListener {
    List<RestaurantModelClass> nextList;
    private HomeScreenContracts view;
    private InteractorContracts interactor;
    private List<RestaurantModelClass> restaurantsList;

    public HomeScreenPresenter(HomeScreenContracts view) {
        this.view = view;
        interactor = new HomeScreenInteractor();
        restaurantsList = new ArrayList<>();
    }

    @Override
    public void loadFirstPage(int startIndex, int count) {
        restaurantsList = interactor.fetchData(this, startIndex, count, (Context) view);
        //view.refreshViews(restaurantList);
    }

    @Override
    public void loadNextPage(int startIndex, int count) {
        nextList = interactor.fetchData(this, startIndex, count, (Context) view);
    }

    @Override
    public void onRestaurantClicked(String resID) {
        view.onRestaurantSelected(resID);
    }

    @Override
    public void showError(String message) {
        view.showError(message);
    }

    @Override
    public void onFetchSuccessful(List<RestaurantModelClass> restaurantList) {
        restaurantsList.addAll(restaurantList);
        view.refreshViews(restaurantsList);
    }

    @Override
    public void onFailure() {
        view.showError("Operation Failed");
    }
}
