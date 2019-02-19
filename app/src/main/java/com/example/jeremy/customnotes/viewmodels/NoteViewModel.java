package com.example.jeremy.customnotes.viewmodels;

import com.example.jeremy.customnotes.Contract;
import com.example.jeremy.customnotes.constants.SortType;
import com.example.jeremy.customnotes.business_logic.data.Note;
import com.example.jeremy.customnotes.business_logic.repositories.INoteRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

@Singleton
public class NoteViewModel extends ViewModel implements Contract.IViewModel {
    //we use mutable live data to change values inside the same live data
    //shows an easy way to share data between fragments
    private INoteRepository noteRepository;

    @Inject
    public NoteViewModel(INoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public void onTouchSaveNoteBtn(Note note) {
        noteRepository.insert(note);
    }

    public void onTouchUpdateNoteBtn(Note note) {
        noteRepository.update(note);
    }

    public void onTouchDeleteNoteBtn(int position) {
        noteRepository.delete(position);
    }

    public void onTouchDeleteAllBtn() {
        noteRepository.deleteAll();
    }

    public void onTouchSortBtn(SortType sortType) {
        noteRepository.sort(sortType);
    }

    public MutableLiveData<List<Note>> getNoteListMutableLiveData() {
        return (MutableLiveData<List<Note>>) noteRepository.getNotes();
    }
    @Override
    public void close() {
        noteRepository.close();
    }
}
