package com.example.secretsantaservi.mytrainingquickplayversion.versionwithfragments;

public interface QuickPlayInterface {
    void goToEnterNames();
    void goToSelectNameForSetParams();
    void goToSetParams(int indexSanta);
    void goToSelectNameForShowReceiver();
    void goToShowReceivers(int indexSanta);
    void finish();
    void removeFragmentSetParams();
    void removeFragmentSelectNameForSetParams();
    void removeFragmentEnterNames();
    void removeFragmentShowReceiver();
}
