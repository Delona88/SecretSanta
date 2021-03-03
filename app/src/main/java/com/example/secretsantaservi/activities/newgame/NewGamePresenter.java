package com.example.secretsantaservi.activities.newgame;

import io.swagger.client.secretsantaclient.MyCallback;
import com.example.secretsantaservi.SecretSantaApplication;
import secretsantamodel.BadConditionsException;


import java.util.HashMap;

public class NewGamePresenter {
    private final NewGameView activity;
    private final NewGameModel model;
    private final Integer currentGameId;


    public NewGamePresenter(NewGameView activity, SecretSantaApplication application) {
        this.activity = activity;
        this.currentGameId = application.getCurrentGameId();
        this.model = new NewGameModel(application.getClient());

    }

    public void init(){
        activity.buildGUI();
        activity.setGameInfo(currentGameId);
        startGetHMWithPersonsInfoAndCreateButtons();
    }

    public void startGetHMWithPersonsInfoAndCreateButtons() {
        activity.showProgressBar();
        model.getHMWithPersonsInfo(currentGameId, new MyCallback<HashMap<String, String>>() {
            @Override
            public void onResponse(HashMap<String, String> infoHM) {
                activity.createButtonsWithParticipantsInfo(infoHM);
                activity.hideProgressBar();
            }

            @Override
            public void onFailure(Throwable t) {
                activity.showToastServerProblem(t.toString());
                activity.hideProgressBar();
            }
        });
    }

    public void startSetGamePlayed() {
        activity.showProgressBar();
        model.setGamePlayedTrue(currentGameId, new MyCallback<Object>() {
            @Override
            public void onResponse(Object response) {
                activity.showToastParticipantAdded();
                activity.hideProgressBar();
            }

            @Override
            public void onFailure(Throwable t) {
                activity.showToastServerProblem(t.toString());
                activity.hideProgressBar();
            }
        });
    }

    public void startToss() {
        activity.showProgressBar();
        model.startToss(currentGameId, new MyCallback<Object>() {
            @Override
            public void onResponse(Object responseCode) {
                activity.showToastGameCreate(currentGameId);
                activity.hideProgressBar();
                activity.finish();
/*                int BAD_REQUEST = 400; //Неверно указаны ограничения. Невозможно установить получателей
                if ((Integer) responseCode == BAD_REQUEST) {
                    activity.showToastBadConditions();
                    activity.hideProgressBar();
                }
*//*                } else {
                    activity.showToastGameCreate(currentGameId);
                    activity.hideProgressBar();
                    activity.finish();
                }*/
            }

            @Override
            public void onFailure(Throwable t) {
                activity.hideProgressBar();
                if (t.getClass() == BadConditionsException.class){
                    activity.showToastBadConditions();
                } else {
                    activity.showToastServerProblem(t.toString());
                }
            }
        });
    }

    public void restart() {
        init();
    }
}
