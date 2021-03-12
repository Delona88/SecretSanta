package com.example.secretsantaservi.mytrainingquickplayversion.versionwithfragments;

public interface QuickPlayInterface {
    void goToEnterNames();
    void goToSelectNamesForSetParams();
    void goToSetParams(int indexSanta);
    void goToSelectNameForShowReceiver();
    void goToShowReceivers(int indexSanta);
    void finish();
    void popBackStack();
}
