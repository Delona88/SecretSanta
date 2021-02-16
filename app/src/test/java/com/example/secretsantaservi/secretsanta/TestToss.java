package com.example.secretsantaservi.secretsanta;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class TestToss {
    Toss toss;
    String email1;
    Person person1;
    ArrayList<String> arrayNaughtylistEmail1;
    PersonGame personGame1;
    HashMap<Integer, PersonGame> personGames1;
    String email2;
    Person person2;
    ArrayList<String> arrayNaughtylistEmail2;
    PersonGame personGame2;
    HashMap<Integer, PersonGame> personGames2;
    String email3;
    Person person3;
    ArrayList<String> arrayNaughtylistEmail3;
    PersonGame personGame3;
    HashMap<Integer, PersonGame> personGames3;
    String email4;
    Person person4;
    ArrayList<String> arrayNaughtylistEmail4;
    PersonGame personGame4;
    HashMap<Integer, PersonGame> personGames4;
    String email5;
    Person person5;
    PersonGame personGame5;
    HashMap<Integer, PersonGame> personGames5;


    ArrayList<Person> participants;
    ArrayList<Person> participantsToss;
    int gameId;

    @Before
    public void setUp() {
        /**
         * тестовые данные: 4 игрока c одной игрой
         * 1 и 2 не могут дарить друг другу
         * 3 и 4 не могут дарить друг другу
         */

        gameId = 1;

        personGame1 = new PersonGame(gameId);
        personGames1 = new HashMap<>();
        personGames1.put(personGame1.getGameId(), personGame1);

        email1 = "email1";
        person1 = new Person(email1, "name1", personGames1);
        arrayNaughtylistEmail1 = new ArrayList<>(Arrays.asList("email2"));
        person1.setArrayNaughtyListEmailByGameId(arrayNaughtylistEmail1, gameId);

        personGame2 = new PersonGame(gameId);
        personGames2 = new HashMap<>();
        personGames2.put(personGame2.getGameId(), personGame2);

        email2 = "email2";
        person2 = new Person(email2, "name2", personGames2);
        arrayNaughtylistEmail2 = new ArrayList<>(Arrays.asList("email1"));
        person2.setArrayNaughtyListEmailByGameId(arrayNaughtylistEmail2, gameId);

        personGame3 = new PersonGame(gameId);
        personGames3 = new HashMap<>();
        personGames3.put(personGame3.getGameId(), personGame3);

        email3 = "email3";
        person3 = new Person(email3, "name3", personGames3);
        arrayNaughtylistEmail3 = new ArrayList<>(Arrays.asList("email4"));
        person3.setArrayNaughtyListEmailByGameId(arrayNaughtylistEmail3, gameId);

        personGame4 = new PersonGame(gameId);
        personGames4 = new HashMap<>();
        personGames4.put(personGame4.getGameId(), personGame4);
        personGames4.put(2, new PersonGame(2)); //не должно сработать

        email4 = "email4";
        person4 = new Person(email4, "name4", personGames4);
        arrayNaughtylistEmail4 = new ArrayList<>(Arrays.asList("email3"));
        person4.setArrayNaughtyListEmailByGameId(arrayNaughtylistEmail4, gameId);


        personGame5 = new PersonGame(gameId);
        personGames5 = new HashMap<>();
        personGames5.put(personGame5.getGameId(), personGame5);

        email5 = "email5";
        person5 = new Person(email5, "name5", personGames5);

        participants = new ArrayList<>(Arrays.asList(person1, person2, person3, person4, person5));

        toss = new Toss(participants, gameId);
    }

    @Test
    public void testTossReceiverEqualsSanta() throws BadConditionsException {
        participantsToss = toss.fillReceiversAndReturnParticipantsArray();
        for (Person person : participantsToss) {
            Assert.assertNotEquals(person.getEmail(), person.getReceiverEmailByGameId(gameId));
        }
    }

    @Test
    public void testTossReceiverInSantaNaughtylist() throws BadConditionsException {
        participantsToss = toss.fillReceiversAndReturnParticipantsArray();
        for (Person person : participantsToss) {
            ArrayList<String> arrayNaughtyist = person.getArrayNaughtyListEmailByGameId(gameId);
            Assert.assertFalse(arrayNaughtyist.contains(person.getReceiverEmailByGameId(gameId)));
        }
    }

    @Test
    public void testTossThrowsException() {
        System.out.println("BadConditionsException expected");
        arrayNaughtylistEmail1 = new ArrayList<>(Arrays.asList("email2","email3","email4","email5"));
        person1.setArrayNaughtyListEmailByGameId(arrayNaughtylistEmail1, gameId);
        try {
            participantsToss = toss.fillReceiversAndReturnParticipantsArray();
        } catch (BadConditionsException badConditions) {
            badConditions.printStackTrace();
        }
    }

}
