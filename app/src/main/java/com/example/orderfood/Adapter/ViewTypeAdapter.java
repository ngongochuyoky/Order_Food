package com.example.orderfood.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderfood.Constant;
import com.example.orderfood.Models.Type;
import com.example.orderfood.R;
import com.example.orderfood.TypeProductActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ViewTypeAdapter extends RecyclerView.Adapter<ViewTypeAdapter.ViewTypeHolder> {
    private Context context;
    private ArrayList<Type> list;

    public ViewTypeAdapter(Context context, ArrayList<Type> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewTypeAdapter.ViewTypeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_product_type,parent,false);
        return new ViewTypeAdapter.ViewTypeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewTypeAdapter.ViewTypeHolder holder, int position) {
        Type type = list.get(position);
        Picasso.get().load(Constant.URL+"storage/product/"+type.getPhoto()).into(holder.imgViewProductType);
        holder.txtProductType.setText(type.getName());
        holder.itemView.setOnClickListener(v->{
            Intent u = new Intent(context, TypeProductActivity.class);
            u.putExtra("typeId",type.getId());
            u.putExtra("position",position);
            context.startActivity(u);
            Log.e("TAG", type.getId()+"" );


        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class ViewTypeHolder extends RecyclerView.ViewHolder {
        private ImageView imgViewProductType;
        private TextView txtProductType;
        private View itemView;

//        private
        public ViewTypeHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            imgViewProductType = itemView.findViewById(R.id.imgViewProductType);
            txtProductType = itemView.findViewById(R.id.txtProductType);
        }
    }
}
