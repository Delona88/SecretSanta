package com.example.secretsantaservi.quickplayversion;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.secretsantaservi.R;

import java.util.ArrayList;

import static com.example.secretsantaservi.quickplayversion.numbersofparicipantsMVP.NumberOfParticipantsPresenter.toss;


public class SelectNameForSetParametersActivity extends AppCompatActivity {
    private ArrayList<Button> namesButton = new ArrayList<>(toss.getNumberOfParticipants());
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
        for (int i = 0; i < toss.getNumberOfParticipants(); i++) {
            addNewNameButtonAndListener(i);
        }
    }

    private void addNewNameButtonAndListener(int index) {
        namesButton.add(new Button(this));
        namesButton.get(index).setText(toss.getNameByIndex(index));
        namesButton.get(index).setOnClickListener(onClickListener);
        namesButton.get(index).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        linearLayoutSelectNameForSetParameters.addView(namesButton.get(index));
    }

    private final View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            //проверка кнопки
            Button pressedButton = (Button) v;
            int indexOfPressedButton = namesButton.indexOf(pressedButton);
            if (indexOfPressedButton != -1) {
                Intent intent = new Intent(SelectNameForSetParametersActivity.this, SetParamsForSantaActivity.class);
                intent.putExtra("indexButton", indexOfPressedButton);
                startActivity(intent);
            }
            if ((v.getId() == buttonGoToResult.getId())) {
                toss.fillReceivers();
                Intent intent = new Intent(SelectNameForSetParametersActivity.this, SelectNameForShowReceiverActivity.class);
                startActivity(intent);
                finish();
            }
        }
    };


}