package com.example.secretsantaservi.activities.gameinfo;

import io.swagger.client.secretsantaclient.Callback;
import com.example.secretsantaservi.SecretSantaApplication;
import secretsantamodel.Person;
import secretsantamodel.PersonGame;


public class GameInfoPresenter {
    private final GameInfoView activity;
    private final GameInfoModel model;

    private final String authorizedPersonEmail;
    private final Integer currentGameId;


    public GameInfoPresenter(GameInfoView view, SecretSantaApplication application) {
        this.activity = view;
        this.authorizedPersonEmail = application.getAuthorizedPersonEmail();
        this.currentGameId = application.getCurrentGameId();
        this.model = new GameInfoModel(application.getClient());
    }

    public void init() {
        activity.buildGUI();
        startGetPersonGameInfo();
    }

    public void startGetPersonGameInfo() {
        activity.showProgressBar();
        model.getPersonGameByPersonIdAndGameId(authorizedPersonEmail, currentGameId, new Callback<PersonGame>() {
            @Override
            public void onResponse(PersonGame personGame) {
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
        model.getPersonById(receiverEmail, new Callback<Person>() {
            @Override
            public void onResponse(Person receiver) {
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
        model.setWhishlist(authorizedPersonEmail, currentGameId, wish, new Callback<Object>() {
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
        model.setPersonGameActiveFalse(currentGameId, authorizedPersonEmail, new Callback<Object>() {
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
