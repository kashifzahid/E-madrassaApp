package com.example.akhlaqcommunication.emaddrassa.PageviewerAdaptor;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.akhlaqcommunication.emaddrassa.LoginFragments.ParentLogin;
import com.example.akhlaqcommunication.emaddrassa.LoginFragments.TeacherLogin;

public class PageviewerAdaptor extends FragmentPagerAdapter {

     int mNumOfTabs;

    public PageviewerAdaptor(FragmentManager fm,int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = null;
        switch (i) {
            case 0:
                fragment = new TeacherLogin();
                break;
            case 1:
                fragment = new ParentLogin();
                break;

        }

        return fragment;
    }
    @Override
    public int getCount() {
        return mNumOfTabs;
    }

}
