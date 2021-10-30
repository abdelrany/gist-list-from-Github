package com.example.gitlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.gitlist.model.GitUserResponce;
import com.example.gitlist.model.GitUsers;
import com.example.gitlist.model.UserListViewModel;
import com.example.gitlist.service.GitRepoServiceAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public List<GitUsers> data =new ArrayList<>();
    public static final String USERNAME_PARAM ="username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        final EditText EditTextUser =findViewById(R.id.EditTextUser);
        Button buttonsearch =findViewById(R.id.buttonsearch);

        ListView listviewUser=findViewById(R.id.listviewUser);

        final UserListViewModel ListViewModel=new UserListViewModel(this,R.layout.users_list_view_layout,data);
        listviewUser.setAdapter(ListViewModel);

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();




       buttonsearch.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String q =EditTextUser.getText().toString();
               GitRepoServiceAPI service = retrofit.create(GitRepoServiceAPI.class);
               Call<GitUserResponce> getuserresponceCall =service.searchUsers(q);
               getuserresponceCall.enqueue(
                       new Callback<GitUserResponce>() {
                           @Override
                           public void onResponse(Call<GitUserResponce> call, Response<GitUserResponce> response) {
                               if(!response.isSuccessful()){

                                   Log.i( "error", String.valueOf(response.code()));
                                   return;
                               }
                               GitUserResponce getuserresponce=response.body();
                               for (GitUsers user :getuserresponce.user)
                               {
                                   data.add(user);

                               }
                               ListViewModel.notifyDataSetChanged();
                           }

                           @Override
                           public void onFailure(Call<GitUserResponce> call, Throwable t) {
                               Log.i("error","error on failure");
                               //36.10

                           }
                       }
               );
               listviewUser.setOnItemClickListener(
                       new AdapterView.OnItemClickListener() {
                           @Override
                           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                               String username = data.get(position).username;
                               Intent intent=new Intent(getApplicationContext(),ProfileActivity.class);
                               intent.putExtra(USERNAME_PARAM,username);
                               startActivity(intent);
                           }
                       }
               );



           }
       });



    }
}