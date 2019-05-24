package com.rvDevelopers.BloodDonation;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Profile extends AppCompatActivity {

    TextView welcome;
    int age;
    String Message, Name, Uname;
    Intent category;
    SharedPreferences age_pref, name_pref;
    Button donate;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        category = getIntent();
        Message = "Welcome ";
        Uname = category.getStringExtra("uname");
        welcome = findViewById(R.id.textView);

        name_pref = this.getSharedPreferences("Name_data", MODE_PRIVATE);
        age_pref = this.getSharedPreferences("age_preference", MODE_PRIVATE);

        Name = name_pref.getString(Uname, "user");
        Message += Name;
        welcome.setText(Message);

        age= age_pref.getInt(Uname, 0);
        donate = findViewById(R.id.button);
    }

    public void donor(View d){
        if(age >= 18 && age <= 60) {
            Intent Donor = new Intent(this,donor.class);
            startActivity(Donor);
            Profile.this.finish();
        } else {
            Toast
                    .makeText(this, "To donate blood age should be between 18 and 60", Toast.LENGTH_LONG)
                    .show();
        }
    }
    public void reciever(View view) {

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to logout")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        logout();
                    }
                })
                .setNegativeButton("No", null)
                .setCancelable(true)
                .show();
    }
    public void logout() {
        startActivity(new Intent(this, login.class));


    }
    public void Test(View view) {
        Intent Test = new Intent(this, BloodBank.class);
        startActivity(Test);
        Profile.this.finish();
    }
}