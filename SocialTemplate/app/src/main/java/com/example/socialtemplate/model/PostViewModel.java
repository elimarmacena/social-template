package com.example.socialtemplate.model;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class PostViewModel extends ViewModel {
    List<ItemView> postDataList = new ArrayList<>();

    public List<ItemView> getPostDataList(){
        return postDataList;
    }
}
