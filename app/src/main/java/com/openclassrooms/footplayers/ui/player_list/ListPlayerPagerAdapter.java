package com.openclassrooms.footplayers.ui.player_list;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


public class ListPlayerPagerAdapter extends FragmentPagerAdapter {


    public ListPlayerPagerAdapter(FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    /**
     * getItem is called to instantiate the fragment for the given page.
     * @param position
     * @return
     **/

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return PlayerFragment.newInstance(false);
            case 1:
                return PlayerFragment.newInstance(true);
        }
        return null;
    }

    /**
     * get the number of pages
     * @return
     */
    //add a second page for favorites
    @Override
    public int getCount() {
        return 2;
    }

}