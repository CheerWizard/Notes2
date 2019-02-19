package com.example.jeremy.customnotes.dependency_injection.modules;

import com.example.jeremy.customnotes.Contract;
import com.example.jeremy.customnotes.ui.fragments.NoteListFragment;
import com.example.jeremy.customnotes.viewmodels.NoteViewModel;

import androidx.lifecycle.ViewModelProviders;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class NoteListFragmentModule {
    @Binds
    abstract Contract.IView provideForecastFragment(NoteListFragment noteListFragment);
    @Provides
    static NoteViewModel provideForecastViewModel(NoteListFragment noteListFragment) {
        return ViewModelProviders.of(noteListFragment).get(NoteViewModel.class);
    }
}
