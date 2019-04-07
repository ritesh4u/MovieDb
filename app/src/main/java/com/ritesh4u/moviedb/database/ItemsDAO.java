package com.ritesh4u.moviedb.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.ritesh4u.moviedb.models.Items;

import java.util.List;

@Dao
public interface ItemsDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMovieInfo(List<Items> movieInfoList);

    @Query("SELECT * FROM items")
    List<Items> getMovieList();
}
