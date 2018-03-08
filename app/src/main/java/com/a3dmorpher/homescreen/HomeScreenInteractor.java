package com.a3dmorpher.homescreen;

import android.content.Context;

import com.a3dmorpher.POJO.RestaurantModelClass;
import com.a3dmorpher.POJO.RestaurantResponseModelClass;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ahextech on 6/3/18.
 */

public class HomeScreenInteractor implements InteractorContracts {
    private onCompleteListener listener;
    private List<RestaurantModelClass> restaurantList;

    @Override
    public List<RestaurantModelClass> fetchData(final onCompleteListener listener, int startIndex, int count, Context context) {
        this.listener = listener;
        restaurantList = new ArrayList<>();
        String url
                = "https://developers.zomato.com/api/v2.1/search?entity_id=4&entity_type=city" + "&start=" + startIndex + "&count=" + count;

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                restaurantList = new RestaurantResponseModelClass().getRestaurantList(response);
                listener.onFetchSuccessful(restaurantList);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onFailure();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("user-key", "d8ebeda9b811b932c5ac18a8edb7729b");
                return params;
            }
        };
        requestQueue.add(request);


        return restaurantList;
    }

}
