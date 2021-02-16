package io.swagger.client.api;

import io.swagger.client.CollectionFormats.*;

import retrofit2.Call;
import retrofit2.http.*;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;

import io.swagger.client.model.Person;
import io.swagger.client.model.SecretSantaGame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface GamesApi {
  /**
   * Add game
   * Create new game
   * @param body game to add (required)
   * @return Call&lt;Void&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("games")
  Call<Void> addGame(
                    @retrofit2.http.Body SecretSantaGame body    
  );

  /**
   * Add participant
   * Add participant
   * @param id id (required)
   * @param email email (required)
   * @return Call&lt;Void&gt;
   */
  @POST("games/{id}/participants/{email}")
  Call<Void> addPersonInGame(
            @retrofit2.http.Path("id") Integer id            ,         @retrofit2.http.Path("email") String email            
  );

  /**
   * Delete game
   * Delete game
   * @param id id (required)
   * @return Call&lt;Void&gt;
   */
  @DELETE("games/{id}")
  Call<Void> deleteGame(
            @retrofit2.http.Path("id") Integer id            
  );

  /**
   * Delete participant
   * Delete participant
   * @param id id (required)
   * @param email email (required)
   * @return Call&lt;Void&gt;
   */
  @DELETE("games/{id}/participants/{email}")
  Call<Void> deletePersonFromGame(
            @retrofit2.http.Path("id") Integer id            ,         @retrofit2.http.Path("email") String email            
  );

  /**
   * Get game by id
   * Get game by id
   * @param id to get game (required)
   * @return Call&lt;SecretSantaGame&gt;
   */
  @GET("games/{id}")
  Call<SecretSantaGame> getGameById(
            @retrofit2.http.Path("id") Integer id            
  );

  /**
   * Get new ID
   * Get new ID
   * @return Call&lt;Integer&gt;
   */
  @GET("games")
  Call<Integer> getNewID();
    

  /**
   * Get all persons by game ID
   * Get all persons by game ID
   * @param id id (required)
   * @return Call&lt;List&lt;Person&gt;&gt;
   */
  @GET("games/{id}/participants")
  Call<List<Person>> getPersonsByGameId(
            @retrofit2.http.Path("id") Integer id            
  );

  /**
   * Set played
   * Set played
   * @param id id (required)
   * @param played played (required)
   * @return Call&lt;Void&gt;
   */
  @POST("games/{id}/played")
  Call<Void> setGamePlayed(
            @retrofit2.http.Path("id") Integer id            ,     @retrofit2.http.Query("played") Boolean played                
  );

  /**
   * Start toss
   * Start toss
   * @param id id (required)
   * @return Call&lt;Void&gt;
   */
  @POST("games/{id}/toss")
  Call<Void> startToss(
            @retrofit2.http.Path("id") Integer id            
  );

}
