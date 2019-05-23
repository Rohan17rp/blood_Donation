package com.rvDevelopers.BloodDonation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class select_category extends AppCompatActivity {

    TextView welcome;
    String Message;
    Intent category;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category);

        category = getIntent();
        Message = "Welcome ";
        Message += category.getStringExtra("uname");
        welcome = findViewById(R.id.textView);
        welcome.setText(Message);
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