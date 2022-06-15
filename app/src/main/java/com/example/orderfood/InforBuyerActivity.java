package com.example.orderfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.orderfood.Fragments.HomeFragment;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class InforBuyerActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    private Button btnOk;
    private TextInputLayout txtLayoutName,txtLayoutPhone,txtLayoutAddress,txtLayoutDate;
    private TextInputEditText txtName,txtDate,txtAddress,txtPhone;
    private SharedPreferences userPref;
    private ProgressDialog dialog;
    private Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor_buyer);
        init();
    }
    private void init(){
//        dialog = new ProgressDialog(this);
//        dialog.setCancelable(false);
        userPref = getApplicationContext().getSharedPreferences("huy", Context.MODE_PRIVATE);
        toolbar = findViewById(R.id.toolbarBack);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Nhập thông tin");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> {
            finish();
        });
        txtLayoutName = findViewById(R.id.txtLayoutName);
        txtLayoutPhone = findViewById(R.id.txtLayoutPhone);
        txtLayoutAddress = findViewById(R.id.txtLayoutAddress);
        txtLayoutDate = findViewById(R.id.txtLayoutDate);
        txtName = findViewById(R.id.txtName);
        txtDate = findViewById(R.id.txtDate);
        txtAddress = findViewById(R.id.txtAddress);
        txtPhone = findViewById(R.id.txtPhone);
        btnOk = findViewById(R.id.btnOk);
        btnOk.setOnClickListener(v->{
            if(validate()){
                data();
            }
        });
        //Hien thi chon ngay
        txtDate.setOnClickListener(v->{
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dialog =new DatePickerDialog(
                    this,
                    android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                    dateSetListener,year,month,day);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
        });
        dateSetListener =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;
                String date = month+"/"+dayOfMonth+"/"+year;
                txtDate.setText(date);
            }
        };
        txtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!txtName.getText().toString().isEmpty()){
                    txtLayoutName.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        txtPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(txtPhone.getText().toString().length()==10){
                    txtLayoutPhone.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        txtAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!txtAddress.getText().toString().isEmpty()){
                    txtLayoutAddress.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        txtDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!txtDate.getText().toString().isEmpty()){
                    txtLayoutDate.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    private boolean validate(){
        if(txtName.getText().toString().isEmpty()){
            txtLayoutName.setErrorEnabled(true);
            txtLayoutName.setError("Không được bỏ trống");
            return false;
        }
        if(txtPhone.getText().toString().length()!=10){
            txtLayoutPhone.setErrorEnabled(true);
            txtLayoutPhone.setError("Phải đủ 10 số");
            return false;
        }
        if(txtAddress.getText().toString().isEmpty()){
            txtLayoutAddress.setErrorEnabled(true);
            txtLayoutAddress.setError("Không được bỏ trống");
            return false;
        }


        if(txtDate.getText().toString().isEmpty()){
            txtLayoutDate.setErrorEnabled(true);
            txtLayoutDate.setError("Không được bỏ trống");
            return false;
        }

        return true;
    }
    private void data(){
//        dialog.setMessage("Đang lưu");
//        dialog.show();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xác nhận");
        builder.setMessage("Bạn có chắc muốn đặt hàng ");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Gui du lieu len sever va nhan ve
                Log.e("huy","Da kich ok ");

                StringRequest request = new StringRequest(Request.Method.POST,Constant.SAVE_ORDER, response -> {

                    try {
                        JSONObject object = new JSONObject(response);
                        if(object.getBoolean("success")){
                            i = new Intent(InforBuyerActivity.this,SuccessActivity.class);
                            i.putExtra("order_id",object.getInt("id"));
                            Log.e("huy","Da kich ok nhan du lieu");

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(),"Thất bại",Toast.LENGTH_SHORT);
                    }
//                    dialog.dismiss();
                    startActivity(i);
                    finish();

                },error -> {
                    error.printStackTrace();
//                    dialog.dismiss();
                }){
                    //them token

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String,String> params = new HashMap<>();
                        params.put("user_id",19+"");
                        params.put("name",txtName.getText().toString().trim());
                        params.put("phone",txtPhone.getText().toString().trim());
                        params.put("address",txtAddress.getText().toString().trim());
                        params.put("date",txtDate.getText().toString().trim());
                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(InforBuyerActivity.this);
                requestQueue.add(request);
                //Gui du lieu len sever va nhan ve
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.show();
    }
}