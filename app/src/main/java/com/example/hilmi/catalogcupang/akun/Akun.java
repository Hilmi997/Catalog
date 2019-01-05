package com.example.hilmi.catalogcupang.akun;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hilmi.catalogcupang.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Akun extends Fragment {


    public Akun() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_akun, container, false);

        //title toobar in fragment
        getActivity().setTitle(getString(R.string.detail_item));


        return view;
    }

}
