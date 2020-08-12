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

public class ProfileFragment extends Fragment {
    private PostViewModel postViewModel;
    private View view;

    public static ProfileFragment newInstance(){
        return new ProfileFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.profile_view_fragment,container,false);
        return this.view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.postViewModel = new ViewModelProvider(getActivity()).get(PostViewModel.class);

        ProfileAdapter adapter = new ProfileAdapter(getContext(),this.postViewModel.getPostDataList());

        RecyclerView recyclePost = this.view.findViewById(R.id.profileRecycle);
        recyclePost.addItemDecoration(new DividerItemDecoration(recyclePost.getContext(), DividerItemDecoration.VERTICAL));
        recyclePost.setAdapter(adapter);
        recyclePost.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
