package com.example.secretsantaservi.quickplay.model;

import java.util.ArrayList;
import java.util.Collections;

public class SecretSantaQuickGame {
    public ArrayList<PersonQuickGame> santas;
    private final int numberOfParticipants;
    public ArrayList<Integer> mixArray = new ArrayList<>(); // не нужно хранить

    public SecretSantaQuickGame(int num) {
        santas = new ArrayList<>();
        this.numberOfParticipants = num;
    }

    public void fillReceivers() {
        mixArray = createMixArray(numberOfParticipants);
        for (int i = 0; i < numberOfParticipants; i++) {
            PersonQuickGame santa = getPersonByIndex(i);
            PersonQuickGame receiver = getPersonByIndex(mixArray.get(i));
            santa.setReceiver(receiver);
        }
    }
    private ArrayList<Integer> createMixArray(int num) { //массив соответствия санты и получателя, в котором элемент не равен индексу и проверяется naughtyList санты
        ArrayList<Integer> array = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            array.add(i);
        }
        boolean okMix = false;
        while (!okMix) {
            Collections.shuffle(array);
            okMix = true;
            for (int i = 0; i < num; i++) {
                if (array.get(i) == i || isPersonInSantaNaughtyList(santas.get(i), santas.get(array.get(i)))) {
                    okMix = false;
                }
            }
        }
        return array;
    }

    public boolean isPersonInSantaNaughtyList(PersonQuickGame santa, PersonQuickGame receiver) {
        if (santa.isPersonInNaughtyList(receiver)) {
            return true;
        }
        return false;
    }

    public void addNewPersonByName(String name) {
        PersonQuickGame person = new PersonQuickGame(name);
        santas.add(person);
    }

    public PersonQuickGame getReceiverBySantaIndex(int index){
        return santas.get(mixArray.get(index));
    }

    public String getNameReceiverBySantasIndex(int index){
        //return santas.get(index).getReceiver().getName();
        return getReceiverBySantaIndex(index).getName();
    }

    public PersonQuickGame getPersonByIndex(int index){
        return santas.get(index);
    }

    public String getNameByIndex(int index){
        return santas.get(index).getName();
    }

    public int getNumberOfParticipants() {
        return numberOfParticipants;
    }

}

