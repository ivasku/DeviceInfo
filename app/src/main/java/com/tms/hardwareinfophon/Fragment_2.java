package com.tms.hardwareinfophon;


import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class Fragment_2 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ListView lv;
    private List<String> list;
    private List<Integer> drawableIds;

    private OnFragmentInteractionListener mListener;

    public Fragment_2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_2.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_2 newInstance(String param1, String param2) {
        Fragment_2 fragment = new Fragment_2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list = new ArrayList<>();

        list.add("Model: " + Build.MODEL + " (" + Build.PRODUCT + ")");
        list.add("Manufacturer: " + Build.MANUFACTURER);
        list.add("Brand: " + Build.BRAND);

        if (Build.BOARD.equalsIgnoreCase(Build.HARDWARE)){
            list.add("Board: " + Build.BOARD);
        }
        else {
            list.add("Board: " + Build.BOARD);
            list.add("Hardware: " + Build.HARDWARE);
        }

        list.add("Screen Size: " + Util.getInstance().getScreenSizeInches() + " inches");
        list.add("Screen Rezolution: " + Util.getInstance().getScreenSize().get(0) + "x" + Util.getInstance().getScreenSize().get(1) + " pixels");
        list.add("Screen Density: " + Util.getInstance().getScreenSize().get(3) + " dpi");

        list.add("Total RAM: " + Util.getInstance().getTotalRam());
        list.add("Available RAM: " + Util.getInstance().getAvailableRam() + " mg (" + Util.getInstance().getAvailableRamPercentage() + "%)");
        list.add("Internal Storage: " + Util.getInstance().getInternalStorage());
        list.add("Available Storage: " + Util.getInstance().getFreeInternalStorage() + " GB");


        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_fragment_2, container, false);
        lv = (ListView) v.findViewById(R.id.frag2lista);
        ArrayAdapter adapter = new ArrayAdapter<String>(v.getContext(), android.R.layout.simple_list_item_1 , list);
        lv.setAdapter(adapter);

        return v;
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
