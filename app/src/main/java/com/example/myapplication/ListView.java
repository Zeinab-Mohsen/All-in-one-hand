package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ListView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        android.widget.ListView l1 = (android.widget.ListView) findViewById(R.id.listView);
        ArrayAdapter<String> Mylist = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        l1.setAdapter(Mylist);
        Button b = (Button) findViewById(R.id.button5);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView t= (TextView) findViewById(R.id.editTextTextPersonName5);
                String s=t.getText().toString();
                Mylist.add(s);
                t.setText("");
            }
        });
        l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ListView.this, ((TextView)view).getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}