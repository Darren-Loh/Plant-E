package com.example.plant_e;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {


    ArrayList<JournalCard> JournalList;

    public RecyclerAdapter(ArrayList<JournalCard> JournalList) {
        this.JournalList = JournalList;
    }

    @NonNull
    @Override


    //
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.expandablecardview, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }


    //takes in data and puts in data into the view
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        holder.JournalDesc.setText(JournalList.get(position).getDescription());
        holder.JournalTitle.setText(JournalList.get(position).getTitle());
        holder.Planttype.setText(JournalList.get(position).getPlant());
        holder.Timestamp.setText(JournalList.get(position).getTimestamp());
        holder.Datestamp.setText(JournalList.get(position).getDatestamp());

        boolean isexpand = JournalList.get(position).isIsexpanded();
        holder.expandablelayout.setVisibility(isexpand ? View.VISIBLE : View.GONE);
    }


    //represents number of rows in the recycler view
    //returns number of rowsas

    @Override
    public int getItemCount() {
        return JournalList.size();
    }


    //defines the rows
    class ViewHolder extends RecyclerView.ViewHolder{

        TextView JournalTitle, JournalDesc, Datestamp, Timestamp, Planttype ;
        ConstraintLayout expandablelayout, titletab;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            expandablelayout = itemView.findViewById(R.id.Expandabletab);
            JournalTitle = itemView.findViewById(R.id.JournalTitle);
            titletab = itemView.findViewById(R.id.TitleTab);

            JournalDesc = itemView.findViewById(R.id.JournalDesc);
            Datestamp = itemView.findViewById(R.id.JournalDate);
            Timestamp = itemView.findViewById(R.id.TimeStamp);
            Planttype = itemView.findViewById(R.id.PlantName);

            titletab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    JournalCard journalentry = JournalList.get(getAdapterPosition());
                    journalentry.setIsexpanded((!journalentry.isIsexpanded()));
                    notifyItemChanged(getAdapterPosition());
                }
            });

        }
    }

}
