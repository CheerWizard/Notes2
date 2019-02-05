package com.example.jeremy.customnotes.services.repositories;

import com.example.jeremy.customnotes.constants.SQLRequestType;
import com.example.jeremy.customnotes.models.Note;

import java.util.List;

import androidx.lifecycle.LiveData;

public interface INoteRepository {
    LiveData<List<Note>> getNotes();
    LiveData<List<Note>> reload(SQLRequestType sqlRequestType , Note note);
    LiveData<List<Note>> reload(SQLRequestType sqlRequestType , int position);
    LiveData<List<Note>> reload(SQLRequestType sqlRequestType);
}
