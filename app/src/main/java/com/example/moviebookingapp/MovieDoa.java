package com.example.moviebookingapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MovieDoa {


    @Insert
    void insert(MovieItem movieItem);


    @Query("SELECT * FROM note_table ")
    LiveData<List<MovieItem>> getAllNotes();
}
