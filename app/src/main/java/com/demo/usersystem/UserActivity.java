package com.demo.usersystem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.demo.R;
import com.demo.usersystem.adapter.UserAdapter;
import com.demo.usersystem.entity.User;
import com.demo.usersystem.realm.UserRealmManager;
import com.jude.easyrecyclerview.EasyRecyclerView;

import java.util.List;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

/**
 * Created by liuwenji on 2017/11/26.
 */

public class UserActivity extends Activity{

    private String TAG = UserActivity.class.getSimpleName();
    EasyRecyclerView recyclerView;
    Button insert_btn;
    Button delete_btn;
    Button update_btn;
    Button query_btn;
    Button other_thread;
    LinearLayoutManager layoutManager;
    UserAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        recyclerView = findViewById(R.id.recycler_view);
        insert_btn = findViewById(R.id.insert_btn);
        delete_btn = findViewById(R.id.delete_btn);
        update_btn = findViewById(R.id.update_btn);
        query_btn = findViewById(R.id.query_btn);
        other_thread = findViewById(R.id.other_thread);
        init();
    }

    private void init(){
        adapter = new UserAdapter(this);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        insert_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();
            }
        });
        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete();
            }
        });
        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
            }
        });
        query_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                query();
            }
        });
        other_thread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserActivity.this,ProfileActivity.class));
            }
        });

        UserRealmManager.getInstance().getRealm().executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                while (true) {
                    Log.d(TAG,"time_01= " + System.currentTimeMillis());
                    User user = realm.createObject(User.class, UUID.randomUUID().toString());
                    user.setAge(25);
                    user.setName("liu");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        UserRealmManager.getInstance().getRealm().executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                while (true) {
                    Log.d(TAG,"time_02= " + System.currentTimeMillis());
                    RealmResults<User> users = realm.where(User.class).findAll();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        UserRealmManager.getInstance().getRealm().executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                while (true) {
                    Log.d(TAG,"time_03= " + System.currentTimeMillis());
                    RealmResults<User> users = realm.where(User.class).findAll();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        addRealmChangeListener();
    }

    private void addRealmChangeListener(){
//        UserRealmManager.getInstance().addRealmChangeListener(new RealmChangeListener<Realm>() {
//            @Override
//            public void onChange(Realm realm) {
//                query();
//            }
//        });
    }
    private void insert(){
        UserRealmManager.getInstance().insertUser();
    }

    private void delete(){
        UserRealmManager.getInstance().deleteUser();
    }

    private void update(){
        UserRealmManager.getInstance().updateUser();
    }

    private void query(){
        List<User> users = UserRealmManager.getInstance().queryUserAll();
        adapter.clear();
        if (users.size() > 0){
            adapter.addAll(users);
        }
    }
}
