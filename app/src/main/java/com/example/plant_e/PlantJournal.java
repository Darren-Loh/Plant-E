package com.example.plant_e;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class PlantJournal extends AppCompatActivity {

    private TextView txtTitle, txtDate;
    private EditText txtDescription;
    private ImageButton btnBackwards, btnForwards;
    private Button btnSave;

    Map<String, JournalStore> journalStoreMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_journal);

        txtTitle = findViewById(R.id.txtTitle);
        txtDate = findViewById(R.id.txtDate);
        txtDescription = findViewById(R.id.txtDescription);
        btnBackwards = findViewById(R.id.btnBackwards);
        btnSave = findViewById(R.id.btnSave);
        btnForwards = findViewById(R.id.btnForwards);


        // ----------------- Date formatting-------------------------------------------
        final LocalDate[] dateObj = {LocalDate.now()};
        final DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String formattedDate = dateObj[0].format(myFormat);

        txtDate.setText(formattedDate);
        final JournalStore journalInstance = new JournalStore(formattedDate);  //date instance

        //----------Hashmap-------------------------------------------
//        if (journalStoreMap.get(txtDate.getText().toString()) != null){
//            txtDescription.setText(journalStoreMap.get(txtDate.getText().toString()).getDescription());  // if Date instance already inside Hashmap
//        }

        //--------------btnBackwards onClickListener----------------------------------------
        btnBackwards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dateObj[0] = dateObj[0].minusDays(1);
                txtDate.setText(dateObj[0].format(myFormat));

                if (journalStoreMap.get(txtDate.getText().toString()) != null){
                    txtDescription.setText(journalStoreMap.get(txtDate.getText().toString()).getDescription());  // if Date instance already inside Hashmap
                }

            }
        });

        //---------------btnForwards onClickListener----------------------------------------
        btnForwards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dateObj[0] = dateObj[0].plusDays(1);
                txtDate.setText(dateObj[0].format(myFormat));

                if (journalStoreMap.get(txtDate.getText().toString()) != null){
                    txtDescription.setText(journalStoreMap.get(txtDate.getText().toString()).getDescription());  // if Date instance already inside Hashmap
                }

            }
        });

        //--------------btnSave onClickListener---------------------------------------------
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                journalStoreMap.put(txtDate.getText().toString(), new JournalStore(txtDate.getText().toString(), txtDescription.getText().toString()));

            }
        });











    }


}