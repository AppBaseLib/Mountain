package com.abt.mountain.debug;


import android.app.Application;
import android.os.StrictMode;

/**
 * Author: WeiQi
 * Date: 2019/4/29 16:30
 * Description: DebugManager
 */
public class DebugManager {

    public static void initialize(Application application) {
        //initLeakCanary(application);
        initStrictMode();
    }

    private static void initStrictMode() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()
                .detectAll()
                .penaltyLog()
                .penaltyDeath()
                .build());
    }

    /*private static void initLeakCanary(Application application) {
        if (LeakCanary.isInAnalyzerProcess(application)) {
            return;
        }
        LeakCanary.install(application);
    }*/
}
