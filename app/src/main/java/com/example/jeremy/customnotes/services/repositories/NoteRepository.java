package com.example.jeremy.customnotes.services.repositories;

import android.content.Context;
import android.os.Handler;

import com.example.jeremy.customnotes.constants.ProcessStates;
import com.example.jeremy.customnotes.constants.SQLRequestType;
import com.example.jeremy.customnotes.models.Note;
import com.example.jeremy.customnotes.services.dao.NoteDAO;
import com.example.jeremy.customnotes.services.databases.NotesDatabase;
import com.example.jeremy.customnotes.utils.cache.NoteCategoryCache;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import androidx.lifecycle.LiveData;

public class NoteRepository implements INoteRepository {

    private NoteDAO noteDAO;
    private Executor executor;
    private Handler handler;
    //global var
    private LiveData<List<Note>> noteListLiveData;

    public NoteRepository(Context context , Handler handler) {
        executor = Executors.newSingleThreadExecutor();
        noteDAO = NotesDatabase.getNotesDatabase(context).noteDAO();
        this.handler = handler;
    }

    @Override
    public LiveData<List<Note>> getNotes() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                noteListLiveData = noteDAO.getNotes();
            }
        });
        return noteListLiveData;
    }

    @Override
    public LiveData<List<Note>> reload(final SQLRequestType sqlRequestType, final Note note) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                switch (sqlRequestType) {
                    case INSERT:
                        noteDAO.save(note);
                        handler.sendEmptyMessage(ProcessStates.Successful.INSERT_SUCCESS);
                        break;
                    case UPDATE:
                        noteDAO.update(note);
                        handler.sendEmptyMessage(ProcessStates.Successful.UPDATE_SUCCESS);
                        break;
                }
                NoteCategoryCache.add(note.getCategory() , note.getCategory());
                noteListLiveData = noteDAO.getNotes();
            }
        });
        return noteListLiveData;
    }

    @Override
    public LiveData<List<Note>> reload(final SQLRequestType sqlRequestType, final int position) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                switch (sqlRequestType) {
                    case DELETE:
                        noteDAO.remove(position);
                        handler.sendEmptyMessage(ProcessStates.Successful.DELETE_SUCCESS);
                        break;
                }
                noteListLiveData = noteDAO.getNotes();
            }
        });
        return noteListLiveData;
    }

    @Override
    public LiveData<List<Note>> reload(final SQLRequestType sqlRequestType) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                switch (sqlRequestType) {
                    case DELETE:
                        noteDAO.removeAll();
                        handler.sendEmptyMessage(ProcessStates.Successful.DELETE_ALL_SUCCESS);
                        break;
                }
                noteListLiveData = noteDAO.getNotes();
            }
        });
        return noteListLiveData;
    }
}
