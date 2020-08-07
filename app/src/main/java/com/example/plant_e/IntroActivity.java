package com.example.plant_e;


import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends AppCompatActivity {

    private ViewPager screenPager;
    IntroViewerPageAdapter introViewerPageAdapter;
    TabLayout tabindicator;
    Button nextbutton;
    Button getstartedbutton;
    Animation getstartedanimation;
    Button skipbutton;
    int position = 0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        //check if the user has already finished the intro once before
        if(restorePrefData()){
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            finish();
        }

        //Initialize views
        getstartedbutton = findViewById(R.id.getstartedbutton);
        tabindicator = findViewById(R.id.tabindicator);
        screenPager = findViewById(R.id.viewPager);
        nextbutton = findViewById(R.id.NextButton);
        getstartedanimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.get_started_animation);
        skipbutton = findViewById(R.id.SkipButton);


        //Hide Action bar
        getSupportActionBar().hide();


        //fill list screen
        final List<ScreenObject> mlist = new ArrayList<>();
        mlist.add(new ScreenObject("Welcome to Plant-E","Start growing your plants at ease \n" + "with the help of technology.\n",R.drawable.img1));
        mlist.add(new ScreenObject("Automatic Watering","When low moisture in soil is detected,\n" + "water is released automatically.\n",R.drawable.img2));
        mlist.add(new ScreenObject("Plant Live Feed","See how your plant is doing.\n Anywhere. Anytime\n",R.drawable.img3));

        //PageViewer Setup
        introViewerPageAdapter = new IntroViewerPageAdapter(this, mlist);
        screenPager.setAdapter(introViewerPageAdapter);

        //Linking tabindicator with pageviewer
        tabindicator.setupWithViewPager(screenPager);

        //Next button
        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position = screenPager.getCurrentItem();
                if (position<mlist.size()-1){
                    position++;
                    screenPager.setCurrentItem(position);
                }
                if(position == mlist.size()-1){
                    //show GETSTARTED button and hide tabindicators

                    loadlastscreen();

                }

            }
        });

        //Getstartedbutton

        getstartedbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SignupActivity.class));


                //save a boolean value so that user that alr go through intro dont need to go through again
                saveprefstate();
                finish();
            }
        });

        //tablayout change listener

        tabindicator.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition() == mlist.size()-1){
                    loadlastscreen();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //Skip Button onclicklistener
        skipbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignupActivity.class));
                saveprefstate();
            }
        });

    }

    private boolean restorePrefData(){
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPref",MODE_PRIVATE);
        Boolean isuserintroed = pref.getBoolean("isIntroed", false);
        return isuserintroed;
    }

    //to show get started button andhide tab indicators: move to last page
    public void loadlastscreen(){
        skipbutton.setVisibility(View.INVISIBLE);
        nextbutton.setVisibility(View.INVISIBLE);
        tabindicator.setVisibility(View.INVISIBLE);
        getstartedbutton.setVisibility(View.VISIBLE);
        getstartedbutton.setAnimation(getstartedanimation);



    }

    public void saveprefstate(){

        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPref",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isIntroed", true);
        editor.commit();



    }







}
