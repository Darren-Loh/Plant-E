package com.example.plant_e;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView btmNavi;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btmNavi = findViewById(R.id.btmNavi);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //-------------Navigation Bottom----------------------------------------------

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
        //-----------------------------------------------------------------------------

        //----------------Navigation Drawer-----------------------------------------

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_settings:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,
                                new SettingsFragment()).commit();
                        break;
                    case R.id.nav_profile:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,
                                new ProfileFragment()).commit();
                        break;
                    // add telegram and logout stuff here
                }


                drawer.closeDrawer(GravityCompat.START);


                return true;
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();




        //in case activity gets destroyed will not set this to be the case
//        if(savedInstanceState == null){ // means if current state is not already selected to be an item
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                    new MessageFragment()).commit();
//            navigationView.setCheckedItem(R.id.nav_message);
//        }

        //--------------------------------------------------------------------------


    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
        super.onBackPressed();
    }


}