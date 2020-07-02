package com.example.lenovo.visa_hackathon.ui;

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
import com.android.volley.RetryPolicy;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.lenovo.visa_hackathon.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class Merchants extends Fragment {
    private MerchantsViewModel mViewModel;
    ArrayList<String> latitude ,longitude,merchant;
    GoogleMap mMap ;
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
                mMap=googleMap;

            for(int i=0;i<latitude.size();i++){
            LatLng sydney = new LatLng(Double.parseDouble(latitude.get(i)), Double.parseDouble(longitude.get(i)));
            mMap.addMarker(new MarkerOptions().position(sydney).title(merchant.get(i)));
           }
            mMap.addMarker(new MarkerOptions().position(new LatLng(37.363922,-121.929163)).title("Current position")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.363922,-121.929163),14));
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
            j11.put("messageDateTime","2020-06-26T17:53:09.903");
            j11.put("requestMessageId","Request_001");
            JSONObject j12=new JSONObject();
            j12.put("merchantName","Starbucks");
            j12.put("merchantCountryCode","840");
            j12.put("latitude","37.363922");
            j12.put("longitude","-121.929163");
            j12.put("distance","2");
            j12.put("distanceUnit","M");
            JSONArray array=new JSONArray();
            array.put("GNLOCATOR");
            JSONObject j13=new JSONObject();
            j13.put("maxRecords","5");
            j13.put("matchIndicators","true");
            j13.put("matchScore","true");
            JSONObject j1
                    =new JSONObject();
            j1.put("header",j11);
            j1.put("searchAttrList",j12);
            j1.put("responseAttrList",array);
            j1.put("searchOptions",j13);
            JSONObject j2=new JSONObject();
            j2.put("payload",j1);
            latitude=new ArrayList<String>();
            longitude=new ArrayList<String>();
            merchant=new ArrayList<String>();

            String URL = "http://192.168.43.174:3000/api/merchant/locator";
            JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, URL, j2, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {

                    try {
                        Log.v("response",response.toString());
                        JSONObject jsonObject=new JSONObject();
                        jsonObject = response.getJSONObject("merchantLocatorServiceResponse");
                        JSONArray jsonArray=new JSONArray();
                        jsonArray=jsonObject.getJSONArray("response");
                        Marker marker=null;


                        for(int i =0 ;i<jsonArray.length();i++)
                        {
                            JSONObject jsonObject1=new JSONObject();
                            jsonObject1=jsonArray.getJSONObject(i);

                            latitude.add(jsonObject1.getJSONObject("responseValues").getString("locationAddressLatitude"));
                            longitude.add(jsonObject1.getJSONObject("responseValues").getString("locationAddressLongitude"));
                            merchant.add(jsonObject1.getJSONObject("responseValues").getString("visaMerchantName"));
                            MarkerOptions options = new MarkerOptions().position(new LatLng(Double.parseDouble(latitude.get(i)), Double.parseDouble(longitude.get(i)))).title(merchant.get(i));
                            mMap.addMarker(options);
                        }
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(37.363922,-121.929163)));
                        mMap.animateCamera(CameraUpdateFactory.zoomTo(14f));
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
            } ) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

            };
            jsonObjectRequest.setRetryPolicy(new RetryPolicy() {
                @Override
                public int getCurrentTimeout() {
                    return 50000;
                }

                @Override
                public int getCurrentRetryCount() {
                    return 50000;
                }

                @Override
                public void retry(VolleyError error) throws VolleyError {

                }
            });
            requestQueue.add(jsonObjectRequest);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return inflater.inflate(R.layout.fragment_merchants, container, false);
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
        mViewModel = ViewModelProviders.of(this).get(MerchantsViewModel.class);
    }

}