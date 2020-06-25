package com.example.lenovo.visa_hackathon.ui.merchlocator;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airbnb.lottie.L;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.lenovo.visa_hackathon.R;
import com.example.lenovo.visa_hackathon.ui.merchlocator.Pojo.MerchantLocatorServiceResponse;
import com.example.lenovo.visa_hackathon.ui.merchlocator.Pojo.Merchpojo;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

public class Merchlocator extends Fragment {

    private MerchlocatorViewModel mViewModel;

    public static Merchlocator newInstance() {
        return new Merchlocator();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        try{
            RequestQueue requestQueue= Volley.newRequestQueue(getContext(),new HurlStack(null,newSslSocketFactory()));
            JSONObject j11=new JSONObject();
            j11.put("messageDateTime","2020-06-23T17:53:09.903");
            j11.put("requestMessageId","Request_001");
            JSONObject j12=new JSONObject();
            j12.put("merchantName","Starbucks");
            j12.put("merchantCountryCode",840);
            j12.put("latitude","37.363922");
            j12.put("longitude","-121.929163");
            j12.put("distance",2);
            j12.put("distanceUnit","M");
            JSONArray array=new JSONArray();
            array.put("GNLOCATOR");
            JSONObject j13=new JSONObject();
            j13.put("maxRecords",5);
            j13.put("matchIndicators",true);
            j13.put("matchScore",true);
            JSONObject j1
                    =new JSONObject();
            j1.put("header",j11);
            j1.put("searchAttrList",j12);
            j1.put("responseAttrList",array);
            j1.put("searchOptions",j13);


            String URL = "https://sandbox.api.visa.com/merchantlocator/v1/locator";
            JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, URL, j1, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {

                    try {

                        JSONObject jsonObject=new JSONObject();
                        jsonObject = response.getJSONObject("merchantLocatorServiceResponse");
                        JSONArray jsonArray=new JSONArray();
                        jsonArray=jsonObject.getJSONArray("response");
                        ArrayList<String> latitude ,longitude,merchant;
                        latitude=new ArrayList<String>();
                        longitude=new ArrayList<String>();
                        merchant=new ArrayList<String>();

                        for(int i =0 ;i<jsonArray.length();i++)
                        {
                            JSONObject jsonObject1=new JSONObject();
                            jsonObject1=jsonArray.getJSONObject(i);
                            latitude.add(jsonObject1.getJSONObject("responseValues").getString("locationAddressLatitude"));
                            longitude.add(jsonObject1.getJSONObject("responseValues").getString("locationAddressLongitude"));
                            merchant.add(jsonObject1.getJSONObject("responseValues").getString("visaMerchantName"));
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

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> headers = new HashMap<>();
                    String credentials = "91NAOOUFLPJHBQAU1IFQ21JM1lhn7J4Ej19O-j36WPDNXm-I0:56UQIkl3Xpd20M5ZUPi8Ujl";
                    String auth = "Basic "
                            + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
                    headers.put("Content-Type", "application/json; charset=utf-8");
                    headers.put("Authorization", auth);
                    headers.put("Host","sandbox.api.visa.com");
                    return headers;
                }
            };
            requestQueue.add(jsonObjectRequest);
        }catch (Exception e) {
            e.printStackTrace();
        }

        return inflater.inflate(R.layout.merchlocator_fragment, container, false);
    }
    private SSLSocketFactory newSslSocketFactory() {
        try {

            // Get an instance of the Bouncy Castle KeyStore format
            KeyStore trusted = KeyStore.getInstance("BKS");
            // Get the raw resource, which contains the keystore with
            // your trusted certificates (root and any intermediate certs)
            InputStream in = getContext().getResources().openRawResource(R.raw.keystore);
            try {
                // Initialize the keystore with the provided trusted certificates
                // Provide the password of the keystore
                trusted.load(in, "temp101".toCharArray());
            } finally {
                in.close();
            }

            String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
            tmf.init(trusted);

            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, tmf.getTrustManagers(), null);

            SSLSocketFactory sf = context.getSocketFactory();
            return sf;
        } catch (Exception e) {
            throw new AssertionError(e);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MerchlocatorViewModel.class);

    }

}