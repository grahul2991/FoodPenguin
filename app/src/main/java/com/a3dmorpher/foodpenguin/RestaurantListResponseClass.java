package com.a3dmorpher.foodpenguin;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asus on 3/4/2018.
 */

public class RestaurantListResponseClass {
    public List<RestaurantListModelClass> restaurantListModelClasses(String response) {
        List<RestaurantListModelClass> restaurantList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("restaurants");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject restaurant = jsonArray.getJSONObject(i);
                RestaurantListModelClass modelClass = new RestaurantListModelClass();
                modelClass.setResID(restaurant.getString("id"));
                modelClass.setResName(restaurant.getString("name"));
                modelClass.setResCuisines(restaurant.getString("cuisines"));
                modelClass.setResPhotoURL(restaurant.getString("thumb"));
                modelClass.setResLocation("Bangalore");
                modelClass.setResRatings("4.4");
                restaurantList.add(modelClass);
            }
        } catch (Exception e) {
            Log.d("Exception", e.getMessage());
        }
        return restaurantList;
    }
}