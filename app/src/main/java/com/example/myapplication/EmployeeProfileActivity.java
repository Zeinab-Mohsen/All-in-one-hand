package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EmployeeProfileActivity extends AppCompatActivity {

    EditText firstName, secondName, nationalId, email, phoneNumber, password;
    TextView defaultName,rate;
    Spinner gender, age, governorate, status, job;
    Button edit, save, cancel, delete;

    Mowazaf mowazaf;

    RelativeLayout relativeLayouts;
    Button yesBtn, noBtn;
    boolean fullData = true;

    public void show(View view)
    {
        edit.setVisibility(View.INVISIBLE);
        delete.setVisibility(View.VISIBLE);
        save.setVisibility(View.VISIBLE);
        cancel.setVisibility(View.VISIBLE);
    }

    public void hide(View view)
    {
        edit.setVisibility(View.VISIBLE);
        delete.setVisibility(View.INVISIBLE);
        save.setVisibility(View.INVISIBLE);
        cancel.setVisibility(View.INVISIBLE);
    }

    public void enable()
    {
        firstName.setEnabled(true);
        firstName.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
        secondName.setEnabled(true);
        secondName.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
        email.setEnabled(true);
        email.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
        phoneNumber.setEnabled(true);
        phoneNumber.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
        password.setEnabled(true);
        password.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
        gender.setEnabled(true);
        age.setEnabled(true);
        governorate.setEnabled(true);
        job.setEnabled(true);
        status.setEnabled(true);
    }

    public void disable()
    {
        firstName.setEnabled(false);
        firstName.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.grey));
        secondName.setEnabled(false);
        secondName.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.grey));
        email.setEnabled(false);
        email.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.grey));
        phoneNumber.setEnabled(false);
        phoneNumber.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.grey));
        password.setEnabled(false);
        password.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.grey));
        gender.setEnabled(false);
        age.setEnabled(false);
        governorate.setEnabled(false);
        job.setEnabled(false);
        status.setEnabled(false);
    }

    public void setting (@NonNull Mowazaf mowazaf)
    {
        ArrayAdapter<CharSequence> genderAdapter = ArrayAdapter.createFromResource(this, R.array.gender, android.R.layout.simple_spinner_item);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(genderAdapter);

        ArrayAdapter<CharSequence> ageAdapter = ArrayAdapter.createFromResource(this, R.array.age, android.R.layout.simple_spinner_item);
        ageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        age.setAdapter(ageAdapter);

        ArrayAdapter<CharSequence> governorateAdapter = ArrayAdapter.createFromResource(this, R.array.governorate, android.R.layout.simple_spinner_item);
        governorateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        governorate.setAdapter(governorateAdapter);

        ArrayAdapter<CharSequence> jobAdapter = ArrayAdapter.createFromResource(this, R.array.jobs, android.R.layout.simple_spinner_item);
        jobAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        job.setAdapter(jobAdapter);

        ArrayAdapter<CharSequence> statusAdapter = ArrayAdapter.createFromResource(this, R.array.status, android.R.layout.simple_spinner_item);
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        status.setAdapter(statusAdapter);


        firstName.setText(mowazaf.getFirst_Name());
        secondName.setText(mowazaf.getSecond_Name());
        nationalId.setText(mowazaf.getID());
        email.setText(mowazaf.getEmail());
        phoneNumber.setText(mowazaf.getPhone_number());
        password.setText(mowazaf.getPassword());
        gender.setSelection(genderAdapter.getPosition(mowazaf.getGender()));
        age.setSelection(ageAdapter.getPosition(mowazaf.getAge()));
        governorate.setSelection(governorateAdapter.getPosition(mowazaf.getGoverment()));
        job.setSelection(jobAdapter.getPosition(mowazaf.getJob()));
        status.setSelection(statusAdapter.getPosition(mowazaf.getStatus()));
        defaultName.setText(mowazaf.getFirst_Name() + " " + mowazaf.getSecond_Name());
        rate.setText(String.valueOf(mowazaf.getRate()));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_profile);

        firstName = (EditText) findViewById(R.id.firstNameProfile);
        secondName = (EditText) findViewById(R.id.secondNameProfile);
        nationalId = (EditText) findViewById(R.id.idProfile);
        email = (EditText) findViewById(R.id.emailProfile);
        phoneNumber = (EditText) findViewById(R.id.phoneProfile);
        password = (EditText) findViewById(R.id.passwordProfile);
        gender = (Spinner) findViewById(R.id.spinnerGender);
        age =(Spinner) findViewById(R.id.spinnerAge);
        governorate = (Spinner) findViewById(R.id.spinnerGovernorate);
        job =(Spinner) findViewById(R.id.spinnerJob);
        status = (Spinner) findViewById(R.id.spinnerStatus);
        edit = (Button) findViewById(R.id.editButton);
        save = (Button) findViewById(R.id.saveButton);
        cancel = (Button) findViewById(R.id.cancelButton);
        delete = (Button) findViewById(R.id.deleteButton);
        defaultName = (TextView) findViewById(R.id.personNameTextView);
        rate = (TextView)findViewById(R.id.ratingTextView);
        disable();

        relativeLayouts = (RelativeLayout) findViewById(R.id.deleteAccountPop);
        relativeLayouts.setVisibility(View.INVISIBLE);

        yesBtn = findViewById(R.id.yes);
        noBtn = findViewById(R.id.no);


        Intent i = getIntent();
        String id = i.getStringExtra("id");
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Mowazaf").child(id);
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    mowazaf = snapshot.getValue(Mowazaf.class);
                    setting(mowazaf);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show(view);
                enable();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hide(view);
                disable();
                setting(mowazaf);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get the inputted data by the user to put in the firebase
                String userFirstName = firstName.getText().toString();
                String userSecondName = secondName.getText().toString();
                String userId = nationalId.getText().toString();
                String userEmail = email.getText().toString();
                String userPhone = phoneNumber.getText().toString();
                String userPassword = password.getText().toString();
                String userAge =age.getSelectedItem().toString();
                String userGender =gender.getSelectedItem().toString();
                String userGovernorate = governorate.getSelectedItem().toString();
                String userJob = job.getSelectedItem().toString();
                String userStatus = status.getSelectedItem().toString();
                String Rate = rate.getText().toString();
                fullData = true;


                //Make sure that the user field all the field with the desired data and shows an error message if the entered data is wrong
                if (userFirstName.equals(""))
                {
                    firstName.setError("الأسم الأول مطلوب.");
                    fullData = false;
                }

                if (userSecondName.equals(""))
                {
                    secondName.setError("الأسم الثاني مطلوب.");
                    fullData = false;
                }

                if (userId.equals(""))
                {
                    nationalId.setError("الرقم القومي مطلوب.");
                    fullData = false;
                }else if (userId.length() != 14)
                {
                    nationalId.setError("الرقم القومي يجب ان يكون ١٤ رقم.");
                    fullData = false;
                }

                if (TextUtils.isEmpty(userEmail))
                {
                    email.setError("البريد الاكتروني مطلوب.");
                    fullData = false;
                }

                if (userPhone.equals(""))
                {
                    phoneNumber.setError("رقم الهاتف مطلوب.");
                    fullData = false;
                }else if (userPhone.length() != 11)
                {
                    phoneNumber.setError("رقم الهاتف يجب ان يكون ١١ رقم.");
                    fullData = false;
                }

                if (userPassword.equals(""))
                {
                    password.setError("كلمه المرور مطلوبه.");
                    fullData = false;
                }else if (userPassword.length() < 6)
                {
                    password.setError("كلمه المرور يجب ان تكون اكبر من ٦ خانات.");
                    fullData = false;
                }

                if (userGender.equals("----"))
                {
                    fullData = false;
                }

                if (userAge.equals("----"))
                {
                    fullData = false;
                }

                if (userGovernorate.equals("----"))
                {
                    fullData = false;
                }

                if (userJob.equals("----")) {
                    fullData = false;
                }

                if (userStatus.equals("----")) {
                    fullData = false;
                }

                if(fullData)
                {
                    Mowazaf updatedMowazaf = new Mowazaf(userFirstName, userSecondName, userId, userPassword, userGender,userGovernorate,userEmail, userPhone, userStatus,userJob,userAge,(float)0.0,(float)0.0,(float)0.0);
                    mowazaf = updatedMowazaf;
                    Toast.makeText(EmployeeProfileActivity.this, "تم تحديث حسابك بنجاح.", Toast.LENGTH_LONG).show();
                    databaseReference.setValue(updatedMowazaf);
                    defaultName.setText(userFirstName + " " + userSecondName);
                    hide(view);
                    disable();
                }
                else
                {
                    Toast.makeText(EmployeeProfileActivity.this, "برجاء التحقق من صحه جميع البيانات و محاوله الحفظ مره اخري.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                relativeLayouts.bringToFront();
                relativeLayouts.setVisibility(View.VISIBLE);
            }
        });

        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.getRef().removeValue();
                Intent intent = new Intent(EmployeeProfileActivity.this, SignInFragment.class);
                startActivity(intent);
                finish();
            }
        });

        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                relativeLayouts.setVisibility(View.INVISIBLE);
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
                Toast.makeText(this, "انت الان في ملفك الشخصي", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.mowazaf_contact_us:
                intent = new Intent(EmployeeProfileActivity.this,contact_us2.class);
                intent.putExtra("id",mowazaf.getID());
                startActivity(intent);
                finish();
                return true;
            case R.id.mowazaf_main_page:
                intent = new Intent(EmployeeProfileActivity.this,mowazaf_main_page.class);
                intent.putExtra("id",mowazaf.getID());
                startActivity(intent);
                finish();
                return true;
            case R.id.mowazaf_logout:
                Toast.makeText(this, "تم تسجيل الخروج بنجاح", Toast.LENGTH_SHORT).show();
                intent= new Intent(EmployeeProfileActivity.this,SignInFragment.class);
                startActivity(intent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}