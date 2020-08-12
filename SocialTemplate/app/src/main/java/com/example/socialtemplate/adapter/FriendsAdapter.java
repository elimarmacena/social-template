package com.example.socialtemplate.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialtemplate.R;
import com.example.socialtemplate.model.ItemView;
import com.example.socialtemplate.model.ProfileDetail;

import java.util.List;

public class FriendsAdapter extends RecyclerView.Adapter {
    Context context;
    List<ItemView> friendsList;

    public FriendsAdapter(Context cntx, List<ItemView> friends){
        this.context = cntx;
        this.friendsList = friends;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View view = null;
        switch (viewType){
            case -1:
                view = inflater.inflate(R.layout.friend_preview,parent,false);
                break;
            case -3:
                view = inflater.inflate(R.layout.search_bar,parent,false);
                break;
            default:
                break;
        }
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()){
            // Friends details
            case -1:
                ProfileDetail profileDetail = ((ProfileDetail)this.friendsList.get(position));
                Bitmap profilePhoto = profileDetail.getProfilePhoto();
                String profileName = profileDetail.getProfileName();
                String cityProfile = profileDetail.getCity();
                int ageProfile = profileDetail.getAge();

                ImageView photo = holder.itemView.findViewById(R.id.profilePic);
                photo.setImageBitmap(profilePhoto);

                TextView name = holder.itemView.findViewById(R.id.profileName);
                name.setText(profileName);

                TextView city = holder.itemView.findViewById(R.id.cityText);
                city.setText(cityProfile);

                TextView age = holder.itemView.findViewById(R.id.ageText);
                age.setText(""+ageProfile);
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return this.friendsList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return this.friendsList.get(position).getType();
    }
}
