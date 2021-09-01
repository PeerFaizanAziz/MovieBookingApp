package com.example.moviebookingapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MovieViewModel extends AndroidViewModel {
    public MovieRepository movieRepository;
    public LiveData<List<MovieItem>> allNotes;

    public MovieViewModel(@NonNull Application application) {
        super(application);
        movieRepository = new MovieRepository(application);
        allNotes = movieRepository.getAllnotes();
    }

    public void insert(MovieItem movieItem) {
        movieRepository.insert(movieItem);
    }

    public LiveData<List<MovieItem>> getAllNotes() {
        return allNotes;
    }
}
