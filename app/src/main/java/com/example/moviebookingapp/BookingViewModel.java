package com.example.moviebookingapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class BookingViewModel  extends AndroidViewModel {
    public BookingRepository bookingRepository;
    public LiveData<List<BookingModel>> allMovies;

    public BookingViewModel(@NonNull Application application) {
        super(application);
        bookingRepository = new BookingRepository(application);
        allMovies = bookingRepository.getAllnotes();
    }

    public void insert(BookingModel bookingModel) {
        bookingRepository.insert(bookingModel);
    }

    public LiveData<List<BookingModel>> getAllNotes() {
        return allMovies;
    }
}
