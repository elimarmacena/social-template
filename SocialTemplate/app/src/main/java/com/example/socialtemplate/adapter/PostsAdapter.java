package com.example.socialtemplate.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialtemplate.R;
import com.example.socialtemplate.activity.HomeActivity;
import com.example.socialtemplate.activity.PostCreationActivity;
import com.example.socialtemplate.model.ItemView;
import com.example.socialtemplate.model.PersonPost;
import com.example.socialtemplate.model.Post;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;
import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter {
    Context context;
    List<ItemView> homeContent;

    public PostsAdapter(Context context, List<ItemView> homeContent){
        this.context = context;
        this.homeContent = homeContent;
    }

    @Override
    public int getItemViewType(int position) {
        return this.homeContent.get(position).getType();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View view;
        switch (viewType){
            case 1:
                // Post creation preview
                view = inflater.inflate(R.layout.post_create_prev,parent,false);
                break;
            case 2:
                // Post with photo preview
                view = inflater.inflate(R.layout.post_photo_preview,parent,false);
                break;
            case 3:
                // Post no photo preview
                view = inflater.inflate(R.layout.post_preview,parent,false);
                break;
            default:
                view = inflater.inflate(R.layout.search_bar,parent,false);
                break;

        }
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        BottomNavigationView contexMenu = ((HomeActivity) this.context).findViewById(R.id.bottonMenu);
        int discoveryPostId = ((HomeActivity) this.context).findViewById(R.id.worldItem).getId();
        int timeLinePostId = ((HomeActivity) this.context).findViewById(R.id.timelineItem).getId();
        int selectedItemId = contexMenu.getSelectedItemId();
        if(selectedItemId == discoveryPostId && (holder.getItemViewType() == 2 || holder.getItemViewType() == 3)){
            holder.itemView.findViewById(R.id.btnFollow).setBackground(this.context.getDrawable(R.drawable.ic_white_follow));
        }
        else if (selectedItemId == timeLinePostId && (holder.getItemViewType() == 2 || holder.getItemViewType() == 3)){
            holder.itemView.findViewById(R.id.btnFollow).setBackground(this.context.getDrawable(R.drawable.ic_white_unfollow));
        }


        // Post creation preview
        switch (holder.getItemViewType()){
            case 1:
                Bitmap thumbProfile = ((Post)this.homeContent.get(position)).getProfilePhoto();
                ImageView profilePic = holder.itemView.findViewById(R.id.imageView);
                final Context localContext = this.context;
                EditText textInput = holder.itemView.findViewById(R.id.textInputEditText);
                textInput.setEnabled(true);
                textInput.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(localContext, PostCreationActivity.class);
                        localContext.startActivity(intent);
                    }
                });
                profilePic.setImageBitmap(thumbProfile);
                // Create the intent for the post screen
                break;
            case 2 : case 3:
                Bitmap thumbOwner = ((PersonPost)this.homeContent.get(position)).getOwnerPhoto();
                String nameOwner = ((PersonPost)this.homeContent.get(position)).getOwnerName();
                String postText = ((PersonPost)this.homeContent.get(position)).getPostText();
                Bitmap thumbPic = ((PersonPost)this.homeContent.get(position)).getPostPhoto();
                // Post no photo preview
                ImageView ownerPic = holder.itemView.findViewById(R.id.ivUser);
                ownerPic.setImageBitmap(thumbOwner);

                TextView ownerName = holder.itemView.findViewById(R.id.tvName);
                ownerName.setText(nameOwner);

                TextView postContent = holder.itemView.findViewById(R.id.tvContent);
                postContent.setText(postText);
                // Post with photo preview
                if(thumbPic != null){
                    ImageView postPhoto = holder.itemView.findViewById(R.id.ivPost);
                    postPhoto.setImageBitmap(thumbPic);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return this.homeContent.size();
    }
}
