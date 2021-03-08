package secretsantamodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Person implements Serializable{

    static final long serialVersionUID = 8208402250750954819L;

    private String email;

    private String name;

    private HashMap<Integer, PersonGame> games = new HashMap<>();

    public Person(String email,String name){
        this.name = name;
        this.email = email;
    }

    public Person(String email,String name, HashMap<Integer, PersonGame> games){
        this.name = name;
        this.email = email;
        this.games = games;
    }

    public void setGames(HashMap<Integer, PersonGame> games) {
        this.games = games;
    }

    public HashMap<Integer, PersonGame> getGames() {
        return games;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setReceiverByGameId(Person receiver, int gameId) {
        games.get(gameId).setReceiverEmail(receiver.getEmail());
    }

    public void setReceiverEmailByGameId(String receiverEmail, int gameId) {
        games.get(gameId).setReceiverEmail(receiverEmail);
    }

    public String getReceiverEmailByGameId(int id){
        return games.get(id).getReceiverEmail();
    }

    public void setArrayNaughtyListEmailByGameId(ArrayList<String> arrayNaughtyListEmail, int id){
        games.get(id).setArrayNaughtyListEmails(arrayNaughtyListEmail);
    }

    public ArrayList<String> getArrayNaughtyListEmailByGameId(int id){
        return games.get(id).getArrayNaughtyListEmails();
    }

    public ArrayList<Integer> getIdAllActiveGames(){
        ArrayList<Integer> gamesId = new ArrayList<>();
        Collection<PersonGame> personGames = this.games.values();
        for (PersonGame personGame : personGames){
            if (personGame.isActive()) {
                gamesId.add(personGame.getGameId());
            }
        }
        return gamesId;
    }

    public void addNewGameById(int id){
        PersonGame personGame = new PersonGame(id);
        games.put(id, personGame);
    }

    public void addGame(PersonGame personGame){
        games.put(personGame.getGameId(), personGame);
    }


    public PersonGame getGameByID(int id){
        return games.get(id);
    }

    public String getWhishListByGameId(int id){
        if (getGameByID(id) == null){
            return null;
        }
        return getGameByID(id).getWishlist();
    }

    public void setWishListByGameId(int id,String wish){
        getGameByID(id).setWishlist(wish);
    }

    public void deleteGameByGameId(Integer id){
        games.remove(id);
    }

    public void setGameActivityByGameId(Integer gameId, Boolean b){
        games.get(gameId).setActivity(b);
    }

    @Override
    public String toString() {
        return "Person{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", games=" + games +
                '}';
    }
}


