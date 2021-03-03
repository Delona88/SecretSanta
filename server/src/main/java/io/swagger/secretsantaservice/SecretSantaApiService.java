package io.swagger.secretsantaservice;


import repositoryapi.repositoriesfactory.RepositoriesFactory;
import repositoryapi.SecretSantaApi;
import org.springframework.stereotype.Service;

import secretsantamodel.*;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class SecretSantaApiService {

    private SecretSantaApi api;

    public SecretSantaApiService(RepositoriesFactory factory) { //создается factory @Bean
        api = new SecretSantaApi(factory);
    }

/*    // по умолчанию
    public SecretSantaApiService() {
        api = new SecretSantaApi(REPO_FACTORY);
    }*/

    public HashMap<String, Person> getAllPersons() {
        return api.getAllPersons();
    }

    public void addPerson(Person person) {
        api.addPerson(person);

    }

    public Person getPersonById(String email) {
        return api.getPersonById(email);
    }

    public void replacePerson(String oldEmail, Person person) {
        api.replacePerson(oldEmail, person);
    }

    public void deletePerson(String email) {
        api.deletePerson(email);
    }

    public HashMap<String, String> getHMWithPersonsInfo(Integer gameId) { //не в API, добавить?
        return api.getHMWithPersonsInfo(gameId);
    }

    public HashMap<String, Person> getPersonsByGameId(Integer gameId) {
        return api.getPersonsByGameId(gameId);
    }

    public void addPersonGameToPerson(String email, PersonGame personGame) {
        api.addPersonGameToPerson(email, personGame);
    }

    public void deletePersonGameFromPerson(Integer gameId, String email) {
        api.deletePersonGameFromPerson(gameId, email);

    }

    public void setNaughtyList(String email, Integer gameId, ArrayList<String> arraylist) {
        api.setNaughtyList(email, gameId, arraylist);
    }

    public void setReceiver(String emailSanta, Integer gameId, String emailReceiver) {
        api.setReceiver(emailSanta, gameId, emailReceiver);
    }

    public void setWhishlist(String email, Integer gameId, String wish) {
        api.setWhishlist(email, gameId, wish);
    }

    public void setPersonGameActive(Integer gameId, String email, Boolean activity) {
        api.setPersonGameActive(gameId, email, activity);
    }

    public void addGame(SecretSantaGame game) {
        api.addGame(game);
    }

    public SecretSantaGame getGameById(int id) {
        return api.getGameById(id);
    }

    public void deleteGame(Integer gameId) {
        api.deleteGame(gameId);
    }

    public int getNewID() {
        return api.getNewID();
    }

    public void addPersonInGame(Integer gameId, String email) {
        api.addPersonInGame(gameId, email);
    }

    public void deleteEmailFromGame(Integer gameId, String email) {
        api.deleteEmailFromGame(gameId, email);
    }

    public void setGamePlayed(Integer gameId, Boolean activity) {
        api.setGamePlayed(gameId, activity);
    }

    public void startToss(Integer gameId) throws BadConditionsException {
        api.startToss(gameId);
    }

}
