package com.example.orderfood.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderfood.Constant;
import com.example.orderfood.Models.Slide;
import com.example.orderfood.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ViewSlideAdapter extends RecyclerView.Adapter<ViewSlideAdapter.ViewSlideHolder>{

    private Context context;
    private ArrayList<Slide> list;

    public ViewSlideAdapter(Context context, ArrayList<Slide> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewSlideHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_slide,parent,false);
        return new ViewSlideHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewSlideHolder holder, int position) {
        Slide slide = list.get(position);
        Picasso.get().load(Constant.URL+"storage/slides/"+slide.getPhoto()).into(holder.imgViewSlide);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class ViewSlideHolder extends RecyclerView.ViewHolder {
        private ImageView imgViewSlide;

        public ViewSlideHolder(@NonNull View itemView) {
            super(itemView);
            imgViewSlide = itemView.findViewById(R.id.imgViewSlide);
        }
    }
}
