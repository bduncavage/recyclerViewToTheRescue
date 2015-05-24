package org.duncavage.recyclerviewdemo.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.duncavage.recyclerviewdemo.R;

/**
 * Created by brett on 5/23/15.
 */
public class ListPagerFragment extends Fragment {
    private ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_viewpager, container, false);
        viewPager = (ViewPager)rootView.findViewById(R.id.view_pager);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager.setAdapter(new ListPagerAdapter(getChildFragmentManager()));
    }

    private static class ListPagerAdapter extends FragmentPagerAdapter {
        public ListPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return RecyclerViewFragment.newInstance(RecyclerViewFragment.LayoutType.values()[position]);
        }

        @Override
        public int getCount() {
            return RecyclerViewFragment.LayoutType.values().length;
        }
    }
}
