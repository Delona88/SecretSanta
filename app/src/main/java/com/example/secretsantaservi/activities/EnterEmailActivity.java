package com.example.secretsantaservi.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import io.swagger.client.secretsantaclient.ApiWithCallbackInterface;
import io.swagger.client.secretsantaclient.Callback;
import com.example.secretsantaservi.R;
import com.example.secretsantaservi.SecretSantaApplication;
import com.example.secretsantaservi.activities.allgames.AllGamesActivity;
import secretsantamodel.*;

import static com.example.secretsantaservi.SecretSantaApplication.APP_PREFERENCE;
import static com.example.secretsantaservi.SecretSantaApplication.APP_PREFERENCE_AUTHORIZED_PERSON_EMAIL;

public class EnterEmailActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private Button buttonGoBack;
    private Button buttonNext;
    private Button buttonToRegistration;
    private EditText editText;

    private SecretSantaApplication secretSantaApplication;

    private ApiWithCallbackInterface client;

    private String authorizedPersonEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_id);

        secretSantaApplication = (SecretSantaApplication) getApplicationContext();

        authorizedPersonEmail = secretSantaApplication.getAuthorizedPersonEmail();

        client = secretSantaApplication.getClient();

        buildGUI();
    }

    private void buildGUI() {

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        buttonGoBack= findViewById(R.id.buttonGoNext);
        buttonGoBack.setOnClickListener(onClickListener);

        buttonNext = findViewById(R.id.buttonNext);
        buttonNext.setOnClickListener(onClickListener);

        buttonToRegistration= findViewById(R.id.buttonToRegistration);
        buttonToRegistration.setOnClickListener(onClickListener);

        editText = findViewById(R.id.editTextEnterId);

        String email = authorizedPersonEmail;
        if (email.length() != 0){
            editText.setText(email);
        }
    }

    private final View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (v.getId() == buttonNext.getId()) {
                setAuthorizedPersonEmailAndFinishOrShowToastIncorrectInfo();
            }
            if (v.getId() == buttonToRegistration.getId()) {
                goToRegistration();
            }
           if (v.getId() == buttonGoBack.getId()) {
                finish();
            }
        }
    };

    private void setAuthorizedPersonEmailAndFinishOrShowToastIncorrectInfo() {
        if (isAllInfoEntered()) {
            String email = editText.getText().toString();
            startGetPersonByIdAndSetAuthorizedPersonEmail(email);
        } else {
            showToastIncorrectInfo();
        }
    }

    private void startGetPersonByIdAndSetAuthorizedPersonEmail(String email) {
        showProgressBar();
        client.getPersonById(email, new Callback<Person>() {
            @Override
            public void onResponse(Person person) {
                hideProgressBar();
                if (person != null) {
                    secretSantaApplication.setAuthorizedPersonEmail(email);
                    setAuthorizedPersonEmailInSharedPreferences(email);
                    goToAllGames();
                } else {
                    showToastLoginNotFound();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                showToastServerProblem(t.toString());
                hideProgressBar();
                t.printStackTrace();
            }
        });
    }

    public void setAuthorizedPersonEmailInSharedPreferences(String authorizedPersonEmail) {
        SharedPreferences settings = getSharedPreferences(APP_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(APP_PREFERENCE_AUTHORIZED_PERSON_EMAIL, authorizedPersonEmail);
        editor.apply();
    }

    private void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    private void goToRegistration() {
        Intent intent = new Intent(EnterEmailActivity.this, RegistrationActivity.class);
        startActivity(intent);
    }

    private void goToAllGames() {
        Intent intent = new Intent(EnterEmailActivity.this, AllGamesActivity.class);
        startActivity(intent);
    }

    public boolean isAllInfoEntered() {
        return (editText.getText().toString().length() != 0);
    }

    public void showToastIncorrectInfo() {
        Toast.makeText(this, getResources().getString(R.string.title_incorrect_info), Toast.LENGTH_LONG).show();
    }

    public void showToastServerProblem(String t) {
        String str = getResources().getString(R.string.title_server_problem) + "\n" + t;
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    public void showToastLoginNotFound() {
        Toast.makeText(this, getResources().getString(R.string.title_login_not_found), Toast.LENGTH_SHORT).show();
    }

}