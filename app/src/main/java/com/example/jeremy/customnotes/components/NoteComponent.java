package com.example.jeremy.customnotes.components;

import com.example.jeremy.customnotes.ui.fragments.NoteEditorFragment;
import com.example.jeremy.customnotes.ui.fragments.NoteListFragment;
import com.example.jeremy.customnotes.utils.modules.NoteModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NoteModule.class})
public interface NoteComponent {
    void inject(NoteEditorFragment noteEditorFragment);
    void inject(NoteListFragment noteListFragment);
}
