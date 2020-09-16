package com.learn.pagingtutorialjetpack.dao;


import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.learn.pagingtutorialjetpack.data.Friend;

import java.util.List;

@Dao
public interface FriendDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Friend smiley);

    @Query("SELECT * FROM friend_list")
    LiveData<List<Friend>> getFriendList();

    @Query("SELECT * FROM friend_list")
    DataSource.Factory<Integer, Friend> getFriendListPaged();
}
