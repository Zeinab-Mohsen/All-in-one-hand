package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class get extends AppCompatActivity {
        FirebaseDatabase f;
        boolean yes = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get);
        Button b = (Button) findViewById(R.id.button13);
        GridView g = (GridView) findViewById(R.id.grid);
        ArrayAdapter<String> ad=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        g.setAdapter(ad);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(yes==false) {
                     ArrayList<User> v=new ArrayList<>();
                    FirebaseDatabase.getInstance().getReference("User").addListenerForSingleValueEvent(new ValueEventListener() {

                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                            for(DataSnapshot ss : snapshot.getChildren())
                            {
                                User mod = ss.getValue(User.class);
                                v.add(mod);
                            }
                            for (User u : v) {
                                ad.add(u.getName());
                                ad.add(u.getAge());
                                ad.add(u.getPhone());
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                    yes=true;
                    Toast.makeText(get.this, "Uploaded Done ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(get.this, "You heve been Uploaded the data already", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}