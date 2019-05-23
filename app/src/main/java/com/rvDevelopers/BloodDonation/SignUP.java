package com.rvDevelopers.BloodDonation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignUP extends AppCompatActivity {

    EditText name, userName, password, cpassword, email, number;
    SharedPreferences sign_up, contact_no, contact_email;
    SharedPreferences.Editor login_editor, contact_editor, email_editor;
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.sign_up);

        name = findViewById(R.id.editText3);
        userName = findViewById(R.id.editText);
        password = findViewById(R.id.editText2);
        cpassword = findViewById(R.id.editText4);
        email = findViewById(R.id.editText6);
        number = findViewById(R.id.editText7);

        sign_up = this.getSharedPreferences("Sign_upData", MODE_PRIVATE);
        login_editor = sign_up.edit();
        contact_no = this.getSharedPreferences("Contact_no", MODE_PRIVATE);
        contact_editor = contact_no.edit();
        contact_email = this.getSharedPreferences("Email_id", MODE_PRIVATE);
        email_editor = contact_email.edit();

    }
    public void SignUp(View view) {
        String pass = password.getText().toString();
        String cpass = cpassword.getText().toString();
        String Name = name.getText().toString();
        String Uname = userName.getText().toString();
        String EMAIL = email.getText().toString();
        String phNO = number.getText().toString();

        if(Name.length() == 0 || EMAIL.length() == 0 || phNO.length() == 0) {
            Toast
                    .makeText(this, "It is compulsory to enter all data", Toast.LENGTH_SHORT)
                    .show();
        } else if(pass.length() < 8) {
            Toast
                    .makeText(this, "Password should contain atleast 8 characters", Toast.LENGTH_LONG)
                    .show();
        } else if(!(pass.equals(cpass))) {
            Toast
                    .makeText(this, "Password does not match", Toast.LENGTH_LONG)
                    .show();
        } else {
            login_editor.putString(Uname, pass);
            login_editor.commit();

            email_editor.putString(Name, EMAIL);
            email_editor.commit();

            contact_editor.putString(Name, phNO);
            contact_editor.commit();

            Toast
                    .makeText(this, "Sign Up successful", Toast.LENGTH_SHORT)
                    .show();

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    onBackPressed();
                }
            }, 1000);
        }
    }
    public void Cancel(View view) {
        onBackPressed();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
