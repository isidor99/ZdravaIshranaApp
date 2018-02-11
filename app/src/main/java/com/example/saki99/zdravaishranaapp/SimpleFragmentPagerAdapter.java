package com.example.saki99.zdravaishranaapp;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Saki99 on 9.2.2018..
 */

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    private Context context;

    public SimpleFragmentPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {

        if (position == 0) {

            return new ReceptiFragment();

        } else if (position == 1) {

            return new ProizvodiFragment();

        } else if (position == 2) {

            return new ProdavniceFragment();

        }

        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return context.getString(R.string.tab_text_1);

            case 1:
                return context.getString(R.string.tab_text_2);

            case 2:
                return context.getString(R.string.tab_text_3);

            default:
                return null;
        }
    }
}
