package repositoryapi;

import secretsantamodel.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;


public interface SecretSantaApiInterface {
    //Persons
    ConcurrentHashMap<String, Person> getAllPersons();

    void addPerson(Person person);

    Person getPersonById(String id);

    void replacePerson(String email, Person person);

    void deletePerson(String email);

    HashMap<String, String> getHMWithPersonsInfo(Integer gameId);

    HashMap<String, Person> getPersonsByGameId(Integer gameId);

    //Person games
    void addPersonGameToPerson(String email, PersonGame personGame);

    void deletePersonGameFromPerson(Integer gameId, String email);

    void setNaughtyList(String email, Integer gameId, ArrayList<String> arraylist);

    void setReceiver(String emailSanta, Integer gameId, String emailReceiver);

    void setWhishlist(String email, Integer gameId, String wish);

    void setPersonGameActive(Integer gameId, String email, Boolean activity);

    //Games
    void addGame(SecretSantaGame game);

    SecretSantaGame getGameById(int id);

    void deleteGame(Integer gameId);

    int getNewID();

    void addPersonInGame(Integer gameId, String email);

    void deleteEmailFromGame(Integer gameId, String email);

    void setGamePlayed(Integer gameId, Boolean activity);

    void startToss(Integer gameId) throws BadConditionsException;


}
