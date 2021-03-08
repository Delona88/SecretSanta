package com.example.secretsantaservi.quickplayversion.numbersofparicipantsMVP;

import com.example.secretsantaservi.quickplayversion.model.SecretSantaQuickGame;

public class NumberOfParticipantsPresenter {
    private final NumberOfParticipantsView activity;
    public static SecretSantaQuickGame toss;

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
            toss = new SecretSantaQuickGame(num);
            activity.startActivityNamesOfParticipants();
        } else {
            activity.showToastIncorrectInfo();
        }
    }
}
