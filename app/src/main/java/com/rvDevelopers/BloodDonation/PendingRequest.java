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

public class PendingRequest extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public PendingRequest() {
    }

    public static PendingRequest newInstance(String param1, String param2) {
        PendingRequest fragment = new PendingRequest();
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
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pending_request, container, false);

        final ArrayList<String> donarUserNameHeart = mListener.getDonorUserNameListS(R.id.pendingHeart);

        ListView listViewHeart = v.findViewById(R.id.pendingHeart);
        ArrayAdapter<String> heartAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, mListener.getNamesS(donarUserNameHeart));
        listViewHeart.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mListener.showDataS(donarUserNameHeart.get(position), R.id.pendingHeart);
            }
        });
        listViewHeart.setAdapter(heartAdapter);

        final ArrayList<String> donarUserNameEye = mListener.getDonorUserNameListS(R.id.pendingEye);

        ListView listViewEye = v.findViewById(R.id.pendingEye);
        ArrayAdapter<String> heartEye = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, mListener.getNamesS(donarUserNameEye));
        listViewEye.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mListener.showDataS(donarUserNameEye.get(position), R.id.pendingEye);
            }
        });
        listViewEye.setAdapter(heartEye);


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
        ArrayList<String> getDonorUserNameListS(int id);
        ArrayList<String> getNamesS(ArrayList<String> username);
        void showDataS(String username, int id);
    }
}
