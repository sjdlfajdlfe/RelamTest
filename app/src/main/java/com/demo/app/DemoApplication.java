package com.demo.app;

import android.app.Application;
import android.content.Context;

import io.realm.Realm;

/**
 * Created by liuwenji on 2017/11/26.
 */

public class DemoApplication extends Application {

    private static DemoApplication demoApplication;
    @Override
    public void onCreate() {
        super.onCreate();
        //Realm.init(DemoApplication.getContext());
        Realm.init(this);
        if (demoApplication == null){
            demoApplication = this;
        }
    }

    public static Context getContext(){
        return demoApplication;
    }
}
