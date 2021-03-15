package secretsantamodel;

import java.util.ArrayList;
import java.util.Collections;

public class Toss {//для локальной игры, для серверной логика на сервере
    private ArrayList<Person> participants;
    private ArrayList<Integer> mixArray = new ArrayList<>(); // не нужно хранить
    private int gameId;

    public Toss(ArrayList<Person> participants, int gameId) {
        this.gameId = gameId;
        this.participants = participants;
    }

    public ArrayList<Person> fillReceiversAndReturnParticipantsArray() throws BadConditionsException {
        int numberOfParticipants = participants.size();
        mixArray = createMixArray2(numberOfParticipants);
        for (int i = 0; i < numberOfParticipants; i++) {
            Person santa = participants.get(i); //понятнее??
            Person receiver = participants.get(mixArray.get(i));
            santa.setReceiverByGameId(receiver, gameId);
            //santas.get(i).setReceiver(santas.get((Integer) mixArray.get(i))); //короче
        }
        return participants;
    }

    public ArrayList<Integer> createMixArray2(int num) throws BadConditionsException { //массив соответствия санты и получателя, в котором элемент не равен индексу и проверяется naughtyList санты
        ArrayList<Integer> array = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            array.add(i);
        }
        boolean okMix = false;
        int numIterations = 0;
        while (!okMix && numIterations < 1000) {
            Collections.shuffle(array);
            okMix = true;
            for (int i = 0; i < num; i++) {
                if (array.get(i) == i || isPersonInSantaNaughtyListEmail(participants.get(i), participants.get(array.get(i)).getEmail())) {
                    okMix = false;
                }
            }
            numIterations++;
        }
        if (numIterations == 1000) {
            throw new BadConditionsException();
        }
        return array;
    }

    private boolean isPersonInSantaNaughtyListEmail(Person santa, String emailReceiver) {
        ArrayList<String> arrayNaughtyListEmail;
        arrayNaughtyListEmail = santa.getArrayNaughtyListEmailByGameId(gameId);
/*        if (arrayNaughtyListEmail==null){
            return false;
        }*/
        return arrayNaughtyListEmail.contains(emailReceiver);
    }


}
