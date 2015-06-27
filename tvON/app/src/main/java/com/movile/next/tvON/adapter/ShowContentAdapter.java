package com.movile.next.tvON.adapter;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.movile.next.tvON.fragment.InformationFragment;
import com.movile.next.tvON.fragment.SeasonFragment;

public class ShowContentAdapter extends FragmentPagerAdapter {
    private static final int ITEMS = 2;

    public ShowContentAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public int getCount() {
        return ITEMS;
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;

        Bundle arguments = new Bundle();
        if (position == 0){
            //arguments.putSerializable(InformationFragment.EXTRA_SHOW);
            fragment = new InformationFragment();
        }

        if (position == 1){
            //arguments.putSerializable(SeasonFragment.EXTRA_SHOW);
            fragment = new SeasonFragment();
        }

        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "INFO";
        }
        if (position == 1) {
            return "SEASONS";
        }
        return "Not found";
    }

}