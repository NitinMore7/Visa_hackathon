package com.example.lenovo.visa_hackathon.ui.home;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.lenovo.visa_hackathon.R;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    RecyclerView recyclerView;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final ProgressDialog progressDialog=ProgressDialog.show(getContext(),"Loading","Wait till we get the best offers for you");
            RequestQueue requestQueue= Volley.newRequestQueue(getContext());
            String url="";
            recyclerView=root.findViewById(R.id.recyclerview);
        final ArrayList<String> name=new ArrayList<String>();
        final ArrayList<String> value=new ArrayList<String>();
            JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject = response.getJSONObject("resource");
                        JSONArray jsonArray = new JSONArray();
                        jsonArray = jsonObject.getJSONArray("merchantControls");

                        for (int i = 0; i < jsonArray.length(); i++) {
                            try {
                                name.add(jsonArray.getJSONObject(i).getString("controlType"));
                                value.add(jsonArray.getJSONObject(i).getJSONObject("spendLimit").getString("currentPeriodSpend"));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            name.add("fooooood");
                            value.add("300");
                            adapter ad=new adapter(name,value);
                        recyclerView.setHasFixedSize(true);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                            recyclerView.setAdapter(ad);
                        }
                        progressDialog.dismiss();
                    } catch (Exception e) {
                        progressDialog.dismiss();
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                }
            });


        return root;
    }

}