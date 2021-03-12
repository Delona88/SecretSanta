package com.example.secretsantaservi.mytrainingquickplayversion;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.secretsantaservi.R;
import com.example.secretsantaservi.mytrainingquickplayversion.model.BadConditionsException;

import java.util.ArrayList;

import static com.example.secretsantaservi.mytrainingquickplayversion.numbersofparicipantsMVP.NumberOfParticipantsActivity.game;

public class SelectNameForSetParametersActivity extends AppCompatActivity {
    private ArrayList<Button> namesButton = new ArrayList<>(game.getNumberOfParticipants());
    private Button buttonGoToResult;
    private LinearLayout linearLayoutSelectNameForSetParameters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_name_for_set_parameters);

        buildGUI();
    }

    private void buildGUI() {
        buttonGoToResult = findViewById(R.id.buttonGoNext);
        buttonGoToResult.setOnClickListener(onClickListener);

        linearLayoutSelectNameForSetParameters = findViewById(R.id.fillableLinearLayoutSelectNameForSetParameters);
        for (int i = 0; i < game.getNumberOfParticipants(); i++) {
            addNewNameButtonAndListener(i);
        }
    }

    private void addNewNameButtonAndListener(int index) {
        namesButton.add(new Button(this));
        namesButton.get(index).setText(game.getNameByIndex(index));
        namesButton.get(index).setOnClickListener(onClickListener);
        namesButton.get(index).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        linearLayoutSelectNameForSetParameters.addView(namesButton.get(index));
    }

    private final View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Button pressedButton = (Button) v;
            int indexOfPressedButton = namesButton.indexOf(pressedButton);
            if (indexOfPressedButton != -1) {
                Intent intent = new Intent(SelectNameForSetParametersActivity.this, SetParamsForSantaActivity.class);
                intent.putExtra("indexButton", indexOfPressedButton);
                startActivity(intent);
            }
            if ((v.getId() == buttonGoToResult.getId())) {
                try {
                    game.fillReceivers();
                    Intent intent = new Intent(SelectNameForSetParametersActivity.this, SelectNameForShowReceiverActivity.class);
                    startActivity(intent);
                    finish();
                } catch (BadConditionsException e) {
                    e.printStackTrace();
                    showToastIncorrectInfo();
                }

            }
        }
    };

    public void showToastIncorrectInfo() {
        Toast.makeText(this, getResources().getString(R.string.title_bad_conditions), Toast.LENGTH_LONG).show();
    }
}