package com.learn.maksymgromov.socialnetwork;

import android.support.v4.app.Fragment;

public class FragmentFactory {
    public static Fragment newInstance(String fragmentType) {
        if(fragmentType.equalsIgnoreCase("ABOUTME")) {
            return new AboutMeFragment();
        } else {
            return new Fragment();
        }
    }
}
