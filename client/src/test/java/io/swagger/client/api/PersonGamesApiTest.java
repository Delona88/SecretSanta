package io.swagger.client.api;

import io.swagger.client.ApiClient;
import io.swagger.client.model.PersonGame;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for PersonGamesApi
 */
public class PersonGamesApiTest {

    private PersonGamesApi api;

    @Before
    public void setup() {
        api = new ApiClient().createService(PersonGamesApi.class);
    }


    /**
     * Add person game
     *
     * Add person game
     */
    @Test
    public void addPersonGameToPersonTest() {
        PersonGame body = null;
        String email = null;
        // Void response = api.addPersonGameToPerson(body, email);

        // TODO: test validations
    }

    /**
     * Delete person game
     *
     * Delete person game
     */
    @Test
    public void deletePersonGameFromPersonTest() {
        String email = null;
        Integer id = null;
        // Void response = api.deletePersonGameFromPerson(email, id);

        // TODO: test validations
    }

    /**
     * Set naughty list
     *
     * Set naughty list
     */
    @Test
    public void setNaughtyListTest() {
        List<String> body = null;
        Integer id = null;
        String email = null;
        // Void response = api.setNaughtyList(body, id, email);

        // TODO: test validations
    }

    /**
     * Set active
     *
     * Set active
     */
    @Test
    public void setPersonGameActiveTest() {
        Integer id = null;
        String email = null;
        Boolean active = null;
        // Void response = api.setPersonGameActive(id, email, active);

        // TODO: test validations
    }

    /**
     * Set receiver
     *
     * Set receiver
     */
    @Test
    public void setReceiverTest() {
        Integer id = null;
        String email = null;
        String receiverEmail = null;
        // Void response = api.setReceiver(id, email, receiverEmail);

        // TODO: test validations
    }

    /**
     * Set whishlist
     *
     * Set whishlist
     */
    @Test
    public void setWhishlistTest() {
        Integer id = null;
        String email = null;
        String wish = null;
        // Void response = api.setWhishlist(id, email, wish);

        // TODO: test validations
    }
}
