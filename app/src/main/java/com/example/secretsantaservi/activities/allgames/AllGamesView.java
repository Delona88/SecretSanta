package com.example.secretsantaservi.activities.allgames;

import java.util.ArrayList;

public interface AllGamesView {
    void showProgressBar();
    void hideProgressBar();
    void createGamesButton(ArrayList<Integer> gamesId);
    void goToFillInfo();
    void buildGUI();
    void showToastServerProblem(String t);
}
