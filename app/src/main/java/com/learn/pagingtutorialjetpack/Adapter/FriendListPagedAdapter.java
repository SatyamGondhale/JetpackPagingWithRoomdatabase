package com.learn.pagingtutorialjetpack.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.learn.pagingtutorialjetpack.R;
import com.learn.pagingtutorialjetpack.data.Friend;

public class FriendListPagedAdapter extends PagedListAdapter<Friend,FriendListPagedAdapter.ViewHolder> {


    public FriendListPagedAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.friend_card,parent,false);
        return new ViewHolder(v);
    }
    private static final DiffUtil.ItemCallback<Friend> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Friend>() {
                @Override
                public boolean areItemsTheSame(@NonNull Friend oldItem, @NonNull Friend newItem) {
                    return oldItem.getId() == newItem.getId();
                }

                @Override
                public boolean areContentsTheSame(@NonNull Friend oldItem, @NonNull Friend newItem) {
                    return oldItem.getId() == newItem.getId();
                }
            };
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Friend friend=friendPosition(position);
        holder.friend.setText(friend.getFriend_name());
    }
    public Friend friendPosition(int pos){
        return getItem(pos);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView friend;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            friend=itemView.findViewById(R.id.friend_name);
        }
    }
}
