package com.rvDevelopers.BloodDonation;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;

import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
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

import java.util.ArrayList;
import java.util.Map;

public class Profile extends AppCompatActivity implements ProfileFrag.OnFragmentInteractionListener,
        Help.helpListner, BloodBankFrag.OnFragmentInteractionListener, AvailableBlood.OnFragmentInteractionListener,
        BloodDonors.OnFragmentInteractionListener, ReceiverFrag.OnFragmentInteractionListener,
        About.OnFragmentInteractionListener, OrganDonors.OnFragmentInteractionListener,
        OrganBank.OnFragmentInteractionListener,OrganDonationFrag.OnFragmentInteractionListener, RequestOrgans.OnFragmentInteractionListener {

    TextView welcome;
    int age;
    String Message, Name, Uname, Blood, Email;
    Intent category;
    SharedPreferences age_pref, name_pref, blood_pref, email_pref, donarList_pref, prev_user;
    SharedPreferences.Editor prev_editor;
    Button donate;
    TextView About;

    boolean check;

    DrawerLayout dl;
    ActionBarDrawerToggle t;
    NavigationView nv;
    Toolbar toolbar;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        prev_user = this.getSharedPreferences("SignedInPref", MODE_PRIVATE);
        prev_editor = prev_user.edit();

        toolbar = findViewById(R.id.toolbar);
        dl = findViewById(R.id.drawerLayout);
        t = new ActionBarDrawerToggle(this, dl, toolbar, R.string.open, R.string.close);
        dl.addDrawerListener(t);
        t.syncState();
        setSupportActionBar(toolbar);
        nv = findViewById(R.id.nv);

        if(savedInstanceState == null) {
            nv.setCheckedItem(R.id.profile);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new ProfileFrag())
                    .commit();
        }

        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.profile:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragment_container, new ProfileFrag())
                                .commit();
                        break;
                    case R.id.hospitals:
                        Toast.makeText(Profile.this, "Hospitals", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.cart:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragment_container, new ReceiverFrag())
                                .commit();
                        break;
                    case R.id.request_organs:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragment_container, new RequestOrgans())
                                .commit();
                        break;
                    case R.id.organ_donate:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragment_container, new OrganDonationFrag())
                                .commit();

                        break;
                    case R.id.blood_donate:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragment_container, new BloodBankFrag())
                                .commit();
                        break;
                    case R.id.help:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragment_container, new Help())
                                .commit();
                        break;
                    case R.id.about:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragment_container, new About())
                                .commit();
                        break;
                    case R.id.logout:
                        logout();
                        break;
                    case R.id.exit:
                        exit();
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

        check = donarList_pref.getBoolean(Uname, false);
        age = age_pref.getInt(Uname, 0);
        donate = findViewById(R.id.button);
    }

    public void logout() {
        new AlertDialog.Builder(this)
                .setTitle("Warning")
                .setMessage("Are you sure you want to logout?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Back();
                    }
                })
                .setNegativeButton("No", null)
                .setCancelable(true)
                .show();
    }

    public void Back() {
        prev_editor.putBoolean("is", false);
        prev_editor.commit();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void EditProfile() {
        Intent edit = new Intent(this, EditProfile.class);
        edit.putExtra("uname", Uname);
        startActivity(edit);
        finish();
    }

    public void donate_list(View view){
        Intent donate = new Intent(this, Donate_list.class);
        startActivity(donate);
        Profile.this.finish();
    }

    @Override
    public void onBackPressed() {
        if(dl.isDrawerOpen(GravityCompat.START)) {
            dl.closeDrawer(GravityCompat.START);
        } else {
            logout();
        }
    }

    public void exit() {
        new AlertDialog.Builder(this)
                .setMessage("Warning")
                .setMessage("Do you want to exit")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("No", null)
                .setCancelable(true)
                .show();
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

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public BloodBank_PagerAdapter getPagerAdapter(TabLayout tabLayout) {
        return new BloodBank_PagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
    }

    public OrganDonation_PagerAdapter getPagerAdapter1(TabLayout tabLayout) {
        return new OrganDonation_PagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
    }

    @Override
    public void showData(TextView name ,TextView uname, TextView blood, TextView email, TextView donationCheckBox, TextView welcome) {
        name.setText(Name);
        uname.setText(Uname);
        blood.setText(Blood);
        email.setText(Email);
        if(check) {
            donationCheckBox.setText("Yes");
        } else {
            donationCheckBox.setText("No");
        }

        welcome.setText(Message);
    }

    public void savedata() {
        Intent category = new Intent(this, Profile.class);
        category.putExtra("uname", Uname);
        startActivity(category);
        finish();
    }

    @Override
    public String donorName(String UserName) {
        SharedPreferences pref = this.getSharedPreferences("Name_data", MODE_PRIVATE);
        String Donor_name = pref.getString(UserName,"null");
        return (Donor_name);
    }

    @Override
    public ArrayList<String> getDonorUserNameList() {
        Map<String, Boolean> donorNames = (Map<String, Boolean>) donarList_pref.getAll();
        ArrayList<String> names = new ArrayList<>();
        for(Map.Entry<String, Boolean> stringMap : donorNames.entrySet()) {
            if(stringMap.getValue()) {
                names.add(stringMap.getKey());
            }
        }
        return names;
    }

    @Override
    public ArrayList<String> getNames(ArrayList<String> username) {
        int i;
        SharedPreferences Donor_name = this.getSharedPreferences("Name_data", MODE_PRIVATE);
        ArrayList<String> name = new ArrayList<>();
        for(i=0;i<username.size();i++) {
            name.add(Donor_name.getString(username.get(i), "null"));
        }
        return name;
    }

    @Override
    public void showData(String username) {
        String bloodGroup = blood_pref.getString(username, "");
        SharedPreferences contact_pref = this.getSharedPreferences("Contact_no", MODE_PRIVATE);
        String contact = contact_pref.getString(username, "");
        String email = email_pref.getString(username, "");
        age = age_pref.getInt(username, 0);

        String message = "Blood group: "+bloodGroup+"\nAge:"+age+"\nContact number:"+contact+"\nEmail id:"+email;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder
                .setTitle("Donate_list info")
                .setMessage(message)
                .setCancelable(true)
                .show();
    }

    @Override
    public void submitRequest() {
        Toast
                .makeText(this, "Feature Under Development", Toast.LENGTH_SHORT)
                .show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new ProfileFrag())
                        .commit();
            }
        }, 1000);
    }
}