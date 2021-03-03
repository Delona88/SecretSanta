package com.example.secretsantaservi.quickplay.model;

import java.util.ArrayList;

public class PersonQuickGame {
    private String name;
    public ArrayList<PersonQuickGame> naughtyList = new ArrayList<>(); // не попадают в список санты
    public PersonQuickGame receiver;

    public PersonQuickGame(String name) {
        this.name = name;
    }

    public void setReceiver(PersonQuickGame receiver) {
        this.receiver = receiver;
    }

    public String getName() {
        return name;
    }

    public PersonQuickGame getReceiver() {
        return receiver;
    }

    public boolean isPersonInNaughtyList(PersonQuickGame person){
        return naughtyList.contains(person);
    }

    public void addInNaughtyList(PersonQuickGame person){
        naughtyList.add(person);
    }

/*    public String getNamesFromNaughtyList(){
        String namesFromNaughtyList = "";
        for (int i=0;i<naughtyList.size(); i++){
            namesFromNaughtyList += " " + naughtyList.get(i).getName();
        }
        return namesFromNaughtyList;
    }*/
}


