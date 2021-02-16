package io.swagger.client.api;

import io.swagger.client.ApiClient;
import io.swagger.client.model.Person;
import io.swagger.client.model.SecretSantaGame;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for GamesApi
 */
public class GamesApiTest {

    private GamesApi api;

    @Before
    public void setup() {
        api = new ApiClient().createService(GamesApi.class);
    }


    /**
     * Add game
     *
     * Create new game
     */
    @Test
    public void addGameTest() {
        SecretSantaGame body = null;
        // Void response = api.addGame(body);

        // TODO: test validations
    }

    /**
     * Add participant
     *
     * Add participant
     */
    @Test
    public void addPersonInGameTest() {
        Integer id = null;
        String email = null;
        // Void response = api.addPersonInGame(id, email);

        // TODO: test validations
    }

    /**
     * Delete game
     *
     * Delete game
     */
    @Test
    public void deleteGameTest() {
        Integer id = null;
        // Void response = api.deleteGame(id);

        // TODO: test validations
    }

    /**
     * Delete participant
     *
     * Delete participant
     */
    @Test
    public void deletePersonFromGameTest() {
        Integer id = null;
        String email = null;
        // Void response = api.deletePersonFromGame(id, email);

        // TODO: test validations
    }

    /**
     * Get game by id
     *
     * Get game by id
     */
    @Test
    public void getGameByIdTest() {
        Integer id = null;
        // SecretSantaGame response = api.getGameById(id);

        // TODO: test validations
    }

    /**
     * Get new ID
     *
     * Get new ID
     */
    @Test
    public void getNewIDTest() {
        // Integer response = api.getNewID();

        // TODO: test validations
    }

    /**
     * Get all persons by game ID
     *
     * Get all persons by game ID
     */
    @Test
    public void getPersonsByGameIdTest() {
        Integer id = null;
        // List<Person> response = api.getPersonsByGameId(id);

        // TODO: test validations
    }

    /**
     * Set played
     *
     * Set played
     */
    @Test
    public void setGamePlayedTest() {
        Integer id = null;
        Boolean played = null;
        // Void response = api.setGamePlayed(id, played);

        // TODO: test validations
    }

    /**
     * Start toss
     *
     * Start toss
     */
    @Test
    public void startTossTest() {
        Integer id = null;
        // Void response = api.startToss(id);

        // TODO: test validations
    }
}
