package com.example.secretsantaservi.mytrainingquickplayversion;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.secretsantaservi.R;

import static com.example.secretsantaservi.mytrainingquickplayversion.numbersofparicipantsMVP.NumberOfParticipantsActivity.game;


public class ShowSantasReceiverActivity extends AppCompatActivity {
    private Button buttonGoBackToSelectNameForShowReceiver;
    private int indexSanta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_santas_receiver);

        int defaultValue = 0;
        indexSanta = getIntent().getIntExtra("indexButton2", defaultValue);

        buildGUI();
    }

    private void buildGUI() {

        TextView textViewShowReceiver = findViewById(R.id.textViewShowSantasReceiver);
        String str1 = getResources().getString(R.string.title_santa_nd_receiver);
        String str = String.format(str1, game.getNameByIndex(indexSanta), game.getNameReceiverBySantaIndex(indexSanta));
        textViewShowReceiver.setText(str);

        buttonGoBackToSelectNameForShowReceiver = findViewById(R.id.buttonGoBackToSelectNameForShowReceiver);
        buttonGoBackToSelectNameForShowReceiver.setOnClickListener(onClickListener);
    }

    private final View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (v.getId() == buttonGoBackToSelectNameForShowReceiver.getId()) {
                finish();
            }

        }
    };
}