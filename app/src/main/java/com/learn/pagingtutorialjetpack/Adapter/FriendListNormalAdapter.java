package com.learn.pagingtutorialjetpack.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.learn.pagingtutorialjetpack.R;
import com.learn.pagingtutorialjetpack.data.Friend;

import java.util.List;

public class FriendListNormalAdapter extends RecyclerView.Adapter<FriendListNormalAdapter.ViewHolder> {

    private Context context;
    private List<Friend> friendsList;

    public FriendListNormalAdapter(Context context,List<Friend> friendsList){
        this.context=context;
        this.friendsList = friendsList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.friend_card,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Friend friend=friendsList.get(position);
        holder.friend.setText(friend.getFriend_name());
    }

    @Override
    public int getItemCount() {
        return friendsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView friend;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            friend=itemView.findViewById(R.id.friend_name);
        }
    }
}
