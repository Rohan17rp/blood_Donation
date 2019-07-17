package com.rvDevelopers.BloodDonation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


public class DonorProfileView extends AppCompatActivity {

        TextView bloodDonor, organDonor, bodyDonor, bloodGroup;
        SharedPreferences BloodGroup;
        String blood;
        Intent category;

        @Override
        public void onCreate(Bundle bundle) {
            super.onCreate(bundle);
            setContentView(R.layout.donor_profile);

            category = getIntent();
            BloodGroup = this.getSharedPreferences("blood_preference",MODE_PRIVATE);
            blood = BloodGroup.toString();


            bloodDonor = findViewById(R.id.textView10);
            organDonor = findViewById(R.id.textView11);
            bodyDonor = findViewById(R.id.textView);
            bloodGroup = findViewById(R.id.textView7);
            bloodDonor.setText("yes");
            organDonor.setText("yes");
            bodyDonor.setText("yes");
            bloodGroup.setText(blood);

        }

        public void Cancel(View view) {
            savedata();
        }
        public void savedata() {
            Intent donorProfile = new Intent(this, Profile.class);
            startActivity(donorProfile);
            finish();
        }
        @Override
        public void onBackPressed() {
            savedata();
        }


}
