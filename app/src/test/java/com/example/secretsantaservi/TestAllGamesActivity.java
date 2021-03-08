package com.example.secretsantaservi;

import com.example.secretsantaservi.androidrepository.repositoriesfactory.fortests.HMRepositoriesFactory;
import com.example.secretsantaservi.api.ApiWithCallback;
import io.swagger.client.secretsantaclient.ApiWithCallbackInterface;
import io.swagger.client.secretsantaclient.Callback;

import com.example.secretsantaservi.activities.allgames.AllGamesActivity;
import com.example.secretsantaservi.activities.allgames.AllGamesPresenter;
import com.example.secretsantaservi.activities.allgames.AllGamesView;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;

import secretsantamodel.*;

public class TestAllGamesActivity {
    AllGamesPresenter presenter;
    AllGamesView activity;
    SecretSantaApplication application;

    String personEmail;
    Person person;
    Integer gameId;
    String receiverEmail;
    PersonGame personGame;
    ArrayList<String> arrayNaughtylistEmail;
    ArrayList<Integer> gamesId;
    String wish;
    SecretSantaGame secretSantaGame;

    @Before
    public void setup() {
        personEmail = "mary";
        person = new Person(personEmail, personEmail);
        gameId = 1;
        receiverEmail = "iren";
        personGame = new PersonGame(gameId);
        arrayNaughtylistEmail = new ArrayList<>(Arrays.asList("ivan"));
        gamesId = new ArrayList<>(Arrays.asList(gameId));
        wish = "wish";
        personGame.setActivity(true);
        person.addGame(personGame);
        secretSantaGame = new SecretSantaGame(gameId);

        ApiWithCallbackInterface client = new ApiWithCallback(new HMRepositoriesFactory());
        client.addPerson(person, new Callback<Object>() {
            @Override
            public void onResponse(Object response) {
            }

            @Override
            public void onFailure(Throwable t) {
            }
        });
        client.addGame(secretSantaGame, new Callback<Object>() {
            @Override
            public void onResponse(Object response) {
            }

            @Override
            public void onFailure(Throwable t) {
            }
        });

        application = new SecretSantaApplication();
        application.setClient(client);

        application.setAuthorizedPersonEmail(personEmail);

        activity = Mockito.mock(AllGamesActivity.class);

        presenter = new AllGamesPresenter(activity, application);
    }

    @Test
    public void testCreatePresenter() {
        Assert.assertNotNull(presenter);
    }

    @Test
    public void testStartGetPersonWithIdAllGames() {
        presenter.startGetPersonWithIdAllGames();
        Mockito.verify(activity, Mockito.times(1)).showProgressBar();
        Mockito.verify(activity).createGamesButton(gamesId);
        Mockito.verify(activity, Mockito.times(1)).hideProgressBar();
    }

    @Test
    public void testStartGetNewGameID() {
        presenter.startGetNewGameID();
        Mockito.verify(activity, Mockito.times(1)).showProgressBar();
        Integer newGameId = gameId + 1;
        Assert.assertEquals(newGameId, application.getCurrentGameId());
        Mockito.verify(activity, Mockito.times(1)).hideProgressBar();
        Mockito.verify(activity).goToFillInfo();
    }

}
