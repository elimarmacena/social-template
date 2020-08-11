package com.example.socialtemplate.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialtemplate.R;
import com.example.socialtemplate.model.PostViewModel;

public class PostFragment extends Fragment {
    private PostViewModel postViewModel;
    private View view;

    public static PostFragment newInstance(){
        return new PostFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.posts_view_fragment,container,false);
        return this.view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.postViewModel = new ViewModelProvider(getActivity()).get(PostViewModel.class);

        HomeAdapter adapter = new HomeAdapter(getContext(),this.postViewModel.getPostDataList());

        RecyclerView recyclePost = this.view.findViewById(R.id.postRecycle);
        recyclePost.addItemDecoration(new DividerItemDecoration(recyclePost.getContext(), DividerItemDecoration.VERTICAL));
        recyclePost.setAdapter(adapter);
        recyclePost.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
