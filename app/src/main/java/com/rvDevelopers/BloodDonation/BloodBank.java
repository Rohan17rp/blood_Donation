package com.rvDevelopers.BloodDonation;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.view.ViewPager;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Map;

public class BloodBank extends AppCompatActivity implements AvailableBlood.OnFragmentInteractionListener, BloodDonors.OnFragmentInteractionListener, Contact.OnFragmentInteractionListener{

    TabLayout tabLayout;
    SharedPreferences pref;
    SharedPreferences Donor_name, donarList_pref, blood_pref, contact_pref, email_pref, age_pref;
    Intent Display;
    String UserName, Name, bloodGroup, contact, email;
    int age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blood_bank);

        tabLayout = findViewById(R.id.tablayout);
        tabLayout.addTab(tabLayout.newTab().setText("Available BLood"));
        tabLayout.addTab(tabLayout.newTab().setText("Donors").setCustomView((ListView)findViewById(R.id.listview)));
        tabLayout.addTab(tabLayout.newTab().setText("Contact"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager)findViewById(R.id.pager);
        final BloodBank_PagerAdapter adapter = new BloodBank_PagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        Intent category = getIntent();
        UserName = category.getStringExtra("uname");
        Donor_name = this.getSharedPreferences("Name_data", MODE_PRIVATE);
        Name = Donor_name.getString(UserName, "user");

        donarList_pref = this.getSharedPreferences("donation_pref", MODE_PRIVATE);
        blood_pref = this.getSharedPreferences("blood_preference", MODE_PRIVATE);
        contact_pref = this.getSharedPreferences("Contact_no", MODE_PRIVATE);
        email_pref = this.getSharedPreferences("Email_id", MODE_PRIVATE);
        age_pref = this.getSharedPreferences("age_preference", MODE_PRIVATE);

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public String donorName(String UserName) {
        Display = getIntent();
        UserName = Display.getStringExtra("uname");

        pref = this.getSharedPreferences("Sign_upData", MODE_PRIVATE);
        Donor_name = this.getSharedPreferences("Name_data", MODE_PRIVATE);
        String Donor_name = pref.getString(UserName,"null");
        return (Donor_name);
    }

    @Override
    public ArrayList<String> getDonorUserNameList() {
        Map<String, Boolean> donorNames = (Map<String, Boolean>) donarList_pref.getAll();
        ArrayList<String> names = new ArrayList<>();
        int no = 0;
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
        ArrayList<String> name = new ArrayList<>();
        for(i=0;i<username.size();i++) {
            name.add(Donor_name.getString(username.get(i), "null"));
        }
        return name;
    }

    @Override
    public void showData(String username) {
        bloodGroup = blood_pref.getString(username, "");
        contact = contact_pref.getString(username, "");
        email = email_pref.getString(username, "");
        age = age_pref.getInt(username, 0);

        String message = "Blood group: "+bloodGroup+"\nAge:"+age+"\nContact number:"+contact+"\nEmail id:"+email;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder
                .setTitle("Donor info")
                .setMessage(message)
                .setCancelable(true)
                .show();
    }

    @Override
    public void onBackPressed() {
        Intent category = new Intent(this, Profile.class);
        category.putExtra("uname", UserName);
        startActivity(category);
        finish();
    }
}