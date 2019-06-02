package com.rvDevelopers.BloodDonation;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class Profile extends AppCompatActivity {

    TextView welcome;
    int age;
    String Message, Name, Uname, Blood, Email;
    Intent category;
    SharedPreferences age_pref, name_pref, blood_pref, email_pref;
    Button donate;
    TextView name ,uname, blood, email;
    boolean check;
    CheckBox checkBox;

    DrawerLayout dl;
    ActionBarDrawerToggle t;
    NavigationView nv;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        dl = (DrawerLayout) findViewById(R.id.drawerLayout);
        t = new ActionBarDrawerToggle(this, dl, R.string.open, R.string.close);

        dl.addDrawerListener(t);
        t.syncState();

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        nv = (NavigationView) findViewById(R.id.nv);


        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.account:
                        Toast.makeText(Profile.this, "My Account", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.settings:
                        Toast.makeText(Profile.this, "Settings", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.mycart:
                        Toast.makeText(Profile.this, "My Cart", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        return true;
                }


                return true;

            }
        });



        category = getIntent();
        Message = "Welcome ";
        Uname = category.getStringExtra("uname");
        welcome = findViewById(R.id.textView);

        name_pref = this.getSharedPreferences("Name_data", MODE_PRIVATE);
        age_pref = this.getSharedPreferences("age_preference", MODE_PRIVATE);
        blood_pref = this.getSharedPreferences("blood_preference", MODE_PRIVATE);
        email_pref = this.getSharedPreferences("Email_id", MODE_PRIVATE);

        Blood = blood_pref.getString(Uname, "");
        Email = email_pref.getString(Uname, "No Mail ID");
        Name = name_pref.getString(Uname, "user");
        Message += Name;
        welcome.setText(Message);

        age = age_pref.getInt(Uname, 0);
        donate = findViewById(R.id.button);

        name = findViewById(R.id.textNameShow);
        uname = findViewById(R.id.textUserNameShow);
        blood = findViewById(R.id.textBloodGroupShow);
        email = findViewById(R.id.textEmailShow);

        name.setText(Name);
        uname.setText(Uname);
        blood.setText(Blood);
        email.setText(Email);

        checkBox = findViewById(R.id.checkBox);


    }

    public void onCheckboxClicked(View view){
        check = ((CheckBox)view).isChecked();
        if(check){
            Toast
                    .makeText(this,"You are a donor",Toast.LENGTH_LONG)
                    .show();
        }
        else {
            Toast
                    .makeText(this,"You are not a donar",Toast.LENGTH_LONG)
                    .show();
        }
    }

    public void EditProfile(View view) {
        Intent edit = new Intent(this, EditProfile.class);
        edit.putExtra("uname", Uname);
        startActivity(edit);
        finish();
    }

    public void receiver(View view){
        Intent request = new Intent(this, Receiver.class);
        startActivity(request);
        Profile.this.finish();
    }
    /*public void donor(View d){
        if(age >= 18 && age <= 60) {
            Intent Donor = new Intent(this, donor.class);
            startActivity(Donor);
            Profile.this.finish();
        } else {
            Toast
                    .makeText(this, "To donate blood age should be between 18 and 60", Toast.LENGTH_LONG)
                    .show();
        }
    }*/
    @Override
    public void onBackPressed() {
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
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
    public void Test(View view) {
        Intent Test = new Intent(this, BloodBank.class);
        startActivity(Test);
        Profile.this.finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }


}