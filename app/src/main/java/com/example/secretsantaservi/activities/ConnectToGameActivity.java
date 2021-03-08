package com.example.secretsantaservi.activities;

import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import io.swagger.client.secretsantaclient.ApiWithCallbackInterface;
import io.swagger.client.secretsantaclient.Callback;
import com.example.secretsantaservi.R;
import com.example.secretsantaservi.SecretSantaApplication;
import secretsantamodel.*;


public class ConnectToGameActivity extends AppCompatActivity {
    private Button buttonNext;
    private Button buttonConnect;
    private EditText editText;
    private ProgressBar progressBar;

    private String authorizedPersonEmail;

    private ApiWithCallbackInterface client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_to_game);

        SecretSantaApplication secretSantaApplication = (SecretSantaApplication) getApplicationContext();

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
        client.getGameById(id, new Callback<SecretSantaGame>() {
            @Override
            public void onResponse(SecretSantaGame game) {
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
        client.addPersonInGame(id, authorizedPersonEmail, new Callback<Object>() {
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
            Integer.parseInt(strNum);
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