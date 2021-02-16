package com.example.secretsantaservi.activities.gameinfo;

import com.example.secretsantaservi.API.ApiWithMyCallbackInterface;
import com.example.secretsantaservi.API.MyCallback;
import com.example.secretsantaservi.secretsanta.Person;
import com.example.secretsantaservi.secretsanta.PersonGame;

public class GameInfoModel {
    ApiWithMyCallbackInterface client;

    public GameInfoModel(ApiWithMyCallbackInterface client) {
        this.client = client;
    }

    public void getPersonGameByPersonIdAndGameId(String email, Integer gameId, MyCallback<PersonGame> myCallback) {
        client.getPersonById(email, new MyCallback<Person>() {
            @Override
            public void onResponse(Person response) {
                Person person = response;
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
