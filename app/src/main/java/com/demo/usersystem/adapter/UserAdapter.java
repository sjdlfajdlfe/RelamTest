package com.demo.usersystem.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.demo.usersystem.adapter.holder.UserViewHolder;
import com.demo.usersystem.entity.User;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 * Created by liuwenji on 2017/11/26.
 */

public class UserAdapter extends RecyclerArrayAdapter<User> {

    public UserAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new UserViewHolder(parent);
    }


}
