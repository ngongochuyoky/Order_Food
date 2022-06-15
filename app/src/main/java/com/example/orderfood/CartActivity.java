package com.example.orderfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.text.DecimalFormat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.orderfood.Adapter.ViewCartAdapter;
import com.example.orderfood.Fragments.HomeFragment;
import com.example.orderfood.Models.CartItem;

public class CartActivity extends AppCompatActivity {

    public static RecyclerView recyclerView8;
    private ViewCartAdapter viewCartAdapter;
    private Button btnPay;
    public static TextView txtTotal;
    public static double tongtien = 0;
    private Toolbar toolbarBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        init();
    }
    private void init(){
        toolbarBack = findViewById(R.id.toolbarBack);
        setSupportActionBar(toolbarBack);
        getSupportActionBar().setTitle("Giỏ hàng");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarBack.setNavigationOnClickListener(v->{
            finish();
        });
        recyclerView8 = findViewById(R.id.rcv_cart);
        recyclerView8.setHasFixedSize(true);
        recyclerView8.setLayoutManager(new LinearLayoutManager(this));
        btnPay = findViewById(R.id.btnPay);
        if(HomeFragment.addCard.size()>0){
            btnPay.setVisibility(View.VISIBLE);
        }
        else btnPay.setVisibility(View.INVISIBLE);
        txtTotal = findViewById(R.id.txtTotal);

        viewCartAdapter = new ViewCartAdapter(CartActivity.this, HomeFragment.addCard);
        recyclerView8.setAdapter(viewCartAdapter);
        if(HomeFragment.addCard.size()>0){
            viewCartAdapter.notifyDataSetChanged();
        }
        btnPay.setOnClickListener(v->{
            startActivity(new Intent(CartActivity.this,ReviewAndPayActivity.class));
        });
        showData();

    }
    public static void showData() {
        tongtien = 0 ;
        for (int i = 0; i < HomeFragment.addCard.size(); i++) {
            tongtien = tongtien + HomeFragment.addCard.get(i).getPrice()*HomeFragment.addCard.get(i).getNumber();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtTotal.setText(decimalFormat.format(tongtien) + "");
    }

}