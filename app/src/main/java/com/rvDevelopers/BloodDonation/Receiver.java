package com.rvDevelopers.BloodDonation;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class Receiver extends AppCompatActivity  {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receiver);
    }

    @Override
    public void onBackPressed() {

        startActivity(new Intent(this, Profile.class));
        finish();
    }
    public void submitRequest(View view){
        Toast
                .makeText(this,"Feature Still developing", Toast.LENGTH_LONG)
                .show();
    }

    public void pressCancel(View v) {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}