package com.example.jeremy.customnotes.utils.modules;

import com.example.jeremy.customnotes.ui.fragments.NoteListFragment;
import com.example.jeremy.customnotes.viewmodels.NoteViewModel;

import javax.inject.Singleton;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import dagger.Module;
import dagger.Provides;

@Module
public class NoteModule {

    @Provides
    @Singleton
    public static NoteViewModel provideNoteViewModel() {
        NoteViewModel viewModel = ViewModelProviders.of(new NoteListFragment()).get(NoteViewModel.class);
//        viewModel.initViewModel(context , handler);
        return viewModel;
    }
}
