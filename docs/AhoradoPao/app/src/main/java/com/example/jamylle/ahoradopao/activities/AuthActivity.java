package com.example.jamylle.ahoradopao.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.jamylle.ahoradopao.R;
import com.example.jamylle.ahoradopao.fragments.LoginFragment;
import com.example.jamylle.ahoradopao.fragments.RegistrationFragment;

import java.util.ArrayList;
import java.util.List;

public class AuthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_auth);
        toolbar.setTitle(R.string.toolbar_title);

        setSupportActionBar(toolbar);

        ViewPager viewPager = (ViewPager)findViewById(R.id.viewPager);

        if (viewPager != null) {

            setUpViewPager(viewPager);
        }

        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setUpViewPager(ViewPager viewPager) {

        TabLayoutAdapter adapter = new TabLayoutAdapter(getSupportFragmentManager());
        adapter.addFragment(new LoginFragment(), "Login");
        adapter.addFragment(new RegistrationFragment(), "Registro");


        viewPager.setAdapter(adapter);
    }

    private class TabLayoutAdapter extends FragmentPagerAdapter {

        private final List<Fragment> fragments = new ArrayList<>();
        private final List<String> fragmentNames = new ArrayList<>();

        public TabLayoutAdapter(FragmentManager fm) {

            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {

            fragments.add(fragment);
            fragmentNames.add(title);
        }

        @Override
        public Fragment getItem(int position) {

            return fragments.get(position);
        }

        @Override
        public int getCount() {

            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {

            return fragmentNames.get(position);
        }
    }
}
