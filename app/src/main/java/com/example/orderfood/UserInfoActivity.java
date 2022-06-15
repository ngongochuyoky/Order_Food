package com.example.orderfood;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserInfoActivity extends AppCompatActivity {

    private TextInputLayout layoutName,txtLayoutPhoneUserInfo,txtLayoutAddressUserInfo;
    private TextInputEditText txtName,txtPhoneUserInfo,txtAddressUserInfo;
    private TextView txtSelectPhoto;
    private Button btnContinue;
    private CircleImageView circleImageView;
    private static final int GALLERY_ADD_PROFILE =1;
    private Bitmap bitmap = null;
    private SharedPreferences userPref;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        init();
    }
    private void init(){
        dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        userPref = getApplicationContext().getSharedPreferences("huy", Context.MODE_PRIVATE);
        //Anh xa layout
        layoutName = findViewById(R.id.txtLayoutNameUserInfo);
        txtLayoutPhoneUserInfo = findViewById(R.id.txtLayoutPhoneUserInfo);
        txtLayoutAddressUserInfo = findViewById(R.id.txtLayoutAddressUserInfo);
        //Anh xa phan con lai
        txtName = findViewById(R.id.txtNameUserInfo);
        txtPhoneUserInfo = findViewById(R.id.txtPhoneUserInfo);
        txtAddressUserInfo = findViewById(R.id.txtAddressUserInfo);
        txtSelectPhoto = findViewById(R.id.txtSelectPhoto);
        btnContinue = findViewById(R.id.btnContinue);
        circleImageView = findViewById(R.id.imgUserInfo);
        //chon hinh anh
        txtSelectPhoto.setOnClickListener(v->{
            Intent i = new Intent(Intent.ACTION_PICK);
            i.setType("image/*");
            startActivityForResult(i,GALLERY_ADD_PROFILE);
        });
        btnContinue.setOnClickListener(v -> {
            if(validate()){
                saveUserInfo();
            }
        });
        txtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!txtName.getText().toString().isEmpty()){
                    layoutName.setErrorEnabled(false);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        txtPhoneUserInfo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(txtPhoneUserInfo.getText().toString().length()!=10){
                    txtLayoutPhoneUserInfo.setErrorEnabled(false);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        txtAddressUserInfo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!txtAddressUserInfo.getText().toString().isEmpty()){
                    txtLayoutAddressUserInfo.setErrorEnabled(false);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==GALLERY_ADD_PROFILE && resultCode==RESULT_OK){
            Uri imgUri = data.getData();
            circleImageView.setImageURI(imgUri);
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),imgUri);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private boolean validate(){
        if(txtName.getText().toString().isEmpty()){
            layoutName.setErrorEnabled(true);
            layoutName.setError("Không được bỏ trống");
            return false;
        }
        if(txtPhoneUserInfo.getText().toString().length()!=10){
            txtLayoutPhoneUserInfo.setErrorEnabled(true);
            txtLayoutPhoneUserInfo.setError("Phải đủ 10 số");
            return false;
        }
        if(txtAddressUserInfo.getText().toString().isEmpty()){
            txtLayoutAddressUserInfo.setErrorEnabled(true);
            txtLayoutAddressUserInfo.setError("Không được bỏ trống");
            return false;
        }
        return true;
    }
    private void saveUserInfo(){
        dialog.setMessage("Đang lưu");
        dialog.show();

        StringRequest request = new StringRequest(Request.Method.POST,Constant.SAVE_USER_INFO, response -> {

            try {
                JSONObject object = new JSONObject(response);
                if(object.getBoolean("success")){
                    SharedPreferences.Editor editor = userPref.edit();
                    editor.putString("photo",object.getString("photo"));
                    editor.putString("name",object.getString("name"));
                    editor.putString("address",object.getString("address"));
                    editor.putString("phone",object.getString("phone"));
                    editor.apply();
                    Toast.makeText(getApplicationContext(),"Thành công",Toast.LENGTH_SHORT);
                    startActivity(new Intent(UserInfoActivity.this,HomeActivity.class));
                    finish();
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(),"Thất bại",Toast.LENGTH_SHORT);
            }
            dialog.dismiss();

        },error -> {
            error.printStackTrace();
            Toast.makeText(getApplicationContext(),"Kết nối ko thành công",Toast.LENGTH_SHORT);
            dialog.dismiss();
        }){
            //them token

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                String token = userPref.getString("token","");
                HashMap<String,String> map = new HashMap<>();
                map.put("Authorization","Bearer"+token);
                return map;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> params = new HashMap<>();
                params.put("name",txtName.getText().toString().trim());
                params.put("phone",txtPhoneUserInfo.getText().toString().trim());
                params.put("address",txtAddressUserInfo.getText().toString().trim());
                params.put("photo",bitmapToString(bitmap));
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(UserInfoActivity.this);
        requestQueue.add(request);
    }
    private String bitmapToString(Bitmap bitmap) {
        if(bitmap!=null){
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
            byte[] array = byteArrayOutputStream.toByteArray();
            return Base64.encodeToString(array, Base64.DEFAULT);
        }
        return "";
    }
}