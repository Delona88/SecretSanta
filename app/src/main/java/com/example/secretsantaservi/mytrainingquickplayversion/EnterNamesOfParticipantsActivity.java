package com.example.secretsantaservi.mytrainingquickplayversion;

import android.content.Intent;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.secretsantaservi.R;

import java.util.ArrayList;

import static com.example.secretsantaservi.mytrainingquickplayversion.numbersofparicipantsMVP.NumberOfParticipantsActivity.game;


public class EnterNamesOfParticipantsActivity extends AppCompatActivity {

    private final ArrayList<EditText> listOfEditTextForNames = new ArrayList<>();
    private Button buttonGoToSetParams;
    private LinearLayout linearLayoutNamesOfParticipants;
    private int numberOfParticipants;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_names_of_participants);

        numberOfParticipants = getIntent().getIntExtra("numberOfParticipants", 0);

        buildGUI();
    }

    public void buildGUI() {

        buttonGoToSetParams = findViewById(R.id.buttonGoToSetParameters);
        buttonGoToSetParams.setOnClickListener(onClickListener);

        linearLayoutNamesOfParticipants = findViewById(R.id.fillableLinearLayoutNamesOfParticipants);
        for (int i = 0; i < numberOfParticipants; i++) {
            addNewEditTextForName(i);
        }
    }

    public void addNewEditTextForName(int index) {
        listOfEditTextForNames.add(new EditText(this));
        listOfEditTextForNames.get(index).setId(index);
        listOfEditTextForNames.get(index).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        linearLayoutNamesOfParticipants.addView(listOfEditTextForNames.get(index));
    }

    private final View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (v.getId() == buttonGoToSetParams.getId()) {
                if (isAllNamesEntered()) {
                    addNamesInToss();
                    Intent intent = new Intent(EnterNamesOfParticipantsActivity.this, SelectNameForSetParametersActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    showToastIncorrectInfo();
                }
            }
        }
    };

    public boolean isAllNamesEntered() {
        boolean namesNotEmpty = true;
        for (int i = 0; i < numberOfParticipants; i++) {
            if (listOfEditTextForNames.get(i).getText().toString().length() == 0) {
                namesNotEmpty = false;
            }
        }
        return namesNotEmpty;
    }

    public void addNamesInToss() {
        for (int i = 0; i < numberOfParticipants; i++) {
            game.addNewPersonByName(listOfEditTextForNames.get(i).getText().toString());
        }
    }

    public void showToastIncorrectInfo() {
        Toast.makeText(this, getResources().getString(R.string.title_incorrect_info), Toast.LENGTH_LONG).show();
    }

}