package com.example.secretsantaservi.activities.newgame;

import android.content.Intent;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.secretsantaservi.API.ApiWithMyCallbackInterface;
import com.example.secretsantaservi.R;
import com.example.secretsantaservi.SecretSantaApplication;
import com.example.secretsantaservi.activities.NewParticipantActivity;
import com.example.secretsantaservi.activities.SelectSantaForSetParamsActivity;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class NewGameActivity extends AppCompatActivity implements NewGameView {
    private Button buttonGoBack;
    private Button buttonUpdate;
    private Button buttonAddParticipant;
    private HashMap<String, Button> buttonsHM = new HashMap<>();
    private Button buttonEndEdit;
    private Button buttonGoToSetParams;
    private Button buttonStartGame;
    private TextView textViewGameInfo;
    private LinearLayout linearLayoutNamesOfParticipants;
    private ProgressBar progressBar;

    private SecretSantaApplication secretSantaApplication;
    //private GameController newGameController;
    private ApiWithMyCallbackInterface client;

    private Boolean allParticipantsAdded = false;

    private NewGamePresenter presenter;

    //TODO test
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);

        if (savedInstanceState != null){
            allParticipantsAdded = savedInstanceState.getBoolean("allParticipantsAdded");
        }

        secretSantaApplication = (SecretSantaApplication) getApplicationContext();
        //newGameController = secretSantaApplication.getNewGameController();

        client = secretSantaApplication.getClient();

        presenter = new NewGamePresenter(this, (SecretSantaApplication) getApplicationContext());
        presenter.init();

        //TODO test
        textView = findViewById(R.id.textViewFillInfo);

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public void buildGUI() {

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        buttonGoBack = findViewById(R.id.buttonGoBack);
        buttonGoBack.setOnClickListener(onClickListener);

        buttonUpdate = findViewById(R.id.buttonUpdate);
        buttonUpdate.setOnClickListener(onClickListener);

        buttonAddParticipant = findViewById(R.id.buttonAddParticipant);
        buttonAddParticipant.setOnClickListener(onClickListener);

        buttonEndEdit = findViewById(R.id.buttonEndEdit);
        buttonEndEdit.setOnClickListener(onClickListener);

        buttonGoToSetParams = findViewById(R.id.buttonGoToSetParams);
        buttonGoToSetParams.setOnClickListener(onClickListener);

        buttonStartGame = findViewById(R.id.buttonStartGame);
        buttonStartGame.setOnClickListener(onClickListener);

        linearLayoutNamesOfParticipants = findViewById(R.id.fillableLinearLayoutFillInfo);
        linearLayoutNamesOfParticipants.removeAllViews();

        setViewsVisibility();
    }

    private void setViewsVisibility() {
        if (!allParticipantsAdded) {
            buttonGoToSetParams.setVisibility(View.GONE);
            buttonStartGame.setVisibility(View.GONE);
        } else {
            buttonAddParticipant.setVisibility(View.GONE);
            buttonUpdate.setVisibility(View.GONE);
            buttonEndEdit.setVisibility(View.GONE);
            buttonGoToSetParams.setVisibility(View.VISIBLE);
            buttonStartGame.setVisibility(View.VISIBLE);
        }
    }

    public void setGameInfo(Integer id) {
        textViewGameInfo = findViewById(R.id.textViewGameInfo);
        String str = "Создана игра №" + id;
        textViewGameInfo.setText(str);
    }

    public void createButtonsWithParticipantsInfo(HashMap<String, String> infoHM) {
        for (String email : infoHM.keySet()) {
            String textOnButton = infoHM.get(email);
            addNewNameButtonInHMAndListener(email, textOnButton);
        }
    }

    public void addNewNameButtonInHMAndListener(String email, String textOnButton) {
        Button button = new Button(this);
        button.setText(textOnButton);
        button.setOnClickListener(onClickListener);
        button.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        linearLayoutNamesOfParticipants.addView(button);
        buttonsHM.put(email, button);
    }

    private final View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            //проверка нажатия кнопки игрока
            Button pressedButton = (Button) v;
            String email = getKeyByValue(buttonsHM, pressedButton);
            if (email != null) {
                //TODO secretSantaApplication.createNewPersonController(email);
                secretSantaApplication.setCurrentPersonEmail(email);
                Intent intent = new Intent(NewGameActivity.this, NewParticipantActivity.class);
                startActivity(intent);
            }
            if (v.getId() == buttonGoBack.getId()) {
                finish();
            }
            if (v.getId() == buttonUpdate.getId()) {
                buildGUI();
            }
            if (v.getId() == buttonAddParticipant.getId()) {
                //TODO secretSantaApplication.createNewPersonController();
                secretSantaApplication.setCurrentPersonEmail(null);
                Intent intent = new Intent(NewGameActivity.this, NewParticipantActivity.class);
                startActivity(intent);
            }
            if (v.getId() == buttonEndEdit.getId()) {
                //startSetGamePlayed();
                presenter.startSetGamePlayed();
                //newGameController.setAllParticipantsAddedTrue();

                allParticipantsAdded = true;
                setViewsVisibility();
            }
            if (v.getId() == buttonGoToSetParams.getId()) {
                Intent intent = new Intent(NewGameActivity.this, SelectSantaForSetParamsActivity.class);
                startActivity(intent);
            }
            if (v.getId() == buttonStartGame.getId()) {
                presenter.startToss();
            }
        }
    };


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("allParticipantsAdded", allParticipantsAdded);
    }

    //TODO проверить
    @Override
    protected void onRestart(){
        super.onRestart();
        presenter.restart();
    }

    public void showToastParticipantAdded() {
        Toast.makeText(this, getResources().getString(R.string.title_participants_add), Toast.LENGTH_SHORT).show();
    }

    public void showToastGameCreate(Integer gameId) {
        String str = String.format(getResources().getString(R.string.title_game_create), "" + gameId);
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    public void showToastServerProblem(String t) {
        String str = getResources().getString(R.string.title_server_problem) + "\n" + t;
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    public void showToastBadConditions() {
        Toast.makeText(this, getResources().getString(R.string.title_bad_conditions), Toast.LENGTH_SHORT).show();
    }

    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    public static <T, E> T getKeyByValue(Map<T, E> map, E value) {
        for (Map.Entry<T, E> entry : map.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }
}
