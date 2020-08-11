package com.example.plant_e;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    ConstraintLayout TitletabHome;
    EditText  TitleHome, DescHome;
    TextView PlantHome, ConfirmButtonHome, CancelButtonHome;
    ConstraintLayout ExpandableTabHome;
    ArrayList<JournalCard> JournalList = MainActivity.JournalList;
    ImageButton OpenButton, CloseButton;

    boolean IsExpanded = false;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        TitletabHome = view.findViewById(R.id.TitleTabHome);
        DescHome = view.findViewById(R.id.DescHome);
        ExpandableTabHome = view.findViewById(R.id.ExpandabletabHome);
        PlantHome = view.findViewById(R.id.PlantNameHome);
        ConfirmButtonHome = view.findViewById(R.id.ConfirmButtonHome);
        CancelButtonHome = view.findViewById(R.id.CancelButtonHome);
        TitleHome = view.findViewById(R.id.TitelHome);
        OpenButton = view.findViewById(R.id.OpenButton);
        CloseButton = view.findViewById(R.id.CloseButton);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ExpandableTabHome.setVisibility(View.GONE);
//
        OpenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IsExpanded = !IsExpanded;
                HomeJournalTabExpander(IsExpanded);

            }
        });

        CloseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                IsExpanded = !IsExpanded;
                HomeJournalTabExpander(IsExpanded);

            }
        });

        TitletabHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IsExpanded = !IsExpanded;
                HomeJournalTabExpander(IsExpanded);

            }
        });

        TitleHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IsExpanded = !IsExpanded;
                HomeJournalTabExpander(IsExpanded);

            }
        });

        CancelButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExpandableTabHome.setVisibility(View.GONE);
            }
        });

        ConfirmButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleDateFormat dateonly = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat timeonly = new SimpleDateFormat("HH:mm");
                SimpleDateFormat dayonly = new SimpleDateFormat("EEEE");
                Date currentdatetime = new Date();
                ExpandableTabHome.setVisibility(View.GONE);
                String Title = TitleHome.getText().toString();
                String Desc = DescHome.getText().toString();

                String datestampvalue = dateonly.format(currentdatetime);
                String timestampvalue = timeonly.format(currentdatetime);
                String daystampvalue = dayonly.format(currentdatetime);

                if(!Title.equals("") && !Desc.equals("")){
                JournalList.add(new JournalCard(datestampvalue, timestampvalue,Title,Desc,"Plant Type", daystampvalue));}
            }
        });

    }


    public void HomeJournalTabExpander(boolean IsExpanded){
        if(IsExpanded){
            CloseButton.setVisibility(View.VISIBLE);
            OpenButton.setVisibility(View.INVISIBLE);
            ExpandableTabHome.setVisibility(View.VISIBLE);
        }
        else{
            CloseButton.setVisibility(View.INVISIBLE);
            OpenButton.setVisibility(View.VISIBLE);
            ExpandableTabHome.setVisibility(View.GONE);
        }

    }
}