//выбор ограничений
package com.example.secretsantaservi.quickplay;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.secretsantaservi.R;

import java.util.ArrayList;


import static com.example.secretsantaservi.quickplay.numbersofparicipants.NumberOfParticipantsPresenter.toss;


public class SetParamsForSanta extends AppCompatActivity {
    int indexSanta;

    ArrayList<CheckBox> namesCheckBox = new ArrayList(toss.getNumberOfParticipants());
    LinearLayout linearLayoutSetParamsForSanta;
    TextView textViewSetParamsForSanta;
    Button buttonGoBackToSelectNameForSetParameters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_params_for_santa);

        int defaultValue = 0;
        indexSanta = getIntent().getIntExtra("indexButton", defaultValue); //получаем из SelectNameForSetParameters

        buildGUI();
    }

    public void buildGUI() {

        textViewSetParamsForSanta = findViewById(R.id.textViewSetParamsForSanta);
        String str1 = getResources().getString(R.string.title_choose_receivers_for_santa);
        String str = String.format(str1, toss.getNameByIndex(indexSanta));
        textViewSetParamsForSanta.setText(str);

        buttonGoBackToSelectNameForSetParameters = findViewById(R.id.buttonGoBackToSelectNameForSetParameters);
        buttonGoBackToSelectNameForSetParameters.setOnClickListener(onClickListener);


        linearLayoutSetParamsForSanta = findViewById(R.id.fillableLinearLayoutSetParamsForSanta);
        for (int i = 0; i < toss.getNumberOfParticipants(); i++) {
            addNewCheckBox(i);
        }
    }

    public void addNewCheckBox(int index) {
        if (indexSanta != index) {
            namesCheckBox.add(new CheckBox(this));
            //namesCheckBox.get(index).setId(index + toss.getNumberOfParticipants() * 4);
            namesCheckBox.get(index).setText(toss.getNameByIndex(index));
            namesCheckBox.get(index).setOnClickListener(onClickListener); //надо ли?
            if (toss.isPersonInSantaNaughtyList(toss.getPersonByIndex(indexSanta), toss.getPersonByIndex(index))) {//getPersonByIndex(indexSanta).isPersonInNaughtyList(santas.getPersonByIndex(i))) {
                namesCheckBox.get(index).setChecked(false);
            } else {
                namesCheckBox.get(index).setChecked(true);
            }
            namesCheckBox.get(index).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT));
            linearLayoutSetParamsForSanta.addView(namesCheckBox.get(index));
        } else {
            namesCheckBox.add(null); // для санты не добавляем CheckBox, помечаем элемент массива как null
        }
    }

    private final View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (v.getId() == buttonGoBackToSelectNameForSetParameters.getId()) {
                fillNaughtyList();
                finish();
            }
        }
    };

    public void fillNaughtyList() {
        for (int i = 0; i < toss.getNumberOfParticipants(); i++) {
            if (namesCheckBox.get(i) != null && !namesCheckBox.get(i).isChecked() && !toss.isPersonInSantaNaughtyList(toss.getPersonByIndex(indexSanta), toss.getPersonByIndex(i))) {
                toss.getPersonByIndex(indexSanta).addInNaughtyList(toss.getPersonByIndex(i));
            }
        }
    }

}