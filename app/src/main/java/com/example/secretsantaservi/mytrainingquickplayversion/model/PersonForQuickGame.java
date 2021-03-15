package com.example.secretsantaservi.mytrainingquickplayversion.model;

import java.util.ArrayList;

public class PersonForQuickGame {
    private final String name;
    private ArrayList<PersonForQuickGame> naughtyList = new ArrayList<>();
    private PersonForQuickGame receiver;

    public PersonForQuickGame(String name) {
        this.name = name;
    }

    public void setReceiver(PersonForQuickGame receiver) {
        this.receiver = receiver;
    }

    public String getName() {
        return name;
    }

    public PersonForQuickGame getReceiver() {
        return receiver;
    }

    public boolean isPersonInNaughtyList(PersonForQuickGame person){
        return naughtyList.contains(person);
    }

    public void addInNaughtyList(PersonForQuickGame person){
        naughtyList.add(person);
    }

    public ArrayList<PersonForQuickGame> getNaughtyList() {
        return naughtyList;
    }

    public void setNaughtyList(ArrayList<PersonForQuickGame> naughtyList){
        this.naughtyList = naughtyList;
    }

    @Override
    public String toString() {
        return "PersonQuickGame{" +
                "name='" + name + '\'' +
                '}';
    }
}


