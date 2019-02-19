package com.example.jeremy.customnotes.ui.activities;

import android.os.Bundle;
import android.view.View;

import com.example.jeremy.customnotes.R;
import com.example.jeremy.customnotes.ui.fragments.NoteEditorFragment;
import com.example.jeremy.customnotes.ui.fragments.NoteListFragment;
import com.example.jeremy.customnotes.utils.cache.NoteCategoryCache;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import dagger.android.support.DaggerAppCompatActivity;

public class LauncherActivity extends DaggerAppCompatActivity implements View.OnClickListener {

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NoteCategoryCache.removeAll();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_note_floating_btn:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.note_container , NoteEditorFragment.getInstance())
                        .addToBackStack(null)
                        .commitAllowingStateLoss();
                addNoteFloatingActionButton.setEnabled(false);
                addNoteFloatingActionButton.setAlpha(0.0f);
                toListFloatingActionButton.setEnabled(true);
                toListFloatingActionButton.setAlpha(1.0f);
                break;
            case R.id.to_list_floating_btn:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.note_container , NoteListFragment.getInstance())
                        .addToBackStack(null)
                        .commitAllowingStateLoss();
                addNoteFloatingActionButton.setEnabled(true);
                addNoteFloatingActionButton.setAlpha(1.0f);
                toListFloatingActionButton.setEnabled(false);
                toListFloatingActionButton.setAlpha(0.0f);
                break;
        }
    }

    private void initViews() {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.note_container , NoteListFragment.getInstance())
                .commitAllowingStateLoss();
        addNoteFloatingActionButton = findViewById(R.id.add_note_floating_btn);
        toListFloatingActionButton = findViewById(R.id.to_list_floating_btn);
        addNoteFloatingActionButton.setEnabled(false);
        addNoteFloatingActionButton.setAlpha(0.0f);
        toListFloatingActionButton.setEnabled(true);
        toListFloatingActionButton.setAlpha(1.0f);
    }

    private void initListeners() {
        addNoteFloatingActionButton.setOnClickListener(this);
        toListFloatingActionButton.setOnClickListener(this);
    }
}
