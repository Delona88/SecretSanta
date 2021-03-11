package com.example.secretsantaservi.activities.allgames;

import io.swagger.client.secretsantaclient.Callback;
import com.example.secretsantaservi.SecretSantaApplication;


import java.util.ArrayList;

public class AllGamesPresenter {
    private final AllGamesModel model;
    private final AllGamesView activity;
    private final SecretSantaApplication secretSantaApplication;

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
        model.getIdAllActiveGamesByPersonId(authorizedPersonEmail, new Callback<ArrayList<Integer>>() {

            @Override
            public void onResponse(ArrayList<Integer> response) {
                activity.createGamesList(response);
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
        model.getNewID(new Callback<Integer>() {

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
