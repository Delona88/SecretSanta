package com.example.secretsantaservi.activities;

import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import io.swagger.client.secretsantaclient.ApiWithCallbackInterface;
import io.swagger.client.secretsantaclient.Callback;
import com.example.secretsantaservi.R;
import com.example.secretsantaservi.SecretSantaApplication;
import secretsantamodel.Person;


import java.util.ArrayList;
import java.util.HashMap;


public class SetParamsActivity extends AppCompatActivity {

    private HashMap<String, CheckBox> checkBoxHM = new HashMap<>();
    private LinearLayout linearLayoutSetParams;
    private TextView textViewSetParams;
    private Button buttonGoBackToSelectNameForSetParams;
    private ProgressBar progressBar;

    private ApiWithCallbackInterface client;

    private Integer currentGameId;
    private String currentPersonEmail;

    private ArrayList<String> arrayNaughtylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_params);

        SecretSantaApplication secretSantaApplication = (SecretSantaApplication) getApplicationContext();

        currentGameId = secretSantaApplication.getCurrentGameId();
        currentPersonEmail = secretSantaApplication.getCurrentPersonEmail();

        client = secretSantaApplication.getClient();

        buildGUI();

    }

    public void buildGUI() {

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        textViewSetParams = findViewById(R.id.textViewSetParams);

        buttonGoBackToSelectNameForSetParams = findViewById(R.id.buttonGoNext);
        buttonGoBackToSelectNameForSetParams.setOnClickListener(onClickListener);

        linearLayoutSetParams = findViewById(R.id.fillableLinearLayoutSetParams);

        startGetPersonAndNaughtylist();

    }

    private void startGetPersonAndNaughtylist() {
        showProgressBar();
        client.getPersonById(currentPersonEmail, new Callback<Person>() {
            @Override
            public void onResponse(Person person) {
                String santaName = person.getName();
                arrayNaughtylist = person.getArrayNaughtyListEmailByGameId(currentGameId);
                setTextWithSantaName(santaName);
                startGetHMWithPersonsInfoAndCreateCheckBoxes();
            }

            @Override
            public void onFailure(Throwable t) {
                showToastServerProblem(t.toString());
                hideProgressBar();
            }
        });
    }

    private void setTextWithSantaName(String santaName) {
        String str = String.format(getResources().getString(R.string.title_choose_receivers_for_santa), santaName);
        textViewSetParams.setText(str);
    }

    private void startGetHMWithPersonsInfoAndCreateCheckBoxes() {
        client.getHMWithPersonsInfo(currentGameId, new Callback<HashMap<String, String>>() {
            @Override
            public void onResponse(HashMap<String, String> infoHM) {
                createCheckBoxesWhithParticipantsInfo(infoHM);
                hideProgressBar();
            }

            @Override
            public void onFailure(Throwable t) {
                showToastServerProblem(t.toString());
                hideProgressBar();
            }
        });
    }

    public void createCheckBoxesWhithParticipantsInfo(HashMap<String, String> infoHM) {
        for (String email : infoHM.keySet()) {
            String textInfo = infoHM.get(email);
            addNewNameCheckBoxInHMAndListener(email, textInfo);
        }
    }

    public void addNewNameCheckBoxInHMAndListener(String email, String textInfo) {
        if (!email.equals(currentPersonEmail)) {
            CheckBox checkBox = new CheckBox(this);
            checkBox.setText(textInfo);
            checkBox.setOnClickListener(onClickListener);

            if (isEmailInSantaNaughtylist(email)) {
                checkBox.setChecked(false);
            } else {
                checkBox.setChecked(true);
            }

            checkBox.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            linearLayoutSetParams.addView(checkBox);
            checkBoxHM.put(email, checkBox);
        } else {
            checkBoxHM.put(email, null);
        }
    }

    private boolean isEmailInSantaNaughtylist(String email) {
        return arrayNaughtylist.contains(email);
    }

    private final View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (v.getId() == buttonGoBackToSelectNameForSetParams.getId()) {
                createArrayNaughtylistAndAddToSanta();
            }
        }
    };

    private void createArrayNaughtylistAndAddToSanta() {
        ArrayList<String> naughtyList = new ArrayList<>();
        for (String email : checkBoxHM.keySet()) {
            CheckBox checkBox = checkBoxHM.get(email);
            if ((checkBox != null) && !(checkBox.isChecked())) {
                naughtyList.add(email);
            }
        }
        startSetNaughtylist(naughtyList);
    }

    private void startSetNaughtylist(ArrayList<String> naughtyList) {
        showProgressBar();
        client.setNaughtyList(currentPersonEmail, currentGameId, naughtyList, new Callback<Object>() {
            @Override
            public void onResponse(Object response) {
                showToastRestrictionsSet();
                hideProgressBar();
                finish();
            }

            @Override
            public void onFailure(Throwable t) {
                showToastServerProblem(t.toString());
                hideProgressBar();
            }
        });
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

    public void showToastRestrictionsSet() {
        Toast.makeText(this, getResources().getString(R.string.title_restrictions_set), Toast.LENGTH_SHORT).show();
    }

}
