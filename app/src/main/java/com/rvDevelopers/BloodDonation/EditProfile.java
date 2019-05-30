package com.rvDevelopers.BloodDonation;

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

public class EditProfile extends AppCompatActivity {

    EditText password, email, number, age, cpassword;
    Spinner blood_type_selector;

    SharedPreferences sign_up, contact_no, contact_email, user_blood, user_age;
    SharedPreferences.Editor login_editor, contact_editor, email_editor, blood_editor, age_editor;

    Intent category;
    String Name, Uname, Blood;
    boolean blood_selected = false;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.edit_profile);
        category = getIntent();
        Uname = category.getStringExtra("uname");

        email = findViewById(R.id.editText11);
        number = findViewById(R.id.editText12);
        age = findViewById(R.id.editText13);
        password = findViewById(R.id.editText5);
        cpassword = findViewById(R.id.editText14);
        blood_type_selector = findViewById(R.id.spinner);

        CharSequence[] groups = { "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-" };
        ArrayAdapter<CharSequence> blood_type = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_spinner_item, groups);
//        ArrayAdapter<CharSequence> blood_type = ArrayAdapter.createFromResource(this, R.array.blood_type, android.R.layout.simple_spinner_item);
        blood_type.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        blood_type_selector.setAdapter(blood_type);
        blood_type_selector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Blood = parent.getItemAtPosition(position).toString();
                blood_selected = true;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sign_up = this.getSharedPreferences("Sign_upData", MODE_PRIVATE);
        login_editor = sign_up.edit();

        contact_no = this.getSharedPreferences("Contact_no", MODE_PRIVATE);
        contact_editor = contact_no.edit();

        contact_email = this.getSharedPreferences("Email_id", MODE_PRIVATE);
        email_editor = contact_email.edit();

        user_blood = this.getSharedPreferences("blood_preference", MODE_PRIVATE);
        blood_editor = user_blood.edit();

        user_age = this.getSharedPreferences("age_preference", MODE_PRIVATE);
        age_editor = user_age.edit();
    }

    public void Save(View view) {
        String pass = password.getText().toString();
        String cpass = cpassword.getText().toString();
        String EMAIL = email.getText().toString();
        String phNO = number.getText().toString();

        int Age = 0;
        if(age.getText().toString().length() != 0) {
            Age = Integer.parseInt(age.getText().toString());
        }

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

            blood_editor.putString(Uname, Blood);
            blood_editor.commit();

            age_editor.putInt(Uname, Age);
            age_editor.commit();

            Toast
                    .makeText(this, "Save successful", Toast.LENGTH_LONG)
                    .show();

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    savedata();
                }
            }, 1000);
        }
    }
    public void Cancel(View view) {
        savedata();
    }
    public void savedata() {
        Intent category = new Intent(this, Profile.class);
        category.putExtra("uname", Uname);
        startActivity(category);
        finish();
    }
}
