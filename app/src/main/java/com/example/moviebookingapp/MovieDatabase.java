package com.example.moviebookingapp;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = MovieItem.class, version = 4)
public abstract class MovieDatabase extends RoomDatabase {

    public static MovieDatabase instance;

    public abstract MovieDoa noteDoa();

    public static synchronized MovieDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    MovieDatabase.class, "note_database")
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

/*
    public static class PopulateAsyncTask extends AsyncTask<Void, Void, Void> {
        private NoteDoa noteDoa;

        public PopulateAsyncTask(NoteDatabase db) {
            noteDoa=db.noteDoa();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            return null;
        }
    }
*/
}
