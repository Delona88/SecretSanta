package com.example.secretsantaservi.mytrainingquickplayversion.versionwithfragments;

import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.example.secretsantaservi.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class QuickPlayActivityWithViewPager extends AppCompatActivity implements QuickPlayInterface {

    private ViewPager viewPager;
    private QuickPlayFragmentPagerAdapter pagerAdapter;

    private EnterNamesFragment enterNamesFragment;
    private SelectNameForSetParamsFragment selectNameForSetParamsFragment;
    private SetParamsFragment setParamsFragment;
    private SelectNameForShowReceiverFragment selectNameForShowReceiverFragment;
    private ShowReceiverFragment showReceiverFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_play);

        pagerAdapter = new QuickPlayFragmentPagerAdapter(getSupportFragmentManager());

        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(pagerAdapter);

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        if (savedInstanceState == null){
            goToEnterNames();
        }

    }

    private static class QuickPlayFragmentPagerAdapter extends FragmentStatePagerAdapter {
        private final ArrayList<Fragment> fragments = new ArrayList<>();
        private final ArrayList<String> titles = new ArrayList<>();

        public QuickPlayFragmentPagerAdapter(@NonNull FragmentManager fm) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @Override
        public Fragment getItem(int position) {

            Log.d("getItem fragment", "position ------>" + position);

            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return titles.size();
        }

        public int addFragmentAndReturnIndex(Fragment fragment, String title) {
            fragments.add(fragment);
            titles.add(title);
            notifyDataSetChanged();
            int i = fragments.indexOf(fragment);

            Log.d("add fragment", "index ------>" + fragment.getId());

            return i;
        }

        public int removeFragmentAndReturnLastIndex(Fragment fragment) {
            int i = fragments.indexOf(fragment);
            fragments.remove(i);
            titles.remove(i);
            notifyDataSetChanged();

            Log.d("remove fragment", "index ------>" + i);

            return i - 1;
        }

        @Override
        public int getItemPosition(@NonNull Object object) {
            return POSITION_NONE;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }

    }


    @Override
    public void goToEnterNames() {
        enterNamesFragment = new EnterNamesFragment();
        int index = pagerAdapter.addFragmentAndReturnIndex(enterNamesFragment, getResources().getString(R.string.title_names));
        viewPager.setCurrentItem(index);


        Log.d("EnterNames fragment", "index ------>" + index);
    }

    @Override
    public void goToSelectNameForSetParams() {
        if (selectNameForSetParamsFragment != null) {
            removeFragmentSelectNameForSetParams();
        }

        selectNameForSetParamsFragment = new SelectNameForSetParamsFragment();
        int index = pagerAdapter.addFragmentAndReturnIndex(selectNameForSetParamsFragment, getResources().getString(R.string.title_conditions));
        viewPager.setCurrentItem(index);


        Log.d("SelectNameForShowReceiver fragment", "index ------>" + index);

    }


    @Override
    public void goToSetParams(int indexSanta) {
        if (setParamsFragment != null) {
            removeFragmentSetParams();
        }

        setParamsFragment = SetParamsFragment.newInstance(indexSanta);
        int index = pagerAdapter.addFragmentAndReturnIndex(setParamsFragment, getResources().getString(R.string.title_set_params));
        viewPager.setCurrentItem(index);


        Log.d("SetParams fragment", "index ------>" + index);

    }

    @Override
    public void goToSelectNameForShowReceiver() {
        removeFragmentNames();
        removeFragmentSelectNameForSetParams();
        if (selectNameForShowReceiverFragment != null) {
            removeFragmentSelectNameForSetParams();
        }

        selectNameForShowReceiverFragment = new SelectNameForShowReceiverFragment();
        int index = pagerAdapter.addFragmentAndReturnIndex(selectNameForShowReceiverFragment, getResources().getString(R.string.title_santa));
        viewPager.setCurrentItem(index);

        Log.d("SelectNameForShowReceiver fragment", "index ------>" + index);

    }

    @Override
    public void goToShowReceivers(int indexSanta) {
        if (showReceiverFragment != null) {
            removeFragmentShowReceiver();
        }

        showReceiverFragment = ShowReceiverFragment.newInstance(indexSanta);
        int index = pagerAdapter.addFragmentAndReturnIndex(showReceiverFragment, getResources().getString(R.string.title_names));
        viewPager.setCurrentItem(index);


        Log.d("ShowReceivers fragment", "index ------>" + index);
    }

    public void removeFragmentNames() {
        pagerAdapter.removeFragmentAndReturnLastIndex(enterNamesFragment);
    }

    public void removeFragmentSelectNameForSetParams() {
        pagerAdapter.removeFragmentAndReturnLastIndex(selectNameForSetParamsFragment);
    }

    @Override
    public void removeFragmentSetParams() {
        pagerAdapter.removeFragmentAndReturnLastIndex(setParamsFragment);
        viewPager.setCurrentItem(selectNameForSetParamsFragment.getId());
        setParamsFragment = null;
    }

    public void removeFragmentShowReceiver() {
        pagerAdapter.removeFragmentAndReturnLastIndex(showReceiverFragment);
        viewPager.setCurrentItem(selectNameForShowReceiverFragment.getId());
        showReceiverFragment = null;
    }

}
