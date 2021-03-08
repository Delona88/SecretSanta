package com.example.secretsantaservi.activities.newgame;

import java.util.HashMap;

public interface NewGameView {
    void buildGUI();
    void createButtonsWithParticipantsInfo(HashMap<String, String> infoHM);
    void setGameInfo(Integer currentGameId);
    void showProgressBar();
    void hideProgressBar();
    void showToastParticipantAdded();
    void showToastBadConditions();
    void showToastGameCreate(Integer gameId);
    void showToastServerProblem(String problem);
    void finish();


}
