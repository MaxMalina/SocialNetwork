package com.learn.maksymgromov.socialnetwork;

import android.support.v4.app.Fragment;

public class FragmentFactory {
    public static Fragment newInstance(String fragmentType) {
        if (fragmentType.equalsIgnoreCase(AboutMeFragment.ABOUT_ME_FRAGMENT_NAME)) {
            return new AboutMeFragment();
        } else if (fragmentType.equalsIgnoreCase(NewsFragment.NEWS_FRAGMENT_NAME)) {
            return new NewsFragment();
        } else {
            return new Fragment();
        }
    }
}
