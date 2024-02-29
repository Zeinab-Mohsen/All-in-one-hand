package com.example.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignInFragment extends Fragment
{
    //Initializing the variables by their id's to be used later on
    CheckBox checkBox;
    EditText password, nationalId;
    Button login ;
    RadioButton customer, employee;
    String userId, userPassword;
    boolean fullData = true;

    //The variables for the database
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    //Erase the data that the user inputted from the views
    public void eraseData(View view)
    {
        customer.setChecked(true);
        employee.setChecked(false);
        nationalId.setText("");
        password.setText("");
        checkBox.setChecked(false);
        fullData = true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // The returned view to be able to create the sign in fragment
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);

        //Find the views of the sign in fragment by their id's
        customer = (RadioButton) view.findViewById(R.id.radioButtonCustomer);
        employee = (RadioButton) view.findViewById(R.id.radioButtonEmployee);
        nationalId = (EditText) view.findViewById(R.id.idSignIn);
        password = (EditText) view.findViewById(R.id.passwordSignIn);
        checkBox = (CheckBox) view.findViewById(R.id.checkBoxShowPassword);
        login = (Button) view.findViewById(R.id.signInButton);



        //Show/hide password
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else
                {
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        //Add the functionality to the button and search for the user's data in the firebase
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode = FirebaseDatabase.getInstance();


                //Get the inputted data by the user to search for in the firebase
                userId = nationalId.getText().toString();
                userPassword = password.getText().toString();
                fullData = true;

                //Make sure that the user field all the field with the desired data and shows an error message if the entered data is wrong
                if (userId.equals(""))
                {
                    nationalId.setError("الرقم القومي مطلوب.");
                    fullData = false;
                }else if (userId.length() != 14)
                {
                    nationalId.setError("الرقم القومي يجب ان يكون ١٤ رقم.");
                    fullData = false;
                }

                if (userPassword.equals("")) {
                    password.setError("كلمه المرور مطلوبه.");
                    fullData = false;
                }


                //Checking the data from the firebase and show the error toast message to warn the user that some data are not filled
                if(fullData)
                {
                    if(customer.isChecked())
                    {
                        reference = rootNode.getReference("Zobon");
                        reference.addListenerForSingleValueEvent(new ValueEventListener()
                        {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot)
                            {
                                if(snapshot.child(userId).exists())
                                {
                                    for(DataSnapshot ds : snapshot.child(userId).getChildren())
                                    {
                                        if(ds.getKey().equals("password"))
                                        {
                                            if(ds.getValue().equals(userPassword))
                                            {
                                                Toast.makeText(getActivity(), "مرحبا بك الي حسابك.", Toast.LENGTH_LONG).show();
                                                Intent i = new Intent(getActivity(), Zobon_main_page.class);
                                                i.putExtra("id",userId);
                                                startActivity(i);
                                                eraseData(view);
                                            }
                                            else
                                            {
                                                Toast.makeText(getActivity(), "كلمه المرور غير صحيحه برجاء ادخال كلمه المرور الصحيحه و اعاده المحاوله.", Toast.LENGTH_LONG).show();
                                                break;
                                            }
                                        }
                                    }
                                }
                                else
                                {
                                    Toast.makeText(getActivity(), "لا يوجد حساب مسجل كزبون بهذا الرقم القومي برجاء انشاء حساب.", Toast.LENGTH_LONG).show();
                                    eraseData(view);
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {}
                        });
                    }
                    else
                    {
                        reference = rootNode.getReference("Mowazaf");
                        reference.addListenerForSingleValueEvent(new ValueEventListener()
                        {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot)
                            {
                                if(snapshot.child(userId).exists())
                                {
                                    for(DataSnapshot ds : snapshot.child(userId).getChildren())
                                    {
                                        if(ds.getKey().equals("password"))
                                        {
                                            if(ds.getValue().equals(userPassword))
                                            {
                                                Toast.makeText(getActivity(), "مرحبا بك الي حسابك.", Toast.LENGTH_LONG).show();
                                                Intent i = new Intent(getActivity(), mowazaf_main_page.class);
                                              //  i.putExtra("type", "mowazaf");
                                                i.putExtra("id",userId);
                                                startActivity(i);
                                                eraseData(view);
                                            }
                                            else
                                            {
                                                Toast.makeText(getActivity(), "كلمه المرور غير صحيحه برجاء ادخال كلمه المرور الصحيحه و اعاده المحاوله.", Toast.LENGTH_LONG).show();
                                                break;
                                            }
                                        }
                                    }
                                }
                                else
                                {
                                    Toast.makeText(getActivity(), "لا يوجد حساب مسجل كموظف بهذا الرقم القومي برجاء انشاء حساب.", Toast.LENGTH_LONG).show();
                                    eraseData(view);
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {}
                        });
                    }
                }
                else
                {
                    Toast.makeText(getActivity(), "برجاء التحقق من صحه جميع البيانات و محاوله الدخول لحسابك مره اخري.", Toast.LENGTH_LONG).show();
                }
            }
        });
        return view;
    }
}
