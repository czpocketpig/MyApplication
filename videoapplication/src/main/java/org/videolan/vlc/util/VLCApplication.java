package org.videolan.vlc.util;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 2017/10/16.
 */

public class VLCApplication extends Application {

    private static VLCApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        sInstance = this;
    }

    public static Context getAppContext() {
        return sInstance;
    }
}
