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


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OrganDonors.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link OrganDonors#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrganDonors extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    String UserName;
    Spinner organ_selector;
    ArrayAdapter organ_type;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public OrganDonors() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OrganDonors.
     */
    // TODO: Rename and change types and number of parameters
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_organ_donors, container, false);
        organ_selector = view.findViewById(R.id.spinner);
        CharSequence[] groups = { "Heart", "Eyes", "Kidney", "Liver", "Pancreas", "Lungs", "Platelets", "Intestine" };
        organ_type = new ArrayAdapter(this.getActivity(), android.R.layout.simple_spinner_item, groups);
        organ_type.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        organ_selector.setAdapter(organ_type);
        final ArrayList<String> donarUserName = mListener.getDonorUserNameList();
        ListView listView = (ListView) view.findViewById(R.id.listview1);
        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, mListener.getNames(donarUserName));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mListener.showData(donarUserName.get(position));
            }
        });
        listView.setAdapter(listViewAdapter);
        return view;

    }

    // TODO: Rename method, update argument and hook method into UI event
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
        // TODO: Update argument type and name
        String donorName (String UserName);
        void onFragmentInteraction(Uri uri);
        ArrayList<String> getDonorUserNameList();
        ArrayList<String> getNames(ArrayList<String> username);
        void showData(String username);
    }


}
