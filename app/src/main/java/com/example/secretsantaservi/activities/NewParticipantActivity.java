package com.example.secretsantaservi.activities;

import android.view.View;
import android.widget.Button;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import io.swagger.client.secretsantaclient.ApiWithCallbackInterface;
import io.swagger.client.secretsantaclient.Callback;
import com.example.secretsantaservi.R;
import com.example.secretsantaservi.SecretSantaApplication;

import secretsantamodel.*;

public class NewParticipantActivity extends AppCompatActivity {
    private Button buttonAdd;
    private EditText editTextName;
    private EditText editTextEmail;
    private ProgressBar progressBar;

    private Integer currentGameId;
    private String currentPersonEmail;

    private ApiWithCallbackInterface client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_participant);

        SecretSantaApplication secretSantaApplication = (SecretSantaApplication) getApplicationContext();

        currentGameId = secretSantaApplication.getCurrentGameId();
        currentPersonEmail = secretSantaApplication.getCurrentPersonEmail();

        client = secretSantaApplication.getClient();

        buildGUI();
    }

    public void buildGUI() {

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        buttonAdd = findViewById(R.id.buttonAddParticipant);
        buttonAdd.setOnClickListener(onClickListener);

        editTextName = findViewById(R.id.editTextParticipantName);
        editTextEmail = findViewById(R.id.editTextParticipantEmail);

        if (currentPersonEmail != null) {
            startGetPersonByIdAndDeleteFromGame();
        }
    }

    private void startGetPersonByIdAndDeleteFromGame() {
        showProgressBar();
        client.getPersonById(currentPersonEmail, new Callback<Person>() {
            @Override
            public void onResponse(Person person) {
                editTextName.setText(person.getName());
                editTextEmail.setText(person.getEmail());
                startDelete();
            }

            @Override
            public void onFailure(Throwable t) {
                showToastServerProblem(t.toString());
                hideProgressBar();
            }
        });
    }

    private void startDelete() {
        showProgressBar();
        client.deletePersonFromGame(currentGameId, currentPersonEmail, new Callback<Object>() {
            @Override
            public void onResponse(Object response) {
                hideProgressBar();
            }

            @Override
            public void onFailure(Throwable t) {
                showToastServerProblem(t.toString());
                hideProgressBar();
            }
        });
    }

    private final View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (v.getId() == buttonAdd.getId()) {
                addAndFinishOrShowToastIncorrectInfo();
            }
        }
    };

    private void addAndFinishOrShowToastIncorrectInfo() {
        if (isAllInfoEntered()) {
            startGetPersonByIdAndCheck();
        } else {
            showToastIncorrectInfo();
        }
    }

    private void startGetPersonByIdAndCheck() {
        showProgressBar();
        client.getPersonById(getEmail(), new Callback<Person>() {
            @Override
            public void onResponse(Person person) {
                if (person == null) {
                    startAddPersonAndPersonInGame();
                } else {
                    startAddPersonInGameById();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                showToastServerProblem(t.toString());
                hideProgressBar();
            }
        });
    }

    private void startAddPersonAndPersonInGame() {
        client.addPerson(new Person(getEmail(), getName()), new Callback<Object>() {
            @Override
            public void onResponse(Object response) {
                showToastParticipantAdded();
                startAddPersonInGameById();
            }

            @Override
            public void onFailure(Throwable t) {
                showToastServerProblem(t.toString());
                hideProgressBar();
            }
        });
    }

    private void startAddPersonInGameById() {
        client.addPersonInGame(currentGameId, getEmail(), new Callback<Object>() {
            @Override
            public void onResponse(Object response) {
                showToastParticipantAdded();
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

    private String getName() {
        return editTextName.getText().toString();
    }

    private String getEmail() {
        return editTextEmail.getText().toString();
    }

    private boolean isAllInfoEntered() {
        return ((getName().length() != 0) && (getEmail().length() != 0));
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

    public void showToastParticipantAdded() {
        Toast.makeText(this, getResources().getString(R.string.title_participant_add_in_game), Toast.LENGTH_SHORT).show();
    }

    public void showToastIncorrectInfo() {
        Toast.makeText(this, getResources().getString(R.string.title_incorrect_info), Toast.LENGTH_LONG).show();
    }

}