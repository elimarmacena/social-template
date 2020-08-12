package com.example.socialtemplate.adapter;

import android.app.Person;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialtemplate.R;
import com.example.socialtemplate.activity.FriendsActivity;
import com.example.socialtemplate.model.ItemView;
import com.example.socialtemplate.model.PersonPost;
import com.example.socialtemplate.model.ProfileDetail;

import java.util.List;

public class ProfileAdapter extends RecyclerView.Adapter {
    Context context;
    List<ItemView> profileContent;

    public ProfileAdapter(Context context, List<ItemView> profileContent){
        this.context = context;
        this.profileContent = profileContent;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View view;
        switch (viewType){
            case 2:
                // Post with photo preview
                view = inflater.inflate(R.layout.post_photo_preview,parent,false);
                break;
            case 3:
                // Post no photo preview
                view = inflater.inflate(R.layout.post_preview,parent,false);
                break;
            default:
                view = inflater.inflate(R.layout.profile_detail,parent,false);
                break;
        }
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder.getItemViewType() == 2 || holder.getItemViewType() == 3) {
            PersonPost post =((PersonPost) this.profileContent.get(position));
            Bitmap thumbOwner = post.getOwnerPhoto();
            String nameOwner = post.getOwnerName();
            String postText = post.getPostText();
            Bitmap thumbPic = post.getPostPhoto();
            Button followButton = holder.itemView.findViewById(R.id.btnFollow);
            followButton.setVisibility(View.INVISIBLE);
            // Post no photo preview
            ImageView ownerPic = holder.itemView.findViewById(R.id.ivUser);
            ownerPic.setImageBitmap(thumbOwner);

            TextView ownerName = holder.itemView.findViewById(R.id.tvName);
            ownerName.setText(nameOwner);

            TextView postContent = holder.itemView.findViewById(R.id.tvContent);
            postContent.setText(postText);
            // Post with photo preview
            if (thumbPic != null) {
                ImageView postPhoto = holder.itemView.findViewById(R.id.ivPost);
                postPhoto.setImageBitmap(thumbPic);
            }
        }
        // Profile details information
        else{
            ProfileDetail profileDetail = ((ProfileDetail)this.profileContent.get(position));
            Bitmap profilePhoto = profileDetail.getProfilePhoto();
            String profileName = profileDetail.getProfileName();
            int friendsCount = profileDetail.getFriendsCount();

            ImageView photo = holder.itemView.findViewById(R.id.profilePic);
            photo.setImageBitmap(profilePhoto);

            TextView name = holder.itemView.findViewById(R.id.nameProfile);
            name.setText(profileName);

            TextView friends = holder.itemView.findViewById(R.id.friendCountText);
            friends.setText(context.getResources().getString(R.string.profile_page_friends) +" "+ friendsCount);
            final Context localContext = this.context;
            //Setting buttons action
            friends.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(localContext, FriendsActivity.class);
                    localContext.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return this.profileContent.size();
    }

    @Override
    public int getItemViewType(int position) {
        return this.profileContent.get(position).getType();
    }

}
