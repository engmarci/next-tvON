package com.movile.next.tvON.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.movile.next.tvON.R;

public class InformationFragment extends Fragment{

    public static final String EXTRA_SHOW = "EXTRA_SHOW";
    private String mShowNumber;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.show_information_fragment,
                container, false);

        mShowNumber = getArguments().getString("EXTRA_SHOW");

        return view;
    }

}
