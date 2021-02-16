package com.example.secretsantaservi;

import com.example.secretsantaservi.API.ApiWithMyCallbackInterface;
import com.example.secretsantaservi.API.MyCallback;
import com.example.secretsantaservi.API.testAPI.RepositoryHMWithMyCallback;
import com.example.secretsantaservi.activities.gameinfo.GameInfoActivity;
import com.example.secretsantaservi.activities.gameinfo.GameInfoModel;
import com.example.secretsantaservi.activities.gameinfo.GameInfoPresenter;
import com.example.secretsantaservi.activities.gameinfo.GameInfoView;
import com.example.secretsantaservi.secretsanta.Person;
import com.example.secretsantaservi.secretsanta.PersonGame;
import com.example.secretsantaservi.secretsanta.SecretSantaGame;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;

public class TestGameInfoActivity {

    GameInfoPresenter presenter;
    GameInfoView activity;
    GameInfoModel model;

    SecretSantaApplication application;
    SecretSantaApplication applicationSpy;

    //тестовые данные для клиента
    String personName;
    String personEmail;
    Person person;
    Integer gameId;
    String receiverEmail;
    Boolean active;
    PersonGame personGame;
    ArrayList<String> arrayNaughtylistEmail;//(ArrayList<String>) Arrays.asList(new String[] {"ivan"});
    ArrayList<Integer> gamesId;
    String wish;
    SecretSantaGame game;
    Person receiver;
    PersonGame receiverPersonGame;
    String receiverWish;
    String receiverInfo;

    @Before
    public void setup() {
        //тестовые данные - person + personGame
        personEmail = "mary";
        person = new Person(personEmail, personEmail);
        receiverEmail = "iren";
        gameId = 1;

        wish = "wish";
        active = true;
        arrayNaughtylistEmail = new ArrayList<String>(Arrays.asList("ivan"));//(ArrayList<String>) Arrays.asList(new String[] {"ivan"});
        personGame = new PersonGame(gameId, receiverEmail, wish, active, arrayNaughtylistEmail);
        person.addGame(personGame);

        gamesId = new ArrayList<Integer>(Arrays.asList(gameId));
        game = new SecretSantaGame(gameId);

        receiverWish = "receiverWish";
        receiverPersonGame = new PersonGame(gameId);
        receiverPersonGame.setWishlist(receiverWish);
        receiver = new Person(receiverEmail, receiverEmail);
        receiver.addGame(receiverPersonGame);

        receiverInfo = receiver.getName() + "(" + receiver.getEmail() + ")" + "\n";


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
        client.addPerson(receiver, new MyCallback<Object>() {
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

        //в application должен быть авторизованный пользователь и игра
        application.setAuthorizedPersonEmail(personEmail);
        application.setCurrentGameId(gameId);


        //Mockito для View
        activity = Mockito.mock(GameInfoActivity.class);

        //applicationSpy = Mockito.spy(application);

        // инициализация presenter
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
