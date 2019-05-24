package com.rvDevelopers.BloodDonation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class SignUP extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    boolean blood_selected = false;
    String bloodType;
    EditText name, userName, password, cpassword, email, number, age;
    SharedPreferences sign_up, contact_no, contact_email, customer_name, user_blood, user_age;
    SharedPreferences.Editor login_editor, contact_editor, email_editor, name_editor, blood_editor, age_editor;
    Spinner blood_type_selector;
    ArrayAdapter<CharSequence> blood_type;
    @SuppressLint("CommitPrefEdits")
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
        age = findViewById(R.id.textAge);

        blood_type_selector = findViewById(R.id.spinner_blood_type);
        blood_type = ArrayAdapter.createFromResource(this, R.array.blood_type, android.R.layout.simple_spinner_item);
        blood_type.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        blood_type_selector.setAdapter(blood_type);
        blood_type_selector.setOnItemSelectedListener(this);

        sign_up = this.getSharedPreferences("Sign_upData", MODE_PRIVATE);
        login_editor = sign_up.edit();

        contact_no = this.getSharedPreferences("Contact_no", MODE_PRIVATE);
        contact_editor = contact_no.edit();

        contact_email = this.getSharedPreferences("Email_id", MODE_PRIVATE);
        email_editor = contact_email.edit();

        customer_name = this.getSharedPreferences("Name_data", MODE_PRIVATE);
        name_editor = customer_name.edit();

        user_blood = this.getSharedPreferences("blood_preference", MODE_PRIVATE);
        blood_editor = user_blood.edit();

        user_age = this.getSharedPreferences("age_preference", MODE_PRIVATE);
        age_editor = user_age.edit();
    }
    public void SignUp(View view) {

        String pass = password.getText().toString();
        String cpass = cpassword.getText().toString();
        String Name = name.getText().toString();
        String Uname = userName.getText().toString();
        String EMAIL = email.getText().toString();
        String phNO = number.getText().toString();
        int Age = Integer.parseInt(age.getText().toString());

        if(Name.length() == 0 || EMAIL.length() == 0 || phNO.length() == 0 || !blood_selected) {
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

            email_editor.putString(Uname, EMAIL);
            email_editor.commit();

            contact_editor.putString(Uname, phNO);
            contact_editor.commit();

            name_editor.putString(Uname, Name);
            name_editor.commit();

            blood_editor.putString(Uname, bloodType);
            blood_editor.commit();

            age_editor.putInt(Uname, Age);
            age_editor.commit();
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(blood_type_selector != null) {
            bloodType = parent.getItemAtPosition(position).toString();
            blood_selected = true;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
