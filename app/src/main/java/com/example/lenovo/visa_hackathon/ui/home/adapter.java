package com.example.lenovo.visa_hackathon.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.example.lenovo.visa_hackathon.R;

import java.util.ArrayList;

public class adapter extends RecyclerView.Adapter<adapter.ViewHolder> {

    private final ArrayList<String> name;
    private final ArrayList<String> value;

    public adapter(ArrayList<String> name, ArrayList<String> value){
        this.name=name;
        this.value=value;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View listitem=layoutInflater.inflate(R.layout.home_list,parent,false);
        ViewHolder viewHolder=new ViewHolder(listitem);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull adapter.ViewHolder holder, int position) {
        holder.nam.setText(name.get(position));
        holder.valu.setText(value.get(position));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    @Override
    public int getItemCount() {
        return name.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nam,valu;
        public CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nam=itemView.findViewById(R.id.txt_name);
            valu=itemView.findViewById(R.id.txt_bal);
            cardView=itemView.findViewById(R.id.card);
        }
    }
}
