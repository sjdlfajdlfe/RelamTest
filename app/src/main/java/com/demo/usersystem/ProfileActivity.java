package com.demo.usersystem;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.demo.usersystem.realm.UserRealmManager;

/**
 * Created by liuwenji on 2018/9/19.
 */

public class ProfileActivity  extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        for (int i = 0; i < 10; i++) {
            UserRealmManager.getInstance().insertUser();
        }
    }
}
