package com.example.orderfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.orderfood.Adapter.ViewProductAllAdapter;
import com.example.orderfood.Fragments.HomeFragment;

public class ProductActivity extends AppCompatActivity {
    private Toolbar toolbarBack;
    private RecyclerView recyclerView;
    private ViewProductAllAdapter viewProductAllAdapter;
    public ProductActivity(){}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        init();
    }
    private void init(){

        toolbarBack = findViewById(R.id.toolbarBack1);
        setSupportActionBar(toolbarBack);
        getSupportActionBar().setTitle("Danh sách");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarBack.setNavigationOnClickListener(v->{
            finish();
        });
        recyclerView = findViewById(R.id.rcv_viewListProduct);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getData();
    }
    private void getData(){
        //set recyclerView
        viewProductAllAdapter = new ViewProductAllAdapter(this, HomeFragment.arrayList2);
        recyclerView.setAdapter(viewProductAllAdapter);

    }
}