package com.example.secretsantaservi.activities.allgames;

import java.util.ArrayList;

public interface AllGamesView {
    void buildGUI();
    void createGamesList(ArrayList<Integer> gamesId);
    void goToFillInfo();
    void showProgressBar();
    void hideProgressBar();
    void showToastServerProblem(String t);
}
