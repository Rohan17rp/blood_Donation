package com.example.blood_donation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import android.view.ViewConfiguration;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }

    Button button = (Button) findViewById(R.id.button);
    EditText username = (EditText) findViewById(R.id.editText);
    EditText password = (EditText) findViewById(R.id.editText2);

        public void check(View v){
        if (username.getText().toString().equals("admin") && password.getText().toString().equals("admin")) {
            Intent category = new Intent(this, select_category.class);
            startActivity(category);
            login.this.finish();
        } else {
            Toast
                    .makeText(login.this, "Invalid Credentials", Toast.LENGTH_LONG)
                    .show();
        }
    }
    }
