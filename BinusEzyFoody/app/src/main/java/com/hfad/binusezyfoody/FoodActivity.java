package com.hfad.binusezyfoody;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import com.hfad.binusezyfoody.adapter.AllMenuAdapter;
import com.hfad.binusezyfoody.model.Allmenu;
import com.hfad.binusezyfoody.model.FoodData;
import com.hfad.binusezyfoody.retrofit.ApiInterface;
import com.hfad.binusezyfoody.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FoodActivity extends AppCompatActivity {
    RecyclerView allMenuRecyclerView;
    ApiInterface apiInterface;
    AllMenuAdapter allMenuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        apiInterface = RetrofitClient.getRetrofitInstance().create(ApiInterface.class);

        Call<List<FoodData>> call = apiInterface.getAllData();


        call.enqueue(new Callback<List<FoodData>>() {
            @Override
            public void onResponse(Call<List<FoodData>> call, Response<List<FoodData>> response) {

                List<FoodData> foodDataList = response.body();

                getAllMenu(foodDataList.get(0).getAllmenu());


            }

            @Override
            public void onFailure(Call<List<FoodData>> call, Throwable t) {
                Toast.makeText(FoodActivity.this, "Not responding.", Toast.LENGTH_SHORT).show();
            }

        });
    }



    private void  getAllMenu(List<Allmenu> allmenuList){

        allMenuRecyclerView = findViewById(R.id.all_menu_recycler);
        AllMenuAdapter allMenuAdapter = new AllMenuAdapter(this, allmenuList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        allMenuRecyclerView.setLayoutManager(layoutManager);
        allMenuRecyclerView.setAdapter(allMenuAdapter);
        allMenuAdapter.notifyDataSetChanged();

    }
}