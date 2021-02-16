//ввод имен участников
package com.example.secretsantaservi.quickplay;

import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.secretsantaservi.R;

import java.util.ArrayList;

import static com.example.secretsantaservi.quickplay.numbersofparicipants.NumberOfParticipantsPresenter.toss;

public class NamesOfParticipants extends AppCompatActivity {

    ArrayList<EditText> listOfEditTextForNames = new ArrayList(toss.getNumberOfParticipants());
    Button buttonGoToSetParametrs;
    LinearLayout linearLayoutNamesOfParticipants;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_names_of_participants);
        buildGUI();

    }

    public void buildGUI(){

        buttonGoToSetParametrs = findViewById(R.id.buttonGoToSetParameters);
        buttonGoToSetParametrs.setOnClickListener(onClickListener);

        linearLayoutNamesOfParticipants = findViewById(R.id.fillableLinearLayoutNamesOfParticipants);
        for (int i = 0; i < toss.getNumberOfParticipants(); i++) {
            addNewEditTextForName(i);
        }
    }

    public void addNewEditTextForName(int index){
        listOfEditTextForNames.add(new EditText(this));
        listOfEditTextForNames.get(index).setId(index);

        listOfEditTextForNames.get(index).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT));
        linearLayoutNamesOfParticipants.addView(listOfEditTextForNames.get(index));
    }

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

    private final View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (v.getId() == buttonGoToSetParametrs.getId()) {

                if (isAllNamesEntered()){
                    addNamesInToss();

                    Intent intent = new Intent(NamesOfParticipants.this, SelectNameForSetParameters.class);
                    startActivity(intent);
                    finish();
                } else {
                    getMessageWindow ();
                }
            }
        }
    };

    public boolean isAllNamesEntered(){
        boolean namesNotEmpty = true;
        for (int i = 0; i < toss.getNumberOfParticipants(); i++) {
            if (listOfEditTextForNames.get(i).getText().toString().length() == 0){
                namesNotEmpty = false;
            }
        }
        return namesNotEmpty;
    }

    public void addNamesInToss(){
        ArrayList<String> names = new ArrayList<>();
        for (int i = 0; i < toss.getNumberOfParticipants(); i++) {
            //names.add(listOfEditTextForNames.get(i).getText().toString());

            toss.addNewPersonByName(listOfEditTextForNames.get(i).getText().toString());
        }
    }

}