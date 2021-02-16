package com.example.secretsantaservi.quickplay;

import java.io.Serializable;
import java.util.ArrayList;

public class Person implements Serializable {
    static final long serialVersionUID = 8208402250750954819L;
    private String email; //уникальный идентификатор участника
    private String name;
    public ArrayList<Person> naughtyList = new ArrayList<>(); // не попадают в список санты
    public Person receiver;
    private String wishlist; //необязательный

    public Person(String name) {
        this.name = name;
    }

    public void setWishlist(String wishlist) {
        this.wishlist = wishlist;
    }

    public void setReceiver(Person receiver) {
        this.receiver = receiver;
    }

    public String getName() {
        return name;
    }

    public String getWishlist() {
        return wishlist;
    }

    public Person getReceiver() {
        return receiver;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isPersonInNaughtyList(Person person){
        return naughtyList.contains(person);
/*        for (int i = 0; i< naughtyList.size(); i++){
            if (person == naughtyList.get(i)){
                return true;
            }
        }
        return false;*/
    }

    public void addInNaughtyList(Person person){
        naughtyList.add(person);
    }


    public void printPersonName() {
        System.out.print(name);
    }


    public String getNamesFromNaughtyList(){
        String namesFromNaughtyList = "";
        for (int i=0;i<naughtyList.size(); i++){
            namesFromNaughtyList += " " + naughtyList.get(i).getName();
        }
        return namesFromNaughtyList;
    }
}


