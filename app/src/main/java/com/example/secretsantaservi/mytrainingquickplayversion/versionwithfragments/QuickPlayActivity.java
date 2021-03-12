package com.example.secretsantaservi.mytrainingquickplayversion.versionwithfragments;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.FragmentTransaction;
import com.example.secretsantaservi.R;
import com.example.secretsantaservi.mytrainingquickplayversion.model.SecretSantaQuickGame;

public class QuickPlayActivity extends AppCompatActivity implements QuickPlayInterface {

    public static SecretSantaQuickGame quickGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_play);
        quickGame = new SecretSantaQuickGame();
        if (savedInstanceState == null) {
            goToEnterNames();
        }
    }

    @Override
    public void goToEnterNames() {
        getSupportFragmentManager().beginTransaction().setReorderingAllowed(true)
                .add(R.id.fragment_container_view, NamesFragment.class, null)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void goToSelectNamesForSetParams() {
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
                .add(R.id.fragment_container_view, SelectSantaForShowReceiverFragment.class, null)
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
    public void popBackStack() {
        getSupportFragmentManager().popBackStack();
    }


}