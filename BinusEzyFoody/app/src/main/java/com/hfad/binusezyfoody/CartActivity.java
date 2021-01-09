package com.hfad.binusezyfoody;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hfad.binusezyfoody.adapter.AllMenuAdapter;
import com.hfad.binusezyfoody.adapter.CartAdapter;
import com.hfad.binusezyfoody.model.Allmenu;
import com.hfad.binusezyfoody.model.Balance;
import com.hfad.binusezyfoody.model.CartData;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class CartActivity extends AppCompatActivity {


    public static int total=0;
    public List<CartData> cartList = new ArrayList<>();
    RecyclerView CartRecyclerView;
    Button placeOrder;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String price = intent.getStringExtra("price");
        String rating = intent.getStringExtra("rating");
        String imageUrl = intent.getStringExtra("imageUrl");
        int qty = intent.getIntExtra("qty", 1);

        //Set back button to activity

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
        placeOrderRequest(prc, name, imageUrl, rating, price, qty);

    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }



    public void calculateTotal(int number){
        TextView displayInteger = (TextView) findViewById(
                R.id.tv_total);
        displayInteger.setText("Rp. " + number);

    }





    private void placeOrderRequest(int payment, String name, String imageUrl, String rating, String price, int qty){
        placeOrder = findViewById(R.id.btn_placeorder);
        final int x = payment;
        final String name1 = name;
        final String imageUrl1 = imageUrl;
        final String rating1 = rating;
        final String price1 = price;
        final int qty1 = qty;



            placeOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (x > Balance.getMoney()) {
                        AlertDialog alertDialog = new AlertDialog.Builder(CartActivity.this).create();
                        alertDialog.setTitle("Alert");
                        alertDialog.setMessage("Add your Balance!");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });

                        alertDialog.show();
                        Intent in = new Intent(CartActivity.this, TopupActivity.class);
                        startActivity(in);
                    }
                        else{
                        Balance.setMoney(Balance.getMoney()-x);
                        Intent cartIntent= new Intent(CartActivity.this, CheckoutActivity.class);
                        cartIntent.putExtra("name", name1);
                        cartIntent.putExtra("imageUrl", imageUrl1);
                        cartIntent.putExtra("rating", rating1);
                        cartIntent.putExtra("price", price1);
                        cartIntent.putExtra("qty", qty1);
                        startActivity(cartIntent);
                        }

                }

        });
    }}



