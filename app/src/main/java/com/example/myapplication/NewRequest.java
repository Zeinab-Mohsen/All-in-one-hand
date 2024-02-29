package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NewRequest extends AppCompatActivity {

    DatePickerDialog datePickerDialog;
    Button DateButton;
    Button time;
    Button Ok,No;
    EditText address;
    int hour2, minute2;
    Spinner job_spinner,Government_spinner,Rate_spinner;
    static Zobon z;
    Intent iii;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_request);

        iii = getIntent();
        System.out.println(iii.getExtras().getString("id"));
        String id = iii.getExtras().getString("id");
        DatabaseReference ZobonRef = FirebaseDatabase.getInstance().getReference("Zobon").child(id);

        ZobonRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                z = snapshot.getValue(Zobon.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        job_spinner = (Spinner) findViewById(R.id.job_spinner);
        ArrayAdapter<CharSequence> job_arr = ArrayAdapter.createFromResource(this, R.array.Jobs, android.R.layout.simple_spinner_item);
        job_arr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        job_spinner.setAdapter(job_arr);

        Government_spinner = (Spinner) findViewById(R.id.government_spinner);
        ArrayAdapter<CharSequence> government_arr = ArrayAdapter.createFromResource(this, R.array.Governments, android.R.layout.simple_spinner_item);
        government_arr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Government_spinner.setAdapter(government_arr);

        Rate_spinner = (Spinner) findViewById(R.id.rate_spinner);
        ArrayAdapter<CharSequence> Rate_arr = ArrayAdapter.createFromResource(this, R.array.rate, android.R.layout.simple_spinner_item);
        Rate_arr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Rate_spinner.setAdapter(Rate_arr);

        address = (EditText) findViewById(R.id.editTextTextPersonName6);
        DateButton = (Button) findViewById(R.id.button14);
        initDatePicker();

         time = (Button) findViewById(R.id.time);
         initTimePicker();

         Ok = (Button) findViewById(R.id.Otlob);
         otlob();

         No = (Button) findViewById(R.id.Cancel);
         cancel();

    }

    private void cancel() {
        No.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void otlob() {
        Ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean yes =true;
                if(time.getText().toString().length()==0)
                {
                    yes = false;
                }
                if(DateButton.getText().toString().length()==0)
                {
                    yes = false;
                }
                if(address.getText().toString().length()==0)
                {
                    yes = false;
                }
                if(yes)
                {
                    Intent i = new Intent(NewRequest.this,search.class);
                    i.putExtra("job",job_spinner.getSelectedItem().toString());
                    i.putExtra("rate",Rate_spinner.getSelectedItem().toString());
                    i.putExtra("government",Government_spinner.getSelectedItem().toString());
                    i.putExtra("id",z.getID());
                    i.putExtra("date",DateButton.getText().toString());
                    i.putExtra("time",time.getText().toString());
                    i.putExtra("address",address.getText().toString());
                    startActivity(i);
                }
                else
                    Toast.makeText(NewRequest.this, "Please Complete the empty fields", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initTimePicker() {
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog= new TimePickerDialog(NewRequest.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minute) {

                        String time_selected=hour+":"+minute;
                         hour2 = hour;
                         minute2=minute;
                        SimpleDateFormat f24 =  new SimpleDateFormat("HH:mm");
                        try {
                            Date date =f24.parse(time_selected);
                            SimpleDateFormat f12 = new SimpleDateFormat("hh:mm aa");
                            String Date=f12.format(date);
                            time.setText(Date);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                },12, 0,false
                );
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timePickerDialog.updateTime(hour2,minute2);
                timePickerDialog.show();
            }
        });
    }

    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener= new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month+=1;
                String Date = makeDateString(day,month,year);
                String ArabicDate2=ToArabicNumber(Date);
                DateButton.setText(ArabicDate2);
            }


        };
        Calendar c = Calendar.getInstance();
        int y = c.get(Calendar.YEAR);
        int m = c.get(Calendar.MONTH);
        int d = c.get(Calendar.DAY_OF_MONTH);
        int s = AlertDialog.THEME_HOLO_LIGHT;
        datePickerDialog = new DatePickerDialog(this,s,dateSetListener,y,m,d);
    }
    private String makeDateString(int day, int month, int year) {
        return day + " / " + month + " / " + year;
    }
    public void openDatePicker(View view)
    {
        datePickerDialog.show();
    }


    public String ToArabicNumber(String Date) {
        String ArabicDate = "";
        for (int i = 0; i < Date.length(); i++) {
            if (Date.charAt(i) == '0')
                ArabicDate += '٠';
            else if (Date.charAt(i) == '1')
                ArabicDate += "١";
            else if (Date.charAt(i) == '2')
                ArabicDate += "٢";
            else if (Date.charAt(i) == '3')
                ArabicDate += "٣";
            else if (Date.charAt(i) == '4')
                ArabicDate += "٤";
            else if (Date.charAt(i) == '5')
                ArabicDate += "٥";
            else if (Date.charAt(i) == '6')
                ArabicDate += "٦";
            else if (Date.charAt(i) == '7')
                ArabicDate += "٧";
            else if (Date.charAt(i) == '8')
                ArabicDate += "٨";
            else if (Date.charAt(i) == '9')
                ArabicDate += "٩";
            else
                ArabicDate += Date.charAt(i);
        }
        return ArabicDate;
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
                intent = new Intent(NewRequest.this,CustomerProfileActivity.class);
                intent.putExtra("id",z.getID());
                startActivity(intent);
                finish();
                return true;

            case R.id.zobon_contact_us:

                intent = new Intent(NewRequest.this,contact_us.class);
                intent.putExtra("id",z.getID());
                startActivity(intent);
                finish();
                return true;

            case R.id.zobon_main_page:
                intent = new Intent(NewRequest.this,Zobon_main_page.class);
                intent.putExtra("id",z.getID());
                startActivity(intent);
                finish();
                return true;
            case R.id.zobon_logout:
                Toast.makeText(this, "تم تسجيل الخروج بنجاح", Toast.LENGTH_SHORT).show();
                intent= new Intent(NewRequest.this,MainActivity.class);
                startActivity(intent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}