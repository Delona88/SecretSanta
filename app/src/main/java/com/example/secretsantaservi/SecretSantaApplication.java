package com.example.secretsantaservi;

import android.app.Application;
import android.widget.Toast;
import io.swagger.client.secretsantaclient.ApiWithMyCallbackInterface;

public class SecretSantaApplication extends Application {

    private ApiWithMyCallbackInterface client;

    private String authorizedPersonEmail;
    private Integer currentGameId;
    private String currentPersonEmail;

    public static final String APP_PREFERENCE = "Settings";
    public static final String APP_PREFERENCE_AUTHORIZED_PERSON_EMAIL = "AuthorizedPersonInfo";

    public final static String FILE_NAME_DB_GAMES = "Games.db";
    public final static String FILE_NAME_DB_PERSONS = "Persons.db";

    public void setClient(ApiWithMyCallbackInterface client) {
        this.client = client;
    }

    public ApiWithMyCallbackInterface getClient() {
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








    //перенесены в activity
    public void showServerProblemToast(String str1) { //временно выдает ошибку
        String str = getResources().getString(R.string.title_server_problem) + str1;
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    public void showToast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    public void showToast(String str, String arg1) {
        String str1 = String.format(str, arg1);
        Toast.makeText(this, str1, Toast.LENGTH_SHORT).show();
    }

    public void showToast(String str, String arg1, String arg2) {
        String str1 = String.format(str, arg1, arg2);
        Toast.makeText(this, str1, Toast.LENGTH_SHORT).show();
    }

    public void showToastWithStringRes(int strResId) {
        Toast.makeText(this, getResources().getString(strResId), Toast.LENGTH_SHORT).show();
    }

    public void showToast(int strResId, String arg1) {
        String str = String.format(getResources().getString(strResId), arg1);
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    public void showToast(int strResId, String arg1, String arg2) {
        String str = String.format(getResources().getString(strResId), arg1, arg2);
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

}
