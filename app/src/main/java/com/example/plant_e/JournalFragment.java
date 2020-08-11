package com.example.plant_e;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.threeten.bp.LocalDate;
import org.threeten.bp.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link JournalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class JournalFragment extends Fragment implements CustomDialog.OnInputSelected{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public JournalFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment JournalFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static JournalFragment newInstance(String param1, String param2) {
        JournalFragment fragment = new JournalFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    //-------------------------outside-------------------------------
//    Map<String, JournalStore> journalStoreMap = new HashMap<>();

    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    FloatingActionButton fab;
    TextView textView2, textView3, noentriestext;
    ArrayList<JournalCard> JournalList = MainActivity.JournalList;

//    ConstraintLayout Recyclerlayout;



    //----------------------------------------------------------------





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_journal, container, false);

        //Floating action button


        fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomDialog dialog = new CustomDialog();
                dialog.setTargetFragment(JournalFragment.this, 1);
                dialog.show(getFragmentManager(), "CustomDialog");
            }
        });

        textView2 = view.findViewById(R.id.textView2);
        textView3 = view.findViewById(R.id.textView3);
        noentriestext = view.findViewById(R.id.Noentriestext);

        recyclerView = view.findViewById(R.id.RecyclerView);
        recyclerAdapter = new RecyclerAdapter(JournalList);
        recyclerView.setAdapter(recyclerAdapter);



        // Inflate the layout for this fragment
        return view;





    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        Recyclerlayout = view.findViewById(R.id.recyclerlayout);
//        Recyclerlayout.addView(R.layout.fragment_journal);
//        setContentView();





//        final TextView txtTitle = (TextView) view.findViewById(R.id.txtTitle);
//        final TextView txtDate = (TextView) view.findViewById(R.id.txtDate);
//        final EditText txtDescription = (EditText) getActivity().findViewById(R.id.txtDescription);
//        ImageButton btnBackwards = view.findViewById(R.id.btnBackwards);
//        Button btnSave = view.findViewById(R.id.btnSave);
//        ImageButton btnForwards = view.findViewById(R.id.btnForwards);
//
//
//
//
//        // ----------------- Date formatting-------------------------------------------
//        final LocalDate[] dateObj = {LocalDate.now()};
//        final DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");
//        String formattedDate = dateObj[0].format(myFormat);
//
//        txtDate.setText(formattedDate);
//        final JournalStore journalInstance = new JournalStore(formattedDate);  //date instance
//
//        //----------Hashmap-------------------------------------------
////        if (journalStoreMap.get(txtDate.getText().toString()) != null){
////            txtDescription.setText(journalStoreMap.get(txtDate.getText().toString()).getDescription());  // if Date instance already inside Hashmap
////        }
//
//        //--------------btnBackwards onClickListener----------------------------------------
//        btnBackwards.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                dateObj[0] = dateObj[0].minusDays(1);
//                txtDate.setText(dateObj[0].format(myFormat));
//
//                if (journalStoreMap.get(txtDate.getText().toString()) != null){
//                    txtDescription.setText(journalStoreMap.get(txtDate.getText().toString()).getDescription());  // if Date instance already inside Hashmap
//                }
//
//            }
//        });
//
//        //---------------btnForwards onClickListener----------------------------------------
//        btnForwards.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                dateObj[0] = dateObj[0].plusDays(1);
//                txtDate.setText(dateObj[0].format(myFormat));
//
//                if (journalStoreMap.get(txtDate.getText().toString()) != null){
//                    txtDescription.setText(journalStoreMap.get(txtDate.getText().toString()).getDescription());  // if Date instance already inside Hashmap
//                }
//
//            }
//        });
//
//        //--------------btnSave onClickListener---------------------------------------------
//        btnSave.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                journalStoreMap.put(txtDate.getText().toString(), new JournalStore(txtDate.getText().toString(), txtDescription.getText().toString()));
//
//            }
//        });
//



    }


    @Override
    public void sendInput(String Title, String Desc, String DateStamp, String TimeStamp, String DayStamp) {
        JournalList.add(new JournalCard(DateStamp, TimeStamp,Title,Desc,"Plant Type", DayStamp));
        if(JournalList.size()>0){
            noentriestext.setVisibility(View.GONE);
        }
    }
}
