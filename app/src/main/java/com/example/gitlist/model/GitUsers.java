package com.example.gitlist.model;
import com.google.gson.annotations.SerializedName;

public class GitUsers {

    public int id;
@SerializedName("login")
    public String username;
@SerializedName("avatar_url")
    public String avatrUrl;
    public  int score;
}
