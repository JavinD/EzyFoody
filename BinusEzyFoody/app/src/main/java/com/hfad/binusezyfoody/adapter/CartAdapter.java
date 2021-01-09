package com.hfad.binusezyfoody.adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hfad.binusezyfoody.model.CartData;
import com.hfad.binusezyfoody.R;


import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    List<CartData>carts;
    Context context;

    public CartAdapter(Context context, List<CartData> carts) {
        this.carts = carts;
        this.context = context;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_items,parent,false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, final int position) {
        CartData cart=carts.get(position);

        holder.cartName.setText(carts.get(position).getName());
        holder.cartPrice.setText("Rp. "+carts.get(position).getPrice());
        holder.cartRating.setText(carts.get(position).getRating());
        holder.cartQty.setText(String.valueOf(carts.get(position).getQty()));
        Glide.with(context).load(carts.get(position).getImageUrl()).into(holder.cartImage);

        };



    @Override
    public int getItemCount() {
        return carts.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder{
        TextView cartName, cartPrice, cartRating, cartQty;
        ImageView cartImage;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            cartImage=(ImageView)itemView.findViewById(R.id.imageView8);
            cartName=(TextView)itemView.findViewById(R.id.name);
            cartPrice=(TextView)itemView.findViewById(R.id.price);
            cartRating=(TextView)itemView.findViewById(R.id.rating);
            cartQty=(TextView)itemView.findViewById(R.id.quantity);
        }
    }
}