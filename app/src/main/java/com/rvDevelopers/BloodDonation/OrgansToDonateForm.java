package com.rvDevelopers.BloodDonation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;

public class OrgansToDonateForm extends AppCompatActivity {

    Intent edit;
    String UserName;
    SharedPreferences heartPref, eyePref, kidneyPref, LiverPref, panPref, lungPref, intPref, platePref;
    SharedPreferences.Editor heartEdit, eueEdit, kidEdit, livEdit, panEdit, lunEdit, intEdit, plateEdit;
    CheckBox heart, eye, kid, liv, pan, lung, plate, inte;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.organs_to_donate);

        edit = getIntent();
        UserName = edit.getStringExtra("uname");

        heartPref = this.getSharedPreferences("heartPref", MODE_PRIVATE);
        eyePref = this.getSharedPreferences("eyePref", MODE_PRIVATE);
        kidneyPref = this.getSharedPreferences("kidneyPref", MODE_PRIVATE);
        LiverPref = this.getSharedPreferences("LiverPref", MODE_PRIVATE);
        panPref = this.getSharedPreferences("pancreasPref", MODE_PRIVATE);
        lungPref = this.getSharedPreferences("lungPref", MODE_PRIVATE);
        platePref = this.getSharedPreferences("platePref",MODE_PRIVATE);
        intPref = this.getSharedPreferences("instestinePref", MODE_PRIVATE);

        heartEdit = heartPref.edit();
        eueEdit = eyePref.edit();
        kidEdit = kidneyPref.edit();
        livEdit = LiverPref.edit();
        panEdit = panPref.edit();
        lunEdit = lungPref.edit();
        plateEdit = platePref.edit();
        intEdit = intPref.edit();

        heart = findViewById(R.id.checkBox);
        eye = findViewById(R.id.checkBox2);
        kid = findViewById(R.id.checkBox3);
        liv = findViewById(R.id.checkBox4);
        pan = findViewById(R.id.checkBox5);
        lung = findViewById(R.id.checkBox6);
        plate = findViewById(R.id.checkBox7);
        inte = findViewById(R.id.checkBox8);

        heart.setChecked(heartPref.getBoolean(UserName, false));
        eye.setChecked(eyePref.getBoolean(UserName, false));
        kid.setChecked(kidneyPref.getBoolean(UserName, false));
        liv.setChecked(LiverPref.getBoolean(UserName, false));
        pan.setChecked(panPref.getBoolean(UserName, false));
        lung.setChecked(lungPref.getBoolean(UserName, false));
        plate.setChecked(platePref.getBoolean(UserName,false));
        inte.setChecked(intPref.getBoolean(UserName, false));
    }

    public void Save(View view) {
        heartEdit.putBoolean(UserName, heart.isChecked()).commit();
        eueEdit.putBoolean(UserName, eye.isChecked()).commit();
        kidEdit.putBoolean(UserName, kid.isChecked()).commit();
        livEdit.putBoolean(UserName, liv.isChecked()).commit();
        panEdit.putBoolean(UserName, pan.isChecked()).commit();
        lunEdit.putBoolean(UserName, lung.isChecked()).commit();
        plateEdit.putBoolean(UserName,plate.isChecked()).commit();
        intEdit.putBoolean(UserName, inte.isChecked()).commit();
        onBackPressed();
    }

    public void Cancel(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        Intent edit = new Intent(this, Profile.class);
        edit.putExtra("uname", UserName);
        startActivity(edit);
        finish();
    }
}
