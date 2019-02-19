package com.example.jeremy.customnotes.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.jeremy.customnotes.Contract;
import com.example.jeremy.customnotes.R;
import com.example.jeremy.customnotes.constants.SortType;
import com.example.jeremy.customnotes.business_logic.data.Note;
import com.example.jeremy.customnotes.adapters.NoteListViewAdapter;
import com.example.jeremy.customnotes.viewmodels.NoteViewModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import dagger.android.support.DaggerFragment;

public class NoteListFragment extends DaggerFragment implements View.OnClickListener , Contract.IView {

    //view model
    @Inject
    NoteViewModel noteViewModel;
    //adapter
    @Inject
    NoteListViewAdapter noteListViewAdapter;
    //views
    private ListView noteListView;
    private Button deleteAllButton , sortByCategoryButton , sortByDescriptionButton , sortByDateButton;

    //new instance
    public static NoteListFragment getInstance() {
        Bundle args = new Bundle();
        NoteListFragment fragment = new NoteListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.note_list_fragment , container , false);
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
        noteListView = view.findViewById(R.id.note_list_view);
        sortByDateButton = view.findViewById(R.id.by_date);
        sortByDescriptionButton = view.findViewById(R.id.by_description);
        sortByCategoryButton = view.findViewById(R.id.by_category);
        deleteAllButton = view.findViewById(R.id.delete_all_btn);
    }

    private void initVars() {
        noteViewModel.getNoteListMutableLiveData().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                if (notes == null) notes = new ArrayList<>();
                noteListViewAdapter.onUpdateList(notes);
            }
        });
        noteListView.setAdapter(noteListViewAdapter);
    }

    private void initListeners() {
        deleteAllButton.setOnClickListener(this);
        sortByCategoryButton.setOnClickListener(this);
        sortByDescriptionButton.setOnClickListener(this);
        sortByDateButton.setOnClickListener(this);

        noteListViewAdapter.addListener(new NoteListViewAdapter.NoteListEventListener() {

            @Override
            public void onDeleteBtn(int position) {
                noteViewModel.onTouchDeleteNoteBtn(position);
            }

            @Override
            public void onUpdateBtn(int position) {
                noteViewModel.onTouchUpdateNoteBtn(new Note(noteListViewAdapter.getDescriptionEditText().getText().toString() ,
                        noteListViewAdapter.getCategotyAutoCompleteTextView().getText().toString() ,
                        new Date()));
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.by_category:
                noteViewModel.onTouchSortBtn(SortType.BY_CATEGORY);
                break;
            case R.id.by_date:
                noteViewModel.onTouchSortBtn(SortType.BY_DATE);
                break;
            case R.id.by_description:
                noteViewModel.onTouchSortBtn(SortType.BY_DESCRIPTION);
                break;
            case R.id.delete_all_btn:
                noteViewModel.onTouchDeleteAllBtn();
                break;
        }
    }
}
