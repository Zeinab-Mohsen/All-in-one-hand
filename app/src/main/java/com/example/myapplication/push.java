package com.example.myapplication;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class push extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push);
        Button b = (Button)findViewById(R.id.button12);
        EditText t1=(EditText)findViewById(R.id.editTextTextPersonName3);
        EditText t2=(EditText)findViewById(R.id.editTextTextPersonName4);
        EditText t3=(EditText)findViewById(R.id.editTextTextPersonName8);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User u= new User(t1.getText().toString(),t2.getText().toString(),t3.getText().toString());
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("User");
                myRef.push().setValue(u);
                t1.getText().clear();
                t2.getText().clear();
                t3.getText().clear();
                Toast.makeText(push.this, "Added Done in The Database", Toast.LENGTH_SHORT).show();
            }
        });

    }
}