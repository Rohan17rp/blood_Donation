package com.rvDevelopers.BloodDonation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.view.ViewPager;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.jar.Attributes;

public class BloodBank extends AppCompatActivity implements AvailableBlood.OnFragmentInteractionListener, BloodDonors.OnFragmentInteractionListener, Contact.OnFragmentInteractionListener{

    TabLayout tabLayout;
    ListView listView;
    SharedPreferences pref;
    SharedPreferences Donor_name;
    Intent Display;

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
        }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, MainActivity.class));
        finish();
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

}