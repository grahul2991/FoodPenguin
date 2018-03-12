package com.a3dmorpher.POJO;

import java.io.Serializable;

/**
 * Created by ahextech on 6/3/18.
 */

public class RestaurantModelClass implements Serializable {
    private String resID, resName, resPicUrl, resLocation, resRating;
    private String resCuisines;

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

    public String getResPicUrl() {
        return resPicUrl;
    }

    public void setResPicUrl(String resPicUrl) {
        this.resPicUrl = resPicUrl;
    }

    public String getResLocation() {
        return resLocation;
    }

    public void setResLocation(String resLocation) {
        this.resLocation = resLocation;
    }

    public String getResRating() {
        return resRating;
    }

    public void setResRating(String resRating) {
        this.resRating = resRating;
    }

    public String getResCuisines() {
        return resCuisines;
    }

    public void setResCuisines(String resCuisines) {
        this.resCuisines = resCuisines;
    }
}
