package secretsantamodel;

import java.io.Serializable;
import java.util.ArrayList;

public class PersonGame implements Serializable {

    static final long serialVersionUID = 3896515597055585980L;

    private int gameId;

    private ArrayList<String> arrayNaughtyListEmails = new ArrayList<>();

    private String receiverEmail;

    private String wishlist;

    private boolean active = false;

    public PersonGame(int gameId) {
        this.gameId = gameId;
    }

    public PersonGame(int gameId, String receiverEmail, String wishlist,
                      boolean active, ArrayList<String> arrayNaughtyListEmails) {
        this.gameId = gameId;
        this.wishlist = wishlist;
        this.active = active;
        this.receiverEmail = receiverEmail;
        this.arrayNaughtyListEmails = arrayNaughtyListEmails;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public ArrayList<String> getArrayNaughtyListEmails() {
        return arrayNaughtyListEmails;
    }

    public void setArrayNaughtyListEmails(ArrayList<String> arrayNaughtyListEmails) {
        this.arrayNaughtyListEmails = arrayNaughtyListEmails;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
        setActivity(true);
    }

    public String getWishlist() {
        return wishlist;
    }

    public void setWishlist(String wishlist) {
        this.wishlist = wishlist;
    }

    public void setActivity(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    @Override
    public String toString() {
        return "PersonGame{" +
                "gameId=" + gameId +
                ", arrayNaughtyListEmail=" + arrayNaughtyListEmails +
                ", receiverEmail='" + receiverEmail + '\'' +
                ", wishlist='" + wishlist + '\'' +
                ", active=" + active +
                '}';
    }
}
