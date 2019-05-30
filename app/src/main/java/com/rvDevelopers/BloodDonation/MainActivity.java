package com.rvDevelopers.BloodDonation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements LoginFrag.OnFragmentInteractionListener, SignUpFrag.OnFragmentInteractionListener {

    boolean blood_selected = false;
    String bloodType;

    TabLayout tabLayout;

    SharedPreferences pref;
    SharedPreferences sign_up, contact_no, contact_email, customer_name, user_blood, user_age;
    SharedPreferences.Editor login_editor, contact_editor, email_editor, name_editor, blood_editor, age_editor;

    ArrayAdapter<CharSequence> blood_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        pref = this.getSharedPreferences("Sign_upData", MODE_PRIVATE);

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

        blood_type = ArrayAdapter.createFromResource(getBaseContext(), R.array.blood_type, android.R.layout.simple_spinner_item);
        blood_type.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

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
    public void SignUp(EditText name, EditText userName, EditText password, EditText cpassword, EditText email, EditText number, EditText age, Spinner blood_type_selector) {

        String pass = password.getText().toString();
        String cpass = cpassword.getText().toString();
        String Name = name.getText().toString();
        String Uname = userName.getText().toString();
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
                    pressCancel();
                }
            }, 1000);
        }

    }

    @Override
    public void spinnerClick(AdapterView<?> parent, View view, int position, long id) {
        bloodType = parent.getItemAtPosition(position).toString();
        blood_selected = true;
    }

    @Override
    public void pressCancel() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
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
