package com.example.twins.nicolinska.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.twins.nicolinska.R;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {
    private Toolbar mToolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_main, container, false);


        viewPager = (ViewPager) mView.findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        mToolbar = (Toolbar) mView.findViewById(R.id.toolbar);
        mToolbar.setTitle(getString(R.string.nicolinska));
        mToolbar.inflateMenu(R.menu.menu_main);

        mToolbar.setOnMenuItemClickListener((MenuItem item) -> {
                switch (item.getItemId()) {
                    case R.id.menu_contact:
                        new MenuContactFragment().show(getFragmentManager(), "menu_contact_fragment");
                        break;
                    case R.id.menu_programs:
                        new MenuProgramsFragment().show(getFragmentManager(), "menu_programs_fragment");
                        break;
                    case R.id.menu_exit:
                        getActivity().onBackPressed();
                        break;
                }
                return true;
        });


        tabLayout = (TabLayout) mView.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        return mView;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(new OrderFragment(), getString(R.string.to_order));
        adapter.addFragment(new GoodsFragment(), getString(R.string.products));
        adapter.addFragment(new AboutUsFragment(), getString(R.string.about_us));
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}
