package com.example.socialtemplate.model;

import android.graphics.Bitmap;

public class PersonPost extends ItemView {
    private Bitmap  ownerPhoto;
    private String  ownerName;
    private int     ownerId;
    private String  postText;
    private Bitmap  postPhoto;

    public PersonPost(Bitmap ownerPhoto, String ownerName, int ownerId, String postText, Bitmap postPhoto) {
        this.type = 1;
        this.ownerPhoto = ownerPhoto;
        this.ownerName = ownerName;
        this.ownerId = ownerId;
        this.postText = postText;
        this.postPhoto = postPhoto;
    }

    public Bitmap getOwnerPhoto() {
        return ownerPhoto;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public String getPostText() {
        return postText;
    }

    public Bitmap getPostPhoto() {
        return postPhoto;
    }
}
