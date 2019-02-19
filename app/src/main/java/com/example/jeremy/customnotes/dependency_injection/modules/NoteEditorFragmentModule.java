package com.example.jeremy.customnotes.dependency_injection.modules;

import com.example.jeremy.customnotes.Contract;
import com.example.jeremy.customnotes.ui.fragments.NoteEditorFragment;
import com.example.jeremy.customnotes.viewmodels.NoteViewModel;

import androidx.lifecycle.ViewModelProviders;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class NoteEditorFragmentModule {
    @Binds
    abstract Contract.IView provideNoteEditorFragment(NoteEditorFragment noteEditorFragment);
    @Provides
    static NoteViewModel provideNoteViewModel(NoteEditorFragment noteEditorFragment) {
        return ViewModelProviders.of(noteEditorFragment).get(NoteViewModel.class);
    }
}
