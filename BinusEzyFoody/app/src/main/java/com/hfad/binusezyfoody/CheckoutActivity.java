package com.hfad.binusezyfoody;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hfad.binusezyfoody.adapter.CartAdapter;
import com.hfad.binusezyfoody.model.CartData;

import java.util.ArrayList;
import java.util.List;

public class CheckoutActivity extends AppCompatActivity {
    public List<CartData> cartList = new ArrayList<>();
    RecyclerView CartRecyclerView;
    Button homeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String price = intent.getStringExtra("price");
        String rating = intent.getStringExtra("rating");
        String imageUrl = intent.getStringExtra("imageUrl");
        int qty = intent.getIntExtra("qty", 1);

        CartData c = new CartData(name, imageUrl, rating, price, qty);
        cartList.add(c);

        CartRecyclerView = findViewById(R.id.recycler_cart);
        CartAdapter cartAdapter = new CartAdapter(this, cartList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        CartRecyclerView.setLayoutManager(layoutManager);
        CartRecyclerView.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();

        int prc = Integer.parseInt(price);
        prc = prc*qty;
        calculateTotal(prc);


        homeBtn = findViewById(R.id.btnHome);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cartIntent = new Intent(CheckoutActivity.this, MainActivity.class);
                startActivity(cartIntent);
            }
        });
    }

    public void calculateTotal(int number){
        TextView displayInteger = (TextView) findViewById(
                R.id.tv_total);
        displayInteger.setText("Rp. " + number);


    }
}