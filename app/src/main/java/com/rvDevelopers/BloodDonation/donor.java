package com.rvDevelopers.BloodDonation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
public class donor extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donor);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, select_category.class));
        finish();
    }
}



