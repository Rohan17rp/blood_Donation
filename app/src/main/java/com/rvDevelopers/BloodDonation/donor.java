package com.rvDevelopers.BloodDonation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class donor extends AppCompatActivity {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);
    }

    public void Test(View view) {
        Intent Test = new Intent(this, BloodBank.class);
        startActivity(Test);
        donor.this.finish();
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, Profile.class));
        finish();
    }
}



