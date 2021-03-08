package repositoryapi;

import repositoryapi.repositoriesfactory.RepositoriesFactory;
import repository.RepositoryInterface;
import secretsantamodel.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SecretSantaApi implements SecretSantaApiInterface {

    private final RepositoryInterface<String, Person> personsRepository;
    private final RepositoryInterface<Integer, SecretSantaGame> gamesRepository;


    /**
     * HMRepositoriesFactory - in-memory
     * FileLocalRepositoriesFactory - for saving in local file
     * FileDropBoxRepositoriesFactory - for saving in file and load to DropBox
     */
    public SecretSantaApi(RepositoriesFactory factory) {
        this.personsRepository = factory.createPersonsRepository();
        this.gamesRepository = factory.createGamesRepository();
    }

    @Override
    public ConcurrentHashMap<String, Person> getAllPersons() {
        return personsRepository.getAll();
    }

    @Override
    public void addPerson(Person person) {
        personsRepository.add(person.getEmail(), person);
    }

    @Override
    public Person getPersonById(String email) {
        personsRepository.getById(email);
        return personsRepository.getById(email);
    }

    @Override
    public void replacePerson(String oldEmail, Person person) {
        personsRepository.add(person.getEmail(), person);
        personsRepository.deleteById(oldEmail);

        ArrayList<Integer> idAllGames = person.getIdAllActiveGames();
        for (Integer id : idAllGames) {
            SecretSantaGame game = gamesRepository.getById(id);
            game.addNewPersonInGameById(person.getEmail());
            game.deleteEmailFromArrayParticipantsEmail(oldEmail);
            gamesRepository.update(game.getGameId(), game);
        }
    }

    @Override
    public void deletePerson(String email) {
        personsRepository.deleteById(email);
    }

    @Override
    public HashMap<String, String> getHMWithPersonsInfo(Integer gameId) { //не в API, добавить?
        HashMap<String, String> hashMapPersonsInfo = new HashMap<>();
        ArrayList<String> participantsEmail = gamesRepository.getById(gameId).getArrayParticipantsEmail();
        for (String email : participantsEmail) {
            String str = personsRepository.getById(email).getName() + " (" + email + ") ";
            hashMapPersonsInfo.put(email, str);
        }
        return hashMapPersonsInfo;
    }

    @Override
    public HashMap<String, Person> getPersonsByGameId(Integer gameId) {
        ArrayList<String> arrayParticipantsEmail = gamesRepository.getById(gameId).getArrayParticipantsEmail();
        HashMap<String, Person> persons = new HashMap<>();
        for (String email : arrayParticipantsEmail) {
            persons.put(email, personsRepository.getById(email));
        }
        return persons;
    }

    @Override
    public void addPersonGameToPerson(String email, PersonGame personGame) {
        Person person = personsRepository.getById(email);
        person.addGame(personGame);
        personsRepository.update(person.getEmail(), person);
    }

    @Override
    public void deletePersonGameFromPerson(Integer gameId, String email) {
        Person person = personsRepository.getById(email);
        person.deleteGameByGameId(gameId);
        personsRepository.update(person.getEmail(), person);

    }

    @Override
    public void setNaughtyList(String email, Integer gameId, ArrayList<String> arraylist) {
        Person person = personsRepository.getById(email);
        person.setArrayNaughtyListEmailByGameId(arraylist, gameId);
        personsRepository.update(person.getEmail(), person);

    }

    @Override
    public void setReceiver(String emailSanta, Integer gameId, String emailReceiver) {
        Person person = personsRepository.getById(emailSanta);
        person.setReceiverEmailByGameId(emailReceiver, gameId);
        personsRepository.update(person.getEmail(), person);
    }

    @Override
    public void setWhishlist(String email, Integer gameId, String wish) {
        Person person = personsRepository.getById(email);
        person.setWishListByGameId(gameId, wish);
        personsRepository.update(person.getEmail(), person);
    }

    @Override
    public void setPersonGameActive(Integer gameId, String email, Boolean activity) {
        Person person = personsRepository.getById(email);
        person.setGameActivityByGameId(gameId, activity);
        personsRepository.update(person.getEmail(), person);
    }

    @Override
    public void addGame(SecretSantaGame game) {
        gamesRepository.add(game.getGameId(), game);
    }


    @Override
    public SecretSantaGame getGameById(int id) {
        return gamesRepository.getById(id);
    }

    @Override
    public void deleteGame(Integer gameId) {
        gamesRepository.deleteById(gameId);
    }

    @Override
    public int getNewID() {
        int maxId = 0;

        Map<Integer, SecretSantaGame> hashMap = gamesRepository.getAll();
        for (Integer key : hashMap.keySet()) {
            if (key > maxId) {
                maxId = key;
            }
        }

        maxId++;
        gamesRepository.add(maxId, new SecretSantaGame(maxId));
        return maxId;
    }

    @Override
    public void addPersonInGame(Integer gameId, String email) {
        SecretSantaGame game = gamesRepository.getById(gameId);
        game.addNewPersonInGameById(email);
        gamesRepository.update(game.getGameId(), game);

        Person person = personsRepository.getById(email);
        person.addNewGameById(gameId);
        personsRepository.update(person.getEmail(), person);
    }

    @Override
    public void deleteEmailFromGame(Integer gameId, String email) {
        SecretSantaGame game = gamesRepository.getById(gameId);
        game.deleteEmailFromArrayParticipantsEmail(email);
        gamesRepository.update(game.getGameId(), game);

        Person person = personsRepository.getById(email);
        person.deleteGameByGameId(gameId);
        personsRepository.update(person.getEmail(), person);
    }

    @Override
    public void setGamePlayed(Integer gameId, Boolean activity) {
        SecretSantaGame game = gamesRepository.getById(gameId);
        game.setPlayed(activity);
        gamesRepository.update(game.getGameId(), game);
    }

    @Override
    public void startToss(Integer gameId) throws BadConditionsException {
        ArrayList<Person> arrayPersons = createArrayPersons(gamesRepository.getById(gameId).getArrayParticipantsEmail());
        Toss toss = new Toss(arrayPersons, gameId);
        arrayPersons = toss.fillReceiversAndReturnParticipantsArray();
        for (Person santa : arrayPersons) {
            String emailSanta = santa.getEmail();
            String emailReceiver = santa.getReceiverEmailByGameId(gameId);

            Person person = personsRepository.getById(emailSanta);
            person.setReceiverEmailByGameId(emailReceiver, gameId);
            personsRepository.update(person.getEmail(), person);

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
