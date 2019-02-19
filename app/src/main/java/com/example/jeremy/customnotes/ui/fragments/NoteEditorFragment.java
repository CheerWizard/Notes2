package com.example.jeremy.customnotes.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.example.jeremy.customnotes.Contract;
import com.example.jeremy.customnotes.R;
import com.example.jeremy.customnotes.business_logic.data.Note;
import com.example.jeremy.customnotes.ui.CustomNoteApplication;
import com.example.jeremy.customnotes.utils.cache.NoteCategoryCache;
import com.example.jeremy.customnotes.viewmodels.NoteViewModel;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import dagger.android.support.DaggerFragment;

public class NoteEditorFragment extends DaggerFragment implements View.OnClickListener , Contract.IView {

    @Inject
    NoteViewModel noteViewModel;
    //views
    private EditText descriptionEditText;
    private Button saveNoteButton;
    private AutoCompleteTextView categoryAutoCompleteTextView;

    public static NoteEditorFragment getInstance() {
        Bundle args = new Bundle();
        NoteEditorFragment fragment = new NoteEditorFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.note_editor_fragment , container , false);
        initViews(view);
        initVars();
        initListeners();
        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        noteViewModel.close();
    }

    private void initViews(View view) {
        descriptionEditText = view.findViewById(R.id.description_edit_text);
        categoryAutoCompleteTextView = view.findViewById(R.id.category_auto_complete_text_view);
        saveNoteButton = view.findViewById(R.id.save_btn);
    }

    private void initVars() {
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(CustomNoteApplication.getInstance() , android.R.layout.simple_list_item_1 , NoteCategoryCache.getNoteCategoryArray());
        categoryAutoCompleteTextView.setAdapter(arrayAdapter);
        noteViewModel.getNoteListMutableLiveData().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                arrayAdapter.addAll(NoteCategoryCache.getNoteCategoryArray());
            }
        });
    }

    private void initListeners() {
        saveNoteButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.save_btn:
                noteViewModel.onTouchSaveNoteBtn(new Note(descriptionEditText.getText().toString() , categoryAutoCompleteTextView.getText().toString() , new Date()));
                break;
        }
    }
}
