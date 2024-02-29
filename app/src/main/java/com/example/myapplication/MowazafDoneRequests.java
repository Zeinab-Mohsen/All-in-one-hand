package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class MowazafDoneRequests extends AppCompatActivity {

    HashMap<Integer, mowazaf_done_requests> MyMap2;
    mowazaf_done_requests m2 = new mowazaf_done_requests();
    RelativeLayout relativeLayouts;
    static Mowazaf m;
    //Done_Requests d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mowazaf_done_requests);

        ArrayList<mowazaf_done_requests> lists2 = new ArrayList<>();
        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1);
        MyMap2 = new HashMap<Integer, mowazaf_done_requests>();
        relativeLayouts = (RelativeLayout) findViewById(R.id.WRpopup);
        relativeLayouts.setVisibility(View.INVISIBLE);

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

        ListView listView2 = (ListView) findViewById(R.id.Done);
        listView2.setAdapter(arrayAdapter2);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Mowazaf_done_requests");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    mowazaf_done_requests users = dataSnapshot.getValue(mowazaf_done_requests.class);
                    lists2.add(users);
                }

                Integer count = new Integer(0);
                for (mowazaf_done_requests mm : lists2) {
                    if(mm.getMowazaf_id().equals(m.getID())) {
                        arrayAdapter2.add(mm.getZobon_first_name() + " " + mm.getZobon_second_name());
                        MyMap2.put(count, mm);
                        count++;
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                information_display(i);
            }
        });

    }

    private void information_display(int i) {
        TextView mwzf_fullName = (TextView) findViewById(R.id.mowazaf_fullName2);
        TextView mwzf_job = (TextView) findViewById(R.id.mowazaf_job2);
        TextView mwzf_phoneNumber = (TextView) findViewById(R.id.mowazaf_phoneNumber2);
        TextView mwzf_rate = (TextView) findViewById(R.id.mowazaf_rate2);
        ImageView mwzf_img;
        mwzf_img = (ImageView) findViewById(R.id.MowazafImage);
        m2 = MyMap2.get((Integer) i);
        TextView req_date = (TextView) findViewById(R.id.request_date);
        TextView req_time = (TextView) findViewById(R.id.request_time2);
        TextView rate = (TextView)findViewById(R.id.Rate);
        EditText Rate=(EditText)findViewById(R.id.Rate_theMowazaf);
        Button save = (Button) findViewById(R.id.canclebutton);
        Button exit = (Button) findViewById(R.id.Exit);

        mwzf_fullName.setText(m2.getZobon_first_name()+" "+m2.getZobon_second_name());
        //  Toast.makeText(search.this, mwzf_fullName.getText().toString(), Toast.LENGTH_SHORT).show();
        mwzf_phoneNumber.setText("رقم الهاتف : "+m2.getZobon_phone_numbeer());
        String rateS=String.valueOf("التقييم : "+m2.getZobon_rate());
        mwzf_rate.setText(rateS);
        if(m2.getZobon().getGender().equals("ذكر"))
            mwzf_img.setBackgroundResource(R.drawable.man);
        else
            mwzf_img.setBackgroundResource(R.drawable.woman);
        req_date.setText("التاريخ : " + m2.getRequest_date());
        req_time.setText("الوقت : " + m2.getRequest_time());

        if(m2.getZobon_is_rated())
        {
            Rate.setVisibility(View.INVISIBLE);
            save.setVisibility(View.INVISIBLE);
            rate.setVisibility(View.VISIBLE);
        }
        else
        {
            rate.setVisibility(View.INVISIBLE);
            Rate.setVisibility(View.VISIBLE);
            save.setVisibility(View.VISIBLE);
        }

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Rate.getText().toString().length() > 0) {
                    Float r = Float.parseFloat(Rate.getText().toString());

                    if (r >= 0 && r <= 5) {
                        Toast.makeText(MowazafDoneRequests.this, "شكرا لتقييمك", Toast.LENGTH_SHORT).show();
                        rate.setText(Rate.getText().toString());
                        m2.getZobon().setCountOfrates(m2.getZobon().getCountOfrates()+1);
                        m2.getZobon().setTotalrates(m2.getZobon().getTotalrates()+r);
                        m2.getZobon().setRate(m2.getZobon().getRate());
                        m2.setZobon_rate(m2.getZobon().getRate());
                        mwzf_rate.setText("التقييم : "+String.valueOf(m2.getZobon().getRate()));
                        m2.setZobon_is_rated(true);
                        DatabaseReference dd = FirebaseDatabase.getInstance().getReference("Zobon").child(m2.getZobon_id());
                        dd.setValue(m2.getZobon());
                        dd = FirebaseDatabase.getInstance().getReference("Mowazaf_done_requests").child(m2.getMowazaf_id()).child("zobon_rate");
                        dd.setValue(m2.getZobon().getRate());
                        Rate.setVisibility(View.INVISIBLE);
                        save.setVisibility(View.INVISIBLE);
                        rate.setVisibility(View.VISIBLE);
                    } else {
                        Toast.makeText(MowazafDoneRequests.this, "يجب ان يكون التقييم بين ال 5 و ال 0", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(MowazafDoneRequests.this, "لم يتم كتابة تقييم", Toast.LENGTH_SHORT).show();
                }
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                relativeLayouts.setVisibility(View.INVISIBLE);
            }
        });
        relativeLayouts.bringToFront();
        relativeLayouts.setVisibility(View.VISIBLE);


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
                intent = new Intent( MowazafDoneRequests.this,EmployeeProfileActivity.class);
                intent.putExtra("id",m.getID());
                startActivity(intent);
                finish();
                return true;
            case R.id.mowazaf_contact_us:
                intent = new Intent(MowazafDoneRequests.this,contact_us2.class);
                intent.putExtra("id",m.getID());
                startActivity(intent);
                finish();
                return true;
            case R.id.mowazaf_main_page:
                intent = new Intent(MowazafDoneRequests.this,mowazaf_main_page.class);
                intent.putExtra("id",m.getID());
                startActivity(intent);
                finish();
                return true;
            case R.id.mowazaf_logout:
                Toast.makeText(this, "تم تسجيل الخروج بنجاح", Toast.LENGTH_SHORT).show();
                intent= new Intent(MowazafDoneRequests.this,MainActivity.class);
                startActivity(intent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}




//  databaseReference.push().setValue(zdr);
      /*  m= new Mowazaf("خالد","محمد","30121456128103","1324Abcd","ذكر","الوادي الجديد","user3@gmail.com","01011451234","مشغول","كهربائي","31",(float)4.8,false);
        myRef.push().setValue(m);
        m= new Mowazaf("زياد","محمد","30121456128104","1324Abcd","ذكر","أسيوط","user4@gmail.com","01011451234","غير متاح","جليسة أطفال","40",(float)4.7,false);
        myRef.push().setValue(m);
        m= new Mowazaf("محسن","محمد","30121456128105","1324Abcd","ذكر","بورسعيد","user5@gmail.com","01011451234","متاح","كهربائي","33",(float)4.0,false);
        myRef.push().setValue(m);
        m= new Mowazaf("اندرو","محمد","30121456128106","1324Abcd","ذكر","الجيزة","user6@gmail.com","01011451234","متاح","نقاش","35",(float)3.5,false);
        myRef.push().setValue(m);
        m= new Mowazaf("اسماعيل","محمد","30121456128107","1324Abcd","ذكر","الجيزة","user7@gmail.com","01011451234","متاح","كهربائي","25",(float)2.5,false);
        myRef.push().setValue(m);
        m= new Mowazaf("ايمن","محمد","30121456128108","1324Abcd","ذكر","الجيزة","user8@gmail.com","01011451234","غير متاح","كهربائي","29",(float)4.5,false);
        myRef.push().setValue(m);
        m= new Mowazaf("امين","محمد","30121456128109","1324Abcd","ذكر","القاهرة","user9@gmail.com","01011451234","متاح","ميكانيكي","27",(float)4.5,false);
        myRef.push().setValue(m);
        m= new Mowazaf("حسام","محمد","30121456128110","1324Abcd","ذكر","السويس","user10@gmail.com","01011451234","متاح","حداد","28",(float)4.5,false);
        myRef.push().setValue(m);
        m= new Mowazaf("مصطفى","محمد","30121456128111","1324Abcd","ذكر","دمياط","user11@gmail.com","01011451234","متاح","ممرض","24",(float)4.9,false);
        myRef.push().setValue(m);
        m= new Mowazaf("عادل","محمد","30121456128112","1324Abcd","ذكر","سوهاج","user12@gmail.com","01011451234","غير متاح","معلم","22",(float)5.0,false);
        myRef.push().setValue(m);
        m= new Mowazaf("عبد الرحمن","محمد","30121456128113","1324Abcd","ذكر","الإسكندرية","user13@gmail.com","01011451234","متاح","معلم","30",(float)4.5,false);
        myRef.push().setValue(m);
        m= new Mowazaf("مكرم","محمد","30121456128114","1324Abcd","ذكر","سوهاج","user14@gmail.com","01011451234","متاح","معلم","30",(float)0.0,false);
        myRef.push().setValue(m);
        m= new Mowazaf("حاتم","محمد","30121456128115","1324Abcd","ذكر","الفيوم","user15@gmail.com","01011451234","مشغول","نجار","39",(float)1.5,false);
        myRef.push().setValue(m);



        Mowazaf m= new Mowazaf("احمد","محمد","30121456128100","1324Abcd","ذكر","القاهرة","user@gmail.com","01011451234","متاح","كهربائي","30",(float)4.5,1.0F,4.5F);
        m= new Mowazaf("احمد","احمد","30121456128101","1324Abcd","ذكر","الإسكندرية","user1@gmail.com","01011451234","متاح","سباك","24",(float)4.3,1.0F,4.5F);
        Zobon z = new Zobon("محمد","محمد","30121456128105","1324Abcd","ذكر","القاهرة","user@gmail.com","01011451234","30",(float)4.5,1.0F,4.5F);

        ArrayList<String>arrayList = new ArrayList<String>();
        Request request = new Request("معلم","القاهرة","543","+653","قنملتث","متاح",(float)4.5,true,true,arrayList);
        mowazaf_done_requests zdr = new mowazaf_done_requests();
        zdr.setRequest(request);
        zdr.setZobon(z);
        zdr.setAll();
        databaseReference.child(z.getID()).setValue(zdr);
        */