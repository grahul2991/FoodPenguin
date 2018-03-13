package com.a3dmorpher.homescreen;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.a3dmorpher.POJO.RestaurantModelClass;
import com.a3dmorpher.foodpenguin.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahextech on 6/3/18.
 */

public class RestaurantsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int LOADING = 1;
    private static final int ITEM_TYPE = 0;
    private boolean isLoadingAdded = false;
    private Context context;
    private List<RestaurantModelClass> restaurantList;
    private HomeScreenPresenter presenter;

    public RestaurantsListAdapter(Context context, HomeScreenContracts view) {
        this.context = context;
        this.restaurantList = new ArrayList<>();
        presenter = new HomeScreenPresenter(view);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        View view;
        switch (viewType) {
            case ITEM_TYPE:
                view = LayoutInflater.from(context)
                        .inflate(R.layout.list_item, parent, false);
                viewHolder = new RestaurantViewHolder(view);
                break;
            case LOADING:
                view = LayoutInflater.from(context)
                        .inflate(R.layout.item_progress, parent, false);
                viewHolder = new LoadingFooterViewHolder(view);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final RestaurantModelClass restaurantModel = restaurantList.get(position);
        switch (getItemViewType(position)) {
            case ITEM_TYPE:
                final RestaurantViewHolder viewHolder = (RestaurantViewHolder) holder;
                viewHolder.tvResName.setText(restaurantModel.getResName());
                viewHolder.tvResCuisines.setText(restaurantModel.getResCuisines());
                viewHolder.tvResRating.setText(restaurantModel.getResRating());
                viewHolder.tvResLocation.setText(restaurantModel.getResLocation());
                Glide.with(context)
                        .load(restaurantModel.getResPicUrl())
                        .listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                viewHolder.mProgressbar.setVisibility(View.GONE);
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                viewHolder.mProgressbar.setVisibility(View.GONE);

                                return false;
                            }
                        })
                        .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL))
                        .into(viewHolder.ivResPhoto);
                viewHolder.resDetailsLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        presenter.onRestaurantClicked(restaurantModel);
                    }
                });
                break;
            case LOADING:
                break;

        }
    }

    @Override
    public int getItemCount() {
        return restaurantList != null ? restaurantList.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        return (position == restaurantList.size() - 1 && isLoadingAdded) ? LOADING : ITEM_TYPE;

    }

    public void refreshAdapter(List<RestaurantModelClass> restaurantList) {
        this.restaurantList = restaurantList;
        notifyDataSetChanged();
    }

    /**
     * Helper Methods
     **/
    public void addLoadingFooter() {
        isLoadingAdded = true;

    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;
    }

    /**
     * View Holders
     **/
    public class RestaurantViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivResPhoto;
        private TextView tvResName, tvResRating, tvResCuisines, tvResLocation;
        private ProgressBar mProgressbar;
        private CardView resDetailsLayout;

        public RestaurantViewHolder(View itemView) {
            super(itemView);
            ivResPhoto = itemView.findViewById(R.id.res_photo);
            mProgressbar = itemView.findViewById(R.id.res_photo_progress);
            tvResName = itemView.findViewById(R.id.res_name);
            tvResRating = itemView.findViewById(R.id.res_ratings);
            tvResLocation = itemView.findViewById(R.id.res_location);
            tvResCuisines = itemView.findViewById(R.id.res_cuisines);
            resDetailsLayout = itemView.findViewById(R.id.res_details_frame_layout);

        }
    }

    public class LoadingFooterViewHolder extends RecyclerView.ViewHolder {

        public LoadingFooterViewHolder(View itemView) {
            super(itemView);
        }
    }
}
