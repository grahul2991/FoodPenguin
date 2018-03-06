package com.a3dmorpher.foodpenguin;

import java.io.Serializable;

/**
 * Created by Asus on 3/4/2018.
 */

public class RestaurantListModelClass implements Serializable{
    private String resID;//Restaurant ID
    private String resName;//Restaurant Name
    private String resPhotoURL;//Restaurant Photo URL
    private String resLocation;//Restaurant Location

    public String getResLocation() {
        return resLocation;
    }

    public void setResLocation(String resLocation) {
        this.resLocation = resLocation;
    }

    private String resCuisines;//Restaurant Cuisines
    private String resRatings;//Restaurant Ratings

    public String getResID() {
        return resID;
    }

    public void setResID(String resID) {
        this.resID = resID;
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public String getResPhotoURL() {
        return resPhotoURL;
    }

    public void setResPhotoURL(String resPhotoURL) {
        this.resPhotoURL = resPhotoURL;
    }

    public String getResCuisines() {
        return resCuisines;
    }

    public void setResCuisines(String resCuisines) {
        this.resCuisines = resCuisines;
    }

    public String getResRatings() {
        return resRatings;
    }

    public void setResRatings(String resRatings) {
        this.resRatings = resRatings;
    }
}
