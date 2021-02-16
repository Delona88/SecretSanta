package com.example.secretsantaservi.activities;

import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.secretsantaservi.API.ApiWithMyCallbackInterface;
import com.example.secretsantaservi.API.MyCallback;
import com.example.secretsantaservi.R;
import com.example.secretsantaservi.SecretSantaApplication;
import com.example.secretsantaservi.secretsanta.SecretSantaGame;

public class ConnectToGameActivity extends AppCompatActivity {
    private Button buttonNext;
    private Button buttonConnect;
    private TextView textViewParticipants;
    private TextView textViewParticipantsText;
    private EditText editText;
    private ProgressBar progressBar;

    private SecretSantaApplication secretSantaApplication;
    //private GameController existGameController;
    //private PersonController authorizedPerson;

    private String authorizedPersonEmail;


    private ApiWithMyCallbackInterface client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_to_game);

        secretSantaApplication = (SecretSantaApplication) getApplicationContext();

        //TODO authorizedPerson = secretSantaApplication.getAuthorizedPersonController();//нет

        authorizedPersonEmail = secretSantaApplication.getAuthorizedPersonEmail();

        client = secretSantaApplication.getClient();

        buildGUI();
    }

    private void buildGUI() {

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        buttonConnect = findViewById(R.id.buttonConnect);
        buttonConnect.setOnClickListener(onClickListener);

        buttonNext = findViewById(R.id.buttonNext);
        buttonNext.setOnClickListener(onClickListener);

        editText = findViewById(R.id.editTextEnterId);
    }

    private final View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (v.getId() == buttonConnect.getId()) {
                checkAndConnectOrShowToastIncorrectInfo();
            }
            if (v.getId() == buttonNext.getId()) {
                finish();
            }
        }
    };

    private void checkAndConnectOrShowToastIncorrectInfo() {
        String id = editText.getText().toString();
        if (isAllInfoEntered() && isNumeric(id)) {
            startGetGameById(Integer.parseInt(id));
        } else {
            showToastIncorrectInfo();
        }
    }

    private void startGetGameById(int id) {
        showProgressBar();
        client.getGameById(id, new MyCallback<SecretSantaGame>() {
            @Override
            public void onResponse(SecretSantaGame response) {
                SecretSantaGame game = response;
                if (game != null && !game.isPlayed()) {
                    startAddPersonInGame(id);
                } else {
                    hideProgressBar();
                    showToastGameDoesNotExist(id);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                showToastServerProblem(t.toString());
            }
        });
    }

    private void startAddPersonInGame(Integer id) {
        //TODO
        //client.addPersonInGameById(existGameController.getGameId(), AuthorizedPerson.getInstance().getEmail(), new MyCallback<Object>() {

        client.addPersonInGame(id, authorizedPersonEmail, new MyCallback<Object>() {
            @Override
            public void onResponse(Object response) {
                hideProgressBar();
                showToastConnectedToGame();
            }

            @Override
            public void onFailure(Throwable t) {
                showToastServerProblem(t.toString());
                hideProgressBar();
            }
        });
    }

    public boolean isAllInfoEntered() {
        return (editText.getText().toString().length() != 0);
    }

    public boolean isNumeric(String strNum) {
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }

    private void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    public void showToastIncorrectInfo() {
        Toast.makeText(this, getResources().getString(R.string.title_incorrect_info), Toast.LENGTH_LONG).show();
    }

    public void showToastServerProblem(String t) {
        String str = getResources().getString(R.string.title_server_problem) + "\n" + t;
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    public void showToastGameDoesNotExist(int id) {
        String str = String.format(getResources().getString(R.string.title_game_does_not_exist), "" + id);
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    public void showToastConnectedToGame() {
        Toast.makeText(this, getResources().getString(R.string.title_connected_to_game), Toast.LENGTH_SHORT).show();
    }

}