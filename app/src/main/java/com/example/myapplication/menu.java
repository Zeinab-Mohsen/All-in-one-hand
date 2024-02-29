package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuView;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        TextView t = (TextView) findViewById(R.id.textView8);
        registerForContextMenu(t);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.contextmenu,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.itemC1:
                Toast.makeText(this, item.getTitle().toString(), Toast.LENGTH_SHORT).show();
                return true;

            case R.id.itemC2:

                Toast.makeText(this, item.getTitle().toString(), Toast.LENGTH_SHORT).show();
                return true;

            case R.id.itemC3:
                Toast.makeText(this, item.getTitle().toString(), Toast.LENGTH_SHORT).show();
                return true;

            case R.id.itemC4:
                Toast.makeText(this, item.getTitle().toString(), Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.item1:
                Toast.makeText(this, item.getTitle().toString(), Toast.LENGTH_SHORT).show();
                return true;

            case R.id.item2:

                Toast.makeText(this, item.getTitle().toString(), Toast.LENGTH_SHORT).show();
                return true;

            case R.id.item3:
                Toast.makeText(this, item.getTitle().toString(), Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}