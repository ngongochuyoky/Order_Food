package com.example.orderfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.orderfood.Adapter.ViewProductAllAdapter;
import com.example.orderfood.Models.Product;
import com.example.orderfood.Models.Type;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TypeProductActivity extends AppCompatActivity {

    private Toolbar toolbarBack;
    private int id = 0,position = 0;
    private RecyclerView recyclerView;
    private ViewProductAllAdapter viewProductAllAdapter;
    private ArrayList<Product> list;
    private TextView txtName1;
    private ImageView imgProduct1;
    private Type type;

    public TypeProductActivity(){}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product);
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
        txtName1 = findViewById(R.id.txtName1);

        imgProduct1 = findViewById(R.id.imgProduct1);
        getData();
    }
    private void getData(){
        list = new ArrayList<>();
        id = getIntent().getIntExtra("typeId",0);
        position = getIntent().getIntExtra("position",0);
        StringRequest request = new StringRequest(Request.Method.POST,Constant.TYPE_ID,response -> {

            try {
                JSONObject object = new JSONObject(response);
                if(object.getBoolean("success")){

                    JSONArray array = new JSONArray(object.getString("products1"));

                    for(int i = 0;i < array.length(); i++){
                        JSONObject productObject = array.getJSONObject(i);
                        Product product = new Product();
                        product.setId(productObject.getInt("id"));
                        product.setDes1(productObject.getString("des1"));
                        product.setPhoto(productObject.getString("photo"));
                        product.setName(productObject.getString("name"));
                        product.setDes(productObject.getString("des"));
                        product.setPrice(productObject.getInt("price"));
                        list.add(product);
                    }

                    JSONArray array1 = new JSONArray(object.getString("type"));
                    JSONObject typeObject = array1.getJSONObject(0);
                    type = new Type();
                    type.setId(typeObject.getInt("id"));
                    type.setPhoto(typeObject.getString("photo"));
                    type.setPhoto1(typeObject.getString("photo1"));
                    Log.e("huy",type.getPhoto1()+"");
                    type.setName(typeObject.getString("name"));
                    //Hien thi top
                    Picasso.get().load(Constant.URL+"storage/types/"+type.getPhoto1()).into(imgProduct1);
                    txtName1.setText(type.getName());

                    //set recyclerView
                    viewProductAllAdapter = new ViewProductAllAdapter(this,list);
                    recyclerView.setAdapter(viewProductAllAdapter);

                }
            } catch (JSONException e) {
                e.printStackTrace();

            }

        },error -> {
            error.printStackTrace();

        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                HashMap<String,String> params = new HashMap<>();
                params.put("id",id+"");
                return params;
            }

        };
        RequestQueue queue = Volley.newRequestQueue(TypeProductActivity.this);
        queue.add(request);
//        Picasso.get().load(Constant.URL+"storage/types/"+type.getPhoto1()).into(imgProduct1);


    }
}