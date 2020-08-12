package com.example.socialtemplate.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.socialtemplate.R;
import com.example.socialtemplate.adapter.FriendsAdapter;
import com.example.socialtemplate.model.ItemView;
import com.example.socialtemplate.util.PostFactory;

import java.util.ArrayList;
import java.util.List;

public class FriendsActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        RecyclerView rvFriends = findViewById(R.id.friendsRecyle);
        rvFriends.addItemDecoration(new DividerItemDecoration(rvFriends.getContext(), DividerItemDecoration.VERTICAL));
        List<ItemView> friendsList = new ArrayList<>();
        //Empty data used to create searchbar
        friendsList.addAll(PostFactory.getEmptyPost());
        //Getting friends data to populate the view
        friendsList.addAll(PostFactory.getFriendList(this));

        FriendsAdapter adapter = new FriendsAdapter(this,friendsList);

        rvFriends.setAdapter(adapter);

        rvFriends.setLayoutManager(new LinearLayoutManager(this));
    }

}
