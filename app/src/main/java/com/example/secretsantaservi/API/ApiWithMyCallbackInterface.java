package com.example.secretsantaservi.API;

import com.example.secretsantaservi.secretsanta.Person;
import com.example.secretsantaservi.secretsanta.PersonGame;
import com.example.secretsantaservi.secretsanta.SecretSantaGame;

import java.util.ArrayList;
import java.util.HashMap;

public interface ApiWithMyCallbackInterface {

    //Persons
    void getAllPersons(MyCallback<HashMap<String, Person>> myCallback);

    void addPerson(Person person, MyCallback<Object> myCallback);

    void getPersonById(String email, MyCallback<Person> myCallback);

    void replacePerson(Person person, String email, MyCallback<Object> myCallback);

    void deletePerson(String email, MyCallback<Object> myCallback);

    void getHMWithPersonsInfo(Integer gameId, MyCallback<HashMap<String, String>> myCallback); //возможно лучше вернуть Persons???

    void getPersonsByGameId(Integer gameId, MyCallback<HashMap<String, Person>> myCallback);

    //Person games
    void addPersonGameToPerson(String email, PersonGame personGame, MyCallback<Object> myCallback);

    void deletePersonGameFromPerson(Integer gameId, String email, MyCallback<Object> myCallback); //подумать

    void setNaughtyList(String email, Integer gameId, ArrayList<String> arraylist, MyCallback<Object> myCallback);

    void setReceiver(String emailSanta, Integer gameId, String emailReceiver, MyCallback<Object> myCallback);

    void setWhishlist(String email, Integer gameId, String wish, MyCallback<Object> myCallback);

    void setPersonGameActive(Integer gameId, String email, Boolean activity, MyCallback<Object> myCallback);

    //Games
    void addGame(SecretSantaGame game, MyCallback<Object> myCallback);

    void getGameById(int id, MyCallback<SecretSantaGame> myCallback);

    void deleteGame(Integer gameId, MyCallback<Object> myCallback);

    void getNewID(MyCallback<Integer> myCallback);

    void addPersonInGame(Integer gameId, String email, MyCallback<Object> myCallback);

    void deletePersonFromGame(Integer gameId, String email, MyCallback<Object> myCallback);

    void setGamePlayed(Integer gameId, Boolean activity, MyCallback<Object> myCallback);

    void startToss(Integer gameId, MyCallback<Object> myCallback);



    //TODO test
    void setGamesByPersonId(String email, HashMap<Integer, PersonGame> games);
}
