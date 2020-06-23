package com.fiek.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UsersActivity extends AppCompatActivity {

    ListView lvUsers;
    UserAdapter userAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);

        lvUsers = findViewById(R.id.listView);

        userAdapter = new UserAdapter(UsersActivity.this);
        lvUsers.setAdapter(userAdapter);
        lvUsers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(UsersActivity.this,"Rreshti: "+position,Toast.LENGTH_SHORT).show();
            }
        });

        GetDbData();
    }

    public void GetDbData()
    {
        SQLiteDatabase objDb = new Databaza(UsersActivity.this).getReadableDatabase();

        Cursor c = objDb.query(Databaza.Perdoruesit,
                new String[]{Perdoruesi.ID,Perdoruesi.Emri}
                        ,"",new String[]{},"","","","");

        c.moveToFirst();

        while(c.isAfterLast()==false)
        {
            UserModel tempUserModel = new UserModel(
                    c.getInt(0),c.getString(1));

            userAdapter.dataSource.add(tempUserModel);
            c.moveToNext();
        }

        c.close();
        objDb.close();

        userAdapter.notifyDataSetChanged();

    }
}
