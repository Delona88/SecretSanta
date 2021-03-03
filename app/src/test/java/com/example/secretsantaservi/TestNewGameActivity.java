package com.example.secretsantaservi;

import com.example.secretsantaservi.api.ApiWithMyCallback;
import io.swagger.client.secretsantaclient.ApiWithMyCallbackInterface;
import io.swagger.client.secretsantaclient.MyCallback;
import com.example.secretsantaservi.activities.newgame.NewGameActivity;
import com.example.secretsantaservi.activities.newgame.NewGameModel;
import com.example.secretsantaservi.activities.newgame.NewGamePresenter;
import com.example.secretsantaservi.activities.newgame.NewGameView;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import repositoryapi.repositoriesfactory.HMRepositoriesFactory;
import secretsantamodel.*;

public class TestNewGameActivity {
    NewGameView activity;
    NewGamePresenter presenter;
    NewGameModel model;
    ApiWithMyCallbackInterface client;

    SecretSantaApplication application;

    //тестовые данные для клиента
    String personEmail;
    Person person;
    Integer gameId;
    String receiverEmail;
    Boolean active;
    PersonGame personGame;
    ArrayList<String> arrayNaughtylistEmail;//(ArrayList<String>) Arrays.asList(new String[] {"ivan"});
    HashMap<Integer, PersonGame> personGames = new HashMap<>();
    ArrayList<Integer> gamesId;
    String wish;

    Person receiver;
    PersonGame receiverPersonGame;
    HashMap<Integer, PersonGame> receiverPersonGames = new HashMap<>();
    String receiverWish;
    String receiverInfo;

    SecretSantaGame game;
    ArrayList<String> emails;

    HashMap<String, String> infoHM; //тестирование info для активити

    @Before
    public void setup() {
        //тестовые данные - person + personGame
        personEmail = "mary";
        gameId = 1;
        receiverEmail = "iren";
        wish = "wish";
        active = true;
        arrayNaughtylistEmail = new ArrayList<>(Arrays.asList("ivan"));//(ArrayList<String>) Arrays.asList(new String[] {"ivan"});
        personGame = new PersonGame(gameId, receiverEmail, wish, active, arrayNaughtylistEmail);
        personGames.put(personGame.getGameId(), personGame);
        person = new Person(personEmail, personEmail, personGames);

        gamesId = new ArrayList<>(Arrays.asList(gameId));
        game = new SecretSantaGame(gameId);
        emails = new ArrayList<>(Arrays.asList("iren", "mary"));
        game.setArrayParticipantsEmail(emails);

        receiverWish = "receiverWish";
        receiverPersonGame = new PersonGame(gameId);
        receiverPersonGame.setWishlist(receiverWish);
        receiverPersonGames.put(receiverPersonGame.getGameId(), receiverPersonGame);
        receiver = new Person(receiverEmail, receiverEmail, receiverPersonGames);
        //из GamesInfoPresenter
        receiverInfo = "Ваш получатель - " + receiver.getName() + "(" + receiver.getEmail() + ")" + "\n";
        String whishlist = receiver.getWhishListByGameId(gameId);
        if (whishlist != null) {
            receiverInfo += "Пожелание от получателя - " + whishlist + "\n";
        }

        //тестовые данные добавляются в клиентский HM
        client = new ApiWithMyCallback(new HMRepositoriesFactory());
        client.addPerson(person, new MyCallback<Object>() {
            @Override
            public void onResponse(Object response) {
            }

            @Override
            public void onFailure(Throwable t) {
            }
        });
        client.addGame(game, new MyCallback<Object>() {
            @Override
            public void onResponse(Object response) {
            }

            @Override
            public void onFailure(Throwable t) {
            }
        });
        client.addPerson(receiver, new MyCallback<Object>() {
            @Override
            public void onResponse(Object response) {

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
        client.getHMWithPersonsInfo(gameId, new MyCallback<HashMap<String, String>>() {
            @Override
            public void onResponse(HashMap<String, String> response) {
                infoHM = response;
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

        //для инициализации presenter нужены activity и application
        application = new SecretSantaApplication();
        application.setClient(client);

        //в application должен быть авторизованный пользователь и игра
/*        application.getAuthorizedPersonController().setPersonId(personEmail);
        application.createNewGameController(gameId);*/
        application.setAuthorizedPersonEmail(personEmail);
        application.setCurrentGameId(gameId);

        //Mockito для View
        activity = Mockito.mock(NewGameActivity.class);

        // инициализация presenter
        presenter = new NewGamePresenter(activity, application);
    }

    @Test
    public void testStartGetHMWithPersonsInfoAndCreateButtons() {
        //presenter = new NewGamePresenter(activity, application);
        presenter.startGetHMWithPersonsInfoAndCreateButtons();
        Mockito.verify(activity).showProgressBar();
        Mockito.verify(activity).createButtonsWithParticipantsInfo(infoHM);
        Mockito.verify(activity).hideProgressBar();
    }

    @Test
    public void testStartSetGamePlayed() {
        //presenter = new NewGamePresenter(activity, application);
        presenter.startSetGamePlayed();
        Mockito.verify(activity).showProgressBar();

        client.getGameById(gameId, new MyCallback<SecretSantaGame>() {
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
        //presenter = new NewGamePresenter(activity, application);
        presenter.startToss();
        Mockito.verify(activity).showProgressBar();
        //проверка жеребъевки из 2 человек
        client.getPersonById(personEmail, new MyCallback<Person>() {
            @Override
            public void onResponse(Person response) {
                String clientReceiverEmail = response.getReceiverEmailByGameId(gameId);
                Assert.assertEquals(receiverEmail, clientReceiverEmail);
                client.getPersonById(clientReceiverEmail, new MyCallback<Person>() {
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
