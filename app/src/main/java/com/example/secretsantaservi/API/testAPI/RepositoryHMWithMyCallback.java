package com.example.secretsantaservi.API.testAPI;

import com.example.secretsantaservi.API.ApiWithMyCallbackInterface;
import com.example.secretsantaservi.API.MyCallback;
import com.example.secretsantaservi.secretsanta.*;

import java.util.ArrayList;
import java.util.HashMap;

public class RepositoryHMWithMyCallback implements ApiWithMyCallbackInterface {
    private HashMap<String, Person> personsHashMap = new HashMap<>();
    private HashMap<Integer, SecretSantaGame> gamesHashMap = new HashMap<>();

    @Override
    public void getAllPersons(MyCallback<HashMap<String, Person>> myCallback) {
        myCallback.onResponse(personsHashMap);
    }

    @Override
    public void addPerson(Person person, MyCallback<Object> myCallback) {
        personsHashMap.put(person.getEmail(), person);
        myCallback.onResponse(null);
    }

    @Override
    public void getPersonById(String email, MyCallback<Person> myCallback) {
        myCallback.onResponse(personsHashMap.get(email));
    }

    @Override
    public void replacePerson(Person person, String oldEmail, MyCallback<Object> myCallback) {
        //замена email у person
        personsHashMap.put(person.getEmail(), person);
        personsHashMap.remove(oldEmail);
        //замена email во всех играх
        ArrayList<Integer> idAllGames = person.getIdAllActiveGames();
        for (Integer id : idAllGames){
            gamesHashMap.get(id).getArrayParticipantsEmail().add(person.getEmail());
            gamesHashMap.get(id).getArrayParticipantsEmail().remove(oldEmail);
        }
    }

    @Override
    public void deletePerson(String email, MyCallback<Object> myCallback) {
        personsHashMap.remove(email);
        myCallback.onResponse(null);
    }

    @Override
    public void getHMWithPersonsInfo(Integer gameId, MyCallback<HashMap<String, String>> myCallback) {
        HashMap<String, String> hashMapPersonsInfo = new HashMap<>();
        ArrayList<String> participantsEmail = gamesHashMap.get(gameId).getArrayParticipantsEmail();
        for (String email : participantsEmail) {
            String str = personsHashMap.get(email).getName() + " (" + email + ") ";
            hashMapPersonsInfo.put(email, str);
        }
        myCallback.onResponse(hashMapPersonsInfo);
    }

    @Override
    public void getPersonsByGameId(Integer gameId, MyCallback<HashMap<String, Person>> myCallback) {
        ArrayList<String> arrayParticipantsEmail = gamesHashMap.get(gameId).getArrayParticipantsEmail();
        HashMap<String, Person> persons = new HashMap<>();
        for (String email : arrayParticipantsEmail) {
            persons.put(email, personsHashMap.get(email));
        }
        myCallback.onResponse(persons);
    }

    @Override
    public void addPersonGameToPerson(String email, PersonGame personGame, MyCallback<Object> myCallback) {
        personsHashMap.get(email).addGame(personGame);
        myCallback.onResponse(null);
    }

    @Override
    public void deletePersonGameFromPerson(Integer gameId, String email, MyCallback<Object> myCallback) {
        personsHashMap.get(email).deletePersonGameByGameId(gameId);
        myCallback.onResponse(null);
    }

    @Override
    public void setNaughtyList(String email, Integer gameId, ArrayList<String> arraylist, MyCallback<Object> myCallback) {
        personsHashMap.get(email).setArrayNaughtyListEmailByGameId(arraylist, gameId);

        myCallback.onResponse(null);
    }

    @Override
    public void setReceiver(String emailSanta, Integer gameId, String emailReceiver, MyCallback<Object> myCallback) {
        personsHashMap.get(emailSanta).setReceiverEmailByGameId(emailReceiver, gameId);

        myCallback.onResponse(null);
    }

    @Override
    public void setWhishlist(String email, Integer gameId, String wish, MyCallback<Object> myCallback) {
        personsHashMap.get(email).setWishListByGameId(gameId, wish);

        myCallback.onResponse(null);
    }

    @Override
    public void setPersonGameActive(Integer gameId, String email, Boolean activity, MyCallback<Object> myCallback) {
        personsHashMap.get(email).setPersonGameActivityByGameId(gameId, activity);

        myCallback.onResponse(null);
    }

    @Override
    public void addGame(SecretSantaGame game, MyCallback<Object> myCallback) {
        gamesHashMap.put(game.getGameId(), game);

        myCallback.onResponse(null);
    }

    @Override
    public void getGameById(int id, MyCallback<SecretSantaGame> myCallback) {
        myCallback.onResponse(gamesHashMap.get(id));

    }

    @Override
    public void deleteGame(Integer gameId, MyCallback<Object> myCallback) {
        gamesHashMap.remove(gameId);

        myCallback.onResponse(null);
    }

    @Override
    public void getNewID(MyCallback<Integer> myCallback) {
        int maxId = 0;
        for (Integer key : gamesHashMap.keySet()) {
            if (key > maxId) {
                maxId = key;
            }
        }
        maxId++;
        SecretSantaGame game = new SecretSantaGame(maxId);
        gamesHashMap.put(game.getGameId(), game);
        myCallback.onResponse(maxId);

    }

    @Override
    public void addPersonInGame(Integer gameId, String email, MyCallback<Object> myCallback) {
        gamesHashMap.get(gameId).addNewPersonInGameById(email);
        personsHashMap.get(email).addNewPersonGameById(gameId);

        myCallback.onResponse(null);
    }

    @Override
    public void deletePersonFromGame(Integer gameId, String email, MyCallback<Object> myCallback) {
        gamesHashMap.get(gameId).deleteEmailFromArrayParticipantsEmail(email);
        personsHashMap.get(email).deletePersonGameByGameId(gameId);

        myCallback.onResponse(null);
    }

    @Override
    public void setGamePlayed(Integer gameId, Boolean activity, MyCallback<Object> myCallback) {
        gamesHashMap.get(gameId).setPlayed(activity);

        myCallback.onResponse(null);
    }

    @Override
    public void startToss(Integer gameId, MyCallback<Object> myCallback) {
        ArrayList<Person> arrayPersons = createArrayPersons(gamesHashMap.get(gameId).getArrayParticipantsEmail());
        Toss toss = new Toss(arrayPersons, gameId);
        try {
            arrayPersons = toss.fillReceiversAndReturnParticipantsArray();
            for (Person person : arrayPersons) {
                String emailSanta = person.getEmail();
                String emailReceiver = person.getReceiverEmailByGameId(gameId);
                personsHashMap.get(emailSanta).setReceiverEmailByGameId(emailReceiver, gameId);
            }
            int OK = 200; //используется HTTP код ответа
            myCallback.onResponse(OK);
        } catch (BadConditionsException badConditions) {
            int BAD_REQUEST = 400;
            myCallback.onResponse(BAD_REQUEST);
            //myCallback.onFailure(badConditions);
            badConditions.printStackTrace();
        }

    }



    //TODO private
    public ArrayList<Person> createArrayPersons(ArrayList<String> arrayParticipantsEmail) {
        ArrayList<Person> persons = new ArrayList<>();
        for (String email : arrayParticipantsEmail) {
            persons.add(personsHashMap.get(email));
        }
        return persons;
    }




    @Override
    public void setGamesByPersonId(String email, HashMap<Integer, PersonGame> games) {

    }

}
