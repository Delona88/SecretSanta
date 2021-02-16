package com.example.secretsantaservi.secretsanta;

import java.io.Serializable;
import java.util.ArrayList;

public class SecretSantaGame implements Serializable {
    static final long serialVersionUID = 242430434818674244L;

    private int gameId;
    private boolean played = false; //true - розыгрыш проведен, назначены получатели
    private ArrayList<String> participantsEmail = new ArrayList<>();

    public SecretSantaGame(int id) {
        this.gameId = id;
    }

    public SecretSantaGame(int id, boolean played, ArrayList<String> participantsEmail) {
        this.gameId = id;
        this.played = played;
        this.participantsEmail = participantsEmail;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setArrayParticipantsEmail(ArrayList<String> emails){ // без проверки
        participantsEmail = emails;
    }

    public ArrayList<String> getArrayParticipantsEmail(){
        return participantsEmail;
    }

    public void addNewPersonInGameById(String id) {
        if (!participantsEmail.contains(id)) {
            participantsEmail.add(id);
        }
    }

    public void deleteEmailFromArrayParticipantsEmail(String email){
        participantsEmail.remove(email);
    }

    public boolean isPlayed() {
        return played;
    }

    public void setPlayed(boolean played) {
        this.played = played;
    }

}

