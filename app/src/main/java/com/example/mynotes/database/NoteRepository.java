package com.example.mynotes.database;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepository {

    private NoteDao mNoteDao;
    private LiveData<List<Note>> mAllNotes;

    public NoteRepository(Application application){
        NoteDataBase noteDataBase = NoteDataBase.getInstance(application);
        mNoteDao = noteDataBase.noteDao();
        mAllNotes = mNoteDao.getAllNotes();
    }

    void insert(Note note){
        new InsertNoteAsyncTask(mNoteDao, DataBaseOperationType.INSERT).execute(note);
    }

    void update(Note note){
        new InsertNoteAsyncTask(mNoteDao, DataBaseOperationType.UPDATE).execute(note);
    }

    void delete(Note note){
        new InsertNoteAsyncTask(mNoteDao, DataBaseOperationType.DELETE).execute(note);
    }

    void deleteAll() {
        new InsertNoteAsyncTask(mNoteDao, DataBaseOperationType.DELETEALL).execute();
    }

    LiveData<List<Note>> getAllNote(){
        return mAllNotes;
    }

    private static class InsertNoteAsyncTask extends AsyncTask<Note, Void, Void> {

        private NoteDao mNoteDao;
        private DataBaseOperationType mDataBaseOperationType;

        InsertNoteAsyncTask(NoteDao noteDao, DataBaseOperationType dataBaseOperationType){
            mNoteDao = noteDao;
            mDataBaseOperationType = dataBaseOperationType;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            switch (mDataBaseOperationType){
                case INSERT:
                    mNoteDao.insert(notes[0]);
                    break;

                case UPDATE:
                    mNoteDao.update(notes[0]);
                    break;

                case DELETE:
                    mNoteDao.delete(notes[0]);
                    break;

                case DELETEALL:
                    mNoteDao.deleteAll();
                    break;
            }
            return null;
        }
    }
}
