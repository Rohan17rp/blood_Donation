package com.rvDevelopers.BloodDonation;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class SignUpFrag extends Fragment {


    EditText name, userName, password, cpassword, email, number, age;
    Spinner blood_type_selector;
    Button signup, cancel;

    ArrayAdapter<CharSequence> blood_type;

    private OnFragmentInteractionListener mListener;

    public SignUpFrag() {
    }

    public static SignUpFrag newInstance() {
        SignUpFrag fragment = new SignUpFrag();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.signup_frag, container, false);

        name = v.findViewById(R.id.editText3);
        userName = v.findViewById(R.id.editText);
        password = v.findViewById(R.id.editText2);
        cpassword = v.findViewById(R.id.editText4);
        email = v.findViewById(R.id.editText6);
        number = v.findViewById(R.id.editText7);
        age = v.findViewById(R.id.textAge);
        blood_type_selector = v.findViewById(R.id.spinner_blood_type);
        signup = v.findViewById(R.id.button);
        cancel = v.findViewById(R.id.button2);

        blood_type = new ArrayAdapter<CharSequence>(getContext(), android.R.layout.simple_spinner_dropdown_item);
        blood_type.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        blood_type.add("A+");
        blood_type.add("A-");
        blood_type.add("B+");
        blood_type.add("B-");
        blood_type.add("AB+");
        blood_type.add("AB-");
        blood_type.add("O+");
        blood_type.add("O-");
        blood_type_selector.setAdapter(blood_type);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.SignUp(name, userName, password, cpassword, email, number, age, blood_type_selector);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.pressCancel();
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
        void SignUp(EditText name, EditText userName, EditText password, EditText cpassword, EditText email, EditText number, EditText age, Spinner blood_type_selector);
        void pressCancel();
    }
}
