package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * SecretSantaGame
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-02-16T11:27:20.662Z[GMT]")


public class SecretSantaGame   {
  @JsonProperty("idOfGame")
  private Integer idOfGame = null;

  @JsonProperty("played")
  private Boolean played = null;

  @JsonProperty("participantsEmail")
  @Valid
  private List<String> participantsEmail = null;

  public SecretSantaGame idOfGame(Integer idOfGame) {
    this.idOfGame = idOfGame;
    return this;
  }

  /**
   * Get idOfGame
   * @return idOfGame
   **/
  @Schema(description = "")
  
    public Integer getIdOfGame() {
    return idOfGame;
  }

  public void setIdOfGame(Integer idOfGame) {
    this.idOfGame = idOfGame;
  }

  public SecretSantaGame played(Boolean played) {
    this.played = played;
    return this;
  }

  /**
   * Get played
   * @return played
   **/
  @Schema(description = "")
  
    public Boolean isPlayed() {
    return played;
  }

  public void setPlayed(Boolean played) {
    this.played = played;
  }

  public SecretSantaGame participantsEmail(List<String> participantsEmail) {
    this.participantsEmail = participantsEmail;
    return this;
  }

  public SecretSantaGame addParticipantsEmailItem(String participantsEmailItem) {
    if (this.participantsEmail == null) {
      this.participantsEmail = new ArrayList<String>();
    }
    this.participantsEmail.add(participantsEmailItem);
    return this;
  }

  /**
   * Get participantsEmail
   * @return participantsEmail
   **/
  @Schema(description = "")
  
    public List<String> getParticipantsEmail() {
    return participantsEmail;
  }

  public void setParticipantsEmail(List<String> participantsEmail) {
    this.participantsEmail = participantsEmail;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SecretSantaGame secretSantaGame = (SecretSantaGame) o;
    return Objects.equals(this.idOfGame, secretSantaGame.idOfGame) &&
        Objects.equals(this.played, secretSantaGame.played) &&
        Objects.equals(this.participantsEmail, secretSantaGame.participantsEmail);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idOfGame, played, participantsEmail);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SecretSantaGame {\n");
    
    sb.append("    idOfGame: ").append(toIndentedString(idOfGame)).append("\n");
    sb.append("    played: ").append(toIndentedString(played)).append("\n");
    sb.append("    participantsEmail: ").append(toIndentedString(participantsEmail)).append("\n");
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
