package com.a3dmorpher;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.a3dmorpher.POJO.ReviewsJsonParser;
import com.a3dmorpher.POJO.ReviewsModelClass;
import com.a3dmorpher.foodpenguin.R;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ahextech on 13/3/18.
 */

public class ReviewFragment extends Fragment {
    List<ReviewsModelClass> reviewList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragement_review, container, false);

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        String url = "https://developers.zomato.com/api/v2.1/reviews?res_id=51040";// + resID;
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                reviewList = new ReviewsJsonParser().getReviews(response);
                int size = reviewList.size();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
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
        ListView listView = view.findViewById(R.id.lv_review);
        ReviewsListAdapter adapter = new ReviewsListAdapter(getContext());
        adapter.updateProducts(reviewList);
        listView.setAdapter(adapter);
        return view;
    }

}
