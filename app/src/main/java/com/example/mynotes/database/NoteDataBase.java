package com.example.mynotes.database;

import android.content.Context;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Note.class}, version = 1)
public abstract class NoteDataBase extends RoomDatabase {

    private static NoteDataBase mInstance;

    public abstract NoteDao noteDao();

    public static synchronized NoteDataBase getInstance(Context context){
        if(mInstance == null){
            mInstance = Room.databaseBuilder(context.getApplicationContext(), NoteDataBase.class,
                    "note_database").fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }
        return mInstance;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(mInstance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{

        private NoteDao noteDao;

        PopulateDbAsyncTask(NoteDataBase noteDataBase){
            noteDao = noteDataBase.noteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            // add all in Note model class
            return null;
        }
    }
}
