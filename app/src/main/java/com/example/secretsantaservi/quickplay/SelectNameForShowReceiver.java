// выбор санты для просмотра получателя
package com.example.secretsantaservi.quickplay;

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

public class SelectNameForShowReceiver extends AppCompatActivity {
    ArrayList<Button> namesButton = new ArrayList(toss.getNumberOfParticipants());
    Button buttonFinish;
    TextView textViewSelectNameForShowReceiver;
    LinearLayout linearLayoutAddNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_name_for_show_receiver);
        buildGUI();

    }

    public void buildGUI() {
        textViewSelectNameForShowReceiver = findViewById(R.id.textViewSelectNameForShowReceiver);

        buttonFinish = findViewById(R.id.buttonFinish);
        buttonFinish.setOnClickListener(onClickListener);

        linearLayoutAddNames = findViewById(R.id.fillableLinearLayoutAddNames);
        for (int i = 0; i < toss.getNumberOfParticipants(); i++) {
            addNewNameButtonAndListener(i);
        }
    }

    public void addNewNameButtonAndListener(int index) {
        namesButton.add(new Button(this));
        namesButton.get(index).setText(toss.getNameByIndex(index));
        namesButton.get(index).setOnClickListener(onClickListener);
        namesButton.get(index).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT));
        linearLayoutAddNames.addView(namesButton.get(index));
    }

    private final View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Button pressedButton = (Button) v;
            int indexOfPressedButton = namesButton.indexOf(pressedButton);
            if (indexOfPressedButton != -1) {
                Intent intent = new Intent(SelectNameForShowReceiver.this, ShowSantasReceiver.class);
                intent.putExtra("indexButton2", indexOfPressedButton);
                startActivity(intent);
            } else {
                finish();
            }
        }
    };

}