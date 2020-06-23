package com.fiek.myapplication;

public class UserModel {
    private int Id;
    private String Name;
    private String Username;

    public UserModel(int id, String name) {
        Id = id;
        Name = name;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }


}
