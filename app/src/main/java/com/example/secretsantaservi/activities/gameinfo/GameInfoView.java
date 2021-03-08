package com.example.secretsantaservi.activities.gameinfo;

public interface GameInfoView {
    void buildGUI();
    void addPersonGameInfo(String personGameId);
    void addWishInfo(String wish);
    void addReceiverInfo(String receiverInfo);
    void addReceiverWishInfo(String receiverWishInfo);
    void showProgressBar();
    void hideProgressBar();
    void showToastServerProblem(String str1);
    void showToastWishSend();
    void showToastGameDelete();
}
