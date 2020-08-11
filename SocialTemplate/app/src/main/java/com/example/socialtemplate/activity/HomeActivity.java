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
                        PostFragment postViewFragment = PostFragment.newInstance();
                        setFragment(postViewFragment);
                        break;
                    case R.id.worldItem:
                        System.out.println("Discovery Clicked");
                        break;
                    case R.id.profileItem:
                        System.out.println("profile clicked");
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
        this.postViewModel = new ViewModelProvider(this).get(PostViewModel.class);
        List<ItemView> posts = this.postViewModel.getPostDataList();

        if(posts.size() == 0){
            posts.addAll(PostFactory.getPreviewCreationPost(this));
            posts.addAll(PostFactory.getTimeLinePost(this));
            bottomNav.setSelectedItemId(R.id.timelineItem);
        }

    }

    private void setFragment(Fragment frag){
        FragmentTransaction fragTrans = getSupportFragmentManager().beginTransaction();
        fragTrans.replace(R.id.fragLayout,frag);
        fragTrans.addToBackStack(null);
        fragTrans.commit();
    }
}
