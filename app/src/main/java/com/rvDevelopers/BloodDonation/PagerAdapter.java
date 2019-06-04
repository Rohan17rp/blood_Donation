package com.rvDevelopers.BloodDonation;

    import android.support.v4.app.Fragment;
    import android.support.v4.app.FragmentManager;
    import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {

    int aNoOfTabs;

    public PagerAdapter(FragmentManager fm, int NumberOfTabs){
        super(fm);
        this.aNoOfTabs = NumberOfTabs;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                final LoginFrag loginFrag = LoginFrag.newInstance("", "");
                return loginFrag;
            case 1:
                final SignUpFrag signUpFrag = SignUpFrag.newInstance();
                return signUpFrag;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return aNoOfTabs;
    }
}
