package com.example.secretsantaservi.quickplay;

import com.example.secretsantaservi.quickplay.Person;

import java.util.ArrayList;
import java.util.Collections;

public class Toss {
    private ArrayList<Person> participants;
    private ArrayList<Integer> mixArray = new ArrayList<>(); // не нужно хранить


    public Toss(ArrayList<Person> participants) {
        this.participants = participants;
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
                if (array.get(i) == i || isPersonInSantaNaughtyList(participants.get(i), participants.get(array.get(i)))) {
                    okMix = false;
                }
            }
        }
        return array;
    }

    private boolean isPersonInSantaNaughtyList(Person santa, Person receiver) {
        if (santa.isPersonInNaughtyList(receiver)) {
            return true;
        }
        return false;
    }

    public void fillReceivers() {
        int numberOfParticipants = participants.size();
        mixArray = createMixArray(numberOfParticipants);
        for (int i = 0; i < numberOfParticipants; i++) {
            Person santa = getPersonByIndex(i); //понятнее??
            Person receiver = getPersonByIndex(mixArray.get(i));
            santa.setReceiver(receiver);
            //santas.get(i).setReceiver(santas.get((Integer) mixArray.get(i))); //короче
        }
    }

    private Person getPersonByIndex(int index){
        return participants.get(index);
    }

}
