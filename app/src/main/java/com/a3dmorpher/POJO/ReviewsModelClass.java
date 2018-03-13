package com.a3dmorpher.POJO;

import java.io.Serializable;

/**
 * Created by ahextech on 13/3/18.
 */

public class ReviewsModelClass implements Serializable {
    private String username, userProfilePic, userReview;
    private float userRating;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserProfilePic() {
        return userProfilePic;
    }

    public void setUserProfilePic(String userProfilePic) {
        this.userProfilePic = userProfilePic;
    }

    public String getUserReview() {
        return userReview;
    }

    public void setUserReview(String userReview) {
        this.userReview = userReview;
    }

    public Float getUserRating() {
        return userRating;
    }

    public void setUserRating(Float userRating) {
        this.userRating = userRating;
    }
}
