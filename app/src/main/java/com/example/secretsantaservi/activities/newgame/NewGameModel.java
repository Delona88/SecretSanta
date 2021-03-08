package com.example.secretsantaservi.activities.newgame;

import io.swagger.client.secretsantaclient.ApiWithCallbackInterface;
import io.swagger.client.secretsantaclient.Callback;

import java.util.HashMap;

public class NewGameModel {
    ApiWithCallbackInterface client;

    public NewGameModel(ApiWithCallbackInterface client) {
        this.client = client;
    }

    public void getHMWithPersonsInfo(Integer gameId, Callback<HashMap<String, String>> myCallback) {
        client.getHMWithPersonsInfo(gameId, new Callback<HashMap<String, String>>() {
            @Override
            public void onResponse(HashMap<String, String> response) {
                myCallback.onResponse(response);
            }

            @Override
            public void onFailure(Throwable t) {
                myCallback.onFailure(t);
            }
        });
    }

    public void setGamePlayedTrue(Integer gameId, Callback<Object> myCallback) {

        client.setGamePlayed(gameId, true, new Callback<Object>() {
            @Override
            public void onResponse(Object response) {
                myCallback.onResponse(response);
            }

            @Override
            public void onFailure(Throwable t) {
                myCallback.onFailure(t);
            }
        });

    }

    public void startToss(Integer gameId, Callback<Object> myCallback) {
        client.startToss(gameId, new Callback<Object>() {
            @Override
            public void onResponse(Object responseCode) {
                myCallback.onResponse(responseCode);
            }

            @Override
            public void onFailure(Throwable t) {
                myCallback.onFailure(t);
            }
        });
    }
}
