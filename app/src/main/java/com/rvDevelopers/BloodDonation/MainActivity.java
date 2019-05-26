package com.rvDevelopers.BloodDonation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.view.ViewPager;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements LoginFrag.OnFragmentInteractionListener, SignUpFrag.OnFragmentInteractionListener{

    TabLayout tabLayout;
    SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        pref = this.getSharedPreferences("Sign_upData", MODE_PRIVATE);
        tabLayout = findViewById(R.id.tablayout1);
        tabLayout.addTab(tabLayout.newTab().setText("Login"));
        tabLayout.addTab(tabLayout.newTab().setText("Sign Up"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager1);
        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager() ,tabLayout.getTabCount());
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
    public void Login(EditText username, EditText password) {
        String UserName = username.getText().toString();
        String Password = password.getText().toString();
        if (CheckCredential(UserName, Password)) {
            Intent category = new Intent(this, Profile.class);
            category.putExtra("uname", UserName);
            startActivity(category);
            endActivity();
        } else {
            Toast
                    .makeText(this , "Invalid Credentials", Toast.LENGTH_LONG)
                    .show();
            username.setText("");
            password.setText("");
        }

    }

    @Override
    public void Exit() {
        finish();
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

    public void endActivity() {
        finish();
    }
}
