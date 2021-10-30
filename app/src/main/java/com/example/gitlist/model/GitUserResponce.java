package com.example.gitlist.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GitUserResponce {
    @SerializedName("total_count")
    public  int totaleCount;
    @SerializedName("items")
    public List<GitUsers> user = new ArrayList<>();
}
