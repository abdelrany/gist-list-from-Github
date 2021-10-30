package com.example.gitlist.service;

import com.example.gitlist.model.GitRepo;
import com.example.gitlist.model.GitUserResponce;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GitRepoServiceAPI {
    @GET("search/users")
    public Call<GitUserResponce> searchUsers(@Query( "q") String query);
    @GET("users/{u}/repos")
    public Call<List<GitRepo>> userRepos(@Path( "u") String query);
}
