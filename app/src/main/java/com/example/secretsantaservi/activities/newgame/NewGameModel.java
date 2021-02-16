package com.example.secretsantaservi.activities.newgame;

import com.example.secretsantaservi.API.ApiWithMyCallbackInterface;
import com.example.secretsantaservi.API.MyCallback;
import com.example.secretsantaservi.R;

import java.util.HashMap;

public class NewGameModel {
    ApiWithMyCallbackInterface client;

    public NewGameModel(ApiWithMyCallbackInterface client) {
        this.client = client;
    }

    public void getHMWithPersonsInfo(Integer gameId, MyCallback<HashMap<String, String>> myCallback) {
        client.getHMWithPersonsInfo(gameId, new MyCallback<HashMap<String, String>>() {
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

    public void setGamePlayedTrue(Integer gameId, MyCallback<Object> myCallback) {

        client.setGamePlayed(gameId, true, new MyCallback<Object>() {
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

    public void startToss(Integer gameId, MyCallback<Object> myCallback) {
        client.startToss(gameId, new MyCallback<Object>() {
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
