package com.example.moviebookingapp;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = BookingModel.class, version = 1)

public abstract class BookingDatabase extends RoomDatabase {
    public static BookingDatabase instance;

    public abstract BookingDao bookingDao();

    public static synchronized BookingDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    BookingDatabase.class, "note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
//            new PopulateAsyncTask(instance).execute();
        }
    };
}
