package com.example.mynotes.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDao {

    @Query("Select * from note_table ORDER BY notePriority DESC")
    LiveData<List<Note>> getAllNotes();

    @Insert
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete (Note note);

    @Query("DELETE FROM note_table")
    void deleteAll();
}
