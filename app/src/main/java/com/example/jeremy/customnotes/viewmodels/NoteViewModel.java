package com.example.jeremy.customnotes.viewmodels;

import android.content.Context;
import android.os.Handler;

import com.example.jeremy.customnotes.constants.SQLRequestType;
import com.example.jeremy.customnotes.constants.SortType;
import com.example.jeremy.customnotes.models.Note;
import com.example.jeremy.customnotes.services.repositories.INoteRepository;
import com.example.jeremy.customnotes.services.repositories.NoteRepository;
import com.example.jeremy.customnotes.utils.resolvers.NoteResolver;

import java.util.List;

import javax.inject.Singleton;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

@Singleton
public class NoteViewModel extends ViewModel {
    //we use mutable live data to change values inside the same live data
    //shows an easy way to share data between fragments
    private MutableLiveData<List<Note>> noteListMutableLiveData;
    private INoteRepository noteRepository;

    //as this class should be singleton , we require private constructor
    private NoteViewModel() {}

    public void initViewModel(Context context , Handler handler) {
        noteRepository = new NoteRepository(context , handler);
        noteListMutableLiveData = (MutableLiveData<List<Note>>) noteRepository.getNotes();
    }

    public void onTouchSaveNoteBtn(Note note) {
        noteListMutableLiveData.setValue(noteRepository.reload(SQLRequestType.INSERT , note).getValue());
    }

    public void onTouchUpdateNoteBtn(Note note) {
        noteListMutableLiveData.setValue(noteRepository.reload(SQLRequestType.UPDATE , note).getValue());
    }

    public void onTouchDeleteNoteBtn(int position) {
        noteListMutableLiveData.setValue(noteRepository.reload(SQLRequestType.DELETE , position).getValue());
    }

    public void onTouchDeleteAllBtn() {
        noteListMutableLiveData.setValue(noteRepository.reload(SQLRequestType.DELETE).getValue());
    }

    public void onTouchSortBtn(SortType sortType) {
        noteListMutableLiveData.setValue(NoteResolver.sort(noteListMutableLiveData.getValue() , sortType));
    }

    public MutableLiveData<List<Note>> getNoteListMutableLiveData() {
        return noteListMutableLiveData;
    }
}
