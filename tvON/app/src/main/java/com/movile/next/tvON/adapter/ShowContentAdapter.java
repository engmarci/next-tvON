package com.movile.next.tvON.adapter;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.movile.next.tvON.fragment.InformationFragment;
import com.movile.next.tvON.fragment.SeasonFragment;

public class ShowContentAdapter extends FragmentPagerAdapter {
    private FragmentManager mFragmentManager;
    static final int ITEMS = 10;

    public ShowContentAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);

        mFragmentManager = fragmentManager;
    }

    @Override
    public int getCount() {
        return ITEMS;
    }

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

}
