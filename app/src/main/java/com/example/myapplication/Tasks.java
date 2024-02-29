package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Tasks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);
        Button b1,b2,b3;
        b2=(Button) findViewById(R.id.button16);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent i = new Intent(Tasks.this,Zobon_main_page.class);
               startActivity(i);
            }
        });
        b3=(Button) findViewById(R.id.button17);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent i = new Intent (Tasks.this,mowazaf_main_page.class);
              startActivity(i);
            }
        });
    }
}