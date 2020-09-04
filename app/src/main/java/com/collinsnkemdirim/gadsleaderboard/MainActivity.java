package com.collinsnkemdirim.gadsleaderboard;


import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.collinsnkemdirim.gadsleaderboard.adapters.LeaderBoardPagerAdapter;
import com.example.gadsleaderboard.R;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Find the view pager that will allow the user to swipe between fragments
        ViewPager viewPager = findViewById(R.id.view_pager);

        LeaderBoardPagerAdapter leaderBoardPagerAdapter = new LeaderBoardPagerAdapter(this, getSupportFragmentManager());

        viewPager.setAdapter(leaderBoardPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.main_tl);

        tabLayout.setupWithViewPager(viewPager);

        Button submitButton = findViewById(R.id.lead_submit_btn);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SubmitFormActivity.class);
                startActivity(intent);
            }
        });

    }
}