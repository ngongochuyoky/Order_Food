package com.example.orderfood.Fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.orderfood.Adapter.ViewProductAdapter;
import com.example.orderfood.Adapter.ViewSlideAdapter;
import com.example.orderfood.Adapter.ViewTypeAdapter;
import com.example.orderfood.Constant;
import com.example.orderfood.HomeActivity;
import com.example.orderfood.Models.CartItem;
import com.example.orderfood.Models.Product;
import com.example.orderfood.Models.Slide;
import com.example.orderfood.Models.Type;
import com.example.orderfood.ProductActivity;
import com.example.orderfood.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class HomeFragment extends Fragment {
      private TextView showAll;
      private View view;
      private RecyclerView recyclerView;
      private RecyclerView recyclerView1;
      private RecyclerView recyclerView2;
      private Toolbar toolbar;
      private ArrayList<Slide> arrayList;
      private ArrayList<Type> arrayList1;
      public static ArrayList<Product> arrayList2;
      private ViewTypeAdapter viewTypeAdapter;
      private ViewSlideAdapter viewSlideAdapter;
      private ViewProductAdapter viewProductAdapter;

      private SharedPreferences sharedPreferences;
      //add Card
      public static ArrayList<CartItem> addCard = new ArrayList<>();
      public static int k=0;
      public HomeFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_home,container,false);
        init();
        return view;
    }
    private void init(){
          sharedPreferences = getContext().getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
          //Anh xa slide dau home
          recyclerView = view.findViewById(R.id.rcv_viewTop);
          recyclerView.setHasFixedSize(true);
          recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false));
          // Anh xa list type
          recyclerView1 = view.findViewById(R.id.rcv_viewList);
          recyclerView1.setHasFixedSize(true);
          recyclerView1.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false));
          //Anh xa list product
          recyclerView2 = view.findViewById(R.id.rcv_viewAll);
          recyclerView2.setHasFixedSize(true);
          recyclerView2.setLayoutManager((new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false)));
//          toolbar = view.findViewById(R.id.toolbarHome);
//        ((HomeActivity)getContext()).setSupportActionBar(toolbar);
          showAll = view.findViewById(R.id.showAll);
          getData();
          action();
      }
    private void action(){
          showAll.setOnClickListener(v->{
               startActivity(new Intent(getContext(), ProductActivity.class));
          });


    }
    private void getData() {
          arrayList = new ArrayList<>();
          arrayList1 = new ArrayList<>();
          arrayList2 = new ArrayList<>();
          //Gui nhan du lieu slide dau
        StringRequest request = new StringRequest(Request.Method.GET, Constant.SLIDES,response -> {

            try {
                JSONObject object = new JSONObject(response);
                if(object.getBoolean("success")){
                    JSONArray array = new JSONArray(object.getString("slides"));
                    for(int i = 0;i < array.length(); i++){
                        JSONObject slideObject = array.getJSONObject(i);
                        Slide slide = new Slide();
                        slide.setPhoto(slideObject.getString("photo"));
                        arrayList.add(slide);
                    }
                    viewSlideAdapter = new ViewSlideAdapter(getContext(),arrayList);
                    recyclerView.setAdapter(viewSlideAdapter);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        },error -> {
              error.printStackTrace();
        }){
          //Gui token di truoc

//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                String token = sharedPreferences.getString("token","");
//                HashMap<String,String> map =new HashMap<>();
//                map.put("Authorization","Bearer"+token);
//                return map;
//            }
        @Override
        protected Map<String, String> getParams() throws AuthFailureError{
            HashMap<String,String> map = new HashMap<>();

            return map;
        }
                };

        //List type product
        StringRequest request1 = new StringRequest(Request.Method.GET, Constant.PRODUCT_TYPE,response -> {

            try {
                JSONObject object = new JSONObject(response);
                if(object.getBoolean("success")){
                    JSONArray array = new JSONArray(object.getString("types"));
                    for(int i = 0;i < array.length(); i++){
                        JSONObject typeObject = array.getJSONObject(i);
                        Type type = new Type();
                        type.setId(typeObject.getInt("id"));
                        type.setPhoto(typeObject.getString("photo"));
                        type.setName(typeObject.getString("name"));

                        arrayList1.add(type);
                    }
                    viewTypeAdapter = new ViewTypeAdapter(getContext(),arrayList1);
                    recyclerView1.setAdapter(viewTypeAdapter);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        },error -> {
            error.printStackTrace();
        }){
            //Gui token di truoc

//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                String token = sharedPreferences.getString("token","");
//                HashMap<String,String> map =new HashMap<>();
//                map.put("Authorization","Bearer"+token);
//                return map;
//            }
@Override
        protected Map<String, String> getParams() throws AuthFailureError{
            HashMap<String,String> map = new HashMap<>();
            return map;
        }
        };

        //Gui nhan du lieu all product

        StringRequest request2 = new StringRequest(Request.Method.GET, Constant.PRODUCT,response -> {

            try {
                JSONObject object = new JSONObject(response);
                if(object.getBoolean("success")){
                    JSONArray array = new JSONArray(object.getString("products"));
                    for(int i = 0;i < array.length(); i++){
                        JSONObject productObject = array.getJSONObject(i);
                        Product product = new Product();
                        product.setId(productObject.getInt("id"));
                        product.setPhoto(productObject.getString("photo"));
                        product.setName(productObject.getString("name"));
                        product.setDes(productObject.getString("des"));
                        product.setDes1(productObject.getString("des1"));
                        product.setPrice(productObject.getInt("price"));
                        product.setAvailable(productObject.getInt("available"));
                        arrayList2.add(product);
                    }
                    viewProductAdapter = new ViewProductAdapter(getContext(),arrayList2);
                    recyclerView2.setAdapter(viewProductAdapter);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        },error -> {
            error.printStackTrace();
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                HashMap<String,String> map = new HashMap<>();
                return map;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(request);
        queue.add(request1);
        queue.add(request2);

    }



}
