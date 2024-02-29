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

public class ZobonWaitingRequests extends AppCompatActivity {

    HashMap<Integer, Mowazaf> MyMap;
    HashMap<Integer, zobon_waiting_requests> MyMap2;
    Mowazaf mowazafs;
    Mowazaf mowazaf2;
    String s1, s2;
    Button button;
    zobon_waiting_requests m2;
    RelativeLayout relativeLayouts, improve;
    ArrayAdapter<String> arrayAdapter2;
    ArrayList<zobon_waiting_requests> lists2;
    DatabaseReference databaseReference;
    static Zobon z;
    static Intent iii;
    //Done_Requests d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zobon_waiting_requests);

        lists2 = new ArrayList<>();
        arrayAdapter2 = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1);
        MyMap2 = new HashMap<Integer, zobon_waiting_requests>();
        relativeLayouts = (RelativeLayout) findViewById(R.id.WRpopup);
        relativeLayouts.setVisibility(View.INVISIBLE);

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

        improve= (RelativeLayout) findViewById(R.id.improvepopup);
        improve.setVisibility(View.INVISIBLE);


        android.widget.ListView listView2 = (ListView) findViewById(R.id.Done);
        listView2.setAdapter(arrayAdapter2);
         databaseReference = FirebaseDatabase.getInstance().getReference("zobon_waiting_requests");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    zobon_waiting_requests users = dataSnapshot.getValue(zobon_waiting_requests.class);
                    lists2.add(users);
                }

                Integer count = new Integer(0);

                for (zobon_waiting_requests mm : lists2) {
                    if(mm.getZobon_id().equals(z.getID())) {
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
        Button cancle = (Button) findViewById(R.id.canclebutton);
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

       cancle.setOnClickListener(new View.OnClickListener() {
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
                       databaseReference.child(m2.getMowazaf_id()).setValue(null);
                       lists2.remove(m2);
                       arrayAdapter2.remove(m2.getMowazaf_first_name() + " " + m2.getMowazaf_second_name());
                       MyMap2.clear();
                       Integer count = new Integer(0);
                       for (zobon_waiting_requests mm : lists2) {
                           MyMap2.put(count,mm);
                           count++;
                       }

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
                intent = new Intent(ZobonWaitingRequests.this,CustomerProfileActivity.class);
                intent.putExtra("id",z.getID());
                startActivity(intent);
                finish();
                return true;
            case R.id.mowazaf_contact_us:
                intent = new Intent(ZobonWaitingRequests.this,contact_us2.class);
                intent.putExtra("id",z.getID());
                startActivity(intent);
                finish();
                return true;
            case R.id.mowazaf_main_page:
                intent = new Intent(ZobonWaitingRequests.this,mowazaf_main_page.class);
                intent.putExtra("id",z.getID());
                startActivity(intent);
                finish();
                return true;
            case R.id.mowazaf_logout:
                Toast.makeText(this, "تم تسجيل الخروج بنجاح", Toast.LENGTH_SHORT).show();
                intent= new Intent(ZobonWaitingRequests.this,MainActivity.class);
                startActivity(intent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}




// to set data in firebase
       /* Mowazaf m= new Mowazaf("احمد","محمد","30121456128100","1324Abcd","ذكر","القاهرة","user@gmail.com","01011451234","متاح","كهربائي","30",(float)4.5,1.0F,4.5F);
        m= new Mowazaf("احمد","احمد","30121456128102","1324Abcd","ذكر","الإسكندرية","user1@gmail.com","01011451234","متاح","سباك","24",(float)4.3,1.0F,4.3F);

        Zobon z = new Zobon("محمد","محمد","30121456128105","1324Abcd","ذكر","القاهرة","user@gmail.com","01011451234","30",(float)4.5,1.0F,4.5F);

        ArrayList<String>arrayList = new ArrayList<String>();
        Request request = new Request("معلم","القاهرة","543","+653","قنملتث","متاح",(float)4.5,true,true,arrayList);
        zobon_waiting_requests zdr = new zobon_waiting_requests(request,m);
        databaseReference.child(zdr.getMowazaf_id()).setValue(zdr);*/