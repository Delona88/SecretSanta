package io.swagger.secretsantaservice;

import secretsantamodel.Person;
import secretsantamodel.PersonGame;
import secretsantamodel.SecretSantaGame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Converter {

    public Person convert(io.swagger.model.Person personTO) {

        List<io.swagger.model.PersonGame> gamesList = personTO.getGames();
        HashMap<Integer, PersonGame> hashMap = new HashMap<>();
        for (io.swagger.model.PersonGame game : gamesList) {
            hashMap.put(game.getIdGame(), convert(game));
        }
        Person personApp = new Person(personTO.getEmail(), personTO.getName(), hashMap);

        return personApp;
    }

    public PersonGame convert(io.swagger.model.PersonGame personGameTO) {

        List<String> emails = personGameTO.getArrayNaughtyListEmail();
        ArrayList<String> arrayEmails = new ArrayList<>();
        for (String email : emails) {
            arrayEmails.add(email);
        }
        //arrayEmails.addAll(emails);
        PersonGame personGame = new PersonGame(personGameTO.getIdGame(), personGameTO.getReceiverEmail(), personGameTO.getWishlist(), personGameTO.isActive(), arrayEmails);

        return personGame;
    }

    public SecretSantaGame convert(io.swagger.model.SecretSantaGame gameTO) {

        List<String> emails = gameTO.getParticipantsEmail();
        ArrayList<String> arrayEmails = new ArrayList<>();
        for (String email : emails) {
            arrayEmails.add(email);
        }
        //arrayEmails.addAll(emails);
        SecretSantaGame game = new SecretSantaGame(gameTO.getIdOfGame(), gameTO.isPlayed(), arrayEmails);

        return game;
    }

    public io.swagger.model.Person convert(Person person) {
        io.swagger.model.Person personTO = new io.swagger.model.Person();
        personTO.setEmail(person.getEmail());
        personTO.setName(person.getName());

        HashMap<Integer, PersonGame> hashMap = person.getGames();
        List<io.swagger.model.PersonGame> personGames = new ArrayList<>();
        for (PersonGame personGame : hashMap.values()) {
            personGames.add(convert(personGame));
        }
        personTO.setGames(personGames);

        return personTO;
    }

    public io.swagger.model.PersonGame convert(PersonGame personGame) {
        io.swagger.model.PersonGame personGameTO = new io.swagger.model.PersonGame();
        personGameTO.setIdGame(personGame.getGameId());
        personGameTO.setReceiverEmail(personGame.getReceiverEmail());
        personGameTO.setWishlist(personGame.getWishlist());
        personGameTO.setActive(personGame.isActive());
        personGameTO.setArrayNaughtyListEmail(personGame.getArrayNaughtyListEmail());

        return personGameTO;
    }

    public io.swagger.model.SecretSantaGame convert(SecretSantaGame game) {
        io.swagger.model.SecretSantaGame gameTO = new io.swagger.model.SecretSantaGame();
        gameTO.setIdOfGame(game.getGameId());
        gameTO.setPlayed(game.isPlayed());
        gameTO.setParticipantsEmail(game.getArrayParticipantsEmail());

        return gameTO;
    }

    public ArrayList<String> fromListToArrayList(List<String> list) {
        ArrayList<String> elements = new ArrayList<>();
        for (String element : list) {
            elements.add(element);
        }
        return elements;
    }


}
