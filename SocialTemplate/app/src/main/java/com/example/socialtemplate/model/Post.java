package com.example.socialtemplate.model;

import android.graphics.Bitmap;

public class Post extends ItemView {
    private Bitmap profilePhoto;

    public Post(Bitmap profilePhoto) {
        this.type = 1;
        this.profilePhoto = profilePhoto;
    }
    public Post(int type) {
        this.type = type;
        this.profilePhoto = null;
    }

    public Bitmap getProfilePhoto(){
        return this.profilePhoto;
    }
}
