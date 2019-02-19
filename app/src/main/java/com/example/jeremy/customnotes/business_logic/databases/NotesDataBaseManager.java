package com.example.jeremy.customnotes.business_logic.databases;

import com.example.jeremy.customnotes.constants.SQLiteConstants;
import com.example.jeremy.customnotes.ui.CustomNoteApplication;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.room.Room;

@Singleton
public class NotesDataBaseManager {

    private NotesDatabase notesDatabase;

    @Inject
    public NotesDataBaseManager() {
        notesDatabase = Room.databaseBuilder(CustomNoteApplication.getInstance(), NotesDatabase.class , SQLiteConstants.Databases.NOTE_DB)
                .fallbackToDestructiveMigration()
                .build();
    }

    public NotesDatabase getNotesDatabase() {
        return notesDatabase;
    }

    public void close() {
        notesDatabase.close();
    }

    public boolean isOpen() {
        return notesDatabase.isOpen();
    }
}
