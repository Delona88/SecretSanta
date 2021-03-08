package io.swagger.client.secretsantaclient;

import secretsantamodel.*;

import java.util.ArrayList;
import java.util.HashMap;

public interface ApiWithCallbackInterface {

    //Persons
    void getAllPersons(Callback<HashMap<String, Person>> myCallback);

    void addPerson(Person person, Callback<Object> myCallback);

    void getPersonById(String email, Callback<Person> myCallback);

    void replacePerson(Person person, String email, Callback<Object> myCallback);

    void deletePerson(String email, Callback<Object> myCallback);

    void getHMWithPersonsInfo(Integer gameId, Callback<HashMap<String, String>> myCallback); //возможно лучше вернуть Persons???

    void getPersonsByGameId(Integer gameId, Callback<HashMap<String, Person>> myCallback);

    //Person games
    void addPersonGameToPerson(String email, PersonGame personGame, Callback<Object> myCallback);

    void deletePersonGameFromPerson(Integer gameId, String email, Callback<Object> myCallback); //подумать

    void setNaughtyList(String email, Integer gameId, ArrayList<String> arraylist, Callback<Object> myCallback);

    void setReceiver(String emailSanta, Integer gameId, String emailReceiver, Callback<Object> myCallback);

    void setWhishlist(String email, Integer gameId, String wish, Callback<Object> myCallback);

    void setPersonGameActive(Integer gameId, String email, Boolean activity, Callback<Object> myCallback);

    //Games
    void addGame(SecretSantaGame game, Callback<Object> myCallback);

    void getGameById(int id, Callback<SecretSantaGame> myCallback);

    void deleteGame(Integer gameId, Callback<Object> myCallback);

    void getNewID(Callback<Integer> myCallback);

    void addPersonInGame(Integer gameId, String email, Callback<Object> myCallback);

    void deletePersonFromGame(Integer gameId, String email, Callback<Object> myCallback);

    void setGamePlayed(Integer gameId, Boolean activity, Callback<Object> myCallback);

    void startToss(Integer gameId, Callback<Object> myCallback);

}
