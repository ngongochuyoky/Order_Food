package com.example.orderfood;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.orderfood.Fragments.HomeFragment;
import com.example.orderfood.Models.CartItem;
import com.example.orderfood.Models.Product;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class DetailProductActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ImageView imgProduct1;
    private TextView txtName1;
    private TextView txtPrice1;
    private Button addCart1;
    private RelativeLayout btnCart;
    private TextView txtDes1;
    private Product product;
    private boolean chuaco  = true;


    public DetailProductActivity(){}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);
        toolbar = findViewById(R.id.toolbarBack);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Chi tiết món ăn");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v->{
            finish();
        });
        init();
        //set du lieu do vao view, goi request, viet api laravel
    }
    private void init(){
        btnCart = findViewById(R.id.btnCart);
       imgProduct1 = findViewById(R.id.imgProduct1);
       txtName1 = findViewById(R.id.txtName1);
       txtPrice1 = findViewById(R.id.txtPrice1);
       addCart1 = findViewById(R.id.addCart1);
       txtDes1 = findViewById(R.id.txtDes1);
       product = new Product();
       //Lay du lieu intent
       product.setId(getIntent().getIntExtra("id",0));
       product.setPrice(getIntent().getIntExtra("price",0));
       product.setName(getIntent().getStringExtra("name"));
       product.setDes1(getIntent().getStringExtra("des1"));
       product.setPhoto(getIntent().getStringExtra("photo"));
       product.setAvailable(getIntent().getIntExtra("available",0));
       getData();
       action();
    }
    private void action(){
        //add card them vao mang addCard

        addCart1.setOnClickListener(v->{
            for (int i=0;i<HomeFragment.addCard.size();i++){
                if(product.getId()==HomeFragment.addCard.get(i).getId()){
                    chuaco  = false;
                }
                break;
            }
            if(chuaco  == true){
                CartItem cartItem = new CartItem();
                cartItem.setId(product.getId());
                cartItem.setName(product.getName());
                cartItem.setPhoto(product.getPhoto());
                cartItem.setPrice(product.getPrice());
                cartItem.setMax(product.getAvailable());
                cartItem.setNumber(1);
                HomeFragment.addCard.add(cartItem);
                Toast.makeText(this,"Đã thêm "+product.getName()+" vào giỏ hàng",Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this,product.getName()+" Đã có trong giỏ hàng",Toast.LENGTH_SHORT).show();
            }


        });
        btnCart.setOnClickListener(v->{
            startActivity(new Intent(DetailProductActivity.this,CartActivity.class));
        });
    }
    private void getData(){
        Picasso.get().load(Constant.URL+"storage/product1/"+product.getPhoto()).into(imgProduct1);
        txtName1.setText(product.getName());
        txtDes1.setText(product.getDes1());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtPrice1.setText(decimalFormat.format(product.getPrice()) + " Đ");

    }


}