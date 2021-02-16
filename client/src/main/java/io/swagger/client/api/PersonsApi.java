package io.swagger.client.api;

import io.swagger.client.CollectionFormats.*;

import retrofit2.Call;
import retrofit2.http.*;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;

import io.swagger.client.model.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface PersonsApi {
  /**
   * Add person
   * Add person
   * @param body Person (required)
   * @return Call&lt;Void&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("persons")
  Call<Void> addPerson(
                    @retrofit2.http.Body Person body    
  );

  /**
   * Delete person
   * Delete person
   * @param email email (required)
   * @return Call&lt;Void&gt;
   */
  @DELETE("persons/{email}")
  Call<Void> deletePerson(
            @retrofit2.http.Path("email") String email            
  );

  /**
   * Get all persons
   * Get all Persons
   * @return Call&lt;List&lt;Person&gt;&gt;
   */
  @GET("persons")
  Call<List<Person>> getAllPersons();
    

  /**
   * Get person by id
   * Get person by id
   * @param email to get person (required)
   * @return Call&lt;Person&gt;
   */
  @GET("persons/{email}")
  Call<Person> getPersonById(
            @retrofit2.http.Path("email") String email            
  );

  /**
   * Replace person
   * Replace person
   * @param body Person (required)
   * @param email to get person (required)
   * @return Call&lt;Void&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @PUT("persons/{email}")
  Call<Void> replacePerson(
                    @retrofit2.http.Body Person body    ,         @retrofit2.http.Path("email") String email            
  );

}
