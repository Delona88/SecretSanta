package com.example.secretsantaservi.API;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.secretsantaservi.secretsanta.Person;
import com.example.secretsantaservi.secretsanta.PersonGame;
import com.example.secretsantaservi.secretsanta.SecretSantaGame;

public class Converter {

    public Person convert(io.swagger.client.model.Person personAPI) {

        List<io.swagger.client.model.PersonGame> gamesList = personAPI.getGames();
        HashMap<Integer, PersonGame> hashMap = new HashMap<>();
        for (io.swagger.client.model.PersonGame game : gamesList) {
            hashMap.put(game.getIdGame(), convert(game));
        }
        Person personApp = new Person(personAPI.getEmail(), personAPI.getName(), hashMap);

        return personApp;
    }

    public PersonGame convert(io.swagger.client.model.PersonGame personGameAPI) {

        List<String> emails = personGameAPI.getArrayNaughtyListEmail();
        ArrayList<String> arrayEmails = new ArrayList<>();
        for (String email : emails) {
            arrayEmails.add(email);
        }
        PersonGame personGame = new PersonGame(personGameAPI.getIdGame(), personGameAPI.getReceiverEmail(), personGameAPI.getWishlist(), personGameAPI.isActive(), arrayEmails);

        return personGame;
    }

    public SecretSantaGame convert(io.swagger.client.model.SecretSantaGame gameAPI) {

        List<String> emails = gameAPI.getParticipantsEmail();
        ArrayList<String> arrayEmails = new ArrayList<>();
        for (String email : emails) {
            arrayEmails.add(email);
        }
        SecretSantaGame game = new SecretSantaGame(gameAPI.getIdOfGame(), gameAPI.isPlayed(), arrayEmails);

        return game;
    }

    public io.swagger.client.model.Person convert(Person person) {
        io.swagger.client.model.Person personAPI = new io.swagger.client.model.Person();
        personAPI.setEmail(person.getEmail());
        personAPI.setName(person.getName());

        HashMap<Integer, PersonGame> hashMap = person.getGames();
        List<io.swagger.client.model.PersonGame> personGames = new ArrayList<>();
        for (PersonGame personGame : hashMap.values()) {
            personGames.add(convert(personGame));
        }
        personAPI.setGames(personGames);

        return personAPI;
    }

    public io.swagger.client.model.PersonGame convert(PersonGame personGame) {
        io.swagger.client.model.PersonGame personGameAPI = new io.swagger.client.model.PersonGame();
        personGameAPI.setIdGame(personGame.getGameId());
        personGameAPI.setReceiverEmail(personGame.getReceiverEmail());
        personGameAPI.setWishlist(personGame.getWishlist());
        personGameAPI.setActive(personGame.isActive());
        personGameAPI.setArrayNaughtyListEmail(personGame.getArrayNaughtyListEmail());

        return personGameAPI;
    }

    public io.swagger.client.model.SecretSantaGame convert(SecretSantaGame game) {
        io.swagger.client.model.SecretSantaGame gameAPI = new io.swagger.client.model.SecretSantaGame();
        gameAPI.setIdOfGame(game.getGameId());
        gameAPI.setPlayed(game.isPlayed());
        gameAPI.setParticipantsEmail(game.getArrayParticipantsEmail());

        return gameAPI;
    }
}
