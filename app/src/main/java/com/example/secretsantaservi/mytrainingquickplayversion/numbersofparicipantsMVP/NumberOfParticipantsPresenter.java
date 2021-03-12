package com.example.secretsantaservi.mytrainingquickplayversion.numbersofparicipantsMVP;

public class NumberOfParticipantsPresenter {
    private final NumberOfParticipantsView activity;


    public NumberOfParticipantsPresenter(NumberOfParticipantsView activity) {
        this.activity = activity;
    }

    public boolean isNumeric(String strNum) {
        try {
            Integer.parseInt(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }

    public void createNewTossAndStartNextActivityOrGetMessageWindow(String str) {
        if (isNumeric(str)) {
            int num = Integer.parseInt(str);
            activity.startActivityNamesOfParticipants(num);
        } else {
            activity.showToastIncorrectInfo();
        }
    }
}
