package com.hfad.binusezyfoody;

import androidx.appcompat.app.AlertDialog;import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hfad.binusezyfoody.model.Balance;

public class TopupActivity extends AppCompatActivity {
    int mon=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topup);
    }

    public void addMoney1(View v){
        Balance.setMoney(Balance.getMoney() + 10000);
       Toast.makeText(this, "Balance: " + Balance.getMoney(), Toast.LENGTH_LONG).show();

    }
    public void addMoney2(View v){
        Balance.setMoney(Balance.getMoney() + 25000);
        Toast.makeText(this, "Balance: " + Balance.getMoney(), Toast.LENGTH_LONG).show();
    }
    public void addMoney3(View v){
        Balance.setMoney(Balance.getMoney() + 50000);
        Toast.makeText(this, "Balance: " + Balance.getMoney(), Toast.LENGTH_LONG).show();
    }
    public void addMoney4 (View v){
        Balance.setMoney(Balance.getMoney() + 100000);
        Toast.makeText(this, "Balance: " + Balance.getMoney(), Toast.LENGTH_LONG).show();
    }

    private void display(int number) {
        TextView displayInteger = (TextView) findViewById(
                R.id.textView12);
        displayInteger.setText("" + number);
    }
}


