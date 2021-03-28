package com.example.currencyonverter.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.currencyonverter.ConverterFragment;
import com.example.currencyonverter.HistoryFragment;

public class PagerAdapter extends FragmentPagerAdapter {

    private static final int PAGE_COUNT = 2;
    private String[] tabTitles = new String[] {"КОНВЕРТЕР", "ИСТОРИЯ"};

    public PagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ConverterFragment();
            case 1:
                return new HistoryFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
