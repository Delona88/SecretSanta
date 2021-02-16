//ввод количества участников
package com.example.secretsantaservi.quickplay.numbersofparicipants;

import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.secretsantaservi.quickplay.NamesOfParticipants;
import com.example.secretsantaservi.R;

public class NumberOfParticipants extends AppCompatActivity implements NumberOfParticipantsView {
    //public static TossForSecretSanta toss;

    //элементы управления
    TextView textViewNumbersOfParticipants;
    Button buttonGoToNamesOfParticipants;

    NumberOfParticipantsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_of_participants);

        buildGUI();

        presenter = new NumberOfParticipantsPresenter(this);
    }

    // устанавливаем элементы управления
    public void buildGUI(){
        buttonGoToNamesOfParticipants = findViewById(R.id.buttonGoToNamesOfParticipants);
        buttonGoToNamesOfParticipants.setOnClickListener(onClickListener);
    }

    //всплывающее сообщение
    public void getMessageWindow (){
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(
                //TODO картинка - всплывающее сообщение
                R.layout.message_layout,
                (ViewGroup) findViewById(R.id.toast_layout));
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

    //проверка, что введено число
    public boolean isNumeric(String strNum) {
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }


    private final View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId()==R.id.buttonGoToNamesOfParticipants){
                EditText textNumberOfParticipant= findViewById(R.id.inputNumberOfParticipant);
                String str = textNumberOfParticipant.getText().toString();
                presenter.createNewTossAndStartNextActivityOrGetMessageWindow(str);
                //startActivityNamesOfParticipants ();
/*                if (isNumeric(str)) {
                    int num = Integer.parseInt(str);
                    toss = new TossForSecretSanta(num);
                    startActivityNamesOfParticipants ();
                } else {
                    getMessageWindow ();
                }*/
            }
        }
    };

    public void startActivityNamesOfParticipants (){
        Intent intent = new Intent(NumberOfParticipants.this, NamesOfParticipants.class);
        startActivity(intent);
        finish();
    }
}