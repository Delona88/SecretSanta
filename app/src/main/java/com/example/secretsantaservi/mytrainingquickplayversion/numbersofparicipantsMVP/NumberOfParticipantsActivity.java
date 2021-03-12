package com.example.secretsantaservi.mytrainingquickplayversion.numbersofparicipantsMVP;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.secretsantaservi.R;
import com.example.secretsantaservi.mytrainingquickplayversion.NamesOfParticipantsActivity;
import com.example.secretsantaservi.mytrainingquickplayversion.model.SecretSantaQuickGame;

public class NumberOfParticipantsActivity extends AppCompatActivity implements NumberOfParticipantsView {

    public static SecretSantaQuickGame game;

    private Button buttonGoToNamesOfParticipants;

    private NumberOfParticipantsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_of_participants);

        buildGUI();

        game = new SecretSantaQuickGame();

        presenter = new NumberOfParticipantsPresenter(this);
    }


    public void buildGUI() {
        buttonGoToNamesOfParticipants = findViewById(R.id.buttonGoToNamesOfParticipants);
        buttonGoToNamesOfParticipants.setOnClickListener(onClickListener);
    }

    private final View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == buttonGoToNamesOfParticipants.getId()) {
                EditText textNumberOfParticipant = findViewById(R.id.inputNumberOfParticipant);
                String str = textNumberOfParticipant.getText().toString();
                presenter.createNewTossAndStartNextActivityOrGetMessageWindow(str);
            }
        }
    };

    public void showToastIncorrectInfo() {
        Toast.makeText(this, getResources().getString(R.string.title_incorrect_info), Toast.LENGTH_LONG).show();
    }

    public void startActivityNamesOfParticipants(int num) {
        Intent intent = new Intent(NumberOfParticipantsActivity.this, NamesOfParticipantsActivity.class);
        intent.putExtra("numberOfParticipants", num);
        startActivity(intent);
        finish();
    }
}