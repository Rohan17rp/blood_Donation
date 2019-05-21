package com.example.blood_donation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class select_category extends AppCompatActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category);
        }

    public void donor(View d){
        Intent Donor = new Intent(this,donor.class);
        startActivity(Donor);
        select_category.this.finish();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, login.class));
        finish();
    }
}