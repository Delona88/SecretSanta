package com.example.secretsantaservi;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;
import com.example.secretsantaservi.API.ApiWithMyCallbackInterface;
import com.example.secretsantaservi.secretsanta.*;

public class SecretSantaApplication extends Application {
    private GameController newGameController;
    private GameController existGameController;

    private PersonController newPersonController; //
    private PersonController authorizedPersonController; //хранит информацию о авторизованном игроке

    private String authorizedPersonEmail;
    private Integer currentGameId;
    private String currentPersonEmail;

    private ApiWithMyCallbackInterface client;

    public static final String APP_PREFERENCE = "Settings";
    public static final String APP_PREFERENCE_AUTHORIZED_PERSON_EMAIL = "AuthorizedPersonInfo";

    private SharedPreferences settings;

    public SecretSantaApplication() {
        //this.client = new ClientRetrofitWithMyCallback(); //TODO проверить
        //this.client = new FilesHMWithMyCallback(this);

        this.authorizedPersonController = new PersonController();
    }

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

    public void setAuthorizedPersonEmailInSharedPreferences(String authorizedPersonEmail){
        //сохранение Email в настройках приложения
        settings = getSharedPreferences(APP_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(APP_PREFERENCE_AUTHORIZED_PERSON_EMAIL, authorizedPersonEmail);
        editor.apply();
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






    public PersonController getAuthorizedPersonController() {
        return authorizedPersonController;
    }

    public void setAuthorizedPersonControllerEmail(String email) {
        settings = getSharedPreferences(APP_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(APP_PREFERENCE_AUTHORIZED_PERSON_EMAIL, email);
        editor.apply();
        authorizedPersonController.setPersonId(email);

        authorizedPersonEmail = email;
    }





    public void createNewPersonController() {
        this.newPersonController = new PersonController();
    }

    public PersonController getNewPersonController() {
        return newPersonController;
    }

    public void createNewGameController(int id) {
        currentGameId = id;
        this.newGameController = new GameController(id);
    }

    public void createNewPersonController(String email) {
        currentPersonEmail = email;
        this.newPersonController = new PersonController(email);
    }

    public GameController getNewGameController() {
        return newGameController;
    }

    public void createExistGameController(int id) {
        currentGameId = id;
        this.existGameController = new GameController(id);
    }

    public void createExistGameController() {
        this.existGameController = new GameController();
    }

    public GameController getExistGameController() {
        return existGameController;
    }








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
