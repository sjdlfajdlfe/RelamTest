package com.demo.usersystem.realm;

import com.demo.usersystem.entity.User;

import java.util.List;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

/**
 * Created by liuwenji on 2017/11/26.
 */

public class UserRealmManager {

    private static UserRealmManager manager;
    private Realm realm;

    public UserRealmManager(){
        realm = UserRealmOpenHelper.getInstance().getRealm();
    }

    public static UserRealmManager getInstance(){
        if (manager == null){
            manager = new UserRealmManager();
        }
        return manager;
    }


    public Realm getRealm() {
        return realm;
    }

    public void addRealmChangeListener(RealmChangeListener changeListener){
        realm.addChangeListener(changeListener);
    }

    public void insertUser(){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                User user = realm.createObject(User.class,UUID.randomUUID().toString());
                user.setAge(25);
                user.setName("liu");
            }
        });
    }

    public void deleteUser(){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                User user = realm.where(User.class).findFirst();
                user.deleteFromRealm();
            }
        });
    }

    public void updateUser(){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                User user = realm.where(User.class).findFirst();
                user.setName("哇哈哈");
            }
        });
    }

    public List<User> queryUserAll(){
        RealmResults<User> users = realm.where(User.class).findAll();
        return realm.copyFromRealm(users);
    }
}
