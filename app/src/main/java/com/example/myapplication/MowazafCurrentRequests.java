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

public class MowazafCurrentRequests extends AppCompatActivity {
    HashMap<Integer, mowazaf_current_requests> MyMap2;
    Zobon zobons;
    String s1, s2;
    Button button;
    mowazaf_current_requests m2 = new mowazaf_current_requests();
    RelativeLayout relativeLayouts , improve;
    DatabaseReference databaseReference;
    ArrayAdapter<String> arrayAdapter2;
    ArrayList<mowazaf_current_requests> lists2;
    static Mowazaf m;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mowazaf_current_requests);

        lists2 = new ArrayList<mowazaf_current_requests>();
        arrayAdapter2 = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1);

        MyMap2 = new HashMap<Integer, mowazaf_current_requests>();
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

        improve= (RelativeLayout) findViewById(R.id.improvepopup);
        improve.setVisibility(View.INVISIBLE);

        android.widget.ListView listView2 = (ListView) findViewById(R.id.Done);
        listView2.setAdapter(arrayAdapter2);
        databaseReference = FirebaseDatabase.getInstance().getReference("mowazaf_current_requests");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    mowazaf_current_requests users = dataSnapshot.getValue(mowazaf_current_requests.class);
                    lists2.add(users);
                }

                Integer count = new Integer(0);
                for (mowazaf_current_requests mm : lists2) {
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
        Button noAcc = (Button) findViewById(R.id.not_acc);
        Button Acc = (Button) findViewById(R.id.accepet);
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

        noAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                improve.bringToFront();
                improve.setVisibility(View.VISIBLE);

                Button yes , no;
                yes = (Button) findViewById(R.id.Yes);
                no = (Button) findViewById(R.id.No);

                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        improve.setVisibility(View.INVISIBLE);
                        relativeLayouts.setVisibility(View.INVISIBLE);
                        databaseReference.child(m2.getZobon_id()).setValue(null);
                        lists2.remove(m2);
                        arrayAdapter2.remove(m2.getZobon_first_name()+" "+m2.getZobon_second_name());
                        MyMap2.clear();
                        Integer count = new Integer(0);
                        for (mowazaf_current_requests mm : lists2) {
                            MyMap2.put(count,mm);
                            count++;
                        }
                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Zobon_done_requests");
                        zobon_done_requests zdr = new zobon_done_requests();
                        zdr.setMowazaf(m);
                        zdr.setRequest(m2.getRequest());
                        zdr.setZobon_id(m2.getZobon_id());
                        zdr.setAll();
                        ref.child(m.getID()).setValue(zdr);
                        ref = FirebaseDatabase.getInstance().getReference("Mowazaf_done_requests");
                        mowazaf_done_requests mdr = new mowazaf_done_requests();
                        mdr.setZobon(m2.getZobon());
                        mdr.setRequest(m2.getRequest());
                        mdr.setMowazaf_id(m.getID());
                        mdr.setAll();
                        ref.child(mdr.getZobon_id()).setValue(mdr);

                        ref = FirebaseDatabase.getInstance().getReference("zobon_waiting_requests");
                        ref.child(m.getID()).setValue(null);
                        sendemail(mdr,zdr);
                    }
                });

                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        improve.setVisibility(View.INVISIBLE);
                    }
                });
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
    private void sendemail(mowazaf_done_requests mdr ,zobon_done_requests zdr) {
        JavaMailAPI javaMailAPI=new JavaMailAPI(this,mdr.getZobon_email(),"Order Is Done", "لقد قام " + zdr.getMowazaf_first_name() +" " + zdr.getMowazaf_second_name() + " بالانتهاء من عمله ");
        javaMailAPI.execute();
        Toast.makeText(this, "لقد تم ارسال الايميل", Toast.LENGTH_SHORT).show();
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
                intent = new Intent( MowazafCurrentRequests.this,EmployeeProfileActivity.class);
                intent.putExtra("id",m.getID());
                startActivity(intent);
                finish();
                return true;
            case R.id.mowazaf_contact_us:
                intent = new Intent(MowazafCurrentRequests.this,contact_us2.class);
                intent.putExtra("id",m.getID());
                startActivity(intent);
                finish();
                return true;
            case R.id.mowazaf_main_page:
                intent = new Intent(MowazafCurrentRequests.this,mowazaf_main_page.class);
                intent.putExtra("id",m.getID());
                startActivity(intent);
                finish();
                return true;
            case R.id.mowazaf_logout:
                Toast.makeText(this, "تم تسجيل الخروج بنجاح", Toast.LENGTH_SHORT).show();
                intent= new Intent(MowazafCurrentRequests.this,MainActivity.class);
                startActivity(intent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}



/*
         m= new Mowazaf("احمد","محمد","30121456128100","1324Abcd","ذكر","القاهرة","user@gmail.com","01011451234","متاح","كهربائي","30",(float)4.5,1.0F,4.5F);
        //m= new Mowazaf("احمد","احمد","30121456128101","1324Abcd","ذكر","الإسكندرية","user1@gmail.com","01011451234","متاح","سباك","24",(float)4.3,false);
        Zobon z = new Zobon("احمد","محمد","30121456128105","1324Abcd","ذكر","القاهرة","nanafaied93@gmail.com","01011451234","30",(float)4.5,1.0F,4.5F);

        ArrayList<String>arrayList = new ArrayList<String>();
        Request request = new Request("معلم","القاهرة","543","+653","قنملتث","متاح",(float)4.5,true,true,arrayList);
        mowazaf_current_requests zdr = new mowazaf_current_requests();
        zdr.setRequest(request);
        zdr.setZobon(z);
        zdr.setAll();
        databaseReference.child(z.getID()).setValue(zdr);
 */