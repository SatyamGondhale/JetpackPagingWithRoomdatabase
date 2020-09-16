package com.learn.pagingtutorialjetpack.viewmodel;

import android.app.Application;
import android.app.ListActivity;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.learn.pagingtutorialjetpack.Constants;
import com.learn.pagingtutorialjetpack.data.Friend;
import com.learn.pagingtutorialjetpack.repository.DataRepository;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    DataRepository repository;
    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        repository=new DataRepository(application);
        repository.insertFriend(new Friend("Sattu"));
        // This is added because onCreate of database in only called after some concrete implementation for database.

    }

    private final static PagedList.Config config
            = new PagedList.Config.Builder()
            .setPageSize(Constants.PAGE_SIZE)
            .setInitialLoadSizeHint(Constants.PAGE_INITIAL_LOAD_SIZE_HINT)
            .setPrefetchDistance(Constants.PAGE_PREFETCH_DISTANCE)
            .setEnablePlaceholders(false)
            .build();
    public LiveData<List<Friend>> getFriendListViewModel(){
        return repository.getFriendListRepo();
    }

    public LiveData<PagedList<Friend>> getPagedFriendListViewModel(){
        return repository.getPagedList(config);
    };
}
