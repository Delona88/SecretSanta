package com.example.secretsantaservi.quickplay;

import com.example.secretsantaservi.quickplay.Person;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class SecretSantaQuickGame implements Serializable {
    static final long serialVersionUID = 242430434818674244L;
    public ArrayList<Person> santas;

    private int idOfToss;

    private int numberOfParticipants;
    public ArrayList<Integer> mixArray = new ArrayList<>(); // не нужно хранить

    public SecretSantaQuickGame(int num) {
        santas = new ArrayList<>();
        this.numberOfParticipants = num;
    }

    public void addNewPerson(Person person) {
        santas.add(person);
    }

    public void addNewPersonByName(String name) {
        Person person = new Person(name);
        santas.add(person);
    }

    public void printSantas() {
        for (int i = 0; i < numberOfParticipants; i++) {
            santas.get(i).printPersonName();
            System.out.println();
        }
    }

    public void printSantaWithReceiver() {
        for (int i = 0; i < numberOfParticipants; i++) {
            santas.get(i).printPersonName();
            System.out.print(" дарит ");
            santas.get(i).getReceiver().printPersonName();
            System.out.println("----------");
        }
    }

    public ArrayList<Integer> createMixArray(int num) { //массив соответствия санты и получателя, в котором элемент не равен индексу и проверяется naughtyList санты
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

    public boolean isPersonInSantaNaughtyList(Person santa, Person receiver) {
        if (santa.isPersonInNaughtyList(receiver)) {
            return true;
        }
        return false;
    }

    public void fillReceivers() {
        mixArray = createMixArray(numberOfParticipants);
        for (int i = 0; i < numberOfParticipants; i++) {
            Person santa = getPersonByIndex(i); //понятнее??
            Person receiver = getPersonByIndex(mixArray.get(i));
            santa.setReceiver(receiver);
            //santas.get(i).setReceiver(santas.get((Integer) mixArray.get(i))); //короче
        }
    }



    public Person getReceiverBySantasIndex(int index){
        return santas.get((Integer) mixArray.get(index));
    }

    public String getNameReceiverBySantasIndex(int index){
        return getReceiverBySantasIndex(index).getName();
    }

    public Person getPersonByIndex(int index){
        return santas.get(index);
    }

    public void addParticipantInSantasNaughtyListByIndexes(int indexSanta,int indexParicipants){

    }

    public String getNameByIndex(int index){
        return santas.get(index).getName();
    }

    public int getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public void setIdOfToss(int idOfToss) {
        this.idOfToss = idOfToss;
    }

    public int getIdOfToss() {
        return idOfToss;
    }

    public ArrayList<Person> getSantas() {
        return santas;
    }
}

