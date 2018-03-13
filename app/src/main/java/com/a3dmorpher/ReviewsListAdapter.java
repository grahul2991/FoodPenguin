package com.a3dmorpher;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.a3dmorpher.POJO.ReviewsModelClass;
import com.a3dmorpher.foodpenguin.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahextech on 13/3/18.
 */

public class ReviewsListAdapter extends BaseAdapter {
    private static final String TAG = "ReviewsListAdapter";
    private final Context context;
    TextView tvName;
    TextView tvReview;
    ImageView ivImage;
    private List<ReviewsModelClass> reviewList = new ArrayList<>();

    public ReviewsListAdapter(Context context) {
        this.context = context;
    }

    public void updateProducts(List<ReviewsModelClass> reviewList) {
        this.reviewList.addAll(reviewList);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return reviewList.size();
    }

    @Override
    public ReviewsModelClass getItem(int position) {
        return reviewList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.reviews, parent, false);
            tvName = convertView.findViewById(R.id.user_name);
            tvReview = convertView.findViewById(R.id.review_text);
            ivImage = convertView.findViewById(R.id.iv_user_pic);
        }

        final ReviewsModelClass review = getItem(position);
        tvName.setText(review.getUsername());
        tvReview.setText(review.getUserReview());
        Log.d(TAG, "Context package name: " + context.getPackageName());
        Glide.with(context).load(review.getUserProfilePic()).
                apply(RequestOptions.errorOf(R.drawable.no_image)).into(ivImage);
        return convertView;
    }
}
