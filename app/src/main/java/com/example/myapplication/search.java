package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class search extends AppCompatActivity {
    HashMap<Integer,Mowazaf> MyMap;
    RelativeLayout r;
    Mowazaf m2;
    static Zobon z;
    static Intent iii;
    Integer c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        MyMap = new HashMap<Integer,Mowazaf>();
        m2 = new Mowazaf();
        android.widget.ListView l;
        r=(RelativeLayout) findViewById(R.id.pop);
        r.setVisibility(View.INVISIBLE);
        l = (android.widget.ListView) findViewById(R.id.Mowazaf_List);

        iii = getIntent();
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

        ArrayAdapter<String> ad = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        l.setAdapter(ad);
        ArrayList<Mowazaf> v = new ArrayList<>();
        FirebaseDatabase.getInstance().getReference("Mowazaf").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot ss : snapshot.getChildren()) {
                    Mowazaf mod = ss.getValue(Mowazaf.class);
                    v.add(mod);
                }
                Intent i = getIntent();
                c=new Integer(0);
                for (Mowazaf u : v)
                {
                   if((i.getExtras().getString("job").equals(u.getJob())) && (i.getExtras().getString("government").equals(u.getGoverment())) && Float.parseFloat(i.getExtras().getString("rate"))<=u.getRate()&&u.getStatus().equals("متاح"))
                   {

                       DatabaseReference reference = FirebaseDatabase.getInstance().getReference("zobon_waiting_requests");
                       reference.addListenerForSingleValueEvent(new ValueEventListener() {
                           @Override
                           public void onDataChange(@NonNull DataSnapshot snapshot) {
                               if(snapshot.child(u.getID()).exists())
                               {
                                   if(!snapshot.child(u.getID()).child("zobon_id").getValue().equals(z.getID()))
                                   {
                                       ad.add(u.getFirst_Name()+" "+u.getSecond_Name());
                                       MyMap.put(c,u);
                                       c++;
                                   }

                               }
                               else
                               {
                                   ad.add(u.getFirst_Name()+" "+u.getSecond_Name());
                                   MyMap.put(c,u);
                                   c++;
                               }
                           }

                           @Override
                           public void onCancelled(@NonNull DatabaseError error) {

                           }
                       });

                   }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view2, int i, long l) {
                information_display(i);
            }
        });
    }

    private void information_display(int i) {
        Button ok_button = (Button) findViewById(R.id.OrderTheMowazaf);
        Button cancel_button = (Button) findViewById(R.id.CancelTheMowazaf);
        TextView mwzf_fullName = (TextView) findViewById(R.id.mowazaf_fullName2);
        TextView mwzf_job = (TextView) findViewById(R.id.mowazaf_job2);
        TextView mwzf_phoneNumber = (TextView) findViewById(R.id.mowazaf_phoneNumber2);
        TextView mwzf_rate = (TextView) findViewById(R.id.mowazaf_rate2);
        ImageView mwzf_img;
        mwzf_img = (ImageView) findViewById(R.id.MowazafImage);
        m2 = MyMap.get((Integer) i);
        String ss = ToArabicNumber(m2.getFirst_Name());
        String ss2 = ToArabicNumber(m2.getSecond_Name());
        mwzf_fullName.setText(ss+" "+ss2);
      //  Toast.makeText(search.this, mwzf_fullName.getText().toString(), Toast.LENGTH_SHORT).show();
        mwzf_job.setText((CharSequence) m2.getJob());
        mwzf_phoneNumber.setText(ToArabicNumber(m2.getPhone_number()));
        String rateS=From_Float_to_String(m2.getRate());
        mwzf_rate.setText("" + ToArabicNumber(rateS));
        if(m2.getJob().equals("كهربائي"))
            mwzf_img.setBackgroundResource(R.drawable.electrical_engineer);
        else if(m2.getJob().equals("سباك"))
            mwzf_img.setBackgroundResource(R.drawable.plumber);
        else if(m2.getJob().equals("حداد"))
            mwzf_img.setBackgroundResource(R.drawable.blacksmith);
        else if(m2.getJob().equals("جليس أطفال"))
            mwzf_img.setBackgroundResource(R.drawable.baby_setter);
        else if(m2.getJob().equals("دكتور"))
            mwzf_img.setBackgroundResource(R.drawable.doctor);
        else if(m2.getJob().equals("ممرض"))
            mwzf_img.setBackgroundResource(R.drawable.nurse);
        else if(m2.getJob().equals("معلم"))
            mwzf_img.setBackgroundResource(R.drawable.teacher);
        else if(m2.getJob().equals("ميكانيكي"))
            mwzf_img.setBackgroundResource(R.drawable.mechanic);
        else if(m2.getJob().equals("نجار"))
            mwzf_img.setBackgroundResource(R.drawable.carpenter);
        else if(m2.getJob().equals("نقاش"))
            mwzf_img.setBackgroundResource(R.drawable.na2a4);
        r.bringToFront();
        r.setVisibility(View.VISIBLE);
        ok_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(search.this, "لقد تم ارسال الطلب بنجاح", Toast.LENGTH_SHORT).show();
                Intent ii = new Intent(search.this,Zobon_main_page.class);
                Request request = new Request(z,String.valueOf(m2.getRate()),m2.getJob(),m2.getGoverment(), iii.getExtras().getString("date"),iii.getExtras().getString("time") ,iii.getExtras().getString("address") , m2.getStatus(),false);
                zobon_waiting_requests zdr = new zobon_waiting_requests();
                zdr.setZobon_id(z.getID());
                zdr.setRequest(request);
                zdr.setMowazaf(m2);
                zdr.setAll();

                mowazaf_sent_requests msr = new mowazaf_sent_requests();
                msr.setRequest(request);
                msr.setZobon(z);
                msr.setMowazaf_id(m2.getID());
                msr.setAll();
                DatabaseReference d = FirebaseDatabase.getInstance().getReference("mowazaf_sent_requests");
                d.child(z.getID()).setValue(msr);

                zobon_waiting_requests zwr = new zobon_waiting_requests();
                zwr.setMowazaf(m2);
                zwr.setRequest(request);
                zwr.setZobon_id(z.getID());
                zwr.setAll();
                d = FirebaseDatabase.getInstance().getReference("zobon_waiting_requests");
                d.child(m2.getID()).setValue(zwr);
                Toast.makeText(search.this, "تم ارسال الطلب بنجاح", Toast.LENGTH_SHORT).show();
                ii.putExtra("id",z.getID());
                startActivity(ii);
                finish();
            }

        });
        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                r.setVisibility(View.INVISIBLE);
            }
        });

    }

    public String From_Float_to_String(float rate) {
        String s = String.valueOf(rate);
        return s;
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
                intent = new Intent(search.this,CustomerProfileActivity.class);
                intent.putExtra("id",z.getID());
                startActivity(intent);
                finish();
                return true;

            case R.id.zobon_contact_us:

                intent = new Intent(search.this,contact_us.class);
                intent.putExtra("id",z.getID());
                startActivity(intent);
                finish();
                return true;

            case R.id.zobon_main_page:
                //Toast.makeText(this, "انت الان في الصفحة الرئيسية", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(search.this,Zobon_main_page.class);
                i.putExtra("id",z.getID());
                startActivity(i);
                finish();
                return true;
            case R.id.zobon_logout:
                Toast.makeText(this, "تم تسجيل الخروج بنجاح", Toast.LENGTH_SHORT).show();
                Intent ii = new Intent(search.this,MainActivity.class);
                startActivity(ii);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}