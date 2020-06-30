package com.example.lenovo.visa_hackathon.ui.ATM;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.lenovo.visa_hackathon.R;
import com.example.lenovo.visa_hackathon.ui.MerchantsViewModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class ATM_locator extends Fragment {
    ArrayList<String> latitude ,longitude,merchant;

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {


            for(int i=0;i<latitude.size();i++){
                LatLng sydney = new LatLng(Double.parseDouble(latitude.get(i)), Double.parseDouble(longitude.get(i)));
                googleMap.addMarker(new MarkerOptions().position(sydney).title(merchant.get(i)));
            }
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.363922,-121.929163),14));
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        try{
            RequestQueue requestQueue= Volley.newRequestQueue(getContext());
            JSONObject j11=new JSONObject();
            j11.put("culture","en-US");
            j11.put("distance","4");
            j11.put("distanceUnit","mi");
            j11.put("metaDataOptions",0);
            JSONObject j111=new JSONObject();
            j111.put("placeName","700 Arch St, Pittsburgh, PA 15212");
            j11.put("location",j111);
            JSONObject j1121=new JSONObject(),j1122=new JSONObject();
            j1121.put("start",10);
            j1121.put("count",20);
            j1122.put("primary","city");
            j1122.put("direction","asc");
            JSONObject j112=new JSONObject();
            j112.put("range",j1121);
            j112.put("sort",j1122);
            j11.put("options",j112);
            JSONObject j1=new JSONObject();
            j1.put("requestData",j11);

            latitude=new ArrayList<String>();
            longitude=new ArrayList<String>();
            merchant=new ArrayList<String>();

            String URL = "http://localhost:3000/api/atm/locator";
           // HttpsTrustManager.allowAllSSL();
            JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, URL, j1, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {

                    try {
                        JSONArray jsonArray=new JSONArray();
                        jsonArray=response.getJSONArray("responseData").getJSONObject(0).getJSONArray("foundATMLocations");




                        for(int i =0 ;i<jsonArray.length();i++)
                        {
                            JSONObject jsonObject1=new JSONObject();
                            jsonObject1=jsonArray.getJSONObject(i);
                            latitude.add(String.valueOf(jsonObject1.getJSONObject("location").getJSONObject("coordinates").getDouble("latitude")));
                            longitude.add(String.valueOf(jsonObject1.getJSONObject("location").getJSONObject("coordinates").getDouble("longitude")));
                            merchant.add(jsonObject1.getJSONObject("location").getString("placeName"));
                        }


                        Log.v("Tag",response.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    NetworkResponse response = error.networkResponse;
                    Log.v("TAG",error.toString());
                    if (error instanceof ServerError && response != null) {
                        try {
                            String res = new String(response.data,
                                    HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                            // Now you can use any deserializer to make sense of data
                            JSONObject obj = new JSONObject(res);
                            Log.v("Tag",obj.toString());
                        } catch (UnsupportedEncodingException e1) {
                            // Couldn't properly decode data to string
                            e1.printStackTrace();
                        } catch (JSONException e2) {
                            // returned data is not JSONObject?
                            e2.printStackTrace();
                        }
                    }
                }
            } )

            {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }
            };

            requestQueue.add(jsonObjectRequest);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return inflater.inflate(R.layout.fragment_a_t_m_locator, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MerchantsViewModel mViewModel = ViewModelProviders.of(this).get(MerchantsViewModel.class);

    }
  }