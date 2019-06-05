package com.rvDevelopers.BloodDonation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

    public class Donate_list extends AppCompatActivity {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.donate_list);
        }

        @Override
        public void onBackPressed() {
            startActivity(new Intent(this, Profile.class));
            finish();
        }

}
