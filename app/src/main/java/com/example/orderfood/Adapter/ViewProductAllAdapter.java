package com.example.orderfood.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderfood.Constant;
import com.example.orderfood.DetailProductActivity;
import com.example.orderfood.Fragments.HomeFragment;
import com.example.orderfood.Models.CartItem;
import com.example.orderfood.Models.Product;
import com.example.orderfood.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;

public class ViewProductAllAdapter extends RecyclerView.Adapter<ViewProductAllAdapter.ViewProducAlltHolder>{
    private Context context;
    private ArrayList<Product> list;
    private ArrayList<Product> listSearch;
    private boolean chuaco = true;

    public ViewProductAllAdapter(Context context, ArrayList<Product> list) {
        this.context = context;
        this.list = list;
        this.listSearch = new ArrayList<>(list);
    }

    @NonNull
    @Override
    public ViewProductAllAdapter.ViewProducAlltHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_list_product,parent,false);
        return new ViewProductAllAdapter.ViewProducAlltHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewProductAllAdapter.ViewProducAlltHolder holder, int position) {
        Product product = list.get(position);
        Picasso.get().load(Constant.URL+"storage/product1/"+product.getPhoto()).into(holder.imgProduct2);
        holder.txtName2.setText(product.getName());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtPrice2.setText(decimalFormat.format(product.getPrice()) + " Đ");
        //add Card them vao mang addCard
        holder.btn_addCart.setOnClickListener(v->{
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
        holder.itemView.setOnClickListener(v->{
            Intent j = new Intent(context, DetailProductActivity.class);
            j.putExtra("id",product.getId());
            j.putExtra("name",product.getName());
            j.putExtra("des1",product.getDes1());
            j.putExtra("photo",product.getPhoto());
            j.putExtra("price",product.getPrice());
            j.putExtra("available",product.getAvailable());
            context.startActivity(j);
            Log.e("huy","itemView");
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            ArrayList<Product> filterList = new ArrayList<>();
            if(constraint.toString().isEmpty()){
                filterList.addAll(listSearch);
            }
            else {
                for (Product product : listSearch){
                    if(product.getName().toLowerCase().contains(constraint.toString().toLowerCase())){
                        filterList.add(product);
                    }
                }

            }

            FilterResults results = new FilterResults();
            results.values = filterList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            list.clear();
            list.addAll((Collection<? extends Product>) results.values);
            notifyDataSetChanged();
        }
    };

    public Filter getFilter(){
        return filter;
    }
    class ViewProducAlltHolder extends RecyclerView.ViewHolder{
        private ImageView imgProduct2;
        private TextView txtName2,txtPrice2;
        private ImageButton btn_addCart;
        private View itemView;
        public ViewProducAlltHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            imgProduct2 = itemView.findViewById(R.id.imgProduct2);
            txtName2 = itemView.findViewById(R.id.txtName2);
            txtPrice2 = itemView.findViewById(R.id.txtPrice2);
            btn_addCart = itemView.findViewById(R.id.btn_addCart);

        }
    }

}
