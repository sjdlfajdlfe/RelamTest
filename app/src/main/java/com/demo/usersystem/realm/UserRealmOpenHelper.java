package com.demo.usersystem.realm;

import com.demo.app.DemoApplication;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by liuwenji on 2017/11/26.
 */

public class UserRealmOpenHelper {

    private static final String REALM_NAME = "user";
    private static final long VERSION = 1l;
    private static UserRealmOpenHelper helper;

    private UserRealmOpenHelper(){}

    public static UserRealmOpenHelper getInstance(){
        if (helper == null){
            helper = new UserRealmOpenHelper();
        }
        return helper;
    }

    public Realm getRealm(){
        Realm realm = Realm.getInstance(new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .name(REALM_NAME)
                .schemaVersion(VERSION)
                .build());
        return realm;
    }
}
