package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.model.PersonGame;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Person
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-02-16T11:27:20.662Z[GMT]")


public class Person   {
  @JsonProperty("email")
  private String email = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("games")
  @Valid
  private List<PersonGame> games = null;

  public Person email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Get email
   * @return email
   **/
  @Schema(description = "")
  
    public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Person name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
   **/
  @Schema(description = "")
  
    public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Person games(List<PersonGame> games) {
    this.games = games;
    return this;
  }

  public Person addGamesItem(PersonGame gamesItem) {
    if (this.games == null) {
      this.games = new ArrayList<PersonGame>();
    }
    this.games.add(gamesItem);
    return this;
  }

  /**
   * Get games
   * @return games
   **/
  @Schema(description = "")
      @Valid
    public List<PersonGame> getGames() {
    return games;
  }

  public void setGames(List<PersonGame> games) {
    this.games = games;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Person person = (Person) o;
    return Objects.equals(this.email, person.email) &&
        Objects.equals(this.name, person.name) &&
        Objects.equals(this.games, person.games);
  }

  @Override
  public int hashCode() {
    return Objects.hash(email, name, games);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Person {\n");
    
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    games: ").append(toIndentedString(games)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
