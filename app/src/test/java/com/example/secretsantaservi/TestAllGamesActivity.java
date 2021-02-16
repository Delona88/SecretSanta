package com.example.secretsantaservi;

import com.example.secretsantaservi.API.ApiWithMyCallbackInterface;
import com.example.secretsantaservi.API.MyCallback;
import com.example.secretsantaservi.API.testAPI.RepositoryHMWithMyCallback;
import com.example.secretsantaservi.activities.allgames.AllGamesActivity;
import com.example.secretsantaservi.activities.allgames.AllGamesPresenter;
import com.example.secretsantaservi.activities.allgames.AllGamesView;
import com.example.secretsantaservi.secretsanta.Person;
import com.example.secretsantaservi.secretsanta.PersonGame;
import com.example.secretsantaservi.secretsanta.SecretSantaGame;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;

public class TestAllGamesActivity {
    AllGamesPresenter presenter;
    AllGamesView activity;
    SecretSantaApplication application;

    //тестовые данные для клиента
    String personName;
    String personEmail;
    Person person;
    Integer gameId;
    String receiverEmail;
    PersonGame personGame;
    ArrayList<String> arrayNaughtylistEmail;//(ArrayList<String>) Arrays.asList(new String[] {"ivan"});
    ArrayList<Integer> gamesId;
    String wish;
    SecretSantaGame game;

    @Before
    public void setup() {
        //тестовые данные - person + personGame
        personEmail = "mary";
        person = new Person(personEmail, personEmail);
        gameId = 1;
        receiverEmail = "iren";
        personGame = new PersonGame(gameId);
        arrayNaughtylistEmail = new ArrayList<>(Arrays.asList("ivan"));//(ArrayList<String>) Arrays.asList(new String[] {"ivan"});
        gamesId = new ArrayList<>(Arrays.asList(gameId));
        wish = "wish";
        personGame.setActivity(true);
        person.addGame(personGame);
        game = new SecretSantaGame(gameId);

        //тестовые данные добавляются в клиентский HM
        ApiWithMyCallbackInterface client = new RepositoryHMWithMyCallback();
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

        //для инициализации presenter нужены activity и application
        application = new SecretSantaApplication();
        application.setClient(client);

        //в application должен быть авторизованный пользователь
        application.setAuthorizedPersonEmail(personEmail);

        //Mockito для View
        activity = Mockito.mock(AllGamesActivity.class);

        // инициализация presenter
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
