package com.example.secretsantaservi.api;

import io.swagger.client.secretsantaclient.ApiWithMyCallbackInterface;
import io.swagger.client.secretsantaclient.MyCallback;
import secretsantamodel.*;

import java.util.ArrayList;
import java.util.HashMap;
import repositoryapi.repositoriesfactory.RepositoriesFactory;
import repository.RepositoryInterface;

public class ApiWithMyCallback implements ApiWithMyCallbackInterface {

    RepositoryInterface<Integer, SecretSantaGame> gamesRepository;
    RepositoryInterface<String, Person> personsRepository;


    public ApiWithMyCallback(RepositoriesFactory factory) {
        gamesRepository = factory.createGamesRepository();
        personsRepository = factory.createPersonsRepository();
    }

    /**
     * Persons and Games API for Secret Santa
     */

    @Override
    public void getAllPersons(MyCallback<HashMap<String, Person>> myCallback) {
        myCallback.onResponse(personsRepository.getAll());
    }

    @Override
    public void addPerson(Person person, MyCallback<Object> myCallback) {
        personsRepository.add(person.getEmail(), person);
        myCallback.onResponse(null);
    }

    @Override
    public void getPersonById(String email, MyCallback<Person> myCallback) {
        myCallback.onResponse(personsRepository.getById(email));
    }

    @Override
    public void replacePerson(Person person, String oldEmail, MyCallback<Object> myCallback) {
        //замена email у person
        personsRepository.add(person.getEmail(), person);
        personsRepository.deleteById(oldEmail);
        //замена email во всех играх
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
    public void deletePerson(String email, MyCallback<Object> myCallback) {
        personsRepository.deleteById(email);

        myCallback.onResponse(null);
    }

    @Override
    public void getHMWithPersonsInfo(Integer gameId, MyCallback<HashMap<String, String>> myCallback) {
        HashMap<String, String> hashMapPersonsInfo = new HashMap<>();
        ArrayList<String> participantsEmail = gamesRepository.getById(gameId).getArrayParticipantsEmail();
        for (String email : participantsEmail) {
            String str = personsRepository.getById(email).getName() + " (" + email + ") ";
            hashMapPersonsInfo.put(email, str);
        }

        myCallback.onResponse(hashMapPersonsInfo);
    }

    @Override
    public void getPersonsByGameId(Integer gameId, MyCallback<HashMap<String, Person>> myCallback) {
        ArrayList<String> arrayParticipantsEmail = gamesRepository.getById(gameId).getArrayParticipantsEmail();
        HashMap<String, Person> persons = new HashMap<>();
        for (String email : arrayParticipantsEmail) {
            persons.put(email, personsRepository.getById(email));
        }

        myCallback.onResponse(persons);
    }

    @Override
    public void addPersonGameToPerson(String email, PersonGame personGame, MyCallback<Object> myCallback) {
        Person person = personsRepository.getById(email);
        person.addGame(personGame);
        personsRepository.update(person.getEmail(), person);

        myCallback.onResponse(null);
    }

    @Override
    public void deletePersonGameFromPerson(Integer gameId, String email, MyCallback<Object> myCallback) {
        Person person = personsRepository.getById(email);
        person.deletePersonGameByGameId(gameId);
        personsRepository.update(person.getEmail(), person);

        myCallback.onResponse(null);
    }

    @Override
    public void setNaughtyList(String email, Integer gameId, ArrayList<String> arraylist, MyCallback<Object> myCallback) {
        Person person = personsRepository.getById(email);
        person.setArrayNaughtyListEmailByGameId(arraylist, gameId);
        personsRepository.update(person.getEmail(), person);

        myCallback.onResponse(null);
    }

    @Override
    public void setReceiver(String emailSanta, Integer gameId, String emailReceiver, MyCallback<Object> myCallback) {
        Person person = personsRepository.getById(emailSanta);
        person.setReceiverEmailByGameId(emailReceiver, gameId);
        personsRepository.update(person.getEmail(), person);
        myCallback.onResponse(null);
    }

    @Override
    public void setWhishlist(String email, Integer gameId, String wish, MyCallback<Object> myCallback) {
        Person person = personsRepository.getById(email);
        person.setWishListByGameId(gameId, wish);
        personsRepository.update(person.getEmail(), person);
        myCallback.onResponse(null);
    }

    @Override
    public void setPersonGameActive(Integer gameId, String email, Boolean activity, MyCallback<Object> myCallback) {
        Person person = personsRepository.getById(email);
        person.setPersonGameActivityByGameId(gameId, activity);
        personsRepository.update(person.getEmail(), person);
        myCallback.onResponse(null);
    }

    @Override
    public void addGame(SecretSantaGame game, MyCallback<Object> myCallback) {
        gamesRepository.add(game.getGameId(), game);

        myCallback.onResponse(null);
    }

    @Override
    public void getGameById(int id, MyCallback<SecretSantaGame> myCallback) {
        myCallback.onResponse(gamesRepository.getById(id));
    }

    @Override
    public void deleteGame(Integer gameId, MyCallback<Object> myCallback) {
        gamesRepository.deleteById(gameId);

        myCallback.onResponse(null);
    }

    @Override
    public void getNewID(MyCallback<Integer> myCallback) {
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
    public void addPersonInGame(Integer gameId, String email, MyCallback<Object> myCallback) {
        SecretSantaGame game = gamesRepository.getById(gameId);
        game.addNewPersonInGameById(email);
        gamesRepository.update(game.getGameId(), game);

        Person person = personsRepository.getById(email);
        person.addNewPersonGameById(gameId);
        personsRepository.update(person.getEmail(), person);


        myCallback.onResponse(null);
    }

    @Override
    public void deletePersonFromGame(Integer gameId, String email, MyCallback<Object> myCallback) {
        SecretSantaGame game = gamesRepository.getById(gameId);
        game.deleteEmailFromArrayParticipantsEmail(email);
        gamesRepository.update(game.getGameId(), game);

        Person person = personsRepository.getById(email);
        person.deletePersonGameByGameId(gameId);
        personsRepository.update(person.getEmail(), person);


        myCallback.onResponse(null);
    }

    @Override
    public void setGamePlayed(Integer gameId, Boolean activity, MyCallback<Object> myCallback) {
        SecretSantaGame game = gamesRepository.getById(gameId);
        game.setPlayed(activity);
        gamesRepository.update(game.getGameId(), game);
        myCallback.onResponse(null);
    }

    @Override
    public void startToss(Integer gameId, MyCallback<Object> myCallback) {
        ArrayList<Person> arrayPersons = createArrayPersons(gamesRepository.getById(gameId).getArrayParticipantsEmail());
        Toss toss = new Toss(arrayPersons, gameId);
        //TODO изменить чтение/запись
        try {
            arrayPersons = toss.fillReceiversAndReturnParticipantsArray();
            for (Person santa : arrayPersons) {
                String emailSanta = santa.getEmail();
                String emailReceiver = santa.getReceiverEmailByGameId(gameId);

                Person person = personsRepository.getById(emailSanta);
                person.setReceiverEmailByGameId(emailReceiver, gameId);
                personsRepository.update(person.getEmail(), person);

            }
            int OK = 200; //используется HTTP код ответа
            myCallback.onResponse(OK);
        } catch (BadConditionsException badConditions) {
/*            int BAD_REQUEST = 400;
            myCallback.onResponse(BAD_REQUEST);*/
            myCallback.onFailure(badConditions);
            badConditions.printStackTrace();
        }

    }

    //TODO private
    private ArrayList<Person> createArrayPersons(ArrayList<String> arrayParticipantsEmail) {
        ArrayList<Person> persons = new ArrayList<>();
        for (String email : arrayParticipantsEmail) {
            persons.add(personsRepository.getById(email));
        }
        return persons;
    }

    @Override
    public void setGamesByPersonId(String email, HashMap<Integer, PersonGame> games) {

    }

}
