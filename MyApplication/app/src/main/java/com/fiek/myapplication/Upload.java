package com.fiek.myapplication;

import java.security.Key;

public class Upload {
    private String mName;
    private int mRating;
    private String mDesc;
    private String mImageUrl;
    private  String mKey;

    public Upload() {
        //empty constructor needed
    }

    public int getmRating() {
        return mRating;
    }

    public void setmRating(int mRating) {
        this.mRating = mRating;
    }

    public String getmDesc() {
        return mDesc;
    }

    public void setmDesc(String mDesc) {
        this.mDesc = mDesc;
    }

    public Upload(String name, String imageUrl, int rating, String desc) {
        if (name.trim().equals("")) {
            name = "No Name";
        }
        mName = name;
        mImageUrl = imageUrl;
        mDesc=desc;
        mRating=rating;

    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }

    public String getKey() {
        return mKey;
    }
    public void setkey(String key){
        mKey=key;
    }
}
