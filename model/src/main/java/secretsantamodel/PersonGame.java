package secretsantamodel;

import java.io.Serializable;
import java.util.ArrayList;

public class PersonGame implements Serializable {
    static final long serialVersionUID = 3896515597055585980L;

    private int gameId;
    private ArrayList<String> arrayNaughtyListEmail = new ArrayList<>();
    private String receiverEmail;
    private String wishlist;
    private boolean active = false; //true - розыгрыш проведен, назначен получатель

    public PersonGame(int gameId) {
        this.gameId = gameId;
    }

    public PersonGame(int gameId, String receiverEmail, String wishlist, boolean active, ArrayList<String> arrayNaughtyListEmail) {
        this.gameId = gameId;
        this.wishlist = wishlist;
        this.active = active;
        this.receiverEmail = receiverEmail;
        this.arrayNaughtyListEmail = arrayNaughtyListEmail;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public ArrayList<String> getArrayNaughtyListEmail() {
        return arrayNaughtyListEmail;
    }

    public void setArrayNaughtyListEmail(ArrayList<String> arrayNaughtyListEmail) {
        this.arrayNaughtyListEmail = arrayNaughtyListEmail;
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
                ", arrayNaughtyListEmail=" + arrayNaughtyListEmail +
                ", receiverEmail='" + receiverEmail + '\'' +
                ", wishlist='" + wishlist + '\'' +
                ", active=" + active +
                '}';
    }
}
