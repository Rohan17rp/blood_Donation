package com.rvDevelopers.BloodDonation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class OrgansToDonate extends AppCompatActivity {

    String Uname;

    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.organs_to_donate);
    }

    public void savedata() {
        Intent category = new Intent(this, Profile.class);
        category.putExtra("uname", Uname);
        startActivity(category);
        finish();
    }
    @Override
    public void onBackPressed() {
        savedata();
    }

}
