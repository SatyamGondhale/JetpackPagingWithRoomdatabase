package com.learn.pagingtutorialjetpack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.learn.pagingtutorialjetpack.Adapter.FriendListNormalAdapter;
import com.learn.pagingtutorialjetpack.Adapter.FriendListPagedAdapter;
import com.learn.pagingtutorialjetpack.data.Friend;
import com.learn.pagingtutorialjetpack.viewmodel.MainActivityViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    MainActivityViewModel mainActivityViewModel;
    RecyclerView recyclerView;
   // FriendListNormalAdapter adapter;
    FriendListPagedAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.friend_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        mainActivityViewModel=new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(MainActivityViewModel.class);
        adapter=new FriendListPagedAdapter();
        mainActivityViewModel.getPagedFriendListViewModel().observe(this, new Observer<PagedList<Friend>>() {
            @Override
            public void onChanged(PagedList<Friend> friends) {
                adapter.submitList(friends);
                recyclerView.setAdapter(adapter);
            }
        });

       /* mainActivityViewModel.getFriendListViewModel().observe(this, new Observer<List<Friend>>() {
            @Override
            public void onChanged(List<Friend> friends) {
                adapter=new FriendListNormalAdapter(MainActivity.this,friends);
                recyclerView.setAdapter(adapter);
            }
        });*/
    }
}