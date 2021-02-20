package com.example.secretsantaservi.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.secretsantaservi.API.ApiWithMyCallback;
import com.example.secretsantaservi.API.ClientRetrofitWithMyCallback;
import com.example.secretsantaservi.API.repository.repositoriesfactory.FileAndroidRepositoriesFactory;
import com.example.secretsantaservi.API.repository.repositoriesfactory.SQLiteRepositoriesFactory;
import com.example.secretsantaservi.R;
import com.example.secretsantaservi.SecretSantaApplication;
import com.example.secretsantaservi.quickplay.numbersofparicipants.NumberOfParticipants;


public class SelectVersionActivity extends AppCompatActivity {

    private Button buttonGoToLocal;
    private Button buttonGoToNetwork;
    private Button buttonGoToQuick;

    private SecretSantaApplication secretSantaApplication;

    private SharedPreferences settings;
    public static final String APP_PREFERENCE = "Settings";
    public static final String APP_PREFERENCE_AUTHORIZED_PERSON_EMAIL = "AuthorizedPersonInfo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_version);

        secretSantaApplication = (SecretSantaApplication) getApplicationContext();

        settings = getSharedPreferences(APP_PREFERENCE, Context.MODE_PRIVATE);
        String email = settings.getString(APP_PREFERENCE_AUTHORIZED_PERSON_EMAIL, "");

        secretSantaApplication.setAuthorizedPersonEmail(email); //пустой по умолчанию

        buildGUI();
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

    public void startQuick() {
        Intent intent = new Intent(SelectVersionActivity.this, NumberOfParticipants.class);
        startActivity(intent);
        //finish();
    }

    public void startLocalVersion() {
        secretSantaApplication.setClient(new ApiWithMyCallback(new SQLiteRepositoriesFactory(this)));
        Intent intent = new Intent(SelectVersionActivity.this, EnterEmailActivity.class);
        startActivity(intent);
        //finish();
    }

    public void startNetworkVersion() {
        secretSantaApplication.setClient(new ClientRetrofitWithMyCallback());
        Intent intent = new Intent(SelectVersionActivity.this, EnterEmailActivity.class);
        startActivity(intent);
        //finish();
    }

}