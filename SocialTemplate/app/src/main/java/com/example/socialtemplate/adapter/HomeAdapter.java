package com.example.socialtemplate.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialtemplate.R;
import com.example.socialtemplate.model.ItemView;
import com.example.socialtemplate.model.PersonPost;
import com.example.socialtemplate.model.Post;

import java.io.File;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter {
    Context context;
    List<ItemView> homeContent;

    public HomeAdapter(Context context, List<ItemView> homeContent){
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
        // Post creation preview
        if(viewType == 1){
            view = inflater.inflate(R.layout.post_create_prev,parent,false);
        }
        // Post with photo preview
        else if(viewType == 2){
            view = inflater.inflate(R.layout.post_photo_preview,parent,false);
        }
        // Post no photo preview
        else{
            view = inflater.inflate(R.layout.post_preview,parent,false);
        }

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        // Post creation preview
        if(holder.getItemViewType() == 1){
            Bitmap thumbProfile = ((Post)this.homeContent.get(position)).getProfilePhoto();
            ImageView profilePic = holder.itemView.findViewById(R.id.imageView);
            EditText textInput = holder.itemView.findViewById(R.id.textInputEditText);
            textInput.setEnabled(false);
            profilePic.setImageBitmap(thumbProfile);
            // Create the intent for the post screen

        }
        else{
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
        }



    }

    @Override
    public int getItemCount() {
        return this.homeContent.size();
    }
}
