package com.example.socialtemplate.model;

import android.graphics.Bitmap;

public class Post extends ItemView {
    private Bitmap ProfilePhoto;

    public Post(Bitmap profilePhoto) {
        this.type = 2;
        this.ProfilePhoto = profilePhoto;
    }
}
