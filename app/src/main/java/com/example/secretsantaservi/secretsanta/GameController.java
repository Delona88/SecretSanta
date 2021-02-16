package com.example.secretsantaservi.secretsanta;

import java.util.ArrayList;
import java.util.HashMap;

public class GameController {
    Integer gameId;
    private boolean allParticipantsAdded = false;

    public GameController(int id) {
        this.gameId = id;
    }

    public GameController() {
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer id) {
        this.gameId = id;
    }

    public void setAllParticipantsAddedTrue (){
        this.allParticipantsAdded = true;
    }

    public boolean isAllParticipantsAdded(){
        return allParticipantsAdded;
    }
}

