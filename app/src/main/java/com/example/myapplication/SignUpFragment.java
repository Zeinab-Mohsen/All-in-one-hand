package com.example.myapplication;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUpFragment extends Fragment
{


    //Initializing the variables by their id's to be used later on
    TextView statusText , jobText;
    Spinner statusSpinner, jobSpinner;
    CheckBox checkBox;
    EditText firstName, secondName, nationalId, email, phoneNumber, password, confirmPassword;
    Spinner gender, age, governorate;
    Button signUP;
    boolean check = true, fullData = true;

    //The variables for the database
    FirebaseDatabase rootNode;
    DatabaseReference reference;


    //A function to show the required views if the user choose to sign up as a employee
    public void show(View view)
    {
        statusText.setVisibility(View.VISIBLE);
        statusSpinner.setVisibility(View.VISIBLE);
        jobText.setVisibility(View.VISIBLE);
        jobSpinner.setVisibility(View.VISIBLE);
        check = false;
    }

    //A function to hide the required views if the user choose to sign up as a employee
    public void hide(View view)
    {
        statusText.setVisibility(View.INVISIBLE);
        statusSpinner.setVisibility(View.INVISIBLE);
        jobText.setVisibility(View.INVISIBLE);
        jobSpinner.setVisibility(View.INVISIBLE);
        check = true;
    }

    //Erase the data that the user inputted from the views
    public void eraseData(View view)
    {
        firstName.setText("");
        secondName.setText("");
        nationalId.setText("");
        email.setText("");
        phoneNumber.setText("");
        password.setText("");
        confirmPassword.setText("");
        gender.setSelection(0);
        age.setSelection(0);
        governorate.setSelection(0);
        statusSpinner.setSelection(0);
        jobSpinner.setSelection(0);
        checkBox.setChecked(false);
        fullData = true;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // The returned view to be able to create the sign up fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        //Find the views of the sign up fragment by their id's
        firstName = (EditText) view.findViewById(R.id.firstNameProfile);
        secondName = (EditText) view.findViewById(R.id.secondNameProfile);
        nationalId = (EditText) view.findViewById(R.id.idProfile);
        email = (EditText) view.findViewById(R.id.emailProfile);
        phoneNumber = (EditText) view.findViewById(R.id.phoneProfile);
        password = (EditText) view.findViewById(R.id.passwordProfile);
        confirmPassword = (EditText) view.findViewById(R.id.confirmPasswordProfile);
        gender = (Spinner) view.findViewById(R.id.spinnerGender);
        age =(Spinner) view.findViewById(R.id.spinnerAge);
        governorate = (Spinner) view.findViewById(R.id.spinnerGovernorate);
        statusText = (TextView) view.findViewById(R.id.textViewStatus);
        jobText = (TextView) view.findViewById(R.id.textViewJob);
        statusSpinner = (Spinner) view.findViewById(R.id.spinnerStatus);
        jobSpinner = (Spinner) view.findViewById(R.id.spinnerJob);
        checkBox = (CheckBox) view.findViewById(R.id.checkBox);
        signUP = (Button) view.findViewById(R.id.signUpBtn);


        //The function to make the checkbox work (hide/show the extra attributes for the employee)
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
               if(b)
               {
                   show(view);
               }
               else
               {
                   hide(view);
               }
            }
        });

        //Add the functionality to the button and add the token data to the firebase
        signUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();

                //Check if the user choose to register as a customer or as a an employees to upload their data in the right firebase
                if(check)
                    reference = rootNode.getReference("Zobon");
                else
                    reference = rootNode.getReference("Mowazaf");


                //Get the inputted data by the user to put in the firebase
                String userFirstName = firstName.getText().toString();
                String userSecondName = secondName.getText().toString();
                String userId = nationalId.getText().toString();
                String userEmail = email.getText().toString();
                String userPhone = phoneNumber.getText().toString();
                String userPassword = password.getText().toString();
                String userConfirmPassword = confirmPassword.getText().toString();
                String userAge =age.getSelectedItem().toString();
                String userGender =gender.getSelectedItem().toString();
                String userGovernorate = governorate.getSelectedItem().toString();
                String userJob = jobSpinner.getSelectedItem().toString();
                String userStatus = statusSpinner.getSelectedItem().toString();
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

                if (userConfirmPassword.equals(""))
                {
                    confirmPassword.setError("تأكيد كلمه المرور مطلوبه.");
                    fullData = false;
                }
                else if (!userPassword.equals(userConfirmPassword))
                {
                    confirmPassword.setError("كلمه المرور غير متشابه.");
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

                if(!check) {
                    if (userJob.equals("----")) {
                        fullData = false;
                    }

                    if (userStatus.equals("----")) {
                        fullData = false;
                    }
                }

                //Logging the data to the firebase and show the error toast message to warn the user that some data are not filled
                if(fullData)
                {
                    if (check)
                    {
                        reference.addListenerForSingleValueEvent(new ValueEventListener()
                        {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot)
                            {
                                if(snapshot.child(userId).exists())
                                {
                                    Toast.makeText(getActivity(), "لديك حساب بالفعل برجاء تسجيل الدخول.", Toast.LENGTH_LONG).show();
                                }
                                else
                                {
                                    Zobon zobon = new Zobon(userFirstName, userSecondName, userId, userPassword, userGender,userGovernorate,userEmail, userPhone,userAge,(float)0.0,(float)0.0,(float)0.0);
                                    Toast.makeText(getActivity(), "تم تسجيل حسابك كزبون بنجاح يمكنك الان الدخول لحسابك عن طريق تسجيل الدخول.", Toast.LENGTH_LONG).show();
                                    reference.child(userId).setValue(zobon);
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {}
                        });
                    }
                    else
                    {
                        reference.addListenerForSingleValueEvent(new ValueEventListener()
                        {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot)
                            {
                                if (snapshot.child(userId).exists())
                                {
                                    Toast.makeText(getActivity(), "لديك حساب بالفعل برجاء تسجيل الدخول.", Toast.LENGTH_LONG).show();
                                }
                                else
                                {
                                    Mowazaf mowazaf = new Mowazaf(userFirstName, userSecondName, userId, userPassword, userGender,userGovernorate,userEmail, userPhone, userStatus,userJob,userAge,(float)0.0,(float)0.0,(float)0.0);
                                    Toast.makeText(getActivity(), "تم تسجيل حسابك كموظف بنجاح يمكنك الان الدخول لحسابك عن طريق تسجيل الدخول.", Toast.LENGTH_LONG).show();
                                    reference.child(userId).setValue(mowazaf);
                                }
                            }
                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {}
                        });
                    }
                    eraseData(view);
                }
                else
                {
                    Toast.makeText(getActivity(), "برجاء التحقق من صحه جميع البيانات و محاوله انشاء حساب جديد مره اخري.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
}
