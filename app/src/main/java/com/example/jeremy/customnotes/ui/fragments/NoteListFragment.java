package com.example.jeremy.customnotes.ui.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jeremy.customnotes.R;
import com.example.jeremy.customnotes.constants.ProcessStates;
import com.example.jeremy.customnotes.constants.SortType;
import com.example.jeremy.customnotes.models.Note;
import com.example.jeremy.customnotes.ui.CustomNoteApplication;
import com.example.jeremy.customnotes.utils.adapters.NoteListViewAdapter;
import com.example.jeremy.customnotes.viewmodels.NoteViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

public class NoteListFragment extends Fragment implements View.OnClickListener , Handler.Callback {
    //injectable class view model
    @Inject NoteViewModel noteViewModel;

    private ListView noteListView;
    private FloatingActionButton deleteAllNotesFloatingActionButton;
    private NoteListViewAdapter noteListViewAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((CustomNoteApplication) getActivity().getApplication()).getNoteComponent().inject(this);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.note_list_fragment , container , false);
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
        noteListView = view.findViewById(R.id.note_list_view);
        deleteAllNotesFloatingActionButton = view.findViewById(R.id.delete_all_floating_btn);
    }

    private void initVars() {
        noteListViewAdapter = new NoteListViewAdapter(getActivity());
        noteViewModel.getNoteListMutableLiveData().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                noteListViewAdapter.onUpdateList(notes);
            }
        });
        noteListView.setAdapter(noteListViewAdapter);
    }

    private void initListeners() {
        deleteAllNotesFloatingActionButton.setOnClickListener(this);
        noteListViewAdapter.addListener(new NoteListViewAdapter.NoteListEventListener() {

            @Override
            public void onDeleteBtn(int position) {
                noteViewModel.onTouchDeleteNoteBtn(position);
            }

            @Override
            public void onUpdateBtn(int position) {
                noteViewModel.onTouchUpdateNoteBtn(new Note(noteListViewAdapter.getDescriptionEditText().getText().toString() ,
                        noteListViewAdapter.getCategoryEditText().getText().toString() ,
                        new Date(noteListViewAdapter.getDateTextView().getText().toString())));
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.note_sort_menu , menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
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
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case ProcessStates.Successful.DELETE_ALL_SUCCESS:
                Toast.makeText(getActivity() , R.string.delete_all_success , Toast.LENGTH_SHORT).show();
                break;
            case ProcessStates.Failed.DELETE_ALL_FAILED:
                Toast.makeText(getActivity() , R.string.delete_all_failed , Toast.LENGTH_SHORT).show();
                break;
            case ProcessStates.Successful.DELETE_SUCCESS:
                Toast.makeText(getActivity() , R.string.delete_success , Toast.LENGTH_SHORT).show();
                break;
            case ProcessStates.Successful.UPDATE_SUCCESS:
                Toast.makeText(getActivity() , R.string.update_success , Toast.LENGTH_SHORT).show();
                break;
            case ProcessStates.Failed.DELETE_FAILED:
                Toast.makeText(getActivity() , R.string.delete_failed , Toast.LENGTH_SHORT).show();
                break;
            case ProcessStates.Failed.UPDATE_FAILED:
                Toast.makeText(getActivity() , R.string.update_failed , Toast.LENGTH_SHORT).show();
                break;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.delete_all_btn:
                noteViewModel.onTouchDeleteAllBtn();
                break;
        }
    }
}
