package io.swagger.client.secretsantaclient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import secretsantamodel.*;

public class Converter {

    public Person convert(io.swagger.client.model.Person personTO) {

        List<io.swagger.client.model.PersonGame> gamesList = personTO.getGames();
        HashMap<Integer, PersonGame> hashMap = new HashMap<>();
        for (io.swagger.client.model.PersonGame game : gamesList) {
            hashMap.put(game.getIdGame(), convert(game));
        }

        return new Person(personTO.getEmail(), personTO.getName(), hashMap);
    }

    public PersonGame convert(io.swagger.client.model.PersonGame personGameTO) {

        List<String> emails = personGameTO.getArrayNaughtyListEmail();
        ArrayList<String> arrayEmails = new ArrayList<>(emails);

        return new PersonGame(personGameTO.getIdGame(), personGameTO.getReceiverEmail(), personGameTO.getWishlist(), personGameTO.isActive(), arrayEmails);
    }

    public SecretSantaGame convert(io.swagger.client.model.SecretSantaGame gameTO) {

        List<String> emails = gameTO.getParticipantsEmail();
        ArrayList<String> arrayEmails = new ArrayList<>(emails);

        return new SecretSantaGame(gameTO.getIdOfGame(), gameTO.isPlayed(), arrayEmails);
    }

    public io.swagger.client.model.Person convert(Person person) {
        io.swagger.client.model.Person personTO = new io.swagger.client.model.Person();
        personTO.setEmail(person.getEmail());
        personTO.setName(person.getName());

        HashMap<Integer, PersonGame> hashMap = person.getGames();
        List<io.swagger.client.model.PersonGame> personGames = new ArrayList<>();
        for (PersonGame personGame : hashMap.values()) {
            personGames.add(convert(personGame));
        }
        personTO.setGames(personGames);

        return personTO;
    }

    public io.swagger.client.model.PersonGame convert(PersonGame personGame) {
        io.swagger.client.model.PersonGame personGameTO = new io.swagger.client.model.PersonGame();
        personGameTO.setIdGame(personGame.getGameId());
        personGameTO.setReceiverEmail(personGame.getReceiverEmail());
        personGameTO.setWishlist(personGame.getWishlist());
        personGameTO.setActive(personGame.isActive());
        personGameTO.setArrayNaughtyListEmail(personGame.getArrayNaughtyListEmails());

        return personGameTO;
    }

    public io.swagger.client.model.SecretSantaGame convert(SecretSantaGame game) {
        io.swagger.client.model.SecretSantaGame gameTO = new io.swagger.client.model.SecretSantaGame();
        gameTO.setIdOfGame(game.getGameId());
        gameTO.setPlayed(game.isPlayed());
        gameTO.setParticipantsEmail(game.getArrayParticipantsEmail());

        return gameTO;
    }
}
