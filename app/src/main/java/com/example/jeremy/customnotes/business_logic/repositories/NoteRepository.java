package com.example.jeremy.customnotes.business_logic.repositories;

import com.example.jeremy.customnotes.business_logic.databases.NotesDataBaseManager;
import com.example.jeremy.customnotes.business_logic.data.Note;
import com.example.jeremy.customnotes.business_logic.dao.NoteDAO;
import com.example.jeremy.customnotes.constants.SortType;
import com.example.jeremy.customnotes.utils.cache.NoteCategoryCache;
import com.example.jeremy.customnotes.utils.resolvers.NoteResolver;
import com.example.jeremy.customnotes.wrappers.ExecutorWrapper;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

@Singleton
public class NoteRepository implements INoteRepository {
    //dao
    private NoteDAO noteDAO;
    //db manager
    private NotesDataBaseManager notesDataBaseManager;
    //thread executor
    private ExecutorWrapper executorWrapper;
    //global var
    private MutableLiveData<List<Note>> noteListLiveData;

    @Inject
    public NoteRepository(NotesDataBaseManager notesDataBaseManager , ExecutorWrapper executorWrapper) {
        this.executorWrapper = executorWrapper;
        this.notesDataBaseManager = notesDataBaseManager;
        noteDAO = notesDataBaseManager.getNotesDatabase().noteDAO();
        noteListLiveData = (MutableLiveData<List<Note>>) noteDAO.getNotes();
    }

    @Override
    public LiveData<List<Note>> getNotes() {
        return noteListLiveData;
    }

    @Override
    public void update(final Note note) {
        executorWrapper.execute(new Runnable() {
            @Override
            public void run() {
                noteDAO.update(note);
                noteListLiveData = (MutableLiveData<List<Note>>) noteDAO.getNotes();
                NoteCategoryCache.add(note.getCategory() , note.getCategory());
            }
        });
    }

    @Override
    public void insert(final Note note) {
        executorWrapper.execute(new Runnable() {
            @Override
            public void run() {
                noteDAO.save(note);
                noteListLiveData = (MutableLiveData<List<Note>>) noteDAO.getNotes();
                NoteCategoryCache.add(note.getCategory() , note.getCategory());
            }
        });
    }

    @Override
    public void delete(final int position) {
        executorWrapper.execute(new Runnable() {
            @Override
            public void run() {
                noteDAO.remove(position);
                noteListLiveData = (MutableLiveData<List<Note>>) noteDAO.getNotes();
            }
        });
    }

    @Override
    public void deleteAll() {
        executorWrapper.execute(new Runnable() {
            @Override
            public void run() {
                noteDAO.removeAll();
                noteListLiveData = (MutableLiveData<List<Note>>) noteDAO.getNotes();
            }
        });
    }

    @Override
    public void sort(SortType sortType) {
        NoteResolver.sort(noteListLiveData.getValue() , sortType);
    }

    @Override
    public void close() {
        notesDataBaseManager.close();
    }
}
