package com.example.secretsantaservi.quickplayversion.model;

import java.util.ArrayList;

public class PersonQuickGame {
    private final String name;
    private ArrayList<PersonQuickGame> naughtyList = new ArrayList<>();
    private PersonQuickGame receiver;

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

}


