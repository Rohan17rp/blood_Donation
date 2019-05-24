package com.rvDevelopers.BloodDonation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }
    public void login(View view) {
        Intent Login = new Intent(this,login.class);
        startActivity(Login);
        MainActivity.this.finish();
    }
    public void signUP(View view) {
        startActivity(new Intent(this, SignUp.class));
        finish();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
