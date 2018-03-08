package com.a3dmorpher.homescreen;

import android.content.Context;

import com.a3dmorpher.POJO.RestaurantModelClass;

import java.util.List;

/**
 * Created by ahextech on 6/3/18.
 */

public interface InteractorContracts {

    List<RestaurantModelClass> fetchData(onCompleteListener listener, int startIndex, int count, Context context);
//    List<RestaurantModelClass> onPageScrolled(int startIndex, int count);

    interface onCompleteListener {
        void onFetchSuccessful(List<RestaurantModelClass> restaurantList);

        void onFailure();

    }
}
