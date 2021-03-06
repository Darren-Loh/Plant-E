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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<JournalCard> JournalList;
    private BottomNavigationView btmNavi;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        JournalList = new ArrayList<>();
        btmNavi = findViewById(R.id.btmNavi);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        final NavigationView navigationView = findViewById(R.id.nav_view);

        //-------------Navigation Bottom----------------------------------------------

        getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();

        btmNavi.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                checkBtmNavi(btmNavi.getMenu(), true);
                CheckDrawer(navigationView.getMenu(), false);

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


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                CheckDrawer(navigationView.getMenu(), true);
                checkBtmNavi(btmNavi.getMenu(), false);
                switch (item.getItemId()){
                    case R.id.nav_settings:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,
                                new SettingsFragment()).commit();
                        item.setChecked(true);


                        break;
                    case R.id.nav_profile:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,
                                new ProfileFragment()).commit();
                        item.setChecked(true);


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

    public static void CheckDrawer(Menu menu, boolean checkable){
        for (int i = 0; i < menu.size(); i++){
            menu.getItem(i).setChecked(checkable);
        }
    }

    public static void checkBtmNavi(Menu menu, boolean checkable){
        for (int i = 0; i < menu.size(); i++){
            menu.getItem(i).setCheckable(checkable);
        }
    }




}