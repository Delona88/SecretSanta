package com.example.secretsantaservi.activities.newgame;

import com.example.secretsantaservi.API.MyCallback;
import com.example.secretsantaservi.SecretSantaApplication;
import com.example.secretsantaservi.secretsanta.BadConditionsException;

import java.util.HashMap;

public class NewGamePresenter {
    private NewGameView activity;
    private SecretSantaApplication application;
    private NewGameModel model;
    private Integer currentGameId;


    public NewGamePresenter(NewGameView activity, SecretSantaApplication application) {
        this.activity = activity;
        this.application = application;
        //this.newGameController = application.getNewGameController();
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
            public void onResponse(HashMap<String, String> response) {
                HashMap<String, String> infoHM = response;
                activity.createButtonsWithParticipantsInfo(infoHM);
                activity.hideProgressBar();
            }

            @Override
            public void onFailure(Throwable t) {
                application.showServerProblemToast(t.toString());
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
                if (t.getClass() == BadConditionsException.class){
                    //TODO test
                    activity.showToastBadConditions();
                    activity.hideProgressBar();
                } else {
                    activity.showToastServerProblem(t.toString());
                    activity.hideProgressBar();
                }
            }
        });
    }

    public void restart() {
        init();
    }
}
