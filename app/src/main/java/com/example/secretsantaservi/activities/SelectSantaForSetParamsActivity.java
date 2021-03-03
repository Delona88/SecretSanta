package com.example.secretsantaservi.activities;

import android.content.Intent;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import io.swagger.client.secretsantaclient.ApiWithMyCallbackInterface;
import io.swagger.client.secretsantaclient.MyCallback;
import com.example.secretsantaservi.R;
import com.example.secretsantaservi.SecretSantaApplication;

import java.util.*;


public class SelectSantaForSetParamsActivity extends AppCompatActivity {

    private HashMap<String, Button> buttonsHM = new HashMap<>();
    private Button buttonGoBack;
    private ProgressBar progressBar;
    private LinearLayout linearLayoutSelectNameForSetParameters;

    private SecretSantaApplication secretSantaApplication;
    private ApiWithMyCallbackInterface client;
    private Integer currentGameId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_santa_for_set_params);

        secretSantaApplication = (SecretSantaApplication) getApplicationContext();
        client = secretSantaApplication.getClient();
        currentGameId = secretSantaApplication.getCurrentGameId();

        buildGUI();

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    public void buildGUI() {

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        buttonGoBack = findViewById(R.id.buttonGoNext);
        buttonGoBack.setOnClickListener(onClickListener);

        linearLayoutSelectNameForSetParameters = findViewById(R.id.fillableLinearLayoutSelectNameForSetParameters);
        linearLayoutSelectNameForSetParameters.removeAllViews();

        buttonsHM = new HashMap<>();
        startGetHMWithPersonsInfoAndCreateButtons();

    }

    private void startGetHMWithPersonsInfoAndCreateButtons() {
        showProgressBar();
        client.getHMWithPersonsInfo(currentGameId, new MyCallback<HashMap<String, String>>() {
            @Override
            public void onResponse(HashMap<String, String> infoHM) {
                createButtonsWithParticipantsInfo(infoHM);
                hideProgressBar();
            }

            @Override
            public void onFailure(Throwable t) {
                showToastServerProblem(t.toString());
                hideProgressBar();
            }
        });
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
        linearLayoutSelectNameForSetParameters.addView(button);
        buttonsHM.put(email, button);
    }


    private final View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            //проверка кнопки в HM
            Button pressedButton = (Button) v;
            String email = getKeyByValue(buttonsHM, pressedButton);
            if (email != null) {
                secretSantaApplication.setCurrentPersonEmail(email);
                goToSetParams();
            }
            if (v.getId() == buttonGoBack.getId()) {
                finish();
            }
        }
    };

    private void goToSetParams(){
        Intent intent = new Intent(SelectSantaForSetParamsActivity.this, SetParamsActivity.class);
        startActivity(intent);
    }

    private void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    public void showToastServerProblem(String t) {
        String str = getResources().getString(R.string.title_server_problem) + "\n" + t;
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
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