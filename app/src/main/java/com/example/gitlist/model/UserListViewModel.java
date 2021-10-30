package com.example.gitlist.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.gitlist.R;

import java.net.URL;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserListViewModel extends ArrayAdapter<GitUsers> {
    private int resource = 0;
     public UserListViewModel(@NonNull Context context, int resource ,List<GitUsers> data) {

         super(context, resource,data);
         this.resource=resource;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View ListViewItem =convertView;
        if(ListViewItem==null)
        {

            ListViewItem= LayoutInflater.from(getContext()).inflate(resource,parent,false);
        }

        CircleImageView imageViewUser=ListViewItem.findViewById(R.id.imageViewAvatar);
        TextView textViewUsername=ListViewItem.findViewById(R.id.textViewUsername);
        TextView textViewScore=ListViewItem.findViewById(R.id.textViewScore);

        textViewUsername.setText(getItem(position).username);
        textViewScore.setText(String.valueOf(getItem(position).score));
        try {
            URL url=new URL(getItem(position).avatrUrl);
            Bitmap bitmap= BitmapFactory.decodeStream(url.openStream());
            imageViewUser.setImageBitmap(bitmap);

    }catch (Exception e){
            e.printStackTrace();

        }
        return ListViewItem;
    }

}
