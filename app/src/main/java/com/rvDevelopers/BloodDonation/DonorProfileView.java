package com.rvDevelopers.BloodDonation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class DonorProfileView extends AppCompatActivity {

    TextView bloodDonor, organDonor, bodyDonor, bloodGroup;
    SharedPreferences BloodGroup, bodyPref, donarList_pref;
    String blood, Uname;
    Intent category;
    SharedPreferences heartPref, eyePref, kidneyPref, LiverPref, panPref, lungPref, intPref;

    @Override
        public void onCreate(Bundle bundle) {
            super.onCreate(bundle);
            setContentView(R.layout.donor_profile);

            category = getIntent();
            Uname = category.getStringExtra("uname");
            bodyPref = this.getSharedPreferences("body_donation_pref", MODE_PRIVATE);
            BloodGroup = this.getSharedPreferences("blood_preference",MODE_PRIVATE);
            donarList_pref = this.getSharedPreferences("donation_pref", MODE_PRIVATE);

            heartPref = this.getSharedPreferences("heartPref", MODE_PRIVATE);
            eyePref = this.getSharedPreferences("eyePref", MODE_PRIVATE);
            kidneyPref = this.getSharedPreferences("kidneyPref", MODE_PRIVATE);
            LiverPref = this.getSharedPreferences("LiverPref", MODE_PRIVATE);
            panPref = this.getSharedPreferences("pancreasPref", MODE_PRIVATE);
            lungPref = this.getSharedPreferences("lungPref", MODE_PRIVATE);
            intPref = this.getSharedPreferences("instestinePref", MODE_PRIVATE);
//            blood = BloodGroup.toString();

            bloodDonor = findViewById(R.id.textView10);
            organDonor = findViewById(R.id.textView11);
            bodyDonor = findViewById(R.id.textView);
            bloodGroup = findViewById(R.id.textView7);

            if(donarList_pref.getBoolean(Uname, false)) bloodDonor.setText("Yes");
            else bloodDonor.setText("No");

            if(heartPref.getBoolean(Uname,false) || eyePref.getBoolean(Uname,false) || kidneyPref.getBoolean(Uname,false) || LiverPref.getBoolean(Uname,false) ||
                    panPref.getBoolean(Uname,false) || lungPref.getBoolean(Uname,false) || intPref.getBoolean(Uname,false)) organDonor.setText("Yes");
            else organDonor.setText("No");

            if(bodyPref.getBoolean(Uname, false)) bodyDonor.setText("Yes");
            else bodyDonor.setText("No");
            bloodGroup.setText(BloodGroup.getString(Uname, ""));
        }

        public void Cancel(View view) {
            savedata();
        }
        public void savedata() {
            Intent donorProfile = new Intent(this, Profile.class);
            donorProfile.putExtra("uname", Uname);
            startActivity(donorProfile);
            finish();
        }
        @Override
        public void onBackPressed() {
            savedata();
        }
}
