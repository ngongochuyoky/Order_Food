package com.example.orderfood.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.orderfood.CartActivity;
import com.example.orderfood.Constant;

import com.example.orderfood.Fragments.HomeFragment;
import com.example.orderfood.Models.CartItem;

import com.example.orderfood.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ViewCartAdapter extends RecyclerView.Adapter<ViewCartAdapter.ViewCartHolder> {

    private Context context;
    private ArrayList<CartItem> list;


    public ViewCartAdapter(Context context, ArrayList<CartItem> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewCartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_cart,parent,false);
        return new ViewCartHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewCartHolder holder, int position) {
        CartItem cartItem = list.get(position);
        Picasso.get().load(Constant.URL+"storage/product1/"+cartItem.getPhoto()).into(holder.imgProduct);
        holder.txtName.setText(cartItem.getName());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtPrice.setText(decimalFormat.format(cartItem.getPrice()) + " Đ");
//        holder.buttonValues.setText(cartItem.getNumber()+"");
        holder.btnNumber.setNumber(String.valueOf(cartItem.getNumber()));
        holder.btnNumber.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
            @Override
            public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
                if(newValue==cartItem.getMax()+1) {
                    HomeFragment.addCard.get(position).setNumber(newValue-1);
                }
                else{
                    HomeFragment.addCard.get(position).setNumber(newValue);
                }
                CartActivity.recyclerView8.getAdapter().notifyItemChanged(position);
                CartActivity.recyclerView8.getAdapter().notifyDataSetChanged();
                CartActivity.showData();
            }
        });

        holder.buttonDelete.setOnClickListener(v->{

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Xác nhận");
            builder.setMessage("Bạn có chắc muốn xóa ");
            builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    HomeFragment.addCard.remove(position);
                    CartActivity.recyclerView8.getAdapter().notifyDataSetChanged();
                    CartActivity.showData();
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            builder.show();

        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewCartHolder extends RecyclerView.ViewHolder {
        private ImageView imgProduct;
        private TextView txtName,txtPrice;
        private ImageView buttonDelete;
        private ElegantNumberButton btnNumber;

        public ViewCartHolder(@NonNull View itemView) {
            super(itemView);
            btnNumber = itemView.findViewById(R.id.btnNumber);
            buttonDelete = itemView.findViewById(R.id.buttonDelete);

            imgProduct = itemView.findViewById(R.id.imgProduct3);
            txtName = itemView.findViewById(R.id.txtName3);
            txtPrice = itemView.findViewById(R.id.txtPrice3);
        }
    }
}
