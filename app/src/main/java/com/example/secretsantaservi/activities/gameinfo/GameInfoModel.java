package com.example.secretsantaservi.activities.gameinfo;

import io.swagger.client.secretsantaclient.ApiWithCallbackInterface;
import io.swagger.client.secretsantaclient.Callback;
import secretsantamodel.Person;
import secretsantamodel.PersonGame;


public class GameInfoModel {
    ApiWithCallbackInterface client;

    public GameInfoModel(ApiWithCallbackInterface client) {
        this.client = client;
    }

    public void getPersonGameByPersonIdAndGameId(String email, Integer gameId, Callback<PersonGame> myCallback) {
        client.getPersonById(email, new Callback<Person>() {
            @Override
            public void onResponse(Person person) {
                PersonGame personGame = person.getGameByID(gameId);
                myCallback.onResponse(personGame);
            }

            @Override
            public void onFailure(Throwable t) {
                myCallback.onFailure(t);
            }
        });
    }

    public void getPersonById(String email, Callback<Person> myCallback) {
        client.getPersonById(email, new Callback<Person>() {
            @Override
            public void onResponse(Person response) {
                myCallback.onResponse(response);
            }

            @Override
            public void onFailure(Throwable t) {
                myCallback.onFailure(t);
            }
        });
    }

    public void setWhishlist(String email, Integer gameId, String wish, Callback<Object> myCallback) {
        client.setWhishlist(email, gameId, wish, new Callback<Object>() {
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

    public void setPersonGameActiveFalse(Integer gameId, String email, Callback<Object> myCallback) {
        client.setPersonGameActive(gameId, email, false, new Callback<Object>() {
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
}
