package com.ritesh4u.moviedb.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.ritesh4u.moviedb.models.Items;

@Database(entities = {Items.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ItemsDAO getItemsDAO();

   private static AppDatabase database;

    public static AppDatabase getDBInstance(Context mContext) {
        if (database == null) {
            database = Room.databaseBuilder(mContext, AppDatabase.class, "movieDB")
                    .allowMainThreadQueries()
                    .build();
        }
        return database;
    }
}
