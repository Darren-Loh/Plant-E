package com.example.plant_e;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView btmNavi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btmNavi = findViewById(R.id.btmNavi);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();

        btmNavi.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;

                switch (item.getItemId()){
                    case R.id.home:
                        selectedFragment = new HomeFragment();
                        break;
                    case R.id.video:
                        selectedFragment = new LiveFeedFragment();
                        break;
                    case R.id.journal:
                        selectedFragment = new JournalFragment();
                        break;
                    case R.id.research:
                        selectedFragment = new PlantDiagnosisFragment();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.container, selectedFragment).commit();


                return true;
            }
        });
//wassap yoyoyo
    }
}