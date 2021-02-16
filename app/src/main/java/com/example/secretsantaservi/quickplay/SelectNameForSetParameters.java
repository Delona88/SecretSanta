package com.example.secretsantaservi.quickplay;

//выбор участников для ограничений и формирование получателей

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.secretsantaservi.R;

import java.util.ArrayList;

import static com.example.secretsantaservi.quickplay.numbersofparicipants.NumberOfParticipantsPresenter.toss;


public class SelectNameForSetParameters extends AppCompatActivity {
    ArrayList<Button> namesButton = new ArrayList(toss.getNumberOfParticipants());
    Button buttonGoToDrawResult;
    TextView textViewSelectNameForSetParameters;
    LinearLayout linearLayoutSelectNameForSetParameters;

    //TODO test
    int i;
    String str = "123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_name_for_set_parameters);

        buildGUI();
    }

    public void buildGUI() {
        buttonGoToDrawResult = findViewById(R.id.buttonGoBack);
        buttonGoToDrawResult.setOnClickListener(onClickListener);

        linearLayoutSelectNameForSetParameters = findViewById(R.id.fillableLinearLayoutSelectNameForSetParameters);
        for (int i = 0; i < toss.getNumberOfParticipants(); i++) {
            addNewNameButtonAndListener(i);
        }
    }

    public void addNewNameButtonAndListener(int index) {
        namesButton.add(new Button(this));
        namesButton.get(index).setText(toss.getNameByIndex(index));
        namesButton.get(index).setOnClickListener(onClickListener);
        namesButton.get(index).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT));
        linearLayoutSelectNameForSetParameters.addView(namesButton.get(index));
    }

    private final View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            //проверка кнопки
            Button pressedButton = (Button) v;
            int indexOfPressedButton = namesButton.indexOf(pressedButton);
            if (indexOfPressedButton != -1) {
                Intent intent = new Intent(SelectNameForSetParameters.this, SetParamsForSanta.class);
                intent.putExtra("indexButton", indexOfPressedButton);
                startActivity(intent);
            } else {
                //начинаем передачу на сервер
                //TODO test
                i = 5;
                toss.fillReceivers();



/*                try {
                    Thread thread = new Thread(new NetworkHandler());
                    thread.start();
                } catch (Exception e) {
                    str = e.toString();
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/



                Intent intent = new Intent(SelectNameForSetParameters.this, SelectNameForShowReceiver.class);
                //TODO test
                intent.putExtra("test", str);
                startActivity(intent);
                finish();
            }
        }
    };




}