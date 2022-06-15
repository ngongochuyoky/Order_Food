package com.example.orderfood.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.example.orderfood.Adapter.ViewSlideAdapter;
import com.example.orderfood.AuthActivity;
import com.example.orderfood.Constant;
import com.example.orderfood.HomeActivity;
import com.example.orderfood.Models.Slide;
import com.example.orderfood.R;
import com.example.orderfood.UpdateInfoActivity;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class AccountFragment extends Fragment {
    private View view;
    private CircleImageView imgUserInfo;
    private TextView txtName,txtName1,txtPhone,txtEmail,txtAddress;
    private Button editAccount,btnLogOut;
    private SharedPreferences userPref;


    public AccountFragment(){}
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_account,container,false);
        init();
        return view;
    }
    private void init(){

        imgUserInfo = view.findViewById(R.id.imgUserInfo);
        txtName = view.findViewById(R.id.txtName);
        btnLogOut = view.findViewById(R.id.btnLogOut);
        txtName1 = view.findViewById(R.id.txtName1);
        txtPhone = view.findViewById(R.id.txtPhone);
        txtEmail = view.findViewById(R.id.txtEmail);
        txtAddress = view.findViewById(R.id.txtAddress);
        editAccount = view.findViewById(R.id.editAccount);
        userPref = getActivity().getApplicationContext().getSharedPreferences("huy", Context.MODE_PRIVATE);
        getData();
        action();
    }
    private void action(){
        editAccount.setOnClickListener(v->{
             getContext().startActivity(new Intent(getContext(), UpdateInfoActivity.class));
        });


    }
    private void getData(){
        String photo = userPref.getString("photo","");
        if(!photo.isEmpty())
            Picasso.get().load(Constant.URL+"storage/profiles/"+photo).into(imgUserInfo);
        else
            Picasso.get().load(Constant.URL+"storage/profiles/"+"account.png").into(imgUserInfo);


        txtName.setText(userPref.getString("name",""));
        txtName1.setText(userPref.getString("name",""));
        txtPhone.setText(userPref.getString("phone",""));
        txtEmail.setText(userPref.getString("email",""));
        txtAddress.setText(userPref.getString("address",""));
    }

}
