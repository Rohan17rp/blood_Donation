package com.rvDevelopers.BloodDonation;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class RequestOrgans extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    EditText address, date, amount;
    Button Request;
    Spinner organ_selector;
    ArrayAdapter<CharSequence> organ_type;

    public RequestOrgans() {
    }

    public static RequestOrgans newInstance(String param1, String param2) {
        RequestOrgans fragment = new RequestOrgans();
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

    String organ;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_request_organs, container, false);

        address = v.findViewById(R.id.editText16);
        amount = v.findViewById(R.id.editText);
        Request = v.findViewById(R.id.button7);
        organ_selector = v.findViewById(R.id.spinner3);
        CharSequence[] groups = { "Heart", "Eyes", "Kidney", "Liver", "Pancreas", "Lungs", "Intestine" };
        organ_type = new ArrayAdapter(this.getActivity(), android.R.layout.simple_spinner_item, groups);
        organ_type.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        organ_selector.setAdapter(organ_type);
        organ_selector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(final AdapterView<?> parent, View view, final int position, long id) {
                organ = parent.getItemAtPosition(position).toString();
                Request.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mListener.submitRequest(address, amount, organ_selector, parent, position);
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
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
        void submitRequest(EditText address, EditText amount, Spinner organ_selector, AdapterView<?> parent, int position);
        String getPrefName(AdapterView<?> parent, int position);
        String getAMPrefName(AdapterView<?> parent, int position);
    }
}
