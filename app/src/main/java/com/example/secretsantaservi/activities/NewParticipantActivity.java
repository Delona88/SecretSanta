package com.example.secretsantaservi.activities;

import android.view.View;
import android.widget.Button;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.secretsantaservi.API.ApiWithMyCallbackInterface;
import com.example.secretsantaservi.API.MyCallback;
import com.example.secretsantaservi.R;
import com.example.secretsantaservi.SecretSantaApplication;
import com.example.secretsantaservi.secretsanta.Person;

public class NewParticipantActivity extends AppCompatActivity {
    private Button buttonAdd;
    private EditText editTextName;
    private EditText editTextEmail;
    private ProgressBar progressBar;

    private SecretSantaApplication secretSantaApplication;

    private Integer currentGameId;
    private String currentPersonEmail;

    private ApiWithMyCallbackInterface client;

    //TODO test
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_participant);

        secretSantaApplication = (SecretSantaApplication) getApplicationContext();

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

        textView = findViewById(R.id.textViewNewParticipantEmail);

        if (currentPersonEmail != null) { //изменяем или добавляем
            startGetPersonByIdAndDeleteFromGame(); //изменяем
        }
    }

    private void startGetPersonByIdAndDeleteFromGame() {
        showProgressBar();
        client.getPersonById(currentPersonEmail, new MyCallback<Person>() {
            @Override
            public void onResponse(Person response) {
                Person person = response;
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

    private void startDelete() {//удаляет Person из игры и PersonGame из Person
        showProgressBar();
        client.deletePersonFromGame(currentGameId, currentPersonEmail, new MyCallback<Object>() {
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
        client.getPersonById(getEmail(), new MyCallback<Person>() {
            @Override
            public void onResponse(Person response) {
                Person person = response;
                if (person == null) { //регистрируем игрока или добавляем ему игру
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
        client.addPerson(new Person(getEmail(), getName()), new MyCallback<Object>() {
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

    private void startAddPersonInGameById() {//добавляет Person в игру и PersonGame для Person
        client.addPersonInGame(currentGameId, getEmail(), new MyCallback<Object>() {
            @Override
            public void onResponse(Object response) {
                showToastParticipantAdded();
                hideProgressBar();

                //TODO закончили работу
                secretSantaApplication.setCurrentPersonEmail(null);
                finish(); //иначе не успевают прогружаться участники на AllGames
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