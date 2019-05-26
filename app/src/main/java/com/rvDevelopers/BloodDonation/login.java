package com.rvDevelopers.BloodDonation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class login extends AppCompatActivity {

    Button button;
    EditText username;
    EditText password;
    SharedPreferences pref;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        button = findViewById(R.id.button);
        username = findViewById(R.id.editText);
        password = findViewById(R.id.editText2);

        pref = this.getSharedPreferences("Sign_upData", MODE_PRIVATE);
    }

    public void check(View v){
        String UserName = username.getText().toString();
        String Password = password.getText().toString();
        if (CheckCredential(UserName, Password)) {
            Intent category = new Intent(this, Profile.class);
            category.putExtra("uname", UserName);
            startActivity(category);
            login.this.finish();
        } else {
            Toast
                    .makeText(login.this, "Invalid Credentials", Toast.LENGTH_LONG)
                    .show();
            username.setText("");
            password.setText("");
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    public boolean CheckCredential(String UserName, String Password) {
        String pass = pref.getString(UserName, "null");
        return (Password.equals(pass));
    }


    public void signUP(View view) {
        startActivity(new Intent(this, SignUp.class));
        finish();
    }

}
