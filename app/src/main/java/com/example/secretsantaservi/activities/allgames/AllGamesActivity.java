package com.example.secretsantaservi.activities.allgames;

import android.content.Intent;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.secretsantaservi.*;
import com.example.secretsantaservi.activities.ConnectToGameActivity;
import com.example.secretsantaservi.activities.newgame.NewGameActivity;
import com.example.secretsantaservi.activities.PersonInfoActivity;
import com.example.secretsantaservi.activities.gameinfo.GameInfoActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AllGamesActivity extends AppCompatActivity implements AllGamesView {
    Button buttonGoBack;
    Button buttonCreateGame;
    Button buttonConnect;
    Button buttonPersonInfo;
    HashMap<Integer, Button> buttonsHM;
    LinearLayout linearLayoutAllGames;
    ProgressBar progressBar;

    SecretSantaApplication secretSantaApplication;

    AllGamesPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_games);

        secretSantaApplication = (SecretSantaApplication) getApplicationContext();

        presenter = new AllGamesPresenter(this, (SecretSantaApplication) getApplicationContext());
        presenter.init();

    }

    public void buildGUI() {
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        linearLayoutAllGames = findViewById(R.id.fillableLinearLayoutAllGames);
        linearLayoutAllGames.removeAllViews();

        buttonPersonInfo = findViewById(R.id.buttonPersonInfo);
        buttonPersonInfo.setOnClickListener(onClickListener);

        buttonGoBack = findViewById(R.id.buttonGoNext);
        buttonGoBack.setOnClickListener(onClickListener);

        buttonCreateGame = findViewById(R.id.buttonCreateGame);
        buttonCreateGame.setOnClickListener(onClickListener);

        buttonConnect = findViewById(R.id.buttonConnect);
        buttonConnect.setOnClickListener(onClickListener);

        buttonsHM = new HashMap<>();
    }

    public void createGamesButton(ArrayList<Integer> gamesId) {
        for (Integer id : gamesId) {
            String textOnButton = "Игра " + id;
            addButtonInHMAndListener(id, textOnButton);
        }
    }

    public void addButtonInHMAndListener(int id, String textOnButton) {
        Button button = new Button(this);
        button.setText(textOnButton);
        button.setOnClickListener(onClickListener);
        button.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        linearLayoutAllGames.addView(button);
        buttonsHM.put(id, button);
    }

    private final View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Button pressedButton = (Button) v;
            Integer id = getKeyByValue(buttonsHM, pressedButton);
            if (id != null) {
                secretSantaApplication.setCurrentGameId(id);
                goToGameInfo();
            }
            if (v.getId() == buttonGoBack.getId()) {
                finish();
            }
            if (v.getId() == buttonPersonInfo.getId()) {
                goToPersonInfo();
            }
            if (v.getId() == buttonCreateGame.getId()) {
                presenter.startGetNewGameID();
            }
            if (v.getId() == buttonConnect.getId()) {
                goToConnectToGame();
            }
        }
    };

    private void goToConnectToGame() {
        Intent intent = new Intent(AllGamesActivity.this, ConnectToGameActivity.class);
        startActivity(intent);
    }

    private void goToPersonInfo() {
        Intent intent = new Intent(AllGamesActivity.this, PersonInfoActivity.class);
        startActivity(intent);
    }

    private void goToGameInfo() {
        Intent intent = new Intent(AllGamesActivity.this, GameInfoActivity.class);
        startActivity(intent);
    }

    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    public void showToastServerProblem(String t) {
        String str = getResources().getString(R.string.title_server_problem) + "\n" + t;
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    public void goToFillInfo() {
        Intent intent = new Intent(AllGamesActivity.this, NewGameActivity.class);
        startActivity(intent);
    }

    public static <T, E> T getKeyByValue(Map<T, E> map, E value) {
        for (Map.Entry<T, E> entry : map.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        presenter.restart();
    }
}