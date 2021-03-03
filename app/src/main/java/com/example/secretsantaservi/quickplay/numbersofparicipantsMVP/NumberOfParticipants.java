//ввод количества участников
package com.example.secretsantaservi.quickplay.numbersofparicipantsMVP;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.secretsantaservi.R;
import com.example.secretsantaservi.quickplay.NamesOfParticipants;

public class NumberOfParticipants extends AppCompatActivity implements NumberOfParticipantsView {
    private Button buttonGoToNamesOfParticipants;

    private NumberOfParticipantsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_of_participants);

        buildGUI();

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

    public void startActivityNamesOfParticipants() {
        Intent intent = new Intent(NumberOfParticipants.this, NamesOfParticipants.class);
        startActivity(intent);
        finish();
    }
}