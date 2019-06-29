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
import android.widget.Spinner;

import java.util.ArrayList;

public class OrganDonors extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    String UserName;
    Spinner organ_selector;
    ArrayAdapter organ_type;

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public OrganDonors() {

    }

    public static OrganDonors newInstance(String param1, String param2) {
        OrganDonors fragment = new OrganDonors();
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

    ArrayAdapter<String> listViewAdapter;
    ArrayList<String> donarUserName;
    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_organ_donors, container, false);
        organ_selector = view.findViewById(R.id.spinner);
        CharSequence[] groups = { "Heart", "Eyes", "Kidney", "Liver", "Pancreas", "Lungs", "Intestine" };
        organ_type = new ArrayAdapter(this.getActivity(), android.R.layout.simple_spinner_item, groups);
        organ_type.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        organ_selector.setAdapter(organ_type);

        listView = (ListView) view.findViewById(R.id.listview1);

        organ_selector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                donarUserName = mListener.getDonorUserNameList1(parent, view, position, id);

                listViewAdapter = new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_list_item_1,
                        mListener.getNames1(donarUserName));

                listView.setAdapter(listViewAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mListener.showData(donarUserName.get(position));
            }
        });

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
        ArrayList<String> getDonorUserNameList1(AdapterView<?> parent, View view, int position, long id);
        ArrayList<String> getNames1(ArrayList<String> username);
        void showData(String username);
        String organGetter(AdapterView<?> parent, View view, int position, long id);
    }


}
