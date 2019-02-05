package com.example.jeremy.customnotes.services.dao;

import com.example.jeremy.customnotes.models.Note;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface NoteDAO {
    //insert note to table
    @Insert
    void save(Note note);
    //update note for table
    @Update
    void update(Note note);
    //get all notes from table
    @Query("SELECT * FROM notes")
    LiveData<List<Note>> getNotes();
    //remove appropriate note from table
    @Delete
    void remove(Note note);
    //remove note by id from table
    @Query("DELETE FROM notes WHERE id = :id")
    void remove(int id);
    //remove all notes from table
    @Query("DELETE FROM notes")
    void removeAll();
}
