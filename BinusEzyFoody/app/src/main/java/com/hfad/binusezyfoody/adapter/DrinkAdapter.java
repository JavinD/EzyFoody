package com.hfad.binusezyfoody.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hfad.binusezyfoody.model.Drink;
import com.hfad.binusezyfoody.R;
import com.hfad.binusezyfoody.Details;

import java.util.List;

public class DrinkAdapter extends RecyclerView.Adapter<DrinkAdapter.DrinkViewHolder> {

    Context context;
    List<Drink> drinkList;

    public DrinkAdapter(Context context, List<Drink> drinkList) {
        this.context = context;
        this.drinkList = drinkList;
    }

    @NonNull
    @Override
    public DrinkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.allmenu_recycler_items, parent, false);

        return new DrinkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DrinkViewHolder holder, final int position) {

        holder.drinkName.setText(drinkList.get(position).getName());
        holder.drinkPrice.setText("Rp. "+drinkList.get(position).getPrice());
        holder.drinkTime.setText(drinkList.get(position).getDeliveryTime());
        holder.drinkRating.setText(drinkList.get(position).getRating());
        holder.drinkCharges.setText(drinkList.get(position).getDeliveryCharges());
        holder.drinkNote.setText(drinkList.get(position).getNote());

        Glide.with(context).load(drinkList.get(position).getImageUrl()).into(holder.drinkImage);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, Details.class);
                i.putExtra("name", drinkList.get(position).getName());
                i.putExtra("price", drinkList.get(position).getPrice());
                i.putExtra("rating", drinkList.get(position).getRating());
                i.putExtra("image", drinkList.get(position).getImageUrl());

                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return drinkList.size();
    }

    public static class DrinkViewHolder extends RecyclerView.ViewHolder{

        TextView drinkName, drinkNote, drinkRating, drinkTime, drinkCharges, drinkPrice;
        ImageView drinkImage;

        public DrinkViewHolder(@NonNull View itemView) {
            super(itemView);

            drinkName = itemView.findViewById(R.id.all_menu_name);
            drinkNote = itemView.findViewById(R.id.all_menu_note);
            drinkCharges = itemView.findViewById(R.id.all_menu_delivery_charge);
            drinkRating = itemView.findViewById(R.id.all_menu_rating);
            drinkTime = itemView.findViewById(R.id.all_menu_deliverytime);
            drinkPrice = itemView.findViewById(R.id.all_menu_price);
            drinkImage = itemView.findViewById(R.id.all_menu_image);
        }
    }

}
