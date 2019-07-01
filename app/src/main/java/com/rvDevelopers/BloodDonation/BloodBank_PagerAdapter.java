package com.rvDevelopers.BloodDonation;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class BloodBank_PagerAdapter extends FragmentStatePagerAdapter {

    int aNoOfTabs;

    public BloodBank_PagerAdapter(FragmentManager fm, int NumberOfTabs){
        super(fm);
        this.aNoOfTabs = NumberOfTabs;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new AvailableBlood();
            case 1:
                return new BloodDonors();
//            case 2:
//                Contact contact = new Contact();
//                return contact;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return aNoOfTabs;
    }
}
