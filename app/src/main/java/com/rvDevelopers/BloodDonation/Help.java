package com.rvDevelopers.BloodDonation;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Help extends Fragment {

    String uname;

    helpListner listner;
    public Help() {

    }

    public static Help newInstance() {
        Bundle args = new Bundle();
        Help fragment = new Help();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.help, container,false);

        inflater.inflate(R.layout.help, container,false);
        TextView info = v.findViewById(R.id.help_page);
        info.setText("\t\nFor help Call on Helpline no \t\nHelpline No. : XXXXXXXXXX\t\nOr mail us on \t\nMail Id : XXXXX@XXXXX.XX");
        return v;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof helpListner) {
            listner = (helpListner) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement helpListner");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listner = null;
    }

    public interface helpListner {

    }
    /*@Override
    View onViewCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help);

        Intent Help = getIntent();
        uname = Help.getStringExtra("uname");
        TextView info = findViewById(R.id.help_page);
        info.setText("\t\nFor help Call on Helpline no \t\nHelpline No. : XXXXXXXXXX\t\nOr mail us on \t\nMail Id : XXXXX@XXXXX.XX");
    }
    @Override
    public void onBackPressed() {
        Intent back = new Intent(this, Profile.class);
        back.putExtra("uname", uname);
        startActivity(back);
        finish();
    }*/
}
