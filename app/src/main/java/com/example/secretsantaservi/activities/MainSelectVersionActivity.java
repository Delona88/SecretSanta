package com.example.secretsantaservi.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.secretsantaservi.R;
import com.example.secretsantaservi.SecretSantaApplication;
import com.example.secretsantaservi.mytrainingquickplayversion.numbersofparicipantsMVP.NumberOfParticipantsActivity;
import com.example.secretsantaservi.mytrainingquickplayversion.versionwithfragments.QuickPlayActivity;

import com.example.secretsantaservi.androidrepository.repositoriesfactory.SQLiteRepositoriesFactory;
import io.swagger.client.secretsantaclient.ClientRetrofitWithCallback;
import com.example.secretsantaservi.api.ApiWithCallback;
import static com.example.secretsantaservi.SecretSantaApplication.*;


public class MainSelectVersionActivity extends AppCompatActivity {

    private Button buttonGoToLocal;
    private Button buttonGoToNetwork;
    private Button buttonGoToQuick;

    private SecretSantaApplication secretSantaApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_version);

        secretSantaApplication = (SecretSantaApplication) getApplicationContext();

        setAuthorizedPersonEmailFromSharedPreferences();

        buildGUI();
    }

    public void setAuthorizedPersonEmailFromSharedPreferences() {
        SharedPreferences settings = getSharedPreferences(APP_PREFERENCE, Context.MODE_PRIVATE);
        String email = settings.getString(APP_PREFERENCE_AUTHORIZED_PERSON_EMAIL, "");

        secretSantaApplication.setAuthorizedPersonEmail(email);
    }

    public void buildGUI() {
        buttonGoToLocal = findViewById(R.id.buttonGoToLocal);
        buttonGoToLocal.setOnClickListener(onClickListener);

        buttonGoToNetwork = findViewById(R.id.buttonGoToNetwork);
        buttonGoToNetwork.setOnClickListener(onClickListener);

        buttonGoToQuick = findViewById(R.id.buttonGoToQuick);
        buttonGoToQuick.setOnClickListener(onClickListener);
    }

    private final View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (v.getId() == buttonGoToQuick.getId()) {
                startQuick();
            }
            if (v.getId() == buttonGoToLocal.getId()) {
                startLocalVersion();
            }
            if (v.getId() == buttonGoToNetwork.getId()) {
                startNetworkVersion();
            }
        }
    };

    /**
     * For QuickPlay version: QuickPlayActivity (with fragments) or NumberOfParticipantsActivity
     */


    public void startQuick() {
        Intent intent = new Intent(MainSelectVersionActivity.this, QuickPlayActivity.class);
        startActivity(intent);
    }

    /**
     * For local version: SQLiteRepositoriesFactory or FileAndroidRepositoriesFactory
     */

    public void startLocalVersion() {
        secretSantaApplication.setClient(new ApiWithCallback
                (new SQLiteRepositoriesFactory(this, FILE_NAME_DB_GAMES, FILE_NAME_DB_PERSONS)));
        Intent intent = new Intent(MainSelectVersionActivity.this, EnterEmailActivity.class);
        startActivity(intent);
    }

    public void startNetworkVersion() {
        secretSantaApplication.setClient(new ClientRetrofitWithCallback());
        Intent intent = new Intent(MainSelectVersionActivity.this, EnterEmailActivity.class);
        startActivity(intent);

    }

}