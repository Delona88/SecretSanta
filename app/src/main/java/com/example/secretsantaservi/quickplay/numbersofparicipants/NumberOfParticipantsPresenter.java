package com.example.secretsantaservi.quickplay.numbersofparicipants;

import com.example.secretsantaservi.quickplay.SecretSantaQuickGame;

public class NumberOfParticipantsPresenter {
    NumberOfParticipantsView activity;
    public static SecretSantaQuickGame toss;

    public NumberOfParticipantsPresenter(NumberOfParticipantsView activity) {
        this.activity = activity;
    }

    public boolean isNumeric(String strNum) {
        try {
            int d = Integer.parseInt(strNum);
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
            activity.getMessageWindow();
        }
    }
}
