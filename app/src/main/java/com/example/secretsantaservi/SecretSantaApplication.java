package com.example.secretsantaservi;

import android.app.Application;
import android.widget.Toast;
import io.swagger.client.secretsantaclient.ApiWithCallbackInterface;

public class SecretSantaApplication extends Application {

    private ApiWithCallbackInterface client;

    private String authorizedPersonEmail;
    private Integer currentGameId;
    private String currentPersonEmail;

    public static final String APP_PREFERENCE = "Settings";
    public static final String APP_PREFERENCE_AUTHORIZED_PERSON_EMAIL = "AuthorizedPersonInfo";

    public final static String FILE_NAME_DB_GAMES = "Games.db";
    public final static String FILE_NAME_DB_PERSONS = "Persons.db";

    public void setClient(ApiWithCallbackInterface client) {
        this.client = client;
    }

    public ApiWithCallbackInterface getClient() {
        return client;
    }

    public String getAuthorizedPersonEmail() {
        return authorizedPersonEmail;
    }

    public void setAuthorizedPersonEmail(String authorizedPersonEmail) {
        this.authorizedPersonEmail = authorizedPersonEmail;
    }

    public Integer getCurrentGameId() {
        return currentGameId;
    }

    public void setCurrentGameId(Integer currentGameId) {
        this.currentGameId = currentGameId;
    }

    public String getCurrentPersonEmail() {
        return currentPersonEmail;
    }

    public void setCurrentPersonEmail(String currentPersonEmail) {
        this.currentPersonEmail = currentPersonEmail;
    }


}
