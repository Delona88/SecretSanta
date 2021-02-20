package com.example.secretsantaservi.secretsanta;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

public class Person implements Serializable {
    static final long serialVersionUID = 8208402250750954819L;

    private String email; //уникальный идентификатор участника
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

    //TODO ПРОВЕРКА: нет такой игры у данного игрока, проверка на следующем уровне??
    /*        if (games.get(id) == null){ //нет такой игры у данного игрока, проверка на следующем уровне
            return null;
        }*/

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
        games.get(id).setArrayNaughtyListEmail(arrayNaughtyListEmail);
    }

    public ArrayList<String> getArrayNaughtyListEmailByGameId(int id){
        return games.get(id).getArrayNaughtyListEmail();
    }

    public ArrayList<Integer> getIdAllActiveGames(){
        ArrayList<Integer> gamesId = new ArrayList<>();
        Collection<PersonGame> personsGames = games.values();
        for (PersonGame personGame : personsGames){
            if (personGame.isActive()) {
                gamesId.add(personGame.getGameId());
            }
        }
        return gamesId;
    }

    public void addNewPersonGameById(int id){
        PersonGame game = new PersonGame(id);
        games.put(id,game);
    }

    public void addGame(PersonGame personGame){
        games.put(personGame.getGameId(),personGame);
    }


    public PersonGame getPersonGameByID(int id){
        return games.get(id);
    }

    public String getWhishListByGameId(int id){
        if (getPersonGameByID(id) == null){ //игра была удалена игроком
            return null;
        }
        return getPersonGameByID(id).getWishlist();
    }

    public void setWishListByGameId(int id,String wish){
        getPersonGameByID(id).setWishlist(wish);
    }

    public void deletePersonGameByGameId(Integer id){
        games.remove(id);
    }

    public void setPersonGameActivityByGameId(Integer gameId, Boolean b){
        games.get(gameId).setActivity(b);
    }

    public String getOpenInfo(){
        return name + " (" + email + ") ";
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


