package io.swagger.client.api;

import io.swagger.client.CollectionFormats.*;

import retrofit2.Call;
import retrofit2.http.*;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;

import io.swagger.client.model.PersonGame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface PersonGamesApi {
  /**
   * Add person game
   * Add person game
   * @param body Array emails (required)
   * @param email email (required)
   * @return Call&lt;Void&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("persons/{email}/persongame")
  Call<Void> addPersonGameToPerson(
                    @retrofit2.http.Body PersonGame body    ,         @retrofit2.http.Path("email") String email            
  );

  /**
   * Delete person game
   * Delete person game
   * @param email email (required)
   * @param id id (required)
   * @return Call&lt;Void&gt;
   */
  @DELETE("persons/{email}/persongame/{id}")
  Call<Void> deletePersonGameFromPerson(
            @retrofit2.http.Path("email") String email            ,         @retrofit2.http.Path("id") Integer id            
  );

  /**
   * Set naughty list
   * Set naughty list
   * @param body Person game (required)
   * @param id id (required)
   * @param email email (required)
   * @return Call&lt;Void&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("persons/{email}/persongame/{id}/naughtylist")
  Call<Void> setNaughtyList(
                    @retrofit2.http.Body List<String> body    ,         @retrofit2.http.Path("id") Integer id            ,         @retrofit2.http.Path("email") String email            
  );

  /**
   * Set active
   * Set active
   * @param id id (required)
   * @param email email (required)
   * @param active active (required)
   * @return Call&lt;Void&gt;
   */
  @POST("persons/{email}/persongame/{id}/active")
  Call<Void> setPersonGameActive(
            @retrofit2.http.Path("id") Integer id            ,         @retrofit2.http.Path("email") String email            ,     @retrofit2.http.Query("active") Boolean active                
  );

  /**
   * Set receiver
   * Set receiver
   * @param id id (required)
   * @param email email (required)
   * @param receiverEmail receiverEmail (required)
   * @return Call&lt;Void&gt;
   */
  @POST("persons/{email}/persongame/{id}/receiver")
  Call<Void> setReceiver(
            @retrofit2.http.Path("id") Integer id            ,         @retrofit2.http.Path("email") String email            ,     @retrofit2.http.Query("receiverEmail") String receiverEmail                
  );

  /**
   * Set whishlist
   * Set whishlist
   * @param id id (required)
   * @param email email (required)
   * @param wish wishlist (required)
   * @return Call&lt;Void&gt;
   */
  @POST("persons/{email}/persongame/{id}/wishlist")
  Call<Void> setWhishlist(
            @retrofit2.http.Path("id") Integer id            ,         @retrofit2.http.Path("email") String email            ,     @retrofit2.http.Query("wish") String wish                
  );

}
