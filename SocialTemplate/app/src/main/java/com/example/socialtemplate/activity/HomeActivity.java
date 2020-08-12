package com.example.socialtemplate.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.socialtemplate.R;
import com.example.socialtemplate.adapter.PostFragment;
import com.example.socialtemplate.adapter.ProfileAdapter;
import com.example.socialtemplate.adapter.ProfileFragment;
import com.example.socialtemplate.model.ItemView;
import com.example.socialtemplate.model.PostViewModel;
import com.example.socialtemplate.util.PostFactory;
import com.example.socialtemplate.util.Utils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class HomeActivity extends AppCompatActivity {
    BottomNavigationView bottomNav;
    PostViewModel postViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bottomNav = findViewById(R.id.bottonMenu);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.timelineItem:
                        // Getting data for the timeline
                        CreatePostTimeline();
                        PostFragment timelinePostViewFragment = PostFragment.newInstance();
                        setFragment(timelinePostViewFragment);
                        break;
                    case R.id.worldItem:
                        // Getting data for the discovery
                        CreatePostDiscovery();
                        PostFragment discoveryPostViewFragment = PostFragment.newInstance();
                        setFragment(discoveryPostViewFragment);
                        break;
                    case R.id.profileItem:
                        // Getting data for the profile
                        CreateProfileContent();
                        ProfileFragment ProfileViewFragment = ProfileFragment.newInstance();
                        setFragment(ProfileViewFragment);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
        //Starting data for the first time accessed
        this.postViewModel = new ViewModelProvider(this).get(PostViewModel.class);
        List<ItemView> posts = this.postViewModel.getPostDataList();
        if(posts.size() == 0){
            bottomNav.setSelectedItemId(R.id.timelineItem);
        }

    }

    private void setFragment(Fragment frag){
        FragmentTransaction fragTrans = getSupportFragmentManager().beginTransaction();
        fragTrans.replace(R.id.fragLayout,frag);
        fragTrans.addToBackStack(null);
        fragTrans.commit();
    }
    private  void CreatePostTimeline(){
        List<ItemView> posts = this.postViewModel.getPostDataList();
        posts.clear();
        posts.addAll(PostFactory.getPreviewCreationPost(this));
        posts.addAll(PostFactory.getTimeLinePost(this));
    }
    private void CreatePostDiscovery(){
        List<ItemView> posts = this.postViewModel.getPostDataList();
        posts.clear();
        posts.addAll(PostFactory.getEmptyPost());
        posts.addAll(PostFactory.getTimeLinePost(this));
    }
    private void CreateProfileContent(){
        List<ItemView> posts = this.postViewModel.getPostDataList();
        posts.clear();
        posts.addAll(PostFactory.getProfileDetail("Elimar",this));
        posts.addAll(PostFactory.getProfilePost("Elimar",this));
    }
}
