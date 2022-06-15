package com.example.orderfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.orderfood.Fragments.HomeFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SuccessActivity extends AppCompatActivity {

    private Button btnBack;
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);
        init();
    }
    private void init(){
        btnBack = findViewById(R.id.btnBack);
        id = getIntent().getIntExtra("order_id",0);
//            StringRequest request = new StringRequest(Request.Method.POST,Constant.SAVE_DETAIL, response -> {
//                try {
//                    JSONObject object = new JSONObject(response);
//                    if(object.getBoolean("success")){
//                        Log.e("huy","xong");
//
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//
//                }
//
//
//
//            },error -> {
//                error.printStackTrace();
//            }){
//                //them token
//
//                @Override
//                protected Map<String, String> getParams() throws AuthFailureError {
//                    JSONArray jsonArray = new JSONArray();
////                    for (int i = 0; i < HomeFragment.addCard.size(); i++) {
////                        JSONObject jsonObject = new JSONObject();
////                        try {
////                            jsonObject.put("order_id", id);
////                            jsonObject.put("product_id", HomeFragment.addCard.get(i).getId());
////                            jsonObject.put("number", HomeFragment.addCard.get(i).getNumber());
////
////                        } catch (JSONException e) {
////                            e.printStackTrace();
////                        }
////                        jsonArray.put(jsonObject);
////                    }
////
//                    HashMap<String,String> params = new HashMap<>();
////                    params.put("json",jsonArray.toString());
//                    params.put("order_id","1");
//                    params.put("product_id","1");
//                    params.put("number","1");
//                    return params;
//                }
//            };


        //test
        StringRequest request = new StringRequest(Request.Method.POST,Constant.SAVE_DETAIL, response -> {

            try {
                JSONObject object = new JSONObject(response);
                if(object.getBoolean("success")){


                }
            } catch (JSONException e) {
                e.printStackTrace();

            }
//


        },error -> {
            error.printStackTrace();
        }){
            //them token

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                JSONArray jsonArray = new JSONArray();
                    for (int i = 0; i < HomeFragment.addCard.size(); i++) {
                        JSONObject jsonObject = new JSONObject();
                        try {
                            jsonObject.put("order_id", id);
                            jsonObject.put("product_id", HomeFragment.addCard.get(i).getId());
                            jsonObject.put("number", HomeFragment.addCard.get(i).getNumber());


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        jsonArray.put(jsonObject);
                    }

                HashMap<String,String> params = new HashMap<>();
                params.put("json",jsonArray.toString());
                Log.e("Param", jsonArray.toString());
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(SuccessActivity.this);
        requestQueue.add(request);
        //test

        btnBack.setOnClickListener(v->{
            startActivity(new Intent(SuccessActivity.this,HomeActivity.class));
            finish();
        });
    }
}