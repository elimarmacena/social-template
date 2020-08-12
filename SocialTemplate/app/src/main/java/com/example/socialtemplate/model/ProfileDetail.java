package com.example.socialtemplate.model;

import android.graphics.Bitmap;

import java.util.concurrent.ThreadLocalRandom;

public class ProfileDetail extends ItemView {
    private String  profileName;
    private Bitmap profilePhoto;
    private int friendsCount;
    private int age = 0;
    private String city = "X";

    public ProfileDetail(String name, Bitmap photo, int friends,int age, String city){
        this.type = -1;
        this.profileName = name;
        this.profilePhoto = photo;
        this.friendsCount = friends;
        this.age = age;
        this.city = city;
    }

    public ProfileDetail(String name, Bitmap photo, int friends){
        this.type = -1;
        this.profileName = name;
        this.profilePhoto = photo;
        this.friendsCount = friends;
    }

    public ProfileDetail(String name, Bitmap photo){
        this.type = -1;
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

    public int getAge() {
        return age;
    }

    public String getCity() {
        return city;
    }
}
