package com.example.moviebookingapp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class MovieRepository {
    private MovieDoa movieDoa;
    private LiveData<List<MovieItem>> allnotes;

    public MovieRepository(Application application) {
        MovieDatabase database = MovieDatabase.getInstance(application);
        movieDoa = database.noteDoa();
        allnotes = movieDoa.getAllNotes();
    }


    public void insert(MovieItem movieItem) {
        new InsertNoteAsyncTask(movieDoa).execute(movieItem);
    }


    public LiveData<List<MovieItem>> getAllnotes() {
        return allnotes;
    }

    public static class InsertNoteAsyncTask extends AsyncTask<MovieItem, Void, Void> {

        private MovieDoa movieDoa;

        public InsertNoteAsyncTask(MovieDoa movieDoa) {
            this.movieDoa = movieDoa;
        }

        @Override
        protected Void doInBackground(MovieItem... movieItems) {
            movieDoa.insert(movieItems[0]);
            return null;
        }
    }

}
