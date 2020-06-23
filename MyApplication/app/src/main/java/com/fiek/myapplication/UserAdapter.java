package com.fiek.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends BaseAdapter {
    List<UserModel> dataSource = new ArrayList<>();
    Context c;
    UserViewHolder userViewHolder = null;

    public UserAdapter(Context c)
    {
        this.c = c;
        //for(int i=1;i<150;i++)
        //    dataSource.add(new UserModel(i,"Filan Fisteku"+i));
    }

    @Override
    public int getCount() {
        return dataSource.size();
    }

    @Override
    public Object getItem(int position) {
        return dataSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return dataSource.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;

        if(convertView==null)
        {
            view = LayoutInflater.from(c).inflate(R.layout.user_row_layout,parent,false);
            userViewHolder = new UserViewHolder(view);
            view.setTag(userViewHolder);
        }
        else
        {
            view = convertView;
            userViewHolder = (UserViewHolder) view.getTag();
        }

        userViewHolder.getTvName().setText(dataSource.get(position).getName());

        return view;
    }
}
