package com.example.jeremy.customnotes.ui;

import com.example.jeremy.customnotes.dependency_injection.components.AppComponent;
import com.example.jeremy.customnotes.dependency_injection.components.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

public class CustomNoteApplication extends DaggerApplication {

    private static CustomNoteApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static synchronized CustomNoteApplication getInstance() {
        return instance;
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        AppComponent appComponent = DaggerAppComponent.builder().application(this).build();
        appComponent.inject(this);
        return appComponent;
    }
}
