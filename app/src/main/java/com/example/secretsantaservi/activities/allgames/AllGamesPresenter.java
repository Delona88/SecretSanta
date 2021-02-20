package com.example.secretsantaservi.activities.allgames;

import com.example.secretsantaservi.API.MyCallback;
import com.example.secretsantaservi.SecretSantaApplication;
import com.example.secretsantaservi.secretsanta.PersonController;

import java.util.ArrayList;

public class AllGamesPresenter {
    private AllGamesModel model;
    private AllGamesView activity;
    private SecretSantaApplication secretSantaApplication;

    private String authorizedPersonEmail;

    public AllGamesPresenter(AllGamesView activity, SecretSantaApplication secretSantaApplication) {
        this.activity = activity;
        this.secretSantaApplication = secretSantaApplication;
        this.authorizedPersonEmail = secretSantaApplication.getAuthorizedPersonEmail();
        this.model = new AllGamesModel(secretSantaApplication.getClient());
    }

    public void init() {
        activity.buildGUI();
        startGetPersonWithIdAllGames();
    }

    public void startGetPersonWithIdAllGames() {
        activity.showProgressBar();
        model.getIdAllActiveGamesByPersonId(authorizedPersonEmail, new MyCallback<ArrayList<Integer>>() {

            @Override
            public void onResponse(ArrayList<Integer> response) {
                activity.createGamesButton(response);
                activity.hideProgressBar();
            }

            @Override
            public void onFailure(Throwable t) {
                activity.showToastServerProblem(t.toString());
                activity.hideProgressBar();
            }
        });
    }


    public void startGetNewGameID() {
        activity.showProgressBar();
        model.getNewID(new MyCallback<Integer>() {

            @Override
            public void onResponse(Integer response) {
                secretSantaApplication.setCurrentGameId(response);
                activity.hideProgressBar();
                activity.goToFillInfo();
            }

            @Override
            public void onFailure(Throwable t) {
                activity.showToastServerProblem(t.toString());
                activity.hideProgressBar();
            }
        });
    }


    public void restart(){
        authorizedPersonEmail = secretSantaApplication.getAuthorizedPersonEmail();
        init();
    }
}
