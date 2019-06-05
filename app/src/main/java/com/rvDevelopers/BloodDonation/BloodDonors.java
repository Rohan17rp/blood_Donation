package com.rvDevelopers.BloodDonation;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class BloodDonors extends Fragment  {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    String UserName;

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public BloodDonors() {

    }

    public static BloodDonors newInstance(String param1, String param2) {
        BloodDonors fragment = new BloodDonors();
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


    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.blood_donors, container, false);

        final ArrayList<String> donarUserName = mListener.getDonorUserNameList();
//        ArrayList<String> donors = new ArrayList<>();
//
//        donors.add(mListener.donorName(UserName));
//
        ListView listView = (ListView) view.findViewById(R.id.listview);
        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, mListener.getNames(donarUserName));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mListener.showData(donarUserName.get(position));
            }
        });
        listView.setAdapter(listViewAdapter);
        // Inflate the layout for this fragment
        return view;
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
        String donorName (String UserName);
        void onFragmentInteraction(Uri uri);
        ArrayList<String> getDonorUserNameList();
        ArrayList<String> getNames(ArrayList<String> username);
        void showData(String username);
    }
}
