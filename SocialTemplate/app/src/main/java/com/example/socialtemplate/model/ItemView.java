package com.example.socialtemplate.model;

public class ItemView {
    // possible values for type : 1 - Preview Post Creation; 2 - Post With Photo; 3 - Post No Photo
    protected int type;


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
