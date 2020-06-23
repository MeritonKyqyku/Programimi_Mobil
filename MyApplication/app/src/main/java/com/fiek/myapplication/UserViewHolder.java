package com.fiek.myapplication;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class UserViewHolder {
    TextView tvName;

    public UserViewHolder(View v)
    {
        tvName = v.findViewById(R.id.tvName);
    }

    public TextView getTvName() {
        return tvName;
    }

    public void setTvName(TextView tvName) {
        this.tvName = tvName;
    }


}
