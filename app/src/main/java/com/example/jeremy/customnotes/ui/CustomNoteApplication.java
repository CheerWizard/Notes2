package com.example.jeremy.customnotes.ui;

import android.app.Application;

import com.example.jeremy.customnotes.components.DaggerNoteComponent;
import com.example.jeremy.customnotes.components.NoteComponent;

public class CustomNoteApplication extends Application {

    private NoteComponent noteComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        noteComponent = DaggerNoteComponent.create();
    }

    public NoteComponent getNoteComponent() {
        return noteComponent;
    }

}
