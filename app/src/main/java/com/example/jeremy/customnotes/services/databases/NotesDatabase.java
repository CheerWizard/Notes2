package com.example.jeremy.customnotes.services.databases;

import android.content.Context;

import com.example.jeremy.customnotes.constants.SQLiteConstants;
import com.example.jeremy.customnotes.services.dao.NoteDAO;
import com.example.jeremy.customnotes.models.Note;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Note.class} , version = 1)
public abstract class NotesDatabase extends RoomDatabase {

    private static NotesDatabase notesDatabase;
    public abstract NoteDAO noteDAO();

    public static synchronized NotesDatabase getNotesDatabase(Context context) {
        if (notesDatabase == null) {
            notesDatabase = Room.databaseBuilder(context.getApplicationContext() , NotesDatabase.class , SQLiteConstants.Databases.NOTE_DB)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return notesDatabase;
    }
}
