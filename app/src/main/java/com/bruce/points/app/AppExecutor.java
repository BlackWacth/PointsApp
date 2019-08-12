package com.bruce.points.app;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppExecutor {

    private final ExecutorService mCachedThreadPool;
    private final ExecutorService mIOThreadPool;
    private final ExecutorService mSingleThreadPool;

    private static AppExecutor sInstance;

    private AppExecutor() {
        mSingleThreadPool = Executors.newSingleThreadExecutor();
        mCachedThreadPool = Executors.newCachedThreadPool();
        mIOThreadPool = Executors.newFixedThreadPool(5);
    }

    public static AppExecutor getInstance() {
        if (sInstance == null) {
            synchronized (AppExecutor.class) {
                if (sInstance == null) {
                    sInstance = new AppExecutor();
                }
            }
        }
        return sInstance;
    }

    public ExecutorService getCachedThreadPool() {
        return mCachedThreadPool;
    }

    public ExecutorService getIOThreadPool() {
        return mIOThreadPool;
    }

    public ExecutorService getSingleThreadPool() {
        return mSingleThreadPool;
    }
}
