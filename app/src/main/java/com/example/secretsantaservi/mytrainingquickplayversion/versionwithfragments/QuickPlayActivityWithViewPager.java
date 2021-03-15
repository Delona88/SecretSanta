package com.example.secretsantaservi.mytrainingquickplayversion.versionwithfragments;

import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.example.secretsantaservi.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class QuickPlayActivityWithViewPager extends AppCompatActivity implements QuickPlayInterface {

    private ViewPager viewPager;
    private QuickPlayFragmentPagerAdapter pagerAdapter;

    private boolean selectNameForSetParamsFragmentOpen;
    private boolean setParamsFragmentOpen;
    private boolean showReceiverFragmentOpen;

    private static final int ENTER_NAME_FRAGMENT_ID = 0;
    private static final int SELECT_NAME_FOR_SET_PARAMS_FRAGMENT_ID = 1;
    private static final int SET_PARAMS_FRAGMENT_ID = 2;
    private static final int SELECT_NAME_FOR_SHOW_RECEIVER_FRAGMENT_ID = 3;
    private static final int SHOW_RECEIVER_FRAGMENT_ID = 4;

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
        private ArrayList<String> titles = new ArrayList<>();
        private ArrayList<Integer> show = new ArrayList<>();

        private int indexSanta;

        public QuickPlayFragmentPagerAdapter(@NonNull FragmentManager fm) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            Log.d("getItem ", "position ------>" + position);

            switch (show.get(position)) {
                case ENTER_NAME_FRAGMENT_ID:
                    return new EnterNamesFragment();
                case SELECT_NAME_FOR_SET_PARAMS_FRAGMENT_ID:
                    return new SelectNameForSetParamsFragment();
                case SET_PARAMS_FRAGMENT_ID:
                    return SetParamsFragment.newInstance(indexSanta);
                case SELECT_NAME_FOR_SHOW_RECEIVER_FRAGMENT_ID:
                    return new SelectNameForShowReceiverFragment();
                case SHOW_RECEIVER_FRAGMENT_ID:
                    return ShowReceiverFragment.newInstance(indexSanta);
                default:
                    return new EnterNamesFragment();
            }
        }

        @Nullable
        @Override
        public Parcelable saveState() {
            Bundle bundle = (Bundle) super.saveState();
            if (bundle != null) {
                bundle.putIntegerArrayList("array", show);
                bundle.putStringArrayList("titles",titles);
            }

            Log.d("saveState ", "show ------>" + show.toString());
            Log.d("saveState ", "titles ------>" + titles.toString());

            return bundle;
        }

        @Override
        public void restoreState(@Nullable Parcelable state, @Nullable ClassLoader loader) {
            super.restoreState(state, loader);
            Bundle bundle = (Bundle) state;
            if (bundle != null) {
                show = bundle.getIntegerArrayList("array");
                titles = bundle.getStringArrayList("titles");
            }
            notifyDataSetChanged();

            Log.d("restoreState ", "show ------>" + show.toString());
            Log.d("restoreState ", "titles ------>" + titles.toString());
        }


        @Override
        public int getCount() {
            return titles.size();
        }

        @Override
        public int getItemPosition(@NonNull Object object) {
            return POSITION_NONE;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }

        public void setIndexSanta(int indexSanta) {
            this.indexSanta = indexSanta;
        }

        public int addFragmentAndReturnIndex(int fragmentId, String title) {
            titles.add(title);
            show.add(fragmentId);
            notifyDataSetChanged();
            int i = titles.indexOf(title);

            Log.d("add fragment", "fragmentId ------>" + fragmentId);

            return i;
        }

        public int removeFragmentAndReturnLastIndex(int fragmentId) {
            int i = show.indexOf(fragmentId);
            show.remove(i);
            titles.remove(i);
            notifyDataSetChanged();

            Log.d("remove fragment", "fragmentId ------>" + fragmentId);

            return i - 1;
        }

    }


    @Override
    public void goToEnterNames() {
        int index = pagerAdapter.addFragmentAndReturnIndex(ENTER_NAME_FRAGMENT_ID, getResources().getString(R.string.title_names));
        viewPager.setCurrentItem(index);


        Log.d("EnterNames ", "index ------>" + index);
    }

    @Override
    public void goToSelectNameForSetParams() {
        if (selectNameForSetParamsFragmentOpen){
            removeFragmentSelectNameForSetParams();
        }

        selectNameForSetParamsFragmentOpen = true;
        int index = pagerAdapter.addFragmentAndReturnIndex(SELECT_NAME_FOR_SET_PARAMS_FRAGMENT_ID, getResources().getString(R.string.title_conditions));
        viewPager.setCurrentItem(index);


        Log.d("SelectNameForShowReceiver ", "index ------>" + index);
    }

    @Override
    public void goToSetParams(int indexSanta) {
        if (setParamsFragmentOpen){
            removeFragmentSetParams();
        }

        setParamsFragmentOpen = true;
        pagerAdapter.setIndexSanta(indexSanta);
        int index = pagerAdapter.addFragmentAndReturnIndex(SET_PARAMS_FRAGMENT_ID, getResources().getString(R.string.title_set_params));
        viewPager.setCurrentItem(index);


        Log.d("SetParams ", "index ------>" + index);
    }

    @Override
    public void goToSelectNameForShowReceiver() {
        removeFragmentEnterNames();
        removeFragmentSelectNameForSetParams();
        int index = pagerAdapter.addFragmentAndReturnIndex(SELECT_NAME_FOR_SHOW_RECEIVER_FRAGMENT_ID, getResources().getString(R.string.title_santa));
        viewPager.setCurrentItem(index);

        Log.d("SelectNameForShowReceiver ", "index ------>" + index);
    }

    @Override
    public void goToShowReceivers(int indexSanta) {
        if (showReceiverFragmentOpen) {
            removeFragmentShowReceiver();
        }

        showReceiverFragmentOpen = true;
        pagerAdapter.setIndexSanta(indexSanta);
        int index = pagerAdapter.addFragmentAndReturnIndex(SHOW_RECEIVER_FRAGMENT_ID, getResources().getString(R.string.title_names));
        viewPager.setCurrentItem(index);


        Log.d("ShowReceivers ", "index ------>" + index);
    }

    public void removeFragmentEnterNames() {
        pagerAdapter.removeFragmentAndReturnLastIndex(ENTER_NAME_FRAGMENT_ID);
    }

    public void removeFragmentSelectNameForSetParams() {
        pagerAdapter.removeFragmentAndReturnLastIndex(SELECT_NAME_FOR_SET_PARAMS_FRAGMENT_ID);
        selectNameForSetParamsFragmentOpen = false;
    }

    @Override
    public void removeFragmentSetParams() {
        pagerAdapter.removeFragmentAndReturnLastIndex(SET_PARAMS_FRAGMENT_ID);
        viewPager.setCurrentItem(1);
        setParamsFragmentOpen = false;
    }

    public void removeFragmentShowReceiver() {
        pagerAdapter.removeFragmentAndReturnLastIndex(SHOW_RECEIVER_FRAGMENT_ID);
        viewPager.setCurrentItem(0);
        showReceiverFragmentOpen = false;
    }

}


