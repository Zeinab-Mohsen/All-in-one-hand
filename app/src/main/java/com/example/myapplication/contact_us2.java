package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class contact_us2 extends AppCompatActivity {
    static Mowazaf m;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us2);
        Button CopyPhone,CopyEmail,Back;
        CopyEmail = (Button) findViewById(R.id.copy2);
        CopyPhone = (Button) findViewById(R.id.copy1);
        Back = (Button) findViewById(R.id.backButton);
        TextView phone,email;
        phone =(TextView) findViewById(R.id.Contact_Phone);
        email =(TextView) findViewById(R.id.Contact_Email);

        Intent ii =getIntent();
        String id = ii.getExtras().getString("id");
        DatabaseReference MowazafRef = FirebaseDatabase.getInstance().getReference("Mowazaf").child(id);

        MowazafRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                m = snapshot.getValue(Mowazaf.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        CopyEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("TextView", email.getText().toString());
                clipboardManager.setPrimaryClip(clipData);

                Toast.makeText(contact_us2.this, "تم نسخ الايميل بنجاح", Toast.LENGTH_SHORT).show();
            }
        });

        CopyPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("TextView", phone.getText().toString());
                clipboardManager.setPrimaryClip(clipData);

                Toast.makeText(contact_us2.this, "تم نسخ رقم الهاتف بنجاح", Toast.LENGTH_SHORT).show();

            }
        });

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mowazaf_side_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId())
        {
            case R.id.mowazaf_profile:
                intent = new Intent(contact_us2.this,CustomerProfileActivity.class);
                intent.putExtra("id",m.getID());
                startActivity(intent);
                finish();
                return true;
            case R.id.mowazaf_contact_us:
                Toast.makeText(this, "انت الان في تواصل معنا", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.mowazaf_main_page:
                intent = new Intent(contact_us2.this,mowazaf_main_page.class);
                intent.putExtra("id",m.getID());
                startActivity(intent);
                finish();
                return true;
            case R.id.mowazaf_logout:
                Toast.makeText(this, "تم تسجيل الخروج بنجاح", Toast.LENGTH_SHORT).show();
                intent= new Intent(contact_us2.this,SignInFragment.class);
                startActivity(intent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}