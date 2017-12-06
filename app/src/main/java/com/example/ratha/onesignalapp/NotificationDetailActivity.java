package com.example.ratha.onesignalapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.ratha.onesignalapp.entity.User;

public class NotificationDetailActivity extends AppCompatActivity {

    private TextView tvName,tvEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nofication_detail);

        tvName=findViewById(R.id.tvName);
        tvEmail=findViewById(R.id.tvEmail);

        User user=null;
        Intent intent=getIntent();
        if(null!=intent){
            Bundle bundle =intent.getExtras();
            if(null!=bundle)
            user= (User) bundle.getParcelable("USER");
        }
        if(null!=user){
            tvName.setText("Name : "+user.getName());
            tvEmail.setText("Email : "+user.getEmail());
        }

    }
}
