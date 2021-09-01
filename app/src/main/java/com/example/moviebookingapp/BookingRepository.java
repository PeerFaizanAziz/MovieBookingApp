package com.example.moviebookingapp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class BookingRepository {
    private BookingDao bookingDao;
    private LiveData<List<BookingModel>> allMoview;

    public BookingRepository(Application application) {
        BookingDatabase database = BookingDatabase.getInstance(application);
        bookingDao = database.bookingDao();
        allMoview = bookingDao.getAllBookings();
    }


    public void insert(BookingModel bookingModel) {
        new InsertNoteAsyncTask(bookingDao).execute(bookingModel);
    }


    public LiveData<List<BookingModel>> getAllnotes() {
        return allMoview;
    }

    public static class InsertNoteAsyncTask extends AsyncTask<BookingModel, Void, Void> {

        private BookingDao bookingDao;

        public InsertNoteAsyncTask(BookingDao bookingDao) {
            this.bookingDao = bookingDao;
        }

        @Override
        protected Void doInBackground(BookingModel... notes) {
            bookingDao.insert(notes[0]);
            return null;
        }
    }

}
