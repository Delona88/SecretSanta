package com.example.secretsantaservi.API.testAPI;

import android.content.Context;
import com.example.secretsantaservi.API.ApiWithMyCallbackInterface;
import com.example.secretsantaservi.API.MyCallback;
import com.example.secretsantaservi.secretsanta.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class FilesHMWithMyCallback implements ApiWithMyCallbackInterface {
    private final static String FILE_NAME_HM_GAMES = "HashMapGames.ser";
    private final static String FILE_NAME_HM_PERSONS = "HashMapPersons.ser";
    private HashMap<Integer, SecretSantaGame> games;
    private HashMap<String, Person> persons;
    Context context; //для записи и чтения файла нужен контекст

    public FilesHMWithMyCallback() {
        games = new HashMap<>();
        persons = new HashMap<>();
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public FilesHMWithMyCallback(Context context) {
        this.context = context;
        games = new HashMap<>(); //для первого запуска
        persons = new HashMap<>();
    }



    /**
     * API
     */

    @Override
    public void getAllPersons(MyCallback<HashMap<String, Person>> myCallback) {
        persons = readHMPersonsFromFile();

        myCallback.onResponse(persons);
    }

    @Override
    public void addPerson(Person person, MyCallback<Object> myCallback) {
        persons = readHMPersonsFromFile();
        if (!persons.containsKey(person.getEmail())) {
            persons.put(person.getEmail(), person);
            writeHMPersonsInFile(persons);
        } else {
            persons.put(person.getEmail(), person);
            writeHMPersonsInFile(persons);
            //TODO подумать?! что делать, если есть в HM такой ключ?
        }

        myCallback.onResponse(null);
    }

    @Override
    public void getPersonById(String email, MyCallback<Person> myCallback) {
        myCallback.onResponse(readHMPersonsFromFile().get(email));
    }

    @Override
    public void replacePerson(Person person, String oldEmail, MyCallback<Object> myCallback) {
        persons = readHMPersonsFromFile();
        persons.put(person.getEmail(), person);
        persons.remove(oldEmail);
        writeHMPersonsInFile(persons);

        //замена email во всех играх
        games = readHMGamesFromFile();
        ArrayList<Integer> idAllGames = person.getIdAllActiveGames();
        for (Integer id : idAllGames){
            games.get(id).getArrayParticipantsEmail().add(person.getEmail());
            games.get(id).getArrayParticipantsEmail().remove(oldEmail);
        }
        writeHMGamesInFile(games);

        myCallback.onResponse(null);
    }

    @Override
    public void deletePerson(String email, MyCallback<Object> myCallback) {
        persons = readHMPersonsFromFile();
        persons.remove(email);
        writeHMPersonsInFile(persons);

        myCallback.onResponse(null);
    }

    @Override
    public void getHMWithPersonsInfo(Integer gameId, MyCallback<HashMap<String, String>> myCallback) {
        HashMap<String, String> hashMapPersonsInfo = new HashMap<>();
        games = readHMGamesFromFile();
        ArrayList<String> participantsEmail = games.get(gameId).getArrayParticipantsEmail();
        for (String email : participantsEmail) {
            String str = readHMPersonsFromFile().get(email).getName() + " (" + email + ") ";
            hashMapPersonsInfo.put(email, str);
        }

        myCallback.onResponse(hashMapPersonsInfo);
    }

    @Override
    public void getPersonsByGameId(Integer gameId, MyCallback<HashMap<String, Person>> myCallback) {
        games = readHMGamesFromFile();
        ArrayList<String> participantsEmail = games.get(gameId).getArrayParticipantsEmail();
        HashMap<String, Person> hashMapPersons = new HashMap<>();
        for (String email : participantsEmail) {
            hashMapPersons.put(email, readHMPersonsFromFile().get(email));
        }

        myCallback.onResponse(hashMapPersons);
    }

    @Override
    public void addPersonGameToPerson(String email, PersonGame personGame, MyCallback<Object> myCallback) {
        persons = readHMPersonsFromFile();
        persons.get(email).addGame(personGame);
        writeHMPersonsInFile(persons);

        myCallback.onResponse(null);
    }

    @Override
    public void deletePersonGameFromPerson(Integer gameId, String email, MyCallback<Object> myCallback) {
        persons = readHMPersonsFromFile();
        persons.get(email).deletePersonGameByGameId(gameId);
        writeHMPersonsInFile(persons);

        myCallback.onResponse(null);
    }

    @Override
    public void setNaughtyList(String email, Integer gameId, ArrayList<String> arraylist, MyCallback<Object> myCallback) {
        persons = readHMPersonsFromFile();
        persons.get(email).setArrayNaughtyListEmailByGameId(arraylist, gameId);
        writeHMPersonsInFile(persons);

        myCallback.onResponse(null);
    }

    @Override
    public void setReceiver(String emailSanta, Integer gameId, String emailReceiver, MyCallback<Object> myCallback) {
        persons = readHMPersonsFromFile();
        persons.get(emailSanta).setReceiverEmailByGameId(emailReceiver, gameId);
        writeHMPersonsInFile(persons);

        myCallback.onResponse(null);
    }

    @Override
    public void setWhishlist(String email, Integer gameId, String wish, MyCallback<Object> myCallback) {
        persons = readHMPersonsFromFile();
        persons.get(email).setWishListByGameId(gameId, wish);
        writeHMPersonsInFile(persons);

        myCallback.onResponse(null);
    }

    @Override
    public void setPersonGameActive(Integer gameId, String email, Boolean activity, MyCallback<Object> myCallback) {
        persons = readHMPersonsFromFile();
        persons.get(email).setPersonGameActivityByGameId(gameId, activity);
        writeHMPersonsInFile(persons);

        myCallback.onResponse(null);
    }

    @Override
    public void addGame(SecretSantaGame game, MyCallback<Object> myCallback) {
        games = readHMGamesFromFile();
        if (!games.containsKey(game.getGameId())) {
            games.put(game.getGameId(), game);
            writeHMGamesInFile(games);
        } else {
            //TODO подумать?!
        }

        myCallback.onResponse(null);
    }

    @Override
    public void getGameById(int id, MyCallback<SecretSantaGame> myCallback) {
        games = readHMGamesFromFile();

        myCallback.onResponse(games.get(id));
    }

    @Override
    public void deleteGame(Integer gameId, MyCallback<Object> myCallback) {
        games = readHMGamesFromFile();
        games.remove(gameId);
        writeHMGamesInFile(games);

        myCallback.onResponse(null);
    }

    @Override
    public void getNewID(MyCallback<Integer> myCallback) {
        games = readHMGamesFromFile();
        int maxId = 0;
        for (Integer key : games.keySet()) {
            if (key > maxId) {
                maxId = key;
            }
        }
        maxId++;
        //записать пустую игру, потом заменять
        SecretSantaGame game = new SecretSantaGame(maxId);
        games.put(game.getGameId(), game);
        writeHMGamesInFile(games);

        myCallback.onResponse(maxId);
    }

    @Override
    public void addPersonInGame(Integer gameId, String email, MyCallback<Object> myCallback) {
        games = readHMGamesFromFile();
        games.get(gameId).addNewPersonInGameById(email);
        writeHMGamesInFile(games);

        persons = readHMPersonsFromFile();
        persons.get(email).addNewPersonGameById(gameId);
        writeHMPersonsInFile(persons);

        myCallback.onResponse(null);
    }

    @Override
    public void deletePersonFromGame(Integer gameId, String email, MyCallback<Object> myCallback) {
        //deleteEmailFromArrayParticipants(gameId, email);
        games = readHMGamesFromFile();
        games.get(gameId).deleteEmailFromArrayParticipantsEmail(email);
        writeHMGamesInFile(games);

        //deletePersonGameFromPerson(gameId, email);
        persons = readHMPersonsFromFile();
        persons.get(email).deletePersonGameByGameId(gameId);
        writeHMPersonsInFile(persons);

        myCallback.onResponse(null);
    }

    @Override
    public void setGamePlayed(Integer gameId, Boolean activity, MyCallback<Object> myCallback) {
        games = readHMGamesFromFile();
        games.get(gameId).setPlayed(activity);
        writeHMGamesInFile(games);

        myCallback.onResponse(null);
    }

    @Override
    public void startToss(Integer gameId, MyCallback<Object> myCallback) {
        games = readHMGamesFromFile();
        ArrayList<Person> arrayParticipantsEmail = createArrayPersons(games.get(gameId).getArrayParticipantsEmail());
        Toss toss = new Toss(arrayParticipantsEmail, gameId);
        try {
            arrayParticipantsEmail = toss.fillReceiversAndReturnParticipantsArray();
            for (Person person : arrayParticipantsEmail) {
                String emailSanta = person.getEmail();
                String emailReceiver = person.getReceiverEmailByGameId(gameId);
                //установить получателя
                persons = readHMPersonsFromFile();
                persons.get(emailSanta).setReceiverEmailByGameId(emailReceiver, gameId);
                writeHMPersonsInFile(persons);
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

    //private перенесено на сервер
    private ArrayList<Person> createArrayPersons(ArrayList<String> arrayParticipantsEmail) {
        ArrayList<Person> persons = new ArrayList<>();
        for (String email : arrayParticipantsEmail) {
            persons.add(readHMPersonsFromFile().get(email));
        }
        return persons;
    }

    /**
     * Методы для записи и чтения
     */

    //private
    private void writeHMPersonsInFile(Object object) {
        writeInFile(object, FILE_NAME_HM_PERSONS);
    }


    private void writeHMGamesInFile(Object object) {
        writeInFile(object, FILE_NAME_HM_GAMES);
    }

    private void writeInFile(Object object, String fileName) {
        FileOutputStream fileOutputStream;
        ObjectOutputStream objectOutputStream;
        try {
            fileOutputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE); //append = true - дописываем в файл
            objectOutputStream = new ObjectOutputStream(fileOutputStream); //appending - не добавляет head, чтобы читать все объекты
            objectOutputStream.writeObject(object);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private HashMap<String, Person> readHMPersonsFromFile() {
        Object object = readFromFile(FILE_NAME_HM_PERSONS);
        if (object == null) {
            HashMap<String, Person> hm = new HashMap<>();
            return new HashMap<>();
        }
        return (HashMap<String, Person>) object;
    }

    private HashMap<Integer, SecretSantaGame> readHMGamesFromFile() {
        Object object = readFromFile(FILE_NAME_HM_GAMES);
        if (object == null) {
            return new HashMap<>();
        }
        return (HashMap<Integer, SecretSantaGame>) object;
    }

    private Object readFromFile(String fileName) {
        FileInputStream fileInputStream;
        ObjectInputStream objectInputStream;
        Object object;
        try {
            fileInputStream = context.openFileInput(fileName);
            objectInputStream = new ObjectInputStream(fileInputStream);
            object = objectInputStream.readObject();
            objectInputStream.close();
            return object;
        } catch (FileNotFoundException e) {
/*            HashMap hm = new HashMap<>();
            writeInFile(hm,fileName);*/
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * дозапись в файл
     */

    private class AppendingObjectOutputStream extends ObjectOutputStream {
        public AppendingObjectOutputStream(OutputStream out) throws IOException {
            super(out);
        }

        @Override
        protected void writeStreamHeader() throws IOException {
            // do not write a header, but reset:
            // this line added after another question
            // showed a problem with the original
            reset();
        }
    }


    //TODO восстановление игр
    public void setGamesByPersonId(String email, HashMap<Integer, PersonGame> games){
        persons = readHMPersonsFromFile();
        persons.get(email).setGames(games);
        writeHMPersonsInFile(persons);
    }


    //TODO удалить после проверки

/*    public void addPerson(Person person) {//вызывать только когда Person  не существует
        persons = readHMPersonsFromFile();
        if (!persons.containsKey(person.getEmail())) {
            persons.put(person.getEmail(), person);
            writeHMPersonsInFile(persons);
        } else {
            persons.put(person.getEmail(), person);
            writeHMPersonsInFile(persons);
            //TODO подумать?! что делать, если есть в HM такой ключ?
        }
    }

    public Person getPersonById(String email) {
        return readHMPersonsFromFile().get(email);
    }

    public void replacePerson(Person person) {
        persons = readHMPersonsFromFile();
        persons.put(person.getEmail(), person);
        writeHMPersonsInFile(persons);
    }

    public void deletePerson(String email) {
        persons = readHMPersonsFromFile();
        persons.remove(email);
        writeHMPersonsInFile(persons);
    }

    public HashMap<String, String> getHMWithPersonsInfo(Integer gameId) {
        HashMap<String, String> hashMapPersonsInfo = new HashMap<>();
        games = readHMGamesFromFile();
        ArrayList<String> participantsEmail = games.get(gameId).getArrayParticipantsEmail();
        for (String email : participantsEmail) {
            String str = readHMPersonsFromFile().get(email).getName() + " (" + email + ") ";
            hashMapPersonsInfo.put(email, str);
        }
        return hashMapPersonsInfo;
    }

    public HashMap<String, Person> getPersonsByGameId(Integer gameId) {
        games = readHMGamesFromFile();
        ArrayList<String> participantsEmail = games.get(gameId).getArrayParticipantsEmail();
        HashMap<String, Person> hashMapPersons = new HashMap<>();
        for (String email : participantsEmail) {
            hashMapPersons.put(email, readHMPersonsFromFile().get(email));
        }
        return hashMapPersons;
    }

    public void addPersonGameToPerson(String email, PersonGame personGame) {
        persons = readHMPersonsFromFile();
        persons.get(email).addGame(personGame);
        writeHMPersonsInFile(persons);
    }//можно сразу новую по id?

    public void deletePersonGameFromPerson(Integer gameId, String email) {
        persons = readHMPersonsFromFile();
        persons.get(email).deletePersonGameByGameId(gameId);
        writeHMPersonsInFile(persons);
    }

    public void setNaughtyList(String email, Integer gameId, ArrayList<String> arraylist) {
        persons = readHMPersonsFromFile();
        persons.get(email).setArrayNaughtyListEmailByGameId(arraylist, gameId);
        writeHMPersonsInFile(persons);
    }

    //private теперь на стороне сервера"!
    public void setReceiver(String emailSanta, Integer gameId, String emailReceiver) {
        persons = readHMPersonsFromFile();
        persons.get(emailSanta).setReceiverEmailByGameId(emailReceiver, gameId);
        writeHMPersonsInFile(persons);
    }

    public void setWhishlist(String email, Integer gameId, String wish) {
        persons = readHMPersonsFromFile();
        persons.get(email).setWishListByGameId(gameId, wish);
        writeHMPersonsInFile(persons);
    }

    public void setPersonGameActive(Integer gameId, String email, Boolean activity) {
        persons = readHMPersonsFromFile();
        persons.get(email).setPersonGameActivityByGameId(gameId, activity);
        writeHMPersonsInFile(persons);
    }

    public void addGame(SecretSantaGame game) {
        games = readHMGamesFromFile();
        if (!games.containsKey(game.getIdOfGame())) {
            games.put(game.getIdOfGame(), game);
            writeHMGamesInFile(games);
        } else {
            //TODO подумать?!
        }
    }

    public SecretSantaGame getGameById(int id) {
        games = readHMGamesFromFile();
        return games.get(id);
    }

    public void deleteGame(Integer gameId) {
        // заглушка
    }

    public int getNewID() {
        games = readHMGamesFromFile();
        int maxId = 0;
        for (Integer key : games.keySet()) {
            if (key > maxId) {
                maxId = key;
            }
        }
        if (maxId == 0) {
            return 1;
        } else {
            maxId++;
            return maxId;
        }
    }

    public void addPersonInGameById(Integer gameId, String email) {
        games = readHMGamesFromFile();
        games.get(gameId).addNewPersonInGameById(email);
        writeHMGamesInFile(games);
    }

    public void deleteEmailFromGame(Integer gameId, String email) {
        //deleteEmailFromArrayParticipants(gameId, email);
        games = readHMGamesFromFile();
        games.get(gameId).deleteEmailFromArrayParticipantsEmail(email);
        writeHMGamesInFile(games);

        //deletePersonGameFromPerson(gameId, email);
        persons = readHMPersonsFromFile();
        persons.get(email).deletePersonGameByGameId(gameId);
        writeHMPersonsInFile(persons);

    }

    private void deleteEmailFromArrayParticipants(Integer gameId, String email) {
        games = readHMGamesFromFile();
        games.get(gameId).deleteEmailFromArrayParticipantsEmail(email);
        writeHMGamesInFile(games);
    }

    public void setGamePlayed(Integer gameId, Boolean b) {
        games = readHMGamesFromFile();
        games.get(gameId).setPlayed(b);
        writeHMGamesInFile(games);
    }

    public void startToss(Integer gameId) { //изменено
        games = readHMGamesFromFile();
        ArrayList<Person> arrayParticipantsEmail = createArrayPersons(games.get(gameId).getArrayParticipantsEmail());
        Toss toss = new Toss(arrayParticipantsEmail, gameId);
        arrayParticipantsEmail = toss.fillReceiversAndReturnParticipantsArray();
        for (Person person : arrayParticipantsEmail) {
            String emailSanta = person.getEmail();
            String emailReceiver = person.getReceiverEmailByGameId(gameId);
            //установить получателя
            persons = readHMPersonsFromFile();
            persons.get(emailSanta).setReceiverEmailByGameId(emailReceiver, gameId);
            writeHMPersonsInFile(persons);
        }
    }*/


}
