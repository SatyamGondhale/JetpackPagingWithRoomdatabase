package com.learn.pagingtutorialjetpack.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "friend_list")
public class Friend {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ColumnInfo(name = "friend_name")
    String friend_name;

    public String getFriend_name() {
        return friend_name;
    }

    public void setFriend_name(String friend_name) {
        this.friend_name = friend_name;
    }

    public Friend(String friend_name) {
        this.friend_name = friend_name;
    }


}
