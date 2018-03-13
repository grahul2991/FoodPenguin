package com.a3dmorpher.POJO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahextech on 6/3/18.
 */

public class RestaurantResponseModelClass {
    List<RestaurantModelClass> restaurantList;


    public List<RestaurantModelClass> getRestaurantList(String response) {
        restaurantList = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray restaurantsArray = jsonObject.getJSONArray("restaurants");
            for (int i = 0; i < restaurantsArray.length(); i++) {
                RestaurantModelClass restaurantModelClass = new RestaurantModelClass();
                JSONObject restaurant = restaurantsArray.getJSONObject(i);
                JSONObject restaurantDetails = restaurant.getJSONObject("restaurant");
                String resName = restaurantDetails.getString("name");
                restaurantModelClass.setResName(resName);
                restaurantModelClass.setResID(restaurantDetails.getString("id"));
                restaurantModelClass.setResFeaturedPic(restaurantDetails.getString("featured_image"));
                restaurantModelClass.setResPicUrl(restaurantDetails.getString("thumb"));
                restaurantModelClass.setResCuisines(restaurantDetails.getString("cuisines"));

                JSONObject location = restaurantDetails.getJSONObject("location");
                restaurantModelClass.setResLocation(location.getString("locality_verbose"));
                JSONObject userRating = restaurantDetails.getJSONObject("user_rating");
                restaurantModelClass.setResRating(userRating.getString("aggregate_rating"));
                restaurantList.add(restaurantModelClass);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return restaurantList;
    }
}
