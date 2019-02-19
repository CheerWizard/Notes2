package com.example.jeremy.customnotes.dependency_injection.components;

import android.app.Application;

import com.example.jeremy.customnotes.dependency_injection.modules.AppModule;
import com.example.jeremy.customnotes.dependency_injection.providers.ActivityProvider;
import com.example.jeremy.customnotes.ui.CustomNoteApplication;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import dagger.android.support.DaggerApplication;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AppModule.class,
        ActivityProvider.class})
public interface AppComponent extends AndroidInjector<DaggerApplication> {
    void inject(CustomNoteApplication app);

    @Override
    void inject(DaggerApplication instance);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }
}
