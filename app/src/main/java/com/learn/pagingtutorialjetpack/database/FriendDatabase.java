package com.learn.pagingtutorialjetpack.database;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.learn.pagingtutorialjetpack.R;
import com.learn.pagingtutorialjetpack.dao.FriendDAO;
import com.learn.pagingtutorialjetpack.data.Friend;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Friend.class},version = 1,exportSchema = false)
public abstract class FriendDatabase extends RoomDatabase {

    public abstract FriendDAO friendDAO();
    private static volatile FriendDatabase dbINSTANCE=null;
    private static final int NUMBER_OF_THREADS = 2;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);


    @NonNull
    public static synchronized FriendDatabase getInstance(final Context context){
        if(dbINSTANCE==null){
            synchronized (FriendDatabase.class){
                if(dbINSTANCE==null){
                    dbINSTANCE= Room.databaseBuilder(context.getApplicationContext(),FriendDatabase.class,"friendsDatabase")
                        .addCallback(new Callback() {
                            @Override
                            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                super.onCreate(db);
                                Executors.newSingleThreadExecutor().execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        fillWithDemoData(context);
                                    }
                                });
                            }
                        }).build();
                }
            }
        }
        return dbINSTANCE;
    }

    @WorkerThread
    private static void fillWithDemoData(Context context) {
        FriendDAO dao = getInstance(context).friendDAO();
        JSONArray emoji = loadJsonArray(context);
        try {
            for (int i = 0; i < emoji.length(); i++) {
                JSONObject item = emoji.getJSONObject(i);
                dao.insert(new Friend(item.getString("name")));
            }
        } catch (JSONException exception) {
            exception.printStackTrace();
        }
    }

    private static JSONArray loadJsonArray(Context context) {
        StringBuilder builder = new StringBuilder();
        InputStream in = context.getResources().openRawResource(R.raw.friend);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            JSONObject json = new JSONObject(builder.toString());
            return json.getJSONArray("friends");

        } catch (IOException | JSONException exception) {
            exception.printStackTrace();
        }

        return null;
    }
}
