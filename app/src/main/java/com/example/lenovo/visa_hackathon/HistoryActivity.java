package com.example.lenovo.visa_hackathon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        int value = intent.getIntExtra("value",-1);
        ArrayList<Transaction> transactions=new ArrayList<Transaction>();
        if(value==0){
            transactions.add(new Transaction("\u20B9 2000","12/1/2020","12:45","Bharat Petroleum"));
            transactions.add(new Transaction("\u20B9 1000","14/1/2020","17:00","Shell"));
            transactions.add(new Transaction("\u20B9 1000","17/1/2020","19:00","HP"));
            transactions.add(new Transaction("\u20B9 2000","12/2/2020","14:00","Bharat Petroleum"));
            transactions.add(new Transaction("\u20B9 2000","19/2/2020","15:32","Indian Oil"));
            transactions.add(new Transaction("\u20B9 5000","3/3/2020","12:45","Shell"));
            transactions.add(new Transaction("\u20B9 1000","9/3/2020","11:14","Bharat Petroleum"));
        }
        else if(value==1){
            transactions.add(new Transaction("\u20B9 100","12/1/2020","12:45","Punjabi Dhaba"));
            transactions.add(new Transaction("\u20B9 150","14/1/2020","17:00","D3"));
            transactions.add(new Transaction("\u20B9 120","17/1/2020","19:00","Sangeetha"));
            transactions.add(new Transaction("\u20B9 100","12/2/2020","14:00","Vinayaka Bhavan"));
            transactions.add(new Transaction("\u20B9 100","19/2/2020","15:32","Cafe Green"));
            transactions.add(new Transaction("\u20B9 120","3/3/2020","12:45","Anjaneya Bhavan"));
            transactions.add(new Transaction("\u20B9 100","9/3/2020","11:14","Tirumala Mess"));
            transactions.add(new Transaction("\u20B9 100","9/3/2020","19:17","Annapurna Mess"));
            transactions.add(new Transaction("\u20B9 100","12/3/2020","21:14","Kumar Mess"));
            transactions.add(new Transaction("\u20B9 100","15/3/2020","01:14","Janakiram"));
            transactions.add(new Transaction("\u20B9 100","3/4/2020","05:16","Hydrebadi Biriyani"));
        }
        else{
            transactions.add(new Transaction("\u20B9 3000","12/1/2020","12:45","MRF Tyres"));
            transactions.add(new Transaction("\u20B9 1500","14/3/2020","17:00","Engine Doctor"));
            transactions.add(new Transaction("\u20B9 1200","17/1/2020","19:00","AL Service"));
        }
        mRecyclerView = findViewById(R.id.recycler_view_item);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new TransactionAdapter(transactions);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mRecyclerView.getContext(), DividerItemDecoration.VERTICAL));
    }
}