package com.abt.basic.app;

import android.app.Application;

import com.abt.mountain.BuildConfig;

/**
 * Author: WeiQi
 * Date: 2019/4/29 16:30
 * Description: BasicApplication
 */
public abstract class BasicApplication extends Application {

    private static BasicApplication sContext;

    public static final BasicApplication getAppContext() {
        return sContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
        init();
        initComplete();
    }

    protected abstract void initComplete();

    private void init() {
        if (BuildConfig.DEBUG) {
            //DebugManager.java.initialize(this);
        }
    }
}

