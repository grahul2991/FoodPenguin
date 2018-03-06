package com.a3dmorpher.foodpenguin;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

/**
 * Created by Asus on 3/4/2018.
 */

public class RestaurantsListAdapter extends RecyclerView.Adapter<RestaurantsListAdapter.RestaurantsViewHolder> {
    private Context context;
    private List<RestaurantListModelClass> restaurantList;
    private HomeScreenContracts homeScreenView;
    private String resID;

    public RestaurantsListAdapter(Context context,
                                  List<RestaurantListModelClass> restaurantList, HomeScreenContracts homeScreenView) {
        this.context = context;
        this.restaurantList = restaurantList;
        this.homeScreenView = homeScreenView;
    }

    @NonNull
    @Override
    public RestaurantsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context.getApplicationContext())
                .inflate(R.layout.restaurant_list_item, parent, false);
        return new RestaurantsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantsViewHolder holder, int position) {
        RestaurantListModelClass modelClass = restaurantList.get(position);
        holder.tvRating.setText(modelClass.getResRatings());
        holder.tvResName.setText(modelClass.getResName());
        holder.tvLocation.setText(modelClass.getResLocation());
        holder.tvCuisines.setText(modelClass.getResCuisines());
        Glide.with(context).load(modelClass.getResPhotoURL())
                .apply(RequestOptions.errorOf(R.drawable.elon_musk)).
                into(holder.ivPhoto);
        resID = modelClass.getResID();
    }

    @Override
    public int getItemCount() {
//        return restaurantList.size();
        return restaurantList.size();
    }

    public void refreshAdapter(List<RestaurantListModelClass> restaurantList) {
        this.restaurantList=restaurantList;
        homeScreenView.refreshRecyclerView();
    }

    public class RestaurantsViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPhoto;
        TextView tvRating, tvResName, tvLocation, tvCuisines;

        public RestaurantsViewHolder(View itemView) {
            super(itemView);
            ivPhoto = itemView.findViewById(R.id.iv_photo);
            tvRating = itemView.findViewById(R.id.tv_rating);
            tvResName = itemView.findViewById(R.id.tv_restaurant_name);
            tvLocation = itemView.findViewById(R.id.tv_location);
            tvCuisines = itemView.findViewById(R.id.tv_cuisines);
        }
    }
}
