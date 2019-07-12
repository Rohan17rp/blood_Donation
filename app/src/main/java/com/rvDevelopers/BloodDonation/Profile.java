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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

public class Profile extends AppCompatActivity implements ProfileFrag.OnFragmentInteractionListener,
        Help.helpListner, BloodBankFrag.OnFragmentInteractionListener, AvailableBlood.OnFragmentInteractionListener,
        BloodDonors.OnFragmentInteractionListener, ReceiverFrag.OnFragmentInteractionListener,
        About.OnFragmentInteractionListener, OrganDonors.OnFragmentInteractionListener,
        OrganBank.OnFragmentInteractionListener,OrganDonationFrag.OnFragmentInteractionListener,
        RequestOrgans.OnFragmentInteractionListener, PendingRequest.OnFragmentInteractionListener,
        TermsAndConditions.OnFragmentInteractionListener, RequestAnything.OnFragmentInteractionListener,
        RequestBody.OnFragmentInteractionListener, Stocks.OnFragmentInteractionListener,
        BodyDonation.OnFragmentInteractionListener, DonorList.OnFragmentInteractionListener, BecomeADonor.OnFragmentInteractionListener,
        BodyDonorList.OnFragmentInteractionListener {

    TextView welcome;
    int age;
    String Message, Name, Uname, Blood, Email;
    Intent category;
    SharedPreferences age_pref, name_pref, blood_pref, email_pref, donarList_pref, prev_user;
    SharedPreferences.Editor prev_editor;
    Button donate;

    boolean check;

    DrawerLayout dl;
    ActionBarDrawerToggle t;
    NavigationView nv;
    Toolbar toolbar;

    SharedPreferences heartPref, eyePref, kidneyPref, LiverPref, panPref, lungPref, intPref;
    SharedPreferences bodyPref;

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

        nv.setCheckedItem(R.id.profile);

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
                    case R.id.pending_requests:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragment_container, new PendingRequest())
                                .commit();
                        break;
                    case R.id.request:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragment_container, new RequestAnything())
                                .commit();
                        break;
                    case R.id.stock:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragment_container, new Stocks())
                                .commit();
                        break;
                    case R.id.donate_list:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragment_container, new DonorList())
                                .commit();
                        break;
                    case R.id.bedonor:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragment_container, new BecomeADonor())
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
                    case R.id.tnc:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragment_container, new TermsAndConditions())
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
        bodyPref = this.getSharedPreferences("body_donation_pref", MODE_PRIVATE);

        Blood = blood_pref.getString(Uname, "");
        Email = email_pref.getString(Uname, "No Mail ID");
        Name = name_pref.getString(Uname, "user");
        Message += Name;

        check = donarList_pref.getBoolean(Uname, false);
        age = age_pref.getInt(Uname, 0);
        donate = findViewById(R.id.button);

        heartPref = this.getSharedPreferences("heartPref", MODE_PRIVATE);
        eyePref = this.getSharedPreferences("eyePref", MODE_PRIVATE);
        kidneyPref = this.getSharedPreferences("kidneyPref", MODE_PRIVATE);
        LiverPref = this.getSharedPreferences("LiverPref", MODE_PRIVATE);
        panPref = this.getSharedPreferences("pancreasPref", MODE_PRIVATE);
        lungPref = this.getSharedPreferences("lungPref", MODE_PRIVATE);
        intPref = this.getSharedPreferences("instestinePref", MODE_PRIVATE);
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

    @Override
    public void organDonateForm() {
        Intent edit = new Intent(this, OrgansToDonateForm.class);
        edit.putExtra("uname", Uname);
        startActivity(edit);
        finish();
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
    public void saveState(CheckBox blood, CheckBox body) {
        SharedPreferences.Editor editor = donarList_pref.edit();
        editor.putBoolean(Uname, blood.isChecked()).commit();
        SharedPreferences.Editor bodysaver = bodyPref.edit();
        bodysaver.putBoolean(Uname, body.isChecked()).commit();

        new Handler().postDelayed(() -> {
            nv.setCheckedItem(R.id.profile);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new ProfileFrag())
                    .commit();
        }, 500);
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
    public ArrayList<String> getDonorUserNameListB() {
        Map<String, Boolean> donorNames = (Map<String, Boolean>) bodyPref.getAll();
        ArrayList<String> names = new ArrayList<>();
        for(Map.Entry<String, Boolean> stringMap : donorNames.entrySet()) {
            if(stringMap.getValue()) {
                names.add(stringMap.getKey());
            }
        }
        return names;
    }

    @Override
    public ArrayList<String> getNamesB(ArrayList<String> username) {
        int i;
        SharedPreferences Donor_name = this.getSharedPreferences("Name_data", MODE_PRIVATE);
        ArrayList<String> name = new ArrayList<>();
        for(i=0;i<username.size();i++) {
            name.add(Donor_name.getString(username.get(i), "null"));
        }
        return name;
    }

    @Override
    public void submitRequest(EditText address, EditText amount, Spinner organ_selector, AdapterView<?> parent, int position) {

        SharedPreferences prefA = this.getSharedPreferences(getPrefName(parent, position), MODE_PRIVATE);
        SharedPreferences.Editor editor = prefA.edit();
        SharedPreferences pref = this.getSharedPreferences(getAMPrefName(parent, position), MODE_PRIVATE);
        SharedPreferences.Editor am = pref.edit();
        editor.putString(Uname, address.getText().toString()).commit();
        am.putString(Uname, amount.getText().toString()).commit();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                nv.setCheckedItem(R.id.profile);
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new ProfileFrag())
                        .commit();
            }
        }, 1000);
    }

    @Override
    public String getPrefName(AdapterView<?> parent, int position) {
        switch (parent.getItemAtPosition(position).toString()) {
            case "Heart":
                return "heartARPref";
            case "Eyes":
                return "eyeARPref";
            case "Kidney":
                return "kidneyARPref";
            case "Liver":
                return "LiverARPref";
            case "Pancreas":
                return "pancreasARPref";
            case "Lungs":
                return "lungARPref";
            case "Intestine":
                return "instestineARPref";
        }
        return null;
    }

    @Override
    public String getAMPrefName(AdapterView<?> parent, int position) {
        switch (parent.getItemAtPosition(position).toString()) {
            case "Heart":
                return "heartAMPref";
            case "Eyes":
                return "eyeAMPref";
            case "Kidney":
                return "kidneyAMPref";
            case "Liver":
                return "LiverAMPref";
            case "Pancreas":
                return "pancreasAMPref";
            case "Lungs":
                return "lungAMPref";
            case "Intestine":
                return "instestineAMPref";
        }
        return null;
    }

    @Override
    public ArrayList<String> getDonorUserNameListS(int id) {
        SharedPreferences nameAGetter;
        String prefName = "";
        switch (id) {
            case R.id.pendingHeart:
                prefName = "heartAMPref";
                break;
            case R.id.pendingEye:
                prefName = "eyeAMPref";
                break;
            case R.id.pendingKidney:
                prefName = "kidneyAMPref";
                break;
            case R.id.pendingLiver:
                prefName = "LiverAMPref";
                break;
            case R.id.pendingPancreas:
                prefName = "pancreasAMPref";
                break;
            case R.id.pendingLungs:
                prefName = "lungAMPref";
                break;
            case R.id.pendingIntestine:
                prefName = "instestineAMPref";
                break;
            case R.id.pendingBlood:
                prefName = "BloodRequestAmount";
                break;
            default:
                prefName = "";
        }
        nameAGetter = this.getSharedPreferences(prefName, MODE_PRIVATE);

        Map<String, Boolean> names = (Map<String, Boolean>) nameAGetter.getAll();
        ArrayList<String> unames = new ArrayList<String>();
        for(Map.Entry<String, Boolean> str : names.entrySet()) {
            unames.add(str.getKey());
        }

        return unames;
    }

    @Override
    public ArrayList<String> getNamesS(ArrayList<String> username) {
        int i;
        SharedPreferences Donor_name = this.getSharedPreferences("Name_data", MODE_PRIVATE);
        ArrayList<String> name = new ArrayList<>();
        for(i=0;i<username.size();i++) {
            name.add(Donor_name.getString(username.get(i), "null"));
        }
        return name;
    }

    @Override
    public void showDataS(String username, int id) {
        String address = "", amount = "";
        SharedPreferences AD, AM;
        String prefName, am;
        switch (id) {
            case R.id.pendingHeart:
                prefName = "heartAMPref";
                am = "heartARPref";
                break;
            case R.id.pendingEye:
                prefName = "eyeAMPref";
                am = "eyeARPref";
                break;
            case R.id.pendingKidney:
                prefName = "kidneyAMPref";
                am = "kidneyARPref";
                break;
            case R.id.pendingLiver:
                prefName = "LiverAMPref";
                am = "LiverARPref";
                break;
            case R.id.pendingPancreas:
                prefName = "pancreasAMPref";
                am = "pancreasARPref";
                break;
            case R.id.pendingLungs:
                prefName = "lungAMPref";
                am = "lungAMPref";
                break;
            case R.id.pendingIntestine:
                prefName = "instestineAMPref";
                am = "instestineARPref";
                break;
            case R.id.pendingBlood:
                am = "BloodRequestA";
                prefName = "BloodRequestAmount";
                break;
            default:
                prefName = "";
                am = "";
        }
        AD = this.getSharedPreferences(am, MODE_PRIVATE);
        AM = this.getSharedPreferences(prefName, MODE_PRIVATE);
        address = AD.getString(username, "");
        amount = AM.getString(username, "");

        String message = "Address: " + address + "\nAmount: " + amount;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder
                .setTitle("Donate_list info")
                .setMessage(message)
                .setCancelable(true)
                .show();
    }

    @Override
    public void showBloodDataS(String username, int id) {
        SharedPreferences AD,AM,T;
        AD = this.getSharedPreferences("BloodRequestA", MODE_PRIVATE);
        AM = this.getSharedPreferences("BloodRequestAmount", MODE_PRIVATE);
        T = this.getSharedPreferences("Blood2Donate", MODE_PRIVATE);

        String address = "", amount = "", type = "";
        address = AD.getString(username, "");
        amount = AM.getString(username, "");
        type = T.getString(username, "");
        String message = "Address: " + address + "\nAmount: " + amount + " ml\nType: " + type;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder
                .setTitle("Donate_list info")
                .setMessage(message)
                .setCancelable(true)
                .show();
    }

    @Override
    public void saveBloodDonateData(String s) {
        SharedPreferences preferences = this.getSharedPreferences("Blood2Donate", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Uname, s).commit();
    }

    @Override
    public void submitRequest(EditText address, EditText amount) {
        SharedPreferences pref = this.getSharedPreferences("BloodRequestA", MODE_PRIVATE);
        SharedPreferences prefA = this.getSharedPreferences("BloodRequestAmount", MODE_PRIVATE);
        SharedPreferences.Editor editor, editor1;
        editor = pref.edit();
        editor1 = prefA.edit();
        editor.putString(Uname, address.getText().toString()).commit();
        editor1.putString(Uname, amount.getText().toString()).commit();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                nv.setCheckedItem(R.id.profile);
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new ProfileFrag())
                        .commit();
            }
        }, 1000);
    }

    @Override
    public ArrayList<String> getDonorUserNameList1(AdapterView<?> parent, View view, int position, long id) {
        SharedPreferences Donor_name = this.getSharedPreferences(organGetter(parent, view, position, id), MODE_PRIVATE);
        Map<String, Boolean> donorNames = (Map<String, Boolean>) Donor_name.getAll();

        ArrayList<String> names = new ArrayList<>();
        for(Map.Entry<String, Boolean> stringMap : donorNames.entrySet()) {
            if(stringMap.getValue()) {
                names.add(stringMap.getKey());
            }
        }
        return names;
    }

    @Override
    public ArrayList<String> getNames1(ArrayList<String> username) {
        int i;
        SharedPreferences Donor_name = this.getSharedPreferences("Name_data", MODE_PRIVATE);
        ArrayList<String> name = new ArrayList<>();
        for(i=0;i<username.size();i++) {
            name.add(Donor_name.getString(username.get(i), "null"));
        }
        return name;
    }

    @Override
    public String organGetter(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getItemAtPosition(position).toString()) {
            case "Heart":
                return "heartPref";
            case "Eyes":
                return "eyePref";
            case "Kidney":
                return "kidneyPref";
            case "Liver":
                return "LiverPref";
            case "Pancreas":
                return "pancreasPref";
            case "Lungs":
                return "lungPref";
            case "Intestine":
                return "instestinePref";
        }

        return null;
    }
}