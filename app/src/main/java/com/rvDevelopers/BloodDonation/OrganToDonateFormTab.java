package com.rvDevelopers.BloodDonation;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

public class OrganToDonateFormTab extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public OrganToDonateFormTab() {

    }

    public static OrganToDonateFormTab newInstance(String param1, String param2) {
        OrganToDonateFormTab fragment = new OrganToDonateFormTab();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    CheckBox heart, eye, kid, liv, pan, lung, inte;
    Button button;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.organs_to_donate, container, false);

        heart = v.findViewById(R.id.checkBox);
        eye = v.findViewById(R.id.checkBox2);
        kid = v.findViewById(R.id.checkBox3);
        liv = v.findViewById(R.id.checkBox4);
        pan = v.findViewById(R.id.checkBox5);
        lung = v.findViewById(R.id.checkBox6);
        inte = v.findViewById(R.id.checkBox8);
        button = v.findViewById(R.id.button6);

        mListener.onFormOpen(heart, eye, kid, liv, pan, lung, inte);
        button.setOnClickListener(view -> {
            mListener.saveOrganform(heart, eye, kid, liv, pan, lung, inte);
        });
        return v;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
        void onFormOpen(CheckBox heart, CheckBox eye, CheckBox kid, CheckBox liv, CheckBox pan, CheckBox lung, CheckBox inte);
        void saveOrganform(CheckBox heart, CheckBox eye, CheckBox kid, CheckBox liv, CheckBox pan, CheckBox lung, CheckBox inte);
    }
}
