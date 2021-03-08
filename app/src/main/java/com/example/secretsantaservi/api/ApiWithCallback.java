package com.example.secretsantaservi.api;

import com.example.secretsantaservi.androidrepository.repositoriesfactory.RepositoriesFactory;
import com.example.secretsantaservi.androidrepository.RepositoryInterface;
import io.swagger.client.secretsantaclient.ApiWithCallbackInterface;
import io.swagger.client.secretsantaclient.Callback;
import secretsantamodel.*;

import java.util.ArrayList;
import java.util.HashMap;

public class ApiWithCallback implements ApiWithCallbackInterface {

    private final RepositoryInterface<Integer, SecretSantaGame> gamesRepository;
    private final RepositoryInterface<String, Person> personsRepository;


    public ApiWithCallback(RepositoriesFactory factory) {
        gamesRepository = factory.createGamesRepository();
        personsRepository = factory.createPersonsRepository();
    }

    @Override
    public void getAllPersons(Callback<HashMap<String, Person>> myCallback) {
        myCallback.onResponse(personsRepository.getAll());
    }

    @Override
    public void addPerson(Person person, Callback<Object> myCallback) {
        personsRepository.add(person.getEmail(), person);
        myCallback.onResponse(null);
    }

    @Override
    public void getPersonById(String email, Callback<Person> myCallback) {
        myCallback.onResponse(personsRepository.getById(email));
    }

    @Override
    public void replacePerson(Person person, String oldEmail, Callback<Object> myCallback) {
        personsRepository.add(person.getEmail(), person);
        personsRepository.deleteById(oldEmail);

        ArrayList<Integer> idAllGames = person.getIdAllActiveGames();
        for (Integer id : idAllGames) {
            SecretSantaGame game = gamesRepository.getById(id);
            game.addNewPersonInGameById(person.getEmail());
            game.deleteEmailFromArrayParticipantsEmail(oldEmail);
            gamesRepository.update(game.getGameId(), game);
        }

        myCallback.onResponse(null);
    }

    @Override
    public void deletePerson(String email, Callback<Object> myCallback) {
        personsRepository.deleteById(email);

        myCallback.onResponse(null);
    }

    @Override
    public void getHMWithPersonsInfo(Integer gameId, Callback<HashMap<String, String>> myCallback) {
        HashMap<String, String> hashMapPersonsInfo = new HashMap<>();
        ArrayList<String> participantsEmail = gamesRepository.getById(gameId).getArrayParticipantsEmail();
        for (String email : participantsEmail) {
            String str = personsRepository.getById(email).getName() + " (" + email + ") ";
            hashMapPersonsInfo.put(email, str);
        }

        myCallback.onResponse(hashMapPersonsInfo);
    }

    @Override
    public void getPersonsByGameId(Integer gameId, Callback<HashMap<String, Person>> myCallback) {
        ArrayList<String> arrayParticipantsEmail = gamesRepository.getById(gameId).getArrayParticipantsEmail();
        HashMap<String, Person> persons = new HashMap<>();
        for (String email : arrayParticipantsEmail) {
            persons.put(email, personsRepository.getById(email));
        }

        myCallback.onResponse(persons);
    }

    @Override
    public void addPersonGameToPerson(String email, PersonGame personGame, Callback<Object> myCallback) {
        Person person = personsRepository.getById(email);
        person.addGame(personGame);
        personsRepository.update(person.getEmail(), person);

        myCallback.onResponse(null);
    }

    @Override
    public void deletePersonGameFromPerson(Integer gameId, String email, Callback<Object> myCallback) {
        Person person = personsRepository.getById(email);
        person.deleteGameByGameId(gameId);
        personsRepository.update(person.getEmail(), person);

        myCallback.onResponse(null);
    }

    @Override
    public void setNaughtyList(String email, Integer gameId, ArrayList<String> arraylist, Callback<Object> myCallback) {
        Person person = personsRepository.getById(email);
        person.setArrayNaughtyListEmailByGameId(arraylist, gameId);
        personsRepository.update(person.getEmail(), person);

        myCallback.onResponse(null);
    }

    @Override
    public void setReceiver(String emailSanta, Integer gameId, String emailReceiver, Callback<Object> myCallback) {
        Person person = personsRepository.getById(emailSanta);
        person.setReceiverEmailByGameId(emailReceiver, gameId);
        personsRepository.update(person.getEmail(), person);
        myCallback.onResponse(null);
    }

    @Override
    public void setWhishlist(String email, Integer gameId, String wish, Callback<Object> myCallback) {
        Person person = personsRepository.getById(email);
        person.setWishListByGameId(gameId, wish);
        personsRepository.update(person.getEmail(), person);
        myCallback.onResponse(null);
    }

    @Override
    public void setPersonGameActive(Integer gameId, String email, Boolean activity, Callback<Object> myCallback) {
        Person person = personsRepository.getById(email);
        person.setGameActivityByGameId(gameId, activity);
        personsRepository.update(person.getEmail(), person);
        myCallback.onResponse(null);
    }

    @Override
    public void addGame(SecretSantaGame game, Callback<Object> myCallback) {
        gamesRepository.add(game.getGameId(), game);

        myCallback.onResponse(null);
    }

    @Override
    public void getGameById(int id, Callback<SecretSantaGame> myCallback) {
        myCallback.onResponse(gamesRepository.getById(id));
    }

    @Override
    public void deleteGame(Integer gameId, Callback<Object> myCallback) {
        gamesRepository.deleteById(gameId);

        myCallback.onResponse(null);
    }

    @Override
    public void getNewID(Callback<Integer> myCallback) {
        int maxId = 0;
        for (Integer key : gamesRepository.getAll().keySet()) {
            if (key > maxId) {
                maxId = key;
            }
        }
        maxId++;
        SecretSantaGame game = new SecretSantaGame(maxId);
        gamesRepository.add(game.getGameId(), game);

        myCallback.onResponse(maxId);

    }

    @Override
    public void addPersonInGame(Integer gameId, String email, Callback<Object> myCallback) {
        SecretSantaGame game = gamesRepository.getById(gameId);
        game.addNewPersonInGameById(email);
        gamesRepository.update(game.getGameId(), game);

        Person person = personsRepository.getById(email);
        person.addNewGameById(gameId);
        personsRepository.update(person.getEmail(), person);

        myCallback.onResponse(null);
    }

    @Override
    public void deletePersonFromGame(Integer gameId, String email, Callback<Object> myCallback) {
        SecretSantaGame game = gamesRepository.getById(gameId);
        game.deleteEmailFromArrayParticipantsEmail(email);
        gamesRepository.update(game.getGameId(), game);

        Person person = personsRepository.getById(email);
        person.deleteGameByGameId(gameId);
        personsRepository.update(person.getEmail(), person);

        myCallback.onResponse(null);
    }

    @Override
    public void setGamePlayed(Integer gameId, Boolean activity, Callback<Object> myCallback) {
        SecretSantaGame game = gamesRepository.getById(gameId);
        game.setPlayed(activity);
        gamesRepository.update(game.getGameId(), game);
        myCallback.onResponse(null);
    }

    @Override
    public void startToss(Integer gameId, Callback<Object> myCallback) {
        ArrayList<Person> arrayPersons = createArrayPersons(gamesRepository.getById(gameId).getArrayParticipantsEmail());
        Toss toss = new Toss(arrayPersons, gameId);
        try {
            arrayPersons = toss.fillReceiversAndReturnParticipantsArray();
            for (Person santa : arrayPersons) {
                String emailSanta = santa.getEmail();
                String emailReceiver = santa.getReceiverEmailByGameId(gameId);

                Person person = personsRepository.getById(emailSanta);
                person.setReceiverEmailByGameId(emailReceiver, gameId);
                personsRepository.update(person.getEmail(), person);
            }
            myCallback.onResponse(null);
        } catch (BadConditionsException badConditions) {
            myCallback.onFailure(badConditions);
            badConditions.printStackTrace();
        }

    }

    private ArrayList<Person> createArrayPersons(ArrayList<String> arrayParticipantsEmail) {
        ArrayList<Person> persons = new ArrayList<>();
        for (String email : arrayParticipantsEmail) {
            persons.add(personsRepository.getById(email));
        }
        return persons;
    }

}
