package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Welcome Username khi Login Vao Home
        SharedPreferences sharedpreferences = getSharedPreferences("share_prefs", Context.MODE_PRIVATE);
        String username = sharedpreferences.getString("username", "").toString();
        Toast.makeText(getApplicationContext(), "Welcome "+username,Toast.LENGTH_SHORT).show();

        //Log Out
        CardView exit = findViewById(R.id.cardExit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.apply();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });

        //Find Doctor
        CardView findDocTor = findViewById(R.id.cardFindDocTor);
        findDocTor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FindDocTorActivity.class));
            }
        });

        //Service
        CardView service= findViewById(R.id.cardService);
        service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ServiceActivity.class));
            }
        });

        //Order Detail
        CardView order = findViewById(R.id.cardOrderDetails);
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, OrderDetailsActivity.class));
            }
        });
    }
}