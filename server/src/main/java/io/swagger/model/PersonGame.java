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
 * PersonGame
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-02-16T11:27:20.662Z[GMT]")


public class PersonGame   {
  @JsonProperty("idGame")
  private Integer idGame = null;

  @JsonProperty("receiverEmail")
  private String receiverEmail = null;

  @JsonProperty("wishlist")
  private String wishlist = null;

  @JsonProperty("active")
  private Boolean active = null;

  @JsonProperty("arrayNaughtyListEmail")
  @Valid
  private List<String> arrayNaughtyListEmail = null;

  public PersonGame idGame(Integer idGame) {
    this.idGame = idGame;
    return this;
  }

  /**
   * Get idGame
   * @return idGame
   **/
  @Schema(description = "")
  
    public Integer getIdGame() {
    return idGame;
  }

  public void setIdGame(Integer idGame) {
    this.idGame = idGame;
  }

  public PersonGame receiverEmail(String receiverEmail) {
    this.receiverEmail = receiverEmail;
    return this;
  }

  /**
   * Get receiverEmail
   * @return receiverEmail
   **/
  @Schema(description = "")
  
    public String getReceiverEmail() {
    return receiverEmail;
  }

  public void setReceiverEmail(String receiverEmail) {
    this.receiverEmail = receiverEmail;
  }

  public PersonGame wishlist(String wishlist) {
    this.wishlist = wishlist;
    return this;
  }

  /**
   * Get wishlist
   * @return wishlist
   **/
  @Schema(description = "")
  
    public String getWishlist() {
    return wishlist;
  }

  public void setWishlist(String wishlist) {
    this.wishlist = wishlist;
  }

  public PersonGame active(Boolean active) {
    this.active = active;
    return this;
  }

  /**
   * Get active
   * @return active
   **/
  @Schema(description = "")
  
    public Boolean isActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public PersonGame arrayNaughtyListEmail(List<String> arrayNaughtyListEmail) {
    this.arrayNaughtyListEmail = arrayNaughtyListEmail;
    return this;
  }

  public PersonGame addArrayNaughtyListEmailItem(String arrayNaughtyListEmailItem) {
    if (this.arrayNaughtyListEmail == null) {
      this.arrayNaughtyListEmail = new ArrayList<String>();
    }
    this.arrayNaughtyListEmail.add(arrayNaughtyListEmailItem);
    return this;
  }

  /**
   * Get arrayNaughtyListEmail
   * @return arrayNaughtyListEmail
   **/
  @Schema(description = "")
  
    public List<String> getArrayNaughtyListEmail() {
    return arrayNaughtyListEmail;
  }

  public void setArrayNaughtyListEmail(List<String> arrayNaughtyListEmail) {
    this.arrayNaughtyListEmail = arrayNaughtyListEmail;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PersonGame personGame = (PersonGame) o;
    return Objects.equals(this.idGame, personGame.idGame) &&
        Objects.equals(this.receiverEmail, personGame.receiverEmail) &&
        Objects.equals(this.wishlist, personGame.wishlist) &&
        Objects.equals(this.active, personGame.active) &&
        Objects.equals(this.arrayNaughtyListEmail, personGame.arrayNaughtyListEmail);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idGame, receiverEmail, wishlist, active, arrayNaughtyListEmail);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PersonGame {\n");
    
    sb.append("    idGame: ").append(toIndentedString(idGame)).append("\n");
    sb.append("    receiverEmail: ").append(toIndentedString(receiverEmail)).append("\n");
    sb.append("    wishlist: ").append(toIndentedString(wishlist)).append("\n");
    sb.append("    active: ").append(toIndentedString(active)).append("\n");
    sb.append("    arrayNaughtyListEmail: ").append(toIndentedString(arrayNaughtyListEmail)).append("\n");
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
