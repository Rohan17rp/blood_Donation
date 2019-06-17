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
import android.widget.TextView;

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

    TextView textView;

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
        if(donarUserNameHeart.size() == 0) {
            TextView textView = v.findViewById(R.id.h);
            textView.setVisibility(View.GONE);
        }
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
        if(donarUserNameEye.size() == 0) {
            TextView textView = v.findViewById(R.id.eye);
            textView.setVisibility(View.GONE);
        }
        listViewEye.setAdapter(heartEye);

        final ArrayList<String> donarUserNameKidney = mListener.getDonorUserNameListS(R.id.pendingKidney);
        ListView listViewKidney = v.findViewById(R.id.pendingKidney);
        ArrayAdapter<String> kidneyAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, mListener.getNamesS(donarUserNameKidney));
        listViewKidney.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mListener.showDataS(donarUserNameKidney.get(position), R.id.pendingKidney);
            }
        });
        if(donarUserNameKidney.size() == 0) {
            TextView textView = v.findViewById(R.id.kid);
            textView.setVisibility(View.GONE);
        }
        listViewKidney.setAdapter(kidneyAdapter);

        final ArrayList<String> donarUserNameLiver = mListener.getDonorUserNameListS(R.id.pendingLiver);
        ListView listViewLiver = v.findViewById(R.id.pendingLiver);
        ArrayAdapter<String> LiverAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, mListener.getNamesS(donarUserNameLiver));
        listViewLiver.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mListener.showDataS(donarUserNameLiver.get(position), R.id.pendingLiver);
            }
        });
        if(donarUserNameLiver.size() == 0) {
            TextView textView = v.findViewById(R.id.liver);
            textView.setVisibility(View.GONE);
        }
        listViewLiver.setAdapter(LiverAdapter);

        final ArrayList<String> donarUserNamePan = mListener.getDonorUserNameListS(R.id.pendingPancreas);
        ListView listViewPan = v.findViewById(R.id.pendingPancreas);
        ArrayAdapter<String> PanAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, mListener.getNamesS(donarUserNamePan));
        listViewPan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mListener.showDataS(donarUserNamePan.get(position), R.id.pendingPancreas);
            }
        });
        if(donarUserNamePan.size() == 0) {
            TextView textView = v.findViewById(R.id.pancre);
            textView.setVisibility(View.GONE);
        }
        listViewPan.setAdapter(PanAdapter);

        final ArrayList<String> donarUserNameLun = mListener.getDonorUserNameListS(R.id.pendingLungs);
        ListView listViewLun = v.findViewById(R.id.pendingLungs);
        ArrayAdapter<String> LunAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, mListener.getNamesS(donarUserNameLun));
        listViewLun.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mListener.showDataS(donarUserNameLun.get(position), R.id.pendingLungs);
            }
        });
        if(donarUserNameLun.size() == 0) {
            TextView t = v.findViewById(R.id.lung);
            t.setVisibility(View.GONE);
        }
        listViewLun.setAdapter(LunAdapter);

        final ArrayList<String> donarUserNameInt = mListener.getDonorUserNameListS(R.id.pendingIntestine);
        ListView listViewInt = v.findViewById(R.id.pendingIntestine);
        ArrayAdapter<String> IntAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, mListener.getNamesS(donarUserNameInt));
        listViewInt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mListener.showDataS(donarUserNameInt.get(position), R.id.pendingIntestine);
            }
        });
        if(donarUserNameInt.size() == 0) {
            TextView textView = v.findViewById(R.id.intes);
            textView.setVisibility(View.GONE);
        }
        listViewInt.setAdapter(IntAdapter);

        final ArrayList<String> donarUserNameBlood = mListener.getDonorUserNameListS(R.id.pendingBlood);
        ListView listViewBlo = v.findViewById(R.id.pendingBlood);
        ArrayAdapter<String> BloAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, mListener.getNamesS(donarUserNameBlood));
        listViewBlo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mListener.showDataS(donarUserNameBlood.get(position), R.id.pendingBlood);
            }
        });
        if(donarUserNameInt.size() == 0) {
            TextView textView = v.findViewById(R.id.blod);
            textView.setVisibility(View.GONE);
        }
        listViewBlo.setAdapter(BloAdapter);

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
