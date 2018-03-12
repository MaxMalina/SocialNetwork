package com.learn.maksymgromov.socialnetwork;

import android.support.v4.app.Fragment;

public class FragmentFactory {
    public static Fragment newInstance(String fragmentType) {
        if (fragmentType.equalsIgnoreCase(AboutMeFragment.TAG)) {
            return new AboutMeFragment();
        } else if (fragmentType.equalsIgnoreCase(NewsFragment.TAG)) {
            return new NewsFragment();
        } else if (fragmentType.equalsIgnoreCase(FriendsFragment.TAG)) {
            return new FriendsFragment();
        } else {
            return new Fragment();
        }
    }
}
