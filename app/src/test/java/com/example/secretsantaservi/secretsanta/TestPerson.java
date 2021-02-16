package com.example.secretsantaservi.secretsanta;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class TestPerson {
    String santaName;
    String santaEmail;
    Person person;
    Integer gameId;
    String receiverEmail;
    PersonGame personGame;
    ArrayList<String> arrayNaughtylistEmail;//(ArrayList<String>) Arrays.asList(new String[] {"ivan"});
    ArrayList<Integer> gamesId;
    String wish;

    @Before
    public void setUp() {
        santaName = "маша";
        santaEmail = "mary";
        person = new Person(santaEmail, santaName);
        gameId = 1;
        receiverEmail = "iren";
        personGame = new PersonGame(gameId);
        arrayNaughtylistEmail = new ArrayList<String>(Arrays.asList("ivan"));//(ArrayList<String>) Arrays.asList(new String[] {"ivan"});
        gamesId = new ArrayList<Integer>(Arrays.asList(gameId));
        wish = "wish";

        person.addGame(personGame);
    }

    @After
    public void after(){
        santaName = null;
        santaEmail = null;
        person = null;
        gameId = null;
        receiverEmail = null;
        personGame = null;
        arrayNaughtylistEmail =null; //(ArrayList<String>) Arrays.asList(new String[] {"ivan"});
        gamesId = null;
        wish = null;
    }


    @Test
    public void setNameAndGetNameTest() {
        person.setName(santaName);
        Assert.assertEquals(santaName, person.getName());
    }

    @Test
    public void setEmailAndGetEmailTest() {
        person.setEmail(santaEmail);
        Assert.assertEquals(santaEmail, person.getEmail());
    }

    @Test
    public void addGameAndGetPersonGameByIdTest() {
        person.addGame(personGame);
        Assert.assertEquals(personGame, person.getPersonGameByID(1));
    }

    @Test
    public void setReceiverEmailByGameIdAndGetReceiverEmailByGameIdTest() {

        person.setReceiverEmailByGameId(receiverEmail, gameId);
        System.out.println(person.toString());
        Assert.assertEquals(receiverEmail, person.getReceiverEmailByGameId(gameId));
    }

    @Test
    public void setArrayNaughtyListEmailByGameIdAndGetArrayNaughtyListEmailByGameIdTest() {
        person.addGame(personGame);
        person.setArrayNaughtyListEmailByGameId(arrayNaughtylistEmail, gameId);
        Assert.assertEquals(arrayNaughtylistEmail, person.getArrayNaughtyListEmailByGameId(gameId));
    }

    @Test
    public void getIdAllActiveGamesTestWithActive() {
        person.addGame(personGame);
        personGame.setActivity(true);
        Assert.assertEquals(gamesId, person.getIdAllActiveGames());
    }

    @Test
    public void getIdAllActiveGamesTestWithNotActive() {
        person.addGame(personGame);
        ArrayList<Integer> gamesId = new ArrayList<>();
        Assert.assertEquals(gamesId, person.getIdAllActiveGames());
    }


    @Test
    public void addNewPersonGameByIdTest() {
        person.addNewPersonGameById(gameId);
        person.setPersonGameActivityByGameId(gameId, true);
        Assert.assertEquals(gamesId, person.getIdAllActiveGames());
    }

    @Test
    public void setWishlistByGameIdAndGetWhishlistByGameIdTest() {
        person.addGame(personGame);
        person.setWishListByGameId(gameId, wish);
        Assert.assertEquals(wish, person.getWhishListByGameId(gameId));
    }

    @Test
    public void deletePersonGameByGameIdTest() {
        person.addGame(personGame);
        person.deletePersonGameByGameId(gameId);
        Assert.assertEquals(null, person.getPersonGameByID(gameId));
    }

    @Test
    public void setGamesAndGetGames() {
        HashMap<Integer, PersonGame> personGameHashMap = new HashMap<>();
        personGameHashMap.put(personGame.getGameId(), personGame);
        person.setGames(personGameHashMap);
        Assert.assertEquals(personGameHashMap, person.getGames());
    }
}
