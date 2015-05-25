package org.duncavage.recyclerviewdemo.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.duncavage.recyclerviewdemo.R;

/**
 * Created by brett on 5/23/15.
 */
public class ListPagerFragment extends Fragment {
    private ViewPager viewPager;
    private PagerTabStrip tabStrip;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_viewpager, container, false);

        viewPager = (ViewPager)rootView.findViewById(R.id.view_pager);
        tabStrip = (PagerTabStrip)rootView.findViewById(R.id.tab_strip);
        tabStrip.setBackgroundColor(getResources().getColor(R.color.toolbar_background));
        tabStrip.setTextColor(getResources().getColor(R.color.dark_text));
        tabStrip.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(R.dimen.textSizeTC3));
        tabStrip.setTabIndicatorColor(getResources().getColor(R.color.rdio_blue));

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager.setAdapter(new ListPagerAdapter(getChildFragmentManager()));
    }

    private class ListPagerAdapter extends FragmentPagerAdapter {
        public ListPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return getTitleForLayoutType(RecyclerViewFragment.LayoutType.values()[position]);
        }

        @Override
        public Fragment getItem(int position) {
            return RecyclerViewFragment.newInstance(RecyclerViewFragment.LayoutType.values()[position]);
        }

        @Override
        public int getCount() {
            return RecyclerViewFragment.LayoutType.values().length;
        }

        private String getTitleForLayoutType(RecyclerViewFragment.LayoutType type) {
            switch (type) {
                case Linear:
                    return getString(R.string.page_title_linear);
                case Grid:
                    return getString(R.string.page_title_grid);
                case GridWithGroupHeadings:
                    return getString(R.string.page_title_grid_with_headings);
                case GridWithHeadingsAndSpans:
                    return getResources().getString(R.string.page_title_grid_with_spans);
                default:
                    return null;
            }
        }
    }
}
