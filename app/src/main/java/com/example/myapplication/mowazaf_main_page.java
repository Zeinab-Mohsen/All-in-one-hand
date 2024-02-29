package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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

public class mowazaf_main_page extends AppCompatActivity {

    RelativeLayout Up_bar;
    static Mowazaf m2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mowazaf_main_page);

        Intent ii =getIntent();
        String id = ii.getExtras().getString("id");
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Mowazaf").child(id);
        Button current_orders, Waiting_orders,Done_orders;
        current_orders = (Button) findViewById(R.id.mowazaf_current_orders);
        Waiting_orders = (Button) findViewById(R.id.mowazaf_waiting_orders);
        Done_orders = (Button) findViewById(R.id.mowazaf_done_orders);
        Up_bar= (RelativeLayout) findViewById(R.id.up_bar2);

        ImageView mwzf_img = (ImageView) findViewById(R.id.imageView4);
        TextView mowazaf_name = (TextView) findViewById(R.id.textView20);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                m2 = snapshot.getValue(Mowazaf.class);

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
                mowazaf_name.setText(m2.getFirst_Name()+" "+m2.getSecond_Name());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });




        current_orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mowazaf_main_page.this,MowazafCurrentRequests.class);
                i.putExtra("id",m2.getID());
                startActivity(i);

            }
        });

        Done_orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mowazaf_main_page.this,MowazafDoneRequests.class);
                i.putExtra("id",m2.getID());
                startActivity(i);

            }
        });
        Waiting_orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mowazaf_main_page.this,MowazafSentRequests.class);
                i.putExtra("id",m2.getID());
                startActivity(i);

            }
        });
    }

   /* @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==2)
        {
            if(resultCode==5)
            {
                boolean d = data.getBooleanExtra("finish",false);
                if(d==true)
                    finish();
            }
        }
    }*/

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
                intent = new Intent( mowazaf_main_page.this,EmployeeProfileActivity.class);
                intent.putExtra("id",m2.getID());
                startActivity(intent);
                finish();
                return true;
            case R.id.mowazaf_contact_us:
                intent = new Intent(mowazaf_main_page.this,contact_us2.class);
                intent.putExtra("id",m2.getID());
                startActivity(intent);
                finish();
                return true;
            case R.id.mowazaf_main_page:
                Toast.makeText(this, "انت الان في الصفحة الرئيسية", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.mowazaf_logout:
                Toast.makeText(this, "تم تسجيل الخروج بنجاح", Toast.LENGTH_SHORT).show();
                intent= new Intent(mowazaf_main_page.this,MainActivity.class);
                startActivity(intent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}