package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class gridView extends AppCompatActivity {

    int c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);
        EditText t= (EditText) findViewById(R.id.editTextTextPersonName);

        GridView g = (GridView) findViewById(R.id.grid);
        Button b = (Button) findViewById(R.id.button3);
        ArrayAdapter<Character> x = new ArrayAdapter<Character>(this, android.R.layout.simple_list_item_1);
        g.setAdapter(x);
        c=0;
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (t.getText().toString().length()>0 ) {
                    Character ch = t.getText().toString().charAt(0);
                    if ((ch >= 'A' && ch <= 'Z')||(ch >= 'a' && ch <= 'z'))
                    {
                        if (c < 12) {
                            c++;
                            x.add(t.getText().toString().charAt(0));
                        } else {
                            Toast.makeText(gridView.this, "Sorry the grid is fall", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                        Toast.makeText(gridView.this, "Wrong Input", Toast.LENGTH_SHORT).show();
                    t.getText().clear();
                }
                else
                    Toast.makeText(gridView.this, "Wrong Input", Toast.LENGTH_SHORT).show();
            }
        });
        g.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Character ch = ((TextView) view).getText().toString().charAt(0);
                if(Character.isUpperCase(ch))
                {
                    ch=Character.toLowerCase(ch);
                }
                else
                    ch = Character.toUpperCase(ch);
                ((TextView) view).setText(Character.toString(ch));
            }
        });
    }
}