package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Zezo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zezo);
        Button b = (Button) findViewById(R.id.button);
        TextView t = (TextView) findViewById(R.id.textView2);
        Intent i = getIntent();
        String s=i.getExtras().getString("Name");

        t.setText("Welcome "+s);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Zezo.this,"Bye "+s,Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}