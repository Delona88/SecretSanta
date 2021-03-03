package com.example.secretsantaservi.activities;

import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import io.swagger.client.secretsantaclient.ApiWithMyCallbackInterface;
import io.swagger.client.secretsantaclient.MyCallback;

import com.example.secretsantaservi.R;
import com.example.secretsantaservi.SecretSantaApplication;
import secretsantamodel.Person;


public class PersonInfoActivity extends AppCompatActivity {
    private Button buttonChangeEmail;
    private Button buttonChangeName;
    private Button buttonGoBack;
    private EditText editTextName;
    private EditText editTextEmail;
    private ProgressBar progressBar;

    private SecretSantaApplication secretSantaApplication;
    private ApiWithMyCallbackInterface client;

    private String authorizedPersonEmail;
    private Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_info);

        secretSantaApplication = (SecretSantaApplication) getApplicationContext();
        client = secretSantaApplication.getClient();

        authorizedPersonEmail = secretSantaApplication.getAuthorizedPersonEmail();

        buildGUI();

    }

    public void buildGUI() {

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        buttonGoBack = findViewById(R.id.buttonGoNext);
        buttonGoBack.setOnClickListener(onClickListener);

        buttonChangeEmail = findViewById(R.id.buttonChangeEmail);
        buttonChangeEmail.setOnClickListener(onClickListener);

        buttonChangeName = findViewById(R.id.buttonChangeName);
        buttonChangeName.setOnClickListener(onClickListener);

        editTextName = findViewById(R.id.extViewPersonName);
        editTextEmail = findViewById(R.id.editTextPersonEmail);

        startGetPersonByIdAndSet();
    }

    private void startGetPersonByIdAndSet() {
        showProgressBar();
        client.getPersonById(authorizedPersonEmail, new MyCallback<Person>() {
            @Override
            public void onResponse(Person response) {
                person = response;
                editTextName.setText(person.getName());
                editTextEmail.setText(person.getEmail());
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
            if (v.getId() == buttonGoBack.getId()) {
                finish();
            }
            if (v.getId() == buttonChangeEmail.getId()) {
                changeEmailOrGetMessage();
            }
            if (v.getId() == buttonChangeName.getId()) {
                changeNameOrGetMessage();
            }
        }
    };


    private void changeEmailOrGetMessage() {
        if (getEmail().length() != 0) {
            startGetPersonByIdCheckEmailAndReplace();
        } else {
            showToastIncorrectInfo();
        }
    }

    private void startGetPersonByIdCheckEmailAndReplace() {
        showProgressBar();
        client.getPersonById(getEmail(), new MyCallback<Person>() {
            @Override
            public void onResponse(Person response) {
                if (response == null) {
                    startChangeEmail();
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

    private void startChangeEmail() {
        showProgressBar();
        person.setEmail(getEmail());
        client.replacePerson(person, authorizedPersonEmail, new MyCallback<Object>() {
            @Override
            public void onResponse(Object response) {
                showToastEmailChanged();
                secretSantaApplication.setAuthorizedPersonEmail(getEmail());
                hideProgressBar();
            }

            @Override
            public void onFailure(Throwable t) {
                showToastServerProblem(t.toString());
                hideProgressBar();
            }
        });
    }

    private void changeNameOrGetMessage() {
        if (getName().length() != 0) {
            startChangeName();
        } else {
            showToastIncorrectInfo();
        }
    }

    private void startChangeName() {
        showProgressBar();
        person.setName(getName());

        client.addPerson(person, new MyCallback<Object>() {
            @Override
            public void onResponse(Object response) {
                showToastNameChanged();
                hideProgressBar();
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

    public void showToastNameChanged() {
        Toast.makeText(this, getResources().getString(R.string.title_name_changed), Toast.LENGTH_SHORT).show();
    }

    public void showToastEmailChanged() {
        Toast.makeText(this, getResources().getString(R.string.title_email_changed), Toast.LENGTH_SHORT).show();
    }

    public void showToastLoginUsed() {
        Toast.makeText(this, getResources().getString(R.string.title_login_used), Toast.LENGTH_SHORT).show();
    }

}
