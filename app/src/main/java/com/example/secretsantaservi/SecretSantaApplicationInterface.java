package com.example.secretsantaservi;

import com.example.secretsantaservi.API.ApiWithMyCallbackInterface;
import com.example.secretsantaservi.secretsanta.GameController;
import com.example.secretsantaservi.secretsanta.PersonController;

public interface SecretSantaApplicationInterface {
    void setClient(ApiWithMyCallbackInterface client);

    ApiWithMyCallbackInterface getClient();

    PersonController getAuthorizedPersonController();

    void createNewPersonController();

    PersonController getNewPersonController();

    void createNewGameController(int id);

    void createNewPersonController(String personId);

    GameController getNewGameController();

    void createExistGameController(int id);

    void createExistGameController();

    GameController getExistGameController();

    void showServerProblemToast(String str1);

    void showToast(String str);

    void showToast(String str, String arg1);

    void showToast(String str, String arg1, String arg2);

    void showToast(int strResId);

    void showToast(int strResId, String arg1);

    void showToast(int strResId, String arg1, String arg2);
}
