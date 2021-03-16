package com.example.newapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class Main_Activity extends FragmentActivity {

    TabLayout tabs;
    Button add;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        add = findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Add_Activity.class);
                startActivity(intent);
            }
        });

        ViewPager vp = findViewById(R.id.vp);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        vp.setAdapter(adapter);
        vp.setCurrentItem(1);

        tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(vp);



    }


}
