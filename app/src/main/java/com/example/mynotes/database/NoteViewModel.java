package com.example.mynotes.database;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    private NoteRepository mNoteRepository;
    private LiveData<List<Note>> mAllNotes;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        mNoteRepository = new NoteRepository(application);
        mAllNotes = mNoteRepository.getAllNote();
    }

    public LiveData<List<Note>> getAllNotes(){
        return mAllNotes;
    }

    public void insert(Note note){
        mNoteRepository.insert(note);
    }

    public void update(Note note){
        mNoteRepository.update(note);
    }

    public void delete(Note note){
        mNoteRepository.delete(note);
    }

    public void deleteAll(){
        mNoteRepository.deleteAll();
    }

}
