package com.example.socialtemplate.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;

import com.example.socialtemplate.R;
import com.example.socialtemplate.model.ItemView;
import com.example.socialtemplate.model.PersonPost;
import com.example.socialtemplate.model.Post;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class PostFactory {
    // Photo used for random posts
    static List<Integer> photoPostPath = new ArrayList<Integer>(){{
       add(R.drawable.humaaans_solo);
       add(R.drawable.humaaans_group);
    }};

    // Photo used for random user
    static List<Integer> photoProfilePath = new ArrayList<Integer>(){{
        add(R.drawable.big_shoes);
        add(R.drawable.big_shoes_f);
    }};

    // Strings for random user names
    static List<String> profileNames = new ArrayList<String>(){{
       add("Emma");
       add("Olivia");
       add("Isabella");
       add("Sophia");
       add("Victoria");
       add("Liam");
       add("Oliver");
       add("James");
       add("Pedro");
       add("Paulo");
    }};

    // String for random post content
    static List<String> postContent = new ArrayList<String>(){{
       add("neque volutpat ac");
       add("tincidunt vitae semper");
       add("augue eget arcu");
       add("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
       add("magna fringilla urna porttitor rhoncus");
       add("tincidunt eget nullam non nisi");
       add("tempor nec feugiat nisl pretium");
    }};

    public static List<ItemView> getPreviewCreationPost(Context cntx){
        Bitmap thumbProfile = BitmapFactory.decodeResource(cntx.getResources(),R.drawable.big_shoes);
        Post previewCreationPost = new Post(thumbProfile);
        List<ItemView> listPost = new ArrayList<ItemView>();
        listPost.add(previewCreationPost);
        return listPost;
    }

    public static List<ItemView> getTimeLinePost(Context cntx){
        List<ItemView> timelinePosts = new ArrayList<ItemView>();
        int totalOfPost = ThreadLocalRandom.current().nextInt(4,15);
        for (int i= 0; i < totalOfPost; i++){
            // getting a random profile picture
            int profilePicPath = photoProfilePath.get(ThreadLocalRandom.current().nextInt(0,2));
            Bitmap thumbProfile = BitmapFactory.decodeResource(cntx.getResources(),profilePicPath);
            // getting a random name
            String profileName = profileNames.get(ThreadLocalRandom.current().nextInt(0,10));
            // getting a random post content
            String postText = postContent.get(ThreadLocalRandom.current().nextInt(0,7));
            // creating a random user id
            int profileId = ThreadLocalRandom.current().nextInt(0,111);
            // determining if will be a post with photo or not
            int indexPhotoPost = ThreadLocalRandom.current().nextInt(-1,2);
            Bitmap thumbPostPhoto = null;
            if(indexPhotoPost != -1){
                int photoPath = photoPostPath.get(indexPhotoPost);
                thumbPostPhoto = BitmapFactory.decodeResource(cntx.getResources(),photoPath);
            }
            PersonPost postStructure = new PersonPost(thumbProfile,profileName,profileId,postText,thumbPostPhoto);
            timelinePosts.add(postStructure);
        }
        return timelinePosts;
    }

    public static List<ItemView> getEmptyPost(){
        final Post emptyPost = new Post(-3);
        List<ItemView> emptyPostList = new ArrayList<ItemView>();
        emptyPostList.add(emptyPost);
        return emptyPostList;
    }
}
