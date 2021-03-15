package com.example.secretsantaservi.mytrainingquickplayversion;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.secretsantaservi.R;

import java.util.ArrayList;

import static com.example.secretsantaservi.mytrainingquickplayversion.numbersofparicipantsMVP.NumberOfParticipantsActivity.game;


public class SetParamsForSantaActivity extends AppCompatActivity {
    private int indexSanta;

    private ArrayList<CheckBox> namesCheckBox = new ArrayList<>(game.getNumberOfParticipants());
    private LinearLayout linearLayoutSetParamsForSanta;
    private TextView textViewSetParamsForSanta;
    private Button buttonGoBackToSelectNameForSetParameters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_params_for_santa);

        int defaultValue = 0;
        indexSanta = getIntent().getIntExtra("indexButton", defaultValue);

        buildGUI();
    }

    private void buildGUI() {

        textViewSetParamsForSanta = findViewById(R.id.textViewSetParamsForSanta);
        String str1 = getResources().getString(R.string.title_choose_receivers_for_santa);
        String str = String.format(str1, game.getNameByIndex(indexSanta));
        textViewSetParamsForSanta.setText(str);

        buttonGoBackToSelectNameForSetParameters = findViewById(R.id.buttonGoBackToSelectNameForSetParameters);
        buttonGoBackToSelectNameForSetParameters.setOnClickListener(onClickListener);

        linearLayoutSetParamsForSanta = findViewById(R.id.fillableLinearLayoutSetParamsForSanta);
        for (int i = 0; i < game.getNumberOfParticipants(); i++) {
            addNewCheckBox(i);
        }

    }

    private void addNewCheckBox(int index) {
        if (indexSanta != index) {
            namesCheckBox.add(new CheckBox(this));
            namesCheckBox.get(index).setText(game.getNameByIndex(index));
            namesCheckBox.get(index).setOnClickListener(onClickListener);
            if (game.isPersonInSantaNaughtyList(game.getPersonByIndex(indexSanta), game.getPersonByIndex(index))) {
                namesCheckBox.get(index).setChecked(false);
            } else {
                namesCheckBox.get(index).setChecked(true);
            }
            namesCheckBox.get(index).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            linearLayoutSetParamsForSanta.addView(namesCheckBox.get(index));
        } else {
            namesCheckBox.add(null);
        }
    }

    private final View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (v.getId() == buttonGoBackToSelectNameForSetParameters.getId()) {
                game.getPersonByIndex(indexSanta).setNaughtyList(new ArrayList<>());
                fillNaughtyList();
                finish();
            }
        }
    };

    private void fillNaughtyList() {
        for (int i = 0; i < game.getNumberOfParticipants(); i++) {
            if (namesCheckBox.get(i) != null && !namesCheckBox.get(i).isChecked() && !game.isPersonInSantaNaughtyList(game.getPersonByIndex(indexSanta), game.getPersonByIndex(i))) {
                game.getPersonByIndex(indexSanta).addInNaughtyList(game.getPersonByIndex(i));
            }
        }
    }
}