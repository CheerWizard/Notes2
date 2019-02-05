package com.example.jeremy.customnotes.ui.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jeremy.customnotes.R;
import com.example.jeremy.customnotes.constants.ProcessStates;
import com.example.jeremy.customnotes.models.Note;
import com.example.jeremy.customnotes.ui.CustomNoteApplication;
import com.example.jeremy.customnotes.utils.cache.NoteCategoryCache;
import com.example.jeremy.customnotes.viewmodels.NoteViewModel;

import java.util.Date;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class NoteEditorFragment extends Fragment implements View.OnClickListener , Handler.Callback {
    //injectable view model class
    @Inject
    NoteViewModel noteViewModel;
    //views
    private EditText descriptionEditText;
    private Button saveNoteButton;
    private AutoCompleteTextView categoryAutoCompleteTextView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((CustomNoteApplication) getActivity().getApplication()).getNoteComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.note_editor_fragment , container , false);
        initViews(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initVars();
        initListeners();
    }

    private void initViews(View view) {
        descriptionEditText = view.findViewById(R.id.description_edit_text);
        categoryAutoCompleteTextView = view.findViewById(R.id.category_auto_complete_text_view);
        saveNoteButton = view.findViewById(R.id.save_btn);
    }

    private void initVars() {
        ArrayAdapter<String> cityArrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1 , NoteCategoryCache.getNoteCategoryArray());
        categoryAutoCompleteTextView.setAdapter(cityArrayAdapter);
    }

    private void initListeners() {
        saveNoteButton.setOnClickListener(this);
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch(msg.what) {
            case ProcessStates.Failed.INSERT_FAILED:
                Toast.makeText(getActivity() , R.string.insert_failed , Toast.LENGTH_SHORT).show();
                break;
            case ProcessStates.Successful.INSERT_SUCCESS:
                Toast.makeText(getActivity() , R.string.insert_success , Toast.LENGTH_SHORT).show();
                break;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.save_btn:
                noteViewModel.onTouchSaveNoteBtn(new Note(descriptionEditText.getText().toString() , categoryAutoCompleteTextView.getText().toString() , new Date(System.currentTimeMillis())));
                break;
        }
    }
}
