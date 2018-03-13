package com.a3dmorpher.POJO;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ahextech on 13/3/18.
 */

public class ReviewsJsonParser {
    ArrayList<ReviewsModelClass> reviewsList;

    public ArrayList<ReviewsModelClass> getReviews(String response) {
        reviewsList = new ArrayList<>();
        try {

            JSONObject jsonObject = new JSONObject(response);
            JSONArray usersReview = jsonObject.getJSONArray("user_reviews");
            for (int i = 0; i < usersReview.length(); i++) {
                ReviewsModelClass reviewsModelClass = new ReviewsModelClass();
                JSONObject review = usersReview.getJSONObject(i);
                JSONObject reviewDetails = review.getJSONObject("review");
                float rating = reviewDetails.getInt("rating");
                reviewsModelClass.setUserRating(rating);
                String reviewText = reviewDetails.getString("review_text");
                reviewsModelClass.setUserReview(reviewText);
                JSONObject userDetails = reviewDetails.getJSONObject("user");
                String username = userDetails.getString("name");
                reviewsModelClass.setUsername(username);
                String userPic = userDetails.getString("profile_image");
                reviewsModelClass.setUserProfilePic(userPic);
                reviewsList.add(reviewsModelClass);

            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return reviewsList;
    }
}
