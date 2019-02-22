package com.example.nguyen_wind7.mychat.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.nguyen_wind7.mychat.MessageActivity;
import com.example.nguyen_wind7.mychat.R;
import com.example.nguyen_wind7.mychat.model.User;
import com.google.android.gms.dynamic.IFragmentWrapper;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by nguyen_wind7 on 2019/02/22.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHoder> {

    private Context context;
    private List<User> users;

    public UserAdapter(Context context, List<User> users) {
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_item,viewGroup,false);
        return new UserAdapter.ViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoder viewHoder, int i) {
        final User user = users.get(i);
        viewHoder.username.setText(user.getUsername());
        if (user.getImageURL().equals("default")){
            viewHoder.profile_image.setImageResource(R.mipmap.ic_launcher);
        } else {
            Glide.with(context).load(user.getImageURL()).into(viewHoder.profile_image);
        }
        viewHoder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MessageActivity.class);
                intent.putExtra("userid",user.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHoder extends RecyclerView.ViewHolder{

        public TextView username;
        public CircleImageView profile_image;

        public ViewHoder(@NonNull View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.username);
            profile_image = itemView.findViewById(R.id.profile_image);
        }
    }
}
