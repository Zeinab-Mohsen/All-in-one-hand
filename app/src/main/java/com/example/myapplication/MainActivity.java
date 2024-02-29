package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity
{

    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private com.example.myapplication.MyFragmentAdapter adapter;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager2 = findViewById(R.id.viewPager2);

        tabLayout.addTab(tabLayout.newTab().setText("تسجيل الدخول"));
        tabLayout.addTab(tabLayout.newTab().setText("تسجيل حساب جديد"));

        FragmentManager fragmentManager = getSupportFragmentManager();
        adapter = new com.example.myapplication.MyFragmentAdapter(fragmentManager, getLifecycle());
        viewPager2.setAdapter(adapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });

    }
}

/*

        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("Zobon");
                Zobon m;
        m= new Zobon("خالد","محمد","30121456128103","1324Abcd","ذكر","الوادي الجديد","user3@gmail.com","01011451234","31",(float)4.8,1.0F,4.8F);
        myRef.child(m.getID()).setValue(m);
        m= new Zobon("زياد","محمد","30121456128104","1324Abcd","ذكر","أسيوط","user4@gmail.com","01011451234","40",(float)4.7,1.0F,4.7F);
        myRef.child(m.getID()).setValue(m);
        m= new Zobon("محسن","محمد","30121456128105","1324Abcd","ذكر","بورسعيد","user5@gmail.com","01011451234","33",(float)4.0,1.0F,4.0F);
        myRef.child(m.getID()).setValue(m);
        m= new Zobon("اندرو","سمير","30121456128106","1324Abcd","ذكر","الجيزة","user6@gmail.com","01011451234","35",(float)3.5,1.0F,3.5F);
        myRef.child(m.getID()).setValue(m);
        m= new Zobon("اسماعيل","محمد","30121456128107","1324Abcd","ذكر","الجيزة","user7@gmail.com","01011451234","25",(float)2.5,1.0F,2.5F);
        myRef.child(m.getID()).setValue(m);
        m= new Zobon("ايمن","محمد","30121456128108","1324Abcd","ذكر","الجيزة","user8@gmail.com","01011451234","29",(float)4.5,1.0F,4.5F);
        myRef.child(m.getID()).setValue(m);
        m= new Zobon("امين","محمد","30121456128109","1324Abcd","ذكر","القاهرة","user9@gmail.com","01011451234","27",(float)4.5,1.0F,4.5F);
        myRef.child(m.getID()).setValue(m);
        m= new Zobon("حسام","محمد","30121456128110","1324Abcd","ذكر","السويس","user10@gmail.com","01011451234","28",(float)4.5,1.0F,4.5F);
        myRef.child(m.getID()).setValue(m);
        m= new Zobon("مصطفى","محمد","30121456128111","1324Abcd","ذكر","دمياط","user11@gmail.com","01011451234","24",(float)4.9,1.0F,4.9F);
        myRef.child(m.getID()).setValue(m);
        m= new Zobon("عادل","محمد","30121456128112","1324Abcd","ذكر","سوهاج","user12@gmail.com","01011451234","22",(float)5.0,1.0F,1.0F);
        myRef.child(m.getID()).setValue(m);
        m= new Zobon("عبد الرحمن","محمد","30121456128113","1324Abcd","ذكر","الإسكندرية","user13@gmail.com","01011451234","30",(float)4.5,1.0F,4.5F);
        myRef.child(m.getID()).setValue(m);
        m= new Zobon("مكرم","محمد","30121456128114","1324Abcd","ذكر","سوهاج","user14@gmail.com","01011451234","30",(float)0.0,1.0F,0.0F);
        myRef.child(m.getID()).setValue(m);
        m= new Zobon("حاتم","محمد","30121456128115","1324Abcd","ذكر","الفيوم","user15@gmail.com","01011451234","39",(float)1.5,1.0F,1.5F);
        myRef.child(m.getID()).setValue(m);


 */