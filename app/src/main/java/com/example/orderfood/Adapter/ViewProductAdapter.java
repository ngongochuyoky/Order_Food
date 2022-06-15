package com.example.orderfood.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderfood.Constant;
import com.example.orderfood.DetailProductActivity;
import com.example.orderfood.Fragments.HomeFragment;
import com.example.orderfood.Models.CartItem;
import com.example.orderfood.Models.Product;
import com.example.orderfood.Models.Type;
import com.example.orderfood.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ViewProductAdapter extends RecyclerView.Adapter<ViewProductAdapter.ViewProductHolder>{
    private Context context;
    private ArrayList<Product> list;
    private boolean chuaco = true;

    public ViewProductAdapter(Context context, ArrayList<Product> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product,parent,false);
        return new ViewProductHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewProductHolder holder, int position) {

        Product product = list.get(position);
        Picasso.get().load(Constant.URL+"storage/product1/"+product.getPhoto()).into(holder.imgItemProduct);
        holder.txtItemProduct.setText(product.getName()+"");
        holder.txtDes.setText(product.getDes());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtPrice.setText(decimalFormat.format(product.getPrice()) + " Đ");
        //Du lieu cho mang hinh chi tiet
        holder.itemView.setOnClickListener(v->{
            Intent j = new Intent(context, DetailProductActivity.class);
            j.putExtra("id",product.getId());
            j.putExtra("name",product.getName());
            j.putExtra("des1",product.getDes1());
            j.putExtra("photo",product.getPhoto());
            j.putExtra("price",product.getPrice());
            //De add cart neen phai co so luong
            j.putExtra("available",product.getAvailable());
            context.startActivity(j);
        });
        //Kich add tai mang hinh
        holder.addCart.setOnClickListener(v->{
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
                Toast.makeText(context,"Đã thêm "+product.getName()+" vào giỏ hàng",Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(context,product.getName()+" Đã có trong giỏ hàng",Toast.LENGTH_SHORT).show();
            }

        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class ViewProductHolder extends RecyclerView.ViewHolder{
        private View itemView;
        private ImageView imgItemProduct,addCart;
        private TextView txtItemProduct,txtDes,txtPrice;
        public ViewProductHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            addCart = itemView.findViewById(R.id.addCart);
            imgItemProduct = itemView.findViewById(R.id.imgItemProduct);
            txtItemProduct = itemView.findViewById(R.id.txtItemProduct);
            txtDes = itemView.findViewById(R.id.txtDes);
            txtPrice = itemView.findViewById(R.id.txtPrice);

        }
    }
}
