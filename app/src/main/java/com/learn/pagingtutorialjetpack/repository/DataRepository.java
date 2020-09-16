package com.learn.pagingtutorialjetpack.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.learn.pagingtutorialjetpack.dao.FriendDAO;
import com.learn.pagingtutorialjetpack.data.Friend;
import com.learn.pagingtutorialjetpack.database.FriendDatabase;

import java.util.List;

public class DataRepository {

    private FriendDAO friendDAO;

    public DataRepository(Application application){
        FriendDatabase friendDatabase=FriendDatabase.getInstance(application);
        friendDAO=friendDatabase.friendDAO();

    }

    public void insertFriend(final Friend friend){
        FriendDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                friendDAO.insert(friend);
            }
        });
    }

    public LiveData<List<Friend>> getFriendListRepo(){
        return friendDAO.getFriendList();
    }
}
