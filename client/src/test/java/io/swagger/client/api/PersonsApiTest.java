package io.swagger.client.api;

import io.swagger.client.ApiClient;
import io.swagger.client.model.Person;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for PersonsApi
 */
public class PersonsApiTest {

    private PersonsApi api;

    @Before
    public void setup() {
        api = new ApiClient().createService(PersonsApi.class);
    }


    /**
     * Add person
     *
     * Add person
     */
    @Test
    public void addPersonTest() {
        Person body = null;
        // Void response = api.addPerson(body);

        // TODO: test validations
    }

    /**
     * Delete person
     *
     * Delete person
     */
    @Test
    public void deletePersonTest() {
        String email = null;
        // Void response = api.deletePerson(email);

        // TODO: test validations
    }

    /**
     * Get all persons
     *
     * Get all Persons
     */
    @Test
    public void getAllPersonsTest() {
        // List<Person> response = api.getAllPersons();

        // TODO: test validations
    }

    /**
     * Get person by id
     *
     * Get person by id
     */
    @Test
    public void getPersonByIdTest() {
        String email = null;
        // Person response = api.getPersonById(email);

        // TODO: test validations
    }

    /**
     * Replace person
     *
     * Replace person
     */
    @Test
    public void replacePersonTest() {
        Person body = null;
        String email = null;
        // Void response = api.replacePerson(body, email);

        // TODO: test validations
    }
}
