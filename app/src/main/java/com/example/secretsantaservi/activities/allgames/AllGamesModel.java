package com.example.secretsantaservi.activities.allgames;

import io.swagger.client.secretsantaclient.ApiWithMyCallbackInterface;
import io.swagger.client.secretsantaclient.MyCallback;
import secretsantamodel.Person;

import java.util.ArrayList;
import java.util.Collections;

public class AllGamesModel {
    private final ApiWithMyCallbackInterface client;

    public AllGamesModel(ApiWithMyCallbackInterface client) {
        this.client = client;
    }

    public void getIdAllActiveGamesByPersonId(String email, MyCallback<ArrayList<Integer>> myCallback) {
        client.getPersonById(email, new MyCallback<Person>() {

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

    public void getNewID(MyCallback<Integer> myCallback){
        client.getNewID(new MyCallback<Integer>() {

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
