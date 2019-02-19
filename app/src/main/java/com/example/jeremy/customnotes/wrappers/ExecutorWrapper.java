package com.example.jeremy.customnotes.wrappers;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ExecutorWrapper {

    private Executor executor;

    @Inject
    public ExecutorWrapper() {
        executor = Executors.newSingleThreadExecutor();
    }

    public void execute(Runnable runnable) {
        executor.execute(runnable);
    }
}
