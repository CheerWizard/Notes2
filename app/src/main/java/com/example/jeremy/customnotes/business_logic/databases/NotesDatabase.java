package com.example.jeremy.customnotes.business_logic.databases;

import com.example.jeremy.customnotes.constants.SQLiteConstants;
import com.example.jeremy.customnotes.business_logic.dao.NoteDAO;
import com.example.jeremy.customnotes.business_logic.data.Note;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Note.class} , version = SQLiteConstants.Databases.NOTE_DB_VERSION_1 , exportSchema = false)
public abstract class NotesDatabase extends RoomDatabase {
    public abstract NoteDAO noteDAO();
}
