package com.example.jeremy.customnotes.business_logic.repositories;

import com.example.jeremy.customnotes.business_logic.data.Note;
import com.example.jeremy.customnotes.constants.SortType;

import java.util.List;

import androidx.lifecycle.LiveData;

public interface INoteRepository {
    LiveData<List<Note>> getNotes();
    void update(Note note);
    void insert(Note note);
    void delete(int position);
    void deleteAll();
    void sort(SortType sortType);
    void close();
}
