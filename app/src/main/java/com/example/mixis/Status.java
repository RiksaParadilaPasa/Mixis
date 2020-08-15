package com.example.mixis;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.legacy.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.mixis.status_fragment.Address;
import com.example.mixis.status_fragment.Invoice;
import com.google.android.material.tabs.TabLayout;

public class Status extends AppCompatActivity {

    private final String[] PAGE_TITLES = new String[] {
            "STATUS", "DETAIL" };
    private final Fragment[] PAGES = new Fragment[] {
            new Invoice(), new Address() };

    public static ViewPager viewPager;
    public static TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new MyPagerAdapter(getFragmentManager()));

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Detail Transaksi");
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    public class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            return PAGES[position];
        }

        @Override
        public int getCount() {
            return PAGES.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return PAGE_TITLES[position];
        }

    }
}
