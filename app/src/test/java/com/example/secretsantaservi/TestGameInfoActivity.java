package com.example.secretsantaservi;

import com.example.secretsantaservi.androidrepository.repositoriesfactory.fortests.HMRepositoriesFactory;
import com.example.secretsantaservi.api.ApiWithCallback;
import io.swagger.client.secretsantaclient.ApiWithCallbackInterface;
import io.swagger.client.secretsantaclient.Callback;

import com.example.secretsantaservi.activities.gameinfo.GameInfoActivity;
import com.example.secretsantaservi.activities.gameinfo.GameInfoPresenter;
import com.example.secretsantaservi.activities.gameinfo.GameInfoView;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;

import secretsantamodel.*;

public class TestGameInfoActivity {

    GameInfoPresenter presenter;
    GameInfoView activity;

    SecretSantaApplication application;

    String personEmail;
    Person person;
    Integer gameId;
    String receiverEmail;
    Boolean active;
    PersonGame personGame;
    ArrayList<String> arrayNaughtylistEmail;
    ArrayList<Integer> gamesId;
    String wish;
    SecretSantaGame secretSantaGame;
    Person receiver;
    PersonGame receiverPersonGame;
    String receiverWish;
    String receiverInfo;

    @Before
    public void setup() {
        personEmail = "mary";
        person = new Person(personEmail, personEmail);
        receiverEmail = "iren";
        gameId = 1;

        wish = "wish";
        active = true;
        arrayNaughtylistEmail = new ArrayList<>(Arrays.asList("ivan"));
        personGame = new PersonGame(gameId, receiverEmail, wish, active, arrayNaughtylistEmail);
        person.addGame(personGame);

        gamesId = new ArrayList<>(Arrays.asList(gameId));
        secretSantaGame = new SecretSantaGame(gameId);

        receiverWish = "receiverWish";
        receiverPersonGame = new PersonGame(gameId);
        receiverPersonGame.setWishlist(receiverWish);
        receiver = new Person(receiverEmail, receiverEmail);
        receiver.addGame(receiverPersonGame);

        receiverInfo = receiver.getName() + "(" + receiver.getEmail() + ")" + "\n";

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
        client.addPerson(receiver, new Callback<Object>() {
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
        application.setCurrentGameId(gameId);

        activity = Mockito.mock(GameInfoActivity.class);

        presenter = new GameInfoPresenter(activity, application);
    }

    @Test
    public void testCreatePresenter() {
        Assert.assertNotNull(presenter);
    }

    @Test
    public void testStartGetPersonGameInfo() {
        presenter.startGetPersonGameInfo();
        Mockito.verify(activity).showProgressBar();
        Mockito.verify(activity).addPersonGameInfo("" + personGame.getGameId());
        Mockito.verify(activity).addWishInfo(personGame.getWishlist());
    }

    @Test
    public void testStartGetAndAddReceiverInfo() {
        presenter.startGetAndAddReceiverInfo(receiverEmail);
        Mockito.verify(activity).addReceiverWishInfo(receiver.getWhishListByGameId(gameId));
        Mockito.verify(activity).addReceiverInfo(receiverInfo);
        Mockito.verify(activity).hideProgressBar();
    }

    @Test
    public void testStartGetNullReceiverInfo() {
        presenter.startGetAndAddReceiverInfo(null);
        receiverInfo = "Ваш получатель уже удалился из игры" + "\n";
        Mockito.verify(activity).addReceiverInfo(receiverInfo);
        Mockito.verify(activity).hideProgressBar();
    }

    @Test
    public void testStartSetWishlist() {
        presenter.startSetWishlist(wish);
        Mockito.verify(activity).showProgressBar();
        Mockito.verify(activity).showToastWishSend();
        Mockito.verify(activity).hideProgressBar();
    }

    @Test
    public void testStartDeleteGame() {
        presenter.startDeleteGame();
        Mockito.verify(activity).showProgressBar();
        Mockito.verify(activity).showToastGameDelete();
        Mockito.verify(activity).hideProgressBar();
    }
}
