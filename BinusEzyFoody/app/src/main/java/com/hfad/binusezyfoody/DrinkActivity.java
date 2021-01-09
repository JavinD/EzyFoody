package com.hfad.binusezyfoody;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.hfad.binusezyfoody.adapter.AllMenuAdapter;
import com.hfad.binusezyfoody.adapter.DrinkAdapter;
import com.hfad.binusezyfoody.model.Drink;
import com.hfad.binusezyfoody.model.FoodData;
import com.hfad.binusezyfoody.retrofit.ApiInterface;
import com.hfad.binusezyfoody.retrofit.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DrinkActivity extends AppCompatActivity {

    RecyclerView drinkRecyclerView;
    ApiInterface apiInterface;
    DrinkAdapter drinkAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        apiInterface = RetrofitClient.getRetrofitInstance().create(ApiInterface.class);

        Call<List<FoodData>> call = apiInterface.getAllData();


        call.enqueue(new Callback<List<FoodData>>() {
            @Override
            public void onResponse(Call<List<FoodData>> call, Response<List<FoodData>> response) {

                List<FoodData> foodDataList = response.body();

                getDrink(foodDataList.get(0).getDrinks());


            }

            @Override
            public void onFailure(Call<List<FoodData>> call, Throwable t) {
                Toast.makeText(DrinkActivity.this, "Not responding.", Toast.LENGTH_SHORT).show();
            }

        });
    }



    private void  getDrink(List<Drink> drinkList){

        drinkRecyclerView = findViewById(R.id.all_menu_recycler);
        DrinkAdapter drinkAdapter = new DrinkAdapter(this, drinkList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        drinkRecyclerView.setLayoutManager(layoutManager);
        drinkRecyclerView.setAdapter(drinkAdapter);
        drinkAdapter.notifyDataSetChanged();

    }
}