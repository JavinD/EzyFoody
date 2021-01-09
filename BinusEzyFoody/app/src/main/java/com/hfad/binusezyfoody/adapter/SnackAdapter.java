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
import com.hfad.binusezyfoody.model.Snack;
import com.hfad.binusezyfoody.R;
import com.hfad.binusezyfoody.Details;

import java.util.List;

public class SnackAdapter extends RecyclerView.Adapter<SnackAdapter.SnackViewHolder> {

    Context context;
    List<Snack> snackList;

    public SnackAdapter(Context context, List<Snack> snackList) {
        this.context = context;
        this.snackList = snackList;
    }

    @NonNull
    @Override
    public SnackViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.allmenu_recycler_items, parent, false);

        return new SnackViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SnackViewHolder holder, final int position) {

        holder.snackName.setText(snackList.get(position).getName());
        holder.snackPrice.setText("Rp. "+snackList.get(position).getPrice());
        holder.snackTime.setText(snackList.get(position).getDeliveryTime());
        holder.snackRating.setText(snackList.get(position).getRating());
        holder.snackCharges.setText(snackList.get(position).getDeliveryCharges());
        holder.snackNote.setText(snackList.get(position).getNote());

        Glide.with(context).load(snackList.get(position).getImageUrl()).into(holder.snackImage);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, Details.class);
                i.putExtra("name", snackList.get(position).getName());
                i.putExtra("price", snackList.get(position).getPrice());
                i.putExtra("rating", snackList.get(position).getRating());
                i.putExtra("image", snackList.get(position).getImageUrl());

                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return snackList.size();
    }

    public static class SnackViewHolder extends RecyclerView.ViewHolder{

        TextView snackName, snackNote, snackRating, snackTime, snackCharges, snackPrice;
        ImageView snackImage;

        public SnackViewHolder(@NonNull View itemView) {
            super(itemView);

            snackName = itemView.findViewById(R.id.all_menu_name);
            snackNote = itemView.findViewById(R.id.all_menu_note);
            snackCharges = itemView.findViewById(R.id.all_menu_delivery_charge);
            snackRating = itemView.findViewById(R.id.all_menu_rating);
            snackTime = itemView.findViewById(R.id.all_menu_deliverytime);
            snackPrice = itemView.findViewById(R.id.all_menu_price);
            snackImage = itemView.findViewById(R.id.all_menu_image);
        }
    }

}
