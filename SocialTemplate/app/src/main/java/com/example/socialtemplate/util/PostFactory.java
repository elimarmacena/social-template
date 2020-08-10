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
    static List<String> photoPostPath = new ArrayList<String>(){{
       add("drawable/humaaans_solo.png");
       add("drawable/humaaans_group.png");
    }};
    static List<String> photoProfilePath = new ArrayList<String>(){{
        add("drawable/big_shoes.png");
        add("drawable/big_shoes_f.png");
    }};

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

    public static List<ItemView> getTimeLinePost(){
        List<ItemView> timelinePosts = new ArrayList<ItemView>();
        int totalOfPost = ThreadLocalRandom.current().nextInt(0,11);
        for (int i= 0; i < totalOfPost; i++){
            String profilePicPath = photoProfilePath.get(ThreadLocalRandom.current().nextInt(0,3));
            File profilePic = new File(profilePicPath);
            Bitmap thumbProfile = BitmapFactory.decodeFile(profilePic.getAbsolutePath());
            String profileName = profileNames.get(ThreadLocalRandom.current().nextInt(0,11));
            String postText = postContent.get(ThreadLocalRandom.current().nextInt(0,8));
            int profileId = ThreadLocalRandom.current().nextInt(0,111);
            int indexPhotoPost = ThreadLocalRandom.current().nextInt(-1,3);
            Bitmap thumbPostPhoto = null;
            if(indexPhotoPost != -1){
                String photoPath = photoPostPath.get(indexPhotoPost);
                File postPhoto = new File(photoPath);
                thumbPostPhoto = BitmapFactory.decodeFile(postPhoto.getAbsolutePath());
            }
            PersonPost postStructure = new PersonPost(thumbProfile,profileName,profileId,postText,thumbPostPhoto);
            timelinePosts.add(postStructure);
        }
        return timelinePosts;
    }
}
