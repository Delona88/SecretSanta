package com.example.secretsantaservi.secretsanta;

public class PersonController {
    private String personId;
    private Person person;

    public PersonController() {
    }

    public PersonController(String id){
        this.personId = id;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

}
