package com.example.orderfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class ReviewAndPayActivity extends AppCompatActivity {

    private TextView txtTotal,txtShip,txtTotalAll;
    private Button btnConfirm;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_and_pay);
        init();
    }
    private void init(){
        toolbar = findViewById(R.id.toolbarBack);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v->{
            finish();
        });

        txtTotal = findViewById(R.id.txtTotal);
        txtShip = findViewById(R.id.txtShip);
        txtTotalAll = findViewById(R.id.txtTotalAll);
        btnConfirm = findViewById(R.id.btnConfirm);
        getData();
    }
    private void getData(){
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtTotal.setText(decimalFormat.format(CartActivity.tongtien));
        txtShip.setText(decimalFormat.format(25000));
        txtTotalAll.setText(decimalFormat.format(25000+CartActivity.tongtien));
        btnConfirm.setOnClickListener(v->{
            startActivity(new Intent(this,InforBuyerActivity.class));
        });
    }
}