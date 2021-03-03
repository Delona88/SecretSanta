package com.example.secretsantaservi.activities.gameinfo;

import io.swagger.client.secretsantaclient.ApiWithMyCallbackInterface;
import io.swagger.client.secretsantaclient.MyCallback;
import secretsantamodel.Person;
import secretsantamodel.PersonGame;


public class GameInfoModel {
    ApiWithMyCallbackInterface client;

    public GameInfoModel(ApiWithMyCallbackInterface client) {
        this.client = client;
    }

    public void getPersonGameByPersonIdAndGameId(String email, Integer gameId, MyCallback<PersonGame> myCallback) {
        client.getPersonById(email, new MyCallback<Person>() {
            @Override
            public void onResponse(Person person) {
                PersonGame personGame = person.getPersonGameByID(gameId);
                myCallback.onResponse(personGame);
            }

            @Override
            public void onFailure(Throwable t) {
                myCallback.onFailure(t);
            }
        });
    }

    public void getPersonById(String email, MyCallback<Person> myCallback) {
        client.getPersonById(email, new MyCallback<Person>() {
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

    public void setWhishlist(String email, Integer gameId, String wish, MyCallback<Object> myCallback) {
        client.setWhishlist(email, gameId, wish, new MyCallback<Object>() {
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

    public void setPersonGameActiveFalse(Integer gameId, String email, MyCallback<Object> myCallback) {
        client.setPersonGameActive(gameId, email, false, new MyCallback<Object>() {
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
