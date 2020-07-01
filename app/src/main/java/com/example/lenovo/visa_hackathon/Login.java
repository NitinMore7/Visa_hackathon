package com.example.lenovo.visa_hackathon;

import android.annotation.SuppressLint;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.math.BigInteger;

public class Login extends AppCompatActivity {

EditText card,otp;
Button btn,gene;
MyDatabase myDatabase;
ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        myDatabase=new MyDatabase(this);
        if(myDatabase.getDifferentItemsCount()>0)
        {
            Intent i=new Intent(Login.this,MainActivity.class);
            startActivity(i);
        }
        card=findViewById(R.id.edt_card);
        card.setText("4521652378549856");
        otp=findViewById(R.id.edt_otp);
        btn=findViewById(R.id.btn_login);
        gene=findViewById(R.id.otpgenerator);

        gene.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                String url = "http://192.168.43.174:3000/api/driver/generateOtp";
                JSONObject j1 = new JSONObject();
                //final ProgressDialog progressDialog = ProgressDialog.show(getBaseContext(), "GENERATING OTP", "Wait till we verify your profile");
                try {
                    j1.put("cardno", Long.parseLong(card.getText().toString()));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, j1, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                //progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_SHORT).show();
                otp.setVisibility(View.VISIBLE);
                gene.setVisibility(View.INVISIBLE);
                btn.setVisibility(View.VISIBLE);
                otp.setText("5169");
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();

                    }
                });
                requestQueue.add(jsonObjectRequest);
            }});



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkRequiredFieldsNotEmpty()){
                    RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
                    String url="http://192.168.43.174:3000/api/driver/login";
                    JSONObject j1=new JSONObject();
                    //final ProgressDialog progressDialog=ProgressDialog.show(getApplicationContext(),"Loading","Wait till we verify your profile");
                    try {
                        j1.put("cardno",Long.parseLong(card.getText().toString()));
                        j1.put("otp",Integer.parseInt(otp.getText().toString()));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    final JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST,url,j1, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            if(response.length()>1){
                                //progressDialog.dismiss();
                                myDatabase.insert(card.getText().toString());
                                Intent i=new Intent(Login.this,MainActivity.class);
                                startActivity(i);
                            }
                            else {
                                //progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(),"Check your credentials and try again",Toast.LENGTH_SHORT).show();
                                Intent i=new Intent(Login.this,MainActivity.class);
                                startActivity(i);
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(),"Check your credentials and try again",Toast.LENGTH_SHORT).show();

                        }
                    });
                    requestQueue.add(jsonObjectRequest);
                }
            }
        });
    }




    private boolean checkRequiredFieldsNotEmpty() {
        boolean result = true;
        if (card.getText().toString().length() <= 0) {
            Toast.makeText(getApplicationContext(), "Please enter your Roll number", Toast.LENGTH_SHORT).show();
            result = false;
        }
        if (otp.getText().toString().length() <= 0) {
            Toast.makeText(getApplicationContext(), "Please enter your password", Toast.LENGTH_SHORT).show();
            result = false;
        }
        return result;
    }
}