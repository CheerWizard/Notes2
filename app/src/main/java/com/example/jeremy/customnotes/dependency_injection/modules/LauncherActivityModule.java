package com.example.jeremy.customnotes.dependency_injection.modules;

import com.example.jeremy.customnotes.ui.activities.LauncherActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class LauncherActivityModule {
    @Provides
    LauncherActivity provideLauncherActivity(LauncherActivity launcherActivity) {
        return launcherActivity;
    }
}
