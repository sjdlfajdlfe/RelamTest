package com.demo.usersystem.adapter.holder;

import android.view.ViewGroup;
import android.widget.TextView;

import com.demo.R;
import com.demo.usersystem.entity.User;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by liuwenji on 2017/11/26.
 */

public class UserViewHolder extends BaseViewHolder<User>{

    TextView tv;
    public UserViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_list);
        tv = itemView.findViewById(R.id.tv);
    }

    @Override
    public void setData(User data) {
        super.setData(data);
        tv.setText("name: "+data.getName()+"              "+"age: "+data.getAge());
    }
}
