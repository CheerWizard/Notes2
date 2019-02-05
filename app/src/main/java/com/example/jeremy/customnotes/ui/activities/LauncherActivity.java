package com.example.jeremy.customnotes.ui.activities;

import android.os.Bundle;
import android.view.View;

import com.example.jeremy.customnotes.R;
import com.example.jeremy.customnotes.ui.fragments.NoteEditorFragment;
import com.example.jeremy.customnotes.ui.fragments.NoteListFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;

public class LauncherActivity extends AppCompatActivity implements View.OnClickListener {

    private FloatingActionButton addNoteFloatingActionButton;
    private FloatingActionButton toListFloatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        initViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initListeners();
    }

    private void initViews() {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.note_container , new NoteEditorFragment())
                .commit();
        addNoteFloatingActionButton = findViewById(R.id.add_note_floating_btn);
        toListFloatingActionButton = findViewById(R.id.to_list_floating_btn);
        addNoteFloatingActionButton.setEnabled(false);
        toListFloatingActionButton.setEnabled(true);
    }

    private void initListeners() {
        addNoteFloatingActionButton.setOnClickListener(this);
        toListFloatingActionButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_note_floating_btn:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.note_container , new NoteEditorFragment())
                        .addToBackStack(null)
                        .commit();
                addNoteFloatingActionButton.setEnabled(false);
                toListFloatingActionButton.setEnabled(true);
                break;
            case R.id.to_list_floating_btn:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.note_container , new NoteListFragment())
                        .addToBackStack(null)
                        .commit();
                addNoteFloatingActionButton.setEnabled(true);
                toListFloatingActionButton.setEnabled(false);
                break;
        }
    }
}
