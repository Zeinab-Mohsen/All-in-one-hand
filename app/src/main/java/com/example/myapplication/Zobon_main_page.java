package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Zobon_main_page extends AppCompatActivity {
    RelativeLayout Up_bar;
    static Zobon z = new Zobon();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zobon_main_page);
        Button new_order, Waiting_orders,Done_orders;
        new_order = (Button) findViewById(R.id.button19);
        Waiting_orders = (Button) findViewById(R.id.button21);
        Done_orders = (Button) findViewById(R.id.button20);
        Up_bar= (RelativeLayout) findViewById(R.id.up_bar2);
        ImageView zobon_img = (ImageView) findViewById(R.id.imageView4);
        TextView zobon_name = (TextView) findViewById(R.id.textView20);

        Intent ii =getIntent();
        String id = ii.getExtras().getString("id");
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Zobon");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                z = snapshot.child(id).getValue(Zobon.class);
                if(z.getGender().equals("ذكر"))
                    zobon_img.setBackgroundResource(R.drawable.man);
                else
                    zobon_img.setBackgroundResource(R.drawable.woman);
                zobon_name.setText(z.getFirst_Name() + " " + z.getSecond_Name());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        new_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Zobon_main_page.this,NewRequest.class);
                i.putExtra("id",z.getID());
                startActivity(i);
            }
        });

        Done_orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Zobon_main_page.this,ZobonDoneRequests.class);
                i.putExtra("id",z.getID());
                startActivity(i);
            }
        });
        Waiting_orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Zobon_main_page.this,ZobonWaitingRequests.class);
                i.putExtra("id",z.getID());
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.zobon_side_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId())
        {
            case R.id.zobon_profile:
                intent = new Intent(Zobon_main_page.this,CustomerProfileActivity.class);
                intent.putExtra("id",z.getID());
                startActivity(intent);
                finish();
                return true;

            case R.id.zobon_contact_us:

                intent = new Intent(Zobon_main_page.this,contact_us.class);
                intent.putExtra("id",z.getID());
                startActivity(intent);
                finish();
                return true;

            case R.id.zobon_main_page:
                Toast.makeText(this, "انت الان في الصفحة الرئيسية", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.zobon_logout:
                Toast.makeText(this, "تم تسجيل الخروج بنجاح", Toast.LENGTH_SHORT).show();
                intent= new Intent(Zobon_main_page.this,MainActivity.class);
                startActivity(intent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}