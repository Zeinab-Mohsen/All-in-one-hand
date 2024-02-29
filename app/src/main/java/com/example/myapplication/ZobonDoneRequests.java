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

public class ZobonDoneRequests extends AppCompatActivity {

    HashMap<Integer, Mowazaf> MyMap;
    HashMap<Integer, zobon_done_requests> MyMap2;
    Mowazaf mowazafs;
    Mowazaf mowazaf2;
    String s1, s2;
    Button button;
    zobon_done_requests m2;
    RelativeLayout relativeLayouts;
    static Zobon z;
    static Intent iii;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zobon_done_requests);

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


        ArrayList<zobon_done_requests> lists2 = new ArrayList<>();
        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1);
        MyMap2 = new HashMap<Integer, zobon_done_requests>();
        relativeLayouts = (RelativeLayout) findViewById(R.id.WRpopup);
        relativeLayouts.setVisibility(View.INVISIBLE);


        ListView listView2 = (ListView) findViewById(R.id.Done);
        listView2.setAdapter(arrayAdapter2);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Zobon_done_requests");


        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    zobon_done_requests users = dataSnapshot.getValue(zobon_done_requests.class);
                    lists2.add(users);
                }

                Integer count = new Integer(0);
                for (zobon_done_requests mm : lists2) {
                    if(mm.getZobon_id().equals(z.getID()))
                    {
                        arrayAdapter2.add(mm.getMowazaf_first_name() + " " + mm.getMowazaf_second_name());
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

        mwzf_fullName.setText(m2.getMowazaf_first_name()+" "+m2.getMowazaf_second_name());
        //  Toast.makeText(search.this, mwzf_fullName.getText().toString(), Toast.LENGTH_SHORT).show();
        mwzf_job.setText("الوظيفة : "+m2.getMowazaf_job());
        mwzf_phoneNumber.setText("رقم الهاتف : "+m2.getMowazaf_phone_numbeer());
        String rateS=String.valueOf("التقييم : "+m2.getMowazaf_rate());
        mwzf_rate.setText(rateS);
        if(m2.getMowazaf_job().equals("كهربائي"))
            mwzf_img.setBackgroundResource(R.drawable.electrical_engineer);
        else if(m2.getMowazaf_job().equals("سباك"))
            mwzf_img.setBackgroundResource(R.drawable.plumber);
        else if(m2.getMowazaf_job().equals("حداد"))
            mwzf_img.setBackgroundResource(R.drawable.blacksmith);
        else if(m2.getMowazaf_job().equals("جليس أطفال"))
            mwzf_img.setBackgroundResource(R.drawable.baby_setter);
        else if(m2.getMowazaf_job().equals("دكتور"))
            mwzf_img.setBackgroundResource(R.drawable.doctor);
        else if(m2.getMowazaf_job().equals("ممرض"))
            mwzf_img.setBackgroundResource(R.drawable.nurse);
        else if(m2.getMowazaf_job().equals("معلم"))
            mwzf_img.setBackgroundResource(R.drawable.teacher);
        else if(m2.getMowazaf_job().equals("ميكانيكي"))
            mwzf_img.setBackgroundResource(R.drawable.mechanic);
        else if(m2.getMowazaf_job().equals("نجار"))
            mwzf_img.setBackgroundResource(R.drawable.carpenter);
        else if(m2.getMowazaf_job().equals("نقاش"))
            mwzf_img.setBackgroundResource(R.drawable.na2a4);

        req_date.setText("التاريخ : " + m2.getRequest_date());
        req_time.setText("الوقت : " + m2.getRequest_time());

        if(m2.getMowazaf_is_rated())
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
                if (Rate.getText().toString().length() > 0)
                {
                    Float r = Float.parseFloat(Rate.getText().toString());

                    if (r >= 0 && r <= 5) {
                        Toast.makeText(ZobonDoneRequests.this, "شكرا لتقييمك", Toast.LENGTH_SHORT).show();
                        rate.setText(Rate.getText().toString());
                        m2.getMowazaf().setCountOfrates(m2.getMowazaf().getCountOfrates()+1);
                        m2.getMowazaf().setTotalrates(m2.getMowazaf().getTotalrates()+r);
                        m2.getMowazaf().setRate(m2.getMowazaf().getRate());
                        m2.setMowazaf_rate(m2.getMowazaf().getRate());
                        mwzf_rate.setText("التقييم : "+String.valueOf(m2.getMowazaf().getRate()));
                        m2.setMowazaf_is_rated(true);
                        DatabaseReference dd = FirebaseDatabase.getInstance().getReference("Mowazaf").child(m2.getMowazaf_id());
                        //dd.setValue(null);
                        dd.setValue(m2.getMowazaf());
                        dd = FirebaseDatabase.getInstance().getReference("Zobon_done_requests").child(m2.getMowazaf_id()).child("mowazaf_rate");
                        dd.setValue(m2.getMowazaf_rate());
                        Rate.setVisibility(View.INVISIBLE);
                        save.setVisibility(View.INVISIBLE);
                        rate.setVisibility(View.VISIBLE);
                    }
                    else {
                        Toast.makeText(ZobonDoneRequests.this, "يجب ان يكون التقييم بين ال 5 و ال 0", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(ZobonDoneRequests.this, "لم يتم كتابة تقييم", Toast.LENGTH_SHORT).show();
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
                intent = new Intent(ZobonDoneRequests.this,CustomerProfileActivity.class);
                intent.putExtra("id",z.getID());
                startActivity(intent);
                finish();
                return true;
            case R.id.mowazaf_contact_us:
                intent = new Intent(ZobonDoneRequests.this,contact_us2.class);
                intent.putExtra("id",z.getID());
                startActivity(intent);
                finish();
                return true;
            case R.id.mowazaf_main_page:
                intent = new Intent(ZobonDoneRequests.this,Zobon_main_page.class);
                intent.putExtra("id",z.getID());
                startActivity(intent);
                finish();
                return true;
            case R.id.mowazaf_logout:
                Toast.makeText(this, "تم تسجيل الخروج بنجاح", Toast.LENGTH_SHORT).show();
                intent= new Intent(ZobonDoneRequests.this,MainActivity.class);
                startActivity(intent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}




//  databaseReference.push().setValue(zdr);



// to set data in firebase
        /*Mowazaf m= new Mowazaf("احمد","محمد","30121456128100","1324Abcd","ذكر","القاهرة","user@gmail.com","01011451234","متاح","كهربائي","30",(float)4.5,1.0F,4.3F);
        m= new Mowazaf("احمد","احمد","30121456128101","1324Abcd","ذكر","الإسكندرية","user1@gmail.com","01011451234","متاح","سباك","24",(float)4.3,1.0F,4.3F);
        Zobon z = new Zobon("محمد","محمد","30121456128105","1324Abcd","ذكر","القاهرة","user@gmail.com","01011451234","30",(float)4.5,1.0F,4.5F);

        ArrayList<String>arrayList = new ArrayList<String>();
        Request request = new Request("معلم","القاهرة","543","+653","قنملتث","متاح",(float)4.5,true,true,arrayList);
        zobon_done_requests zdr = new zobon_done_requests(request,m);
        databaseReference.child(zdr.getMowazaf_id()).setValue(zdr);*/