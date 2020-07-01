package com.example.lenovo.visa_hackathon;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ExampleViewHolder> {
    private ArrayList<Transaction> mExampleList;
    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView mTransAmount;
        public TextView mTransDate;
        public TextView mTransPlace;
        public TextView mTransTime;
        public ExampleViewHolder(View itemView) {
            super(itemView);
            mTransAmount = itemView.findViewById(R.id.transaction_amount);
            mTransDate = itemView.findViewById(R.id.transaction_date);
            mTransPlace = itemView.findViewById(R.id.transaction_place);
            mTransTime = itemView.findViewById(R.id.transaction_time);
        }
    }
    public TransactionAdapter(ArrayList<Transaction> exampleList) {
        mExampleList = exampleList;
    }
    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v);
        return evh;
    }
    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        Transaction currentItem = mExampleList.get(position);
        holder.mTransAmount.setText(currentItem.getTransAmt());
        holder.mTransTime.setText(currentItem.getTransTime());
        holder.mTransPlace.setText(currentItem.getTransPlace());
        holder.mTransDate.setText(currentItem.getTransDate());
    }
    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}
