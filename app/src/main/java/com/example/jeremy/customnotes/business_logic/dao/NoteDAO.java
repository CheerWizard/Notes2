package com.example.jeremy.customnotes.business_logic.dao;

import com.example.jeremy.customnotes.constants.SQLiteConstants;
import com.example.jeremy.customnotes.business_logic.data.Note;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface NoteDAO {
    //insert note to table
    @Insert(onConflict = REPLACE)
    void save(Note note);
    //update note for table
    @Update
    void update(Note note);
    //get all notes from table
    @Query("SELECT * FROM " + SQLiteConstants.Tables.NOTE_TABLE)
    LiveData<List<Note>> getNotes();
    //remove appropriate note from table
    @Delete
    void remove(Note note);
    //remove note by id from table
    @Query("DELETE FROM " + SQLiteConstants.Tables.NOTE_TABLE + " WHERE id = :id")
    void remove(int id);
    //remove all notes from table
    @Query("DELETE FROM " + SQLiteConstants.Tables.NOTE_TABLE)
    void removeAll();
}
