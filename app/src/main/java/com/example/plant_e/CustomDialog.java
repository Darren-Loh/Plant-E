package com.example.plant_e;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.LocalTime;
import org.threeten.bp.format.DateTimeFormatter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomDialog extends DialogFragment {


    public interface  OnInputSelected{
        void sendInput(String Title, String Desc, String DateStamp, String TimeStamp);
    }

    public OnInputSelected inputSelected;


    //widgets
    private EditText titleinput, descriptioninput;
    private TextView datestamp, timestamp, confirmbutton, cancelbutton;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.new_journal_entry_dialogue, container, false);

        //Initialize views
        titleinput = view.findViewById(R.id.dialog_title);
        descriptioninput = view.findViewById(R.id.dialog_description);
        datestamp = view.findViewById(R.id.DialogDateStamp);
        timestamp = view.findViewById(R.id.DialogTimestamp);
        confirmbutton = view.findViewById(R.id.ConfirmButton);
        cancelbutton = view.findViewById(R.id.CancelButton);

        //Time and Datestamps
//        SimpleDateFormat dateonly = new SimpleDateFormat("dd/MM/yyyy");
//        SimpleDateFormat timeonly = new SimpleDateFormat("HH:mm");
//        Date currentdatetime = new Date();
        LocalDate dateonly = LocalDate.now();
        LocalTime timeonly = LocalTime.now().plusHours(8);
        DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeformat = DateTimeFormatter.ofPattern("HH:mm");


//        datestamp.setText(dateonly.format(currentdatetime));
//        timestamp.setText(timeonly.format(currentdatetime));
        datestamp.setText(dateonly.format(dateformat));
        timestamp.setText(timeonly.format(timeformat));

        final String datestampvalue = datestamp.getText().toString();
        final String timestampvalue = timestamp.getText().toString();



        //On click listeners for buttons
        cancelbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });

        confirmbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Titleinput = titleinput.getText().toString();
                String Descinput = descriptioninput.getText().toString();

                if(Titleinput.equals("")){
                    titleinput.setError("Please give this journal entry a title.");
                    titleinput.requestFocus();
                }
                else if(Descinput.equals("")){
                    descriptioninput.setError("Please give this journal entry a description.");
                    descriptioninput.requestFocus();
                }
                else{
                    inputSelected.sendInput(Titleinput, Descinput, datestampvalue, timestampvalue );
                }
                getDialog().dismiss();



            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try{
            inputSelected = (OnInputSelected) getTargetFragment();
        }catch (ClassCastException e){

        }

    }
}
