package com.example.secretsantaservi;

import com.example.secretsantaservi.androidrepository.repositoriesfactory.fortests.HMRepositoriesFactory;
import com.example.secretsantaservi.api.ApiWithCallback;
import io.swagger.client.secretsantaclient.ApiWithCallbackInterface;
import io.swagger.client.secretsantaclient.Callback;
import com.example.secretsantaservi.activities.newgame.NewGameActivity;
import com.example.secretsantaservi.activities.newgame.NewGamePresenter;
import com.example.secretsantaservi.activities.newgame.NewGameView;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import secretsantamodel.*;

public class TestNewGameActivity {
    NewGameView activity;
    NewGamePresenter presenter;
    ApiWithCallbackInterface client;

    SecretSantaApplication application;

    String personEmail;
    Person person;
    Integer gameId;
    String receiverEmail;
    Boolean active;
    PersonGame personGame;
    ArrayList<String> arrayNaughtylistEmail;
    HashMap<Integer, PersonGame> personGames = new HashMap<>();
    ArrayList<Integer> gamesId;
    String wish;

    Person receiver;
    PersonGame receiverPersonGame;
    HashMap<Integer, PersonGame> receiverGames = new HashMap<>();
    String receiverWish;
    String receiverInfo;

    SecretSantaGame secretSantaGame;
    ArrayList<String> emails;

    HashMap<String, String> infoHM;

    @Before
    public void setup() {
        personEmail = "mary";
        gameId = 1;
        receiverEmail = "iren";
        wish = "wish";
        active = true;
        arrayNaughtylistEmail = new ArrayList<>(Arrays.asList("ivan"));
        personGame = new PersonGame(gameId, receiverEmail, wish, active, arrayNaughtylistEmail);
        personGames.put(personGame.getGameId(), personGame);
        person = new Person(personEmail, personEmail, personGames);

        gamesId = new ArrayList<>(Arrays.asList(gameId));
        secretSantaGame = new SecretSantaGame(gameId);
        emails = new ArrayList<>(Arrays.asList("iren", "mary"));
        secretSantaGame.setArrayParticipantsEmail(emails);

        receiverWish = "receiverWish";
        receiverPersonGame = new PersonGame(gameId);
        receiverPersonGame.setWishlist(receiverWish);
        receiverGames.put(receiverPersonGame.getGameId(), receiverPersonGame);
        receiver = new Person(receiverEmail, receiverEmail, receiverGames);

        receiverInfo = "Ваш получатель - " + receiver.getName() + "(" + receiver.getEmail() + ")" + "\n";
        String whishlist = receiver.getWhishListByGameId(gameId);
        if (whishlist != null) {
            receiverInfo += "Пожелание от получателя - " + whishlist + "\n";
        }

        client = new ApiWithCallback(new HMRepositoriesFactory());
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
        client.addPerson(receiver, new Callback<Object>() {
            @Override
            public void onResponse(Object response) {

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
        client.getHMWithPersonsInfo(gameId, new Callback<HashMap<String, String>>() {
            @Override
            public void onResponse(HashMap<String, String> response) {
                infoHM = response;
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

        application = new SecretSantaApplication();
        application.setClient(client);

        application.setAuthorizedPersonEmail(personEmail);
        application.setCurrentGameId(gameId);

        activity = Mockito.mock(NewGameActivity.class);

        presenter = new NewGamePresenter(activity, application);
    }

    @Test
    public void testStartGetHMWithPersonsInfoAndCreateButtons() {
        presenter.startGetHMWithPersonsInfoAndCreateButtons();
        Mockito.verify(activity).showProgressBar();
        Mockito.verify(activity).createButtonsWithParticipantsInfo(infoHM);
        Mockito.verify(activity).hideProgressBar();
    }

    @Test
    public void testStartSetGamePlayed() {
        presenter.startSetGamePlayed();
        Mockito.verify(activity).showProgressBar();

        client.getGameById(gameId, new Callback<SecretSantaGame>() {
            @Override
            public void onResponse(SecretSantaGame response) {
                Assert.assertTrue(response.isPlayed());
            }

            @Override
            public void onFailure(Throwable t) {
            }
        });
        //работает для HM
        //Assert.assertTrue(game.isPlayed());
        Mockito.verify(activity).showToastParticipantAdded();
        Mockito.verify(activity).hideProgressBar();
    }

    @Test
    public void testStartTossOk() {
        presenter.startToss();
        Mockito.verify(activity).showProgressBar();
        client.getPersonById(personEmail, new Callback<Person>() {
            @Override
            public void onResponse(Person response) {
                String clientReceiverEmail = response.getReceiverEmailByGameId(gameId);
                Assert.assertEquals(receiverEmail, clientReceiverEmail);
                client.getPersonById(clientReceiverEmail, new Callback<Person>() {
                    @Override
                    public void onResponse(Person response) {
                        Assert.assertEquals(personEmail, response.getReceiverEmailByGameId(gameId));
                    }

                    @Override
                    public void onFailure(Throwable t) {
                    }
                });
            }

            @Override
            public void onFailure(Throwable t) {
            }
        });
        //работает для HM
        //Assert.assertEquals(receiverEmail, person.getReceiverEmailByGameId(gameId));
        //Assert.assertEquals(personEmail, receiver.getReceiverEmailByGameId(gameId));
        Mockito.verify(activity).showToastGameCreate(gameId);
        Mockito.verify(activity).hideProgressBar();
        Mockito.verify(activity).finish();
    }

    @Test
    public void testStartTossBadConditions() {
        System.out.println("BadConditionsException expected");
        arrayNaughtylistEmail.add(receiverEmail);
        person.setArrayNaughtyListEmailByGameId(arrayNaughtylistEmail, gameId);
        presenter.startToss();
        Mockito.verify(activity).showProgressBar();
        Mockito.verify(activity).showToastBadConditions();
        Mockito.verify(activity).hideProgressBar();
    }

}
