package com.hfad.binusezyfoody;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.hfad.binusezyfoody.adapter.SnackAdapter;
import com.hfad.binusezyfoody.model.Snack;
import com.hfad.binusezyfoody.model.FoodData;
import com.hfad.binusezyfoody.retrofit.ApiInterface;
import com.hfad.binusezyfoody.retrofit.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SnackActivity extends AppCompatActivity {

    RecyclerView snackRecyclerView;
    ApiInterface apiInterface;
    SnackAdapter snackAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snack);

        apiInterface = RetrofitClient.getRetrofitInstance().create(ApiInterface.class);

        Call<List<FoodData>> call = apiInterface.getAllData();


        call.enqueue(new Callback<List<FoodData>>() {
            @Override
            public void onResponse(Call<List<FoodData>> call, Response<List<FoodData>> response) {

                List<FoodData> foodDataList = response.body();

                getSnack(foodDataList.get(0).getSnacks());


            }

            @Override
            public void onFailure(Call<List<FoodData>> call, Throwable t) {
                Toast.makeText(SnackActivity.this, "Not responding.", Toast.LENGTH_SHORT).show();
            }

        });
    }



    private void  getSnack(List<Snack> snackList){

        snackRecyclerView = findViewById(R.id.all_menu_recycler);
        SnackAdapter snackAdapter = new SnackAdapter(this, snackList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        snackRecyclerView.setLayoutManager(layoutManager);
        snackRecyclerView.setAdapter(snackAdapter);
        snackAdapter.notifyDataSetChanged();

    }
}