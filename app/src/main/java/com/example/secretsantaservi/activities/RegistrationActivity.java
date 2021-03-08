package com.example.secretsantaservi.activities;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import io.swagger.client.secretsantaclient.ApiWithCallbackInterface;
import io.swagger.client.secretsantaclient.Callback;
import com.example.secretsantaservi.R;
import com.example.secretsantaservi.SecretSantaApplication;
import com.example.secretsantaservi.activities.allgames.AllGamesActivity;
import secretsantamodel.Person;


public class RegistrationActivity extends AppCompatActivity {

    private Button buttonAdd;
    private EditText editTextName;
    private EditText editTextEmail;
    private ProgressBar progressBar;

    private SecretSantaApplication secretSantaApplication;
    private ApiWithCallbackInterface client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        secretSantaApplication = (SecretSantaApplication) getApplicationContext();
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
    }

    private final View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (v.getId() == buttonAdd.getId()) {
                addPersonAndFinishOrGetMessage();
            }
        }
    };

    private void addPersonAndFinishOrGetMessage() {
        if (isAllInfoEntered()) {
            startGetPersonByIdCheckAndAdd();
        } else {
            showToastIncorrectInfo();
        }
    }

    private void startGetPersonByIdCheckAndAdd() {
        showProgressBar();
        client.getPersonById(getEmail(), new Callback<Person>() {
            @Override
            public void onResponse(Person response) {
                if (response == null) {
                    startAddPerson();
                } else {
                    showToastLoginUsed();
                    hideProgressBar();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                showToastServerProblem(t.toString());
                hideProgressBar();
            }
        });
    }


    private void startAddPerson() {
        client.addPerson(new Person(getEmail(), getName()), new Callback<Object>() {

            @Override
            public void onResponse(Object response) {
                showToastRegistrationOk();
                secretSantaApplication.setAuthorizedPersonEmail(getEmail());
                hideProgressBar();
                goToAllGames();
            }

            @Override
            public void onFailure(Throwable t) {
                showToastServerProblem(t.toString());
                hideProgressBar();
            }
        });
    }

    private void goToAllGames() {
        Intent intent = new Intent(RegistrationActivity.this, AllGamesActivity.class);
        startActivity(intent);
        finish();
    }

    private void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
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

    public void showToastIncorrectInfo() {
        Toast.makeText(this, getResources().getString(R.string.title_incorrect_info), Toast.LENGTH_LONG).show();
    }

    public void showToastServerProblem(String t) {
        String str = getResources().getString(R.string.title_server_problem) + "\n" + t;
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    public void showToastRegistrationOk() {
        Toast.makeText(this, getResources().getString(R.string.title_registration_ok), Toast.LENGTH_SHORT).show();
    }

    public void showToastLoginUsed() {
        Toast.makeText(this, getResources().getString(R.string.title_login_used), Toast.LENGTH_SHORT).show();
    }

}


