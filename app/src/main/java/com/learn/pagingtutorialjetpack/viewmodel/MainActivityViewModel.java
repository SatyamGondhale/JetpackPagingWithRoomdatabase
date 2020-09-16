package com.learn.pagingtutorialjetpack.viewmodel;

import android.app.Application;
import android.app.ListActivity;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.learn.pagingtutorialjetpack.data.Friend;
import com.learn.pagingtutorialjetpack.repository.DataRepository;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    DataRepository repository;
    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        repository=new DataRepository(application);

    }

    public LiveData<List<Friend>> getFriendListViewModel(){
        return repository.getFriendListRepo();
    }
}
