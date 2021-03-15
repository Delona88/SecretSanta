package com.example.secretsantaservi.mytrainingquickplayversion.versionwithfragments;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.FragmentTransaction;
import com.example.secretsantaservi.R;

public class QuickPlayActivityWithFragments extends AppCompatActivity implements QuickPlayInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_play2);
        if (savedInstanceState == null) {
            goToEnterNames();
        }
    }

    @Override
    public void goToEnterNames() {
        getSupportFragmentManager().beginTransaction().setReorderingAllowed(true)
                .add(R.id.fragment_container_view, EnterNamesFragment.class, null)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void goToSelectNameForSetParams() {
        getSupportFragmentManager().beginTransaction().setReorderingAllowed(true)
                .add(R.id.fragment_container_view, SelectNameForSetParamsFragment.class, null)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(null)
                .commit();
    }


    @Override
    public void goToSetParams(int indexSanta) {
        Bundle bundle = new Bundle();
        bundle.putInt("indexSanta", indexSanta);
        getSupportFragmentManager().beginTransaction().setReorderingAllowed(true)
                .add(R.id.fragment_container_view, SetParamsFragment.class, bundle)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void goToSelectNameForShowReceiver() {
        getSupportFragmentManager().beginTransaction().setReorderingAllowed(true)
                .add(R.id.fragment_container_view, SelectNameForShowReceiverFragment.class, null)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void goToShowReceivers(int indexSanta) {
        Bundle bundle = new Bundle();
        bundle.putInt("indexSanta", indexSanta);
        getSupportFragmentManager().beginTransaction().setReorderingAllowed(true)
                .add(R.id.fragment_container_view, ShowReceiverFragment.class, bundle)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void removeFragmentSetParams() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void removeFragmentSelectNameForSetParams() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void removeFragmentEnterNames() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void removeFragmentShowReceiver() {
        getSupportFragmentManager().popBackStack();
    }

}