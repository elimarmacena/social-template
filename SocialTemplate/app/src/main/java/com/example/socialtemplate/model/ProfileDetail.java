package com.example.socialtemplate.model;

import android.graphics.Bitmap;

import java.util.concurrent.ThreadLocalRandom;

public class ProfileDetail extends ItemView {
    private String  profileName;
    private Bitmap profilePhoto;
    private int friendsCount;

    public ProfileDetail(String name, Bitmap photo, int friends){
        this.type = -1;
        this.profileName = name;
        this.profilePhoto = photo;
        this.friendsCount = friends;
    }

    public ProfileDetail(String name, Bitmap photo){
        this.profileName = name;
        this.profilePhoto = photo;
        this.friendsCount = ThreadLocalRandom.current().nextInt(0,1000);;
    }

    public String getProfileName() {
        return profileName;
    }

    public Bitmap getProfilePhoto() {
        return profilePhoto;
    }

    public int getFriendsCount() {
        return friendsCount;
    }
}
