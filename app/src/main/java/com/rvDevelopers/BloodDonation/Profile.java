package com.rvDevelopers.BloodDonation;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Profile extends AppCompatActivity {

    TextView welcome;
    int age;
    String Message, Name, Uname, Blood, Email;
    Intent category;
    SharedPreferences age_pref, name_pref, blood_pref, email_pref, donarList_pref;
    Button donate;
    TextView name ,uname, blood, email, donationCheckBox;
    TextView About,help;
    Intent edit;

    boolean check;

    DrawerLayout dl;
    ActionBarDrawerToggle t;
    NavigationView nv;
    Toolbar toolbar;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        toolbar = findViewById(R.id.toolbar);
        dl = findViewById(R.id.drawerLayout);
        t = new ActionBarDrawerToggle(this, dl, toolbar, R.string.open, R.string.close);
        dl.addDrawerListener(t);
        t.syncState();
//        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        setSupportActionBar(toolbar);
        nv = findViewById(R.id.nv);

        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.edit:
                        EditProfile(findViewById(R.id.editText9));
                        Toast.makeText(Profile.this, "Edit Account", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.hospitals:
                        Toast.makeText(Profile.this, "Hospitals", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.cart:
                        receiver(findViewById(R.id.editText9));
                        Toast.makeText(Profile.this, "Request", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.organ_donate:
                        donate_list(findViewById(R.id.editText9));
                        Toast.makeText(Profile.this, "Organ Donation", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.blood_donate:
                        Test(findViewById(R.id.editText9));
                        Toast.makeText(Profile.this, "Blood Donation", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.help:
                        setContentView(R.layout.help);
                        help = findViewById(R.id.help_page);
                        help .setText("\nFor help Call on Helpline no \nHelpline No. : XXXXXXXXXX\nOr mail us on \nMail Id : XXXXX@XXXXX.XX");
                        Toast.makeText(Profile.this, "Help", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.about:
                        setContentView(R.layout.about);
                        About = findViewById(R.id.about_page);
                        About.setText("It is a app used for blood as well as organ donation\nBy\nRohan Patil\tVed Patil\n");
                        Toast.makeText(Profile.this, "About", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.exit:
                    Toast.makeText(Profile.this, "You have been successfully logged out", Toast.LENGTH_SHORT).show();
                        logout();
                        break;
                    default:
                        return true;
                }
                dl.closeDrawer(GravityCompat.START);
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
        donarList_pref = this.getSharedPreferences("donation_pref", MODE_PRIVATE);

        Blood = blood_pref.getString(Uname, "");
        Email = email_pref.getString(Uname, "No Mail ID");
        Name = name_pref.getString(Uname, "user");
        Message += Name;
        welcome.setText(Message);

        check = donarList_pref.getBoolean(Uname, false);
        age = age_pref.getInt(Uname, 0);
        donate = findViewById(R.id.button);

        name = findViewById(R.id.textNameShow);
        uname = findViewById(R.id.textUserNameShow);
        blood = findViewById(R.id.textBloodGroupShow);
        email = findViewById(R.id.textEmailShow);
        donationCheckBox = findViewById(R.id.textDonarStatus);

        name.setText(Name);
        uname.setText(Uname);
        blood.setText(Blood);
        email.setText(Email);
        if(check) {
            donationCheckBox.setText("Yes");
        } else {
            donationCheckBox.setText("No");
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
    public void donate_list(View view){
        Intent donate = new Intent(this, Donate_list.class);
        startActivity(donate);
        Profile.this.finish();
    }
    /*public void donor(View d){
        if(age >= 18 && age <= 60) {
            Intent Donate_list = new Intent(this, donor.class);
            startActivity(Donate_list);
            Profile.this.finish();
        } else {
            Toast
                    .makeText(this, "To donate blood age should be between 18 and 60", Toast.LENGTH_LONG)
                    .show();
        }
    }*/
    @Override
    public void onBackPressed() {
        if(dl.isDrawerOpen(GravityCompat.START)) {
            dl.closeDrawer(GravityCompat.START);
        } else {
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
    }

    public void logout() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
    public void Test(View view) {
        Intent category = new Intent(this, BloodBank.class);
        category.putExtra("uname", Uname);
        startActivity(category);
        Profile.this.finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
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