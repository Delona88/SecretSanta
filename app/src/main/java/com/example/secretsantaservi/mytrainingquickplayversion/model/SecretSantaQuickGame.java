package com.example.secretsantaservi.mytrainingquickplayversion.model;

import java.util.ArrayList;
import java.util.Collections;

public class SecretSantaQuickGame {
    private final ArrayList<PersonForQuickGame> participants = new ArrayList<>();

    public void fillReceivers() throws BadConditionsException {
        ArrayList<Integer> mixArray = createMixArray(participants.size());
        for (int i = 0; i < participants.size(); i++) {
            PersonForQuickGame santa = getPersonByIndex(i);
            PersonForQuickGame receiver = getPersonByIndex(mixArray.get(i));
            santa.setReceiver(receiver);
        }
    }

    private ArrayList<Integer> createMixArray(int num) throws BadConditionsException {
        ArrayList<Integer> array = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            array.add(i);
        }
        boolean okMix = false;
        int count = 0;
        while (!okMix && count < 1000) {
            Collections.shuffle(array);
            okMix = true;
            for (int i = 0; i < num; i++) {
                if (array.get(i) == i || isPersonInSantaNaughtyList(participants.get(i), participants.get(array.get(i)))) {
                    okMix = false;
                }
            }
            count++;
        }
        if (count == 100) {
            throw new BadConditionsException();
        }
        return array;
    }

    public ArrayList<String> getParticipantsNames() {
        ArrayList<String> names = new ArrayList<>();
        for (PersonForQuickGame person : participants) {
            names.add(person.getName());
        }
        return names;
    }

    public ArrayList<String> getParticipantsNamesWithoutSanta(int santaId) {
        ArrayList<String> personNames = new ArrayList<>(getParticipantsNames());
        personNames.remove(participants.get(santaId).getName());
        return personNames;
    }

    public PersonForQuickGame getPersonByName(String name) {
        for (PersonForQuickGame person : participants) {
            if (person.getName().equals(name)) {
                return person;
            }
        }
        return null;
    }

    public boolean isPersonInSantaNaughtyList(PersonForQuickGame santa, PersonForQuickGame receiver) {
        return santa.isPersonInNaughtyList(receiver);
    }

    public void addNewPersonByName(String name) {
        PersonForQuickGame person = new PersonForQuickGame(name);
        participants.add(person);
    }

    public PersonForQuickGame getReceiverBySantaIndex(int index) {
        return participants.get(index).getReceiver();
    }

    public String getNameReceiverBySantaIndex(int index) {
        return getReceiverBySantaIndex(index).getName();
    }

    public PersonForQuickGame getPersonByIndex(int index) {
        return participants.get(index);
    }

    public String getNameByIndex(int index) {
        return participants.get(index).getName();
    }

    public int getNumberOfParticipants() {
        return participants.size();
    }

    @Override
    public String toString() {
        String str = "";
        for (PersonForQuickGame personQuickGame : participants) {
            str += personQuickGame.getName() + "->" + personQuickGame.getNaughtyList().toString() + "\n";
        }
        return str;
    }
}

