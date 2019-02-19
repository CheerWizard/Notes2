package com.example.jeremy.customnotes.dependency_injection.providers;

import com.example.jeremy.customnotes.dependency_injection.modules.LauncherActivityModule;
import com.example.jeremy.customnotes.ui.activities.LauncherActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityProvider {
    @ContributesAndroidInjector(modules = {LauncherActivityModule.class, FragmentProvider.class})
    abstract LauncherActivity bindLauncherActivity();
}
