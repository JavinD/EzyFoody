package com.hfad.binusezyfoody;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.hfad.binusezyfoody.adapter.AllMenuAdapter;
import com.hfad.binusezyfoody.adapter.PopularAdapter;
import com.hfad.binusezyfoody.model.CartData;
import com.hfad.binusezyfoody.model.FoodData;
import com.hfad.binusezyfoody.retrofit.ApiInterface;
import com.hfad.binusezyfoody.retrofit.RetrofitClient;
import com.hfad.binusezyfoody.model.Allmenu;
import com.hfad.binusezyfoody.model.Popular;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    ApiInterface apiInterface;
    RecyclerView popularRecyclerView;
    PopularAdapter popularAdapter;
    ImageView foodClick, drinkClick, snackClick, topupClick, cartClick, mapsClick;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        foodClick = findViewById(R.id.food);
        foodClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent foodIntent = new Intent(MainActivity.this, FoodActivity.class);
                startActivity(foodIntent);
            }
        });

        drinkClick = findViewById(R.id.drinks);
        drinkClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent drinkIntent = new Intent(MainActivity.this, DrinkActivity.class);
                startActivity(drinkIntent);
            }
        });

        snackClick = findViewById(R.id.snacks);
        snackClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent snackIntent = new Intent(MainActivity.this, SnackActivity.class);
                startActivity(snackIntent);
            }
        });

        topupClick = findViewById(R.id.topup);
        topupClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent topupIntent = new Intent(MainActivity.this, TopupActivity.class);
                startActivity(topupIntent);
            }
        });

        mapsClick = findViewById(R.id.maps);
        mapsClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapIntent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(mapIntent);
            }
        });

        cartClick = findViewById(R.id.imageView);
        cartClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addData();

            }
        });

        apiInterface = RetrofitClient.getRetrofitInstance().create(ApiInterface.class);

        Call<List<FoodData>> call = apiInterface.getAllData();


        call.enqueue(new Callback<List<FoodData>>() {
            @Override
            public void onResponse(Call<List<FoodData>> call, Response<List<FoodData>> response) {

                List<FoodData> foodDataList = response.body();

                getPopularData(foodDataList.get(0).getPopular());
            }

            @Override
            public void onFailure(Call<List<FoodData>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Not responding.", Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void getPopularData(List<Popular> popularList) {

        popularRecyclerView = findViewById(R.id.popular_recycler);
        popularAdapter = new PopularAdapter(this, popularList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        popularRecyclerView.setLayoutManager(layoutManager);
        popularRecyclerView.setAdapter(popularAdapter);

    }

    private void addData(){
        Intent cartIntent = new Intent(MainActivity.this, CartActivity.class);
        cartIntent.putExtra("name", "Nasi Uduk");
        cartIntent.putExtra("imageUrl", "https://dtlscuh0h90jk.cloudfront.net/item/photos/IDGFSTI00002ycp/1541575842468306825_9dfbf43fe95a4dacaccab888acabd94a.jpg");
        cartIntent.putExtra("rating", "5.0");
        cartIntent.putExtra("price", "15000");
        cartIntent.putExtra("qty", 3);
        startActivity(cartIntent);
    }


}