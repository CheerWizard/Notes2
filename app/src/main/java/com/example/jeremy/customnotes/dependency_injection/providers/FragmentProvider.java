package com.example.jeremy.customnotes.dependency_injection.providers;

import com.example.jeremy.customnotes.dependency_injection.modules.NoteEditorFragmentModule;
import com.example.jeremy.customnotes.dependency_injection.modules.NoteListFragmentModule;
import com.example.jeremy.customnotes.ui.fragments.NoteEditorFragment;
import com.example.jeremy.customnotes.ui.fragments.NoteListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentProvider {
    @ContributesAndroidInjector(modules = NoteEditorFragmentModule.class)
    abstract NoteEditorFragment provideNoteEditorFragment();
    @ContributesAndroidInjector(modules = NoteListFragmentModule.class)
    abstract NoteListFragment provideNoteListFragment();
}
