package com.example.moviebookingapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BookingDao {
    @Insert
    void insert(BookingModel bookingModel);

    @Query("SELECT * FROM booking_table ")
    LiveData<List<BookingModel>> getAllBookings();


}
