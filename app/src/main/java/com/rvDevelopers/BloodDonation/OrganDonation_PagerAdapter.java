package com.rvDevelopers.BloodDonation;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class OrganDonation_PagerAdapter extends FragmentStatePagerAdapter {
    int aNoOfTabs;

    public OrganDonation_PagerAdapter(FragmentManager fm, int NumberOfTabs){
        super(fm);
        this.aNoOfTabs = NumberOfTabs;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new OrganBank();
            case 1:
                return new OrganDonors();
            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return aNoOfTabs;
    }
}
