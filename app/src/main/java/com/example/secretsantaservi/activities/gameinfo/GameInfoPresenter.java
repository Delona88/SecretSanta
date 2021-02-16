package com.example.secretsantaservi.activities.gameinfo;

import com.example.secretsantaservi.API.MyCallback;
import com.example.secretsantaservi.SecretSantaApplication;
import com.example.secretsantaservi.secretsanta.Person;
import com.example.secretsantaservi.secretsanta.PersonGame;

public class GameInfoPresenter {
    private GameInfoView activity;
    private GameInfoModel model;
    private SecretSantaApplication secretSantaApplication;

    private String authorizedPersonEmail;
    private Integer currentGameId;


    public GameInfoPresenter(GameInfoView view, SecretSantaApplication application) {
        this.activity = view;
        this.secretSantaApplication = application;
        this.authorizedPersonEmail = secretSantaApplication.getAuthorizedPersonEmail();
        this.currentGameId = secretSantaApplication.getCurrentGameId();
        this.model = new GameInfoModel(application.getClient());
    }

    public void init() {
        activity.buildGUI();
        startGetPersonGameInfo();
    }

    public void startGetPersonGameInfo() {
        activity.showProgressBar();
        model.getPersonGameByPersonIdAndGameId(authorizedPersonEmail, currentGameId, new MyCallback<PersonGame>() {
            @Override
            public void onResponse(PersonGame response) {
                PersonGame personGame = response;
                activity.addPersonGameInfo("" + personGame.getGameId());
                activity.addWishInfo(personGame.getWishlist());
                startGetAndAddReceiverInfo(personGame.getReceiverEmail());
            }

            @Override
            public void onFailure(Throwable t) {
                activity.showToastServerProblem(t.toString());
                activity.hideProgressBar();
            }
        });

    }

    public void startGetAndAddReceiverInfo(String receiverEmail) {
        model.getPersonById(receiverEmail, new MyCallback<Person>() {
            @Override
            public void onResponse(Person response) {
                Person receiver = response;
                String receiverInfo;
                if (receiver == null) {
                    receiverInfo = "Ваш получатель уже удалился из игры" + "\n";
                } else {
                    receiverInfo = receiver.getName() + "(" + receiver.getEmail() + ")" + "\n";
                    String receiverWishInfo= receiver.getWhishListByGameId(currentGameId);
                    if (receiverWishInfo != null) {
                        activity.addReceiverWishInfo(receiverWishInfo);
                    }
                }
                activity.addReceiverInfo(receiverInfo);
                activity.hideProgressBar();
            }

            @Override
            public void onFailure(Throwable t) {
                activity.showToastServerProblem(t.toString());
                activity.hideProgressBar();
            }
        });
    }


    public void startSetWishlist(String wish) {
        activity.showProgressBar();
        model.setWhishlist(authorizedPersonEmail, currentGameId, wish, new MyCallback<Object>() {
            @Override
            public void onResponse(Object response) {
                activity.showToastWishSend();
                activity.hideProgressBar();
            }

            @Override
            public void onFailure(Throwable t) {
                activity.showToastServerProblem(t.toString());
                activity.hideProgressBar();
            }
        });

    }

    public void startDeleteGame(){
        activity.showProgressBar();
        model.setPersonGameActiveFalse(currentGameId, authorizedPersonEmail, new MyCallback<Object>() {
            @Override
            public void onResponse(Object response) {
                activity.showToastGameDelete();
                activity.hideProgressBar();
            }

            @Override
            public void onFailure(Throwable t) {
                activity.showToastServerProblem(t.toString());
                activity.hideProgressBar();
            }
        });
    }


}
