package com.example.secretsantaservi.activities.allgames;

import io.swagger.client.secretsantaclient.ApiWithCallbackInterface;
import io.swagger.client.secretsantaclient.Callback;
import secretsantamodel.Person;

import java.util.ArrayList;
import java.util.Collections;

public class AllGamesModel {
    private final ApiWithCallbackInterface client;

    public AllGamesModel(ApiWithCallbackInterface client) {
        this.client = client;
    }

    public void getIdAllActiveGamesByPersonId(String email, Callback<ArrayList<Integer>> myCallback) {
        client.getPersonById(email, new Callback<Person>() {

            @Override
            public void onResponse(Person response) {
                ArrayList<Integer> idAllActiveGames = response.getIdAllActiveGames();
                Collections.sort(idAllActiveGames);
                myCallback.onResponse(idAllActiveGames);
            }

            @Override
            public void onFailure(Throwable t) {
                myCallback.onFailure(t);
            }
        });
    }

    public void getNewID(Callback<Integer> myCallback){
        client.getNewID(new Callback<Integer>() {

            @Override
            public void onResponse(Integer response) {
                myCallback.onResponse(response);
            }

            @Override
            public void onFailure(Throwable t) {
                myCallback.onFailure(t);
            }
        });
    }
}
