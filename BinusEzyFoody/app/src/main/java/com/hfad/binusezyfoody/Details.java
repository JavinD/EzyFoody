package com.hfad.binusezyfoody;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.view.View;

import com.bumptech.glide.Glide;
import com.hfad.binusezyfoody.model.Allmenu;
import com.hfad.binusezyfoody.model.CartData;


import java.util.Vector;

public class Details extends AppCompatActivity {

    ImageView imageView;
    TextView itemName, itemPrice, itemRating, itemDesc;
    RatingBar ratingBar;
    Button addCart;
    Vector<CartData> CartList = new Vector<CartData>();


    String name, price, rating, imageUrl, note;
    int minteger = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);



        Intent intent = getIntent();

        name = intent.getStringExtra("name");
        price = intent.getStringExtra("price");
        rating = intent.getStringExtra("rating");
        imageUrl = intent.getStringExtra("image");
        note = intent.getStringExtra( "note");

        imageView = findViewById(R.id.imageView5);
        itemName = findViewById(R.id.name);
        itemPrice = findViewById(R.id.price);
        itemRating = findViewById(R.id.rating);
        ratingBar = findViewById(R.id.ratingBar);
        itemDesc = findViewById(R.id.textView8);

        Glide.with(getApplicationContext()).load(imageUrl).into(imageView);
        itemName.setText(name);
        itemPrice.setText("Rp. "+price);
        itemRating.setText(rating);
        ratingBar.setRating(Float.parseFloat(rating));
        itemDesc.setText(note);

        CartList.add(new CartData(name, imageUrl, rating, price, minteger));

        addCart = findViewById(R.id.buttonCart);
        addCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cartIntent = new Intent(Details.this, CartActivity.class);
                cartIntent.putExtra("name", name);
                cartIntent.putExtra("imageUrl", imageUrl);
                cartIntent.putExtra("rating", rating);
                cartIntent.putExtra("price", price);
                cartIntent.putExtra("qty", minteger);
                startActivity(cartIntent);
            }
        });

    }

    public void increaseInteger(View view) {
        minteger = minteger + 1;
        display(minteger);

    }public void decreaseInteger(View view) {
        if (minteger<1){}

        else{
            minteger = minteger - 1;
            display(minteger);
        }
    }

    private void display(int number) {
        TextView displayInteger = (TextView) findViewById(
                R.id.textView5);
        displayInteger.setText("" + number);
    }
}