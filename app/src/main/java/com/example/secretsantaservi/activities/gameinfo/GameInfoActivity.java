package com.example.secretsantaservi.activities.gameinfo;

import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.secretsantaservi.R;
import com.example.secretsantaservi.SecretSantaApplication;

public class GameInfoActivity extends AppCompatActivity implements GameInfoView {
    private TextView textViewGameInfo;
    private TextView textViewReceiverInfo;
    private TextView textViewReceiverWish;
    private TextView textViewWishInfo;
    private TextView textViewTextSendMessageToSanta;
    private EditText editTextWish;
    private Button buttonBack;
    private Button buttonSendWish;
    private Button buttonDeleteGame;
    private ProgressBar progressBar;

    private GameInfoPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_info);

        presenter = new GameInfoPresenter(this, (SecretSantaApplication) getApplicationContext());
        presenter.init();
        buildGUI();
    }

    public void buildGUI() {
        progressBar = findViewById(R.id.progressBar);

        textViewGameInfo = findViewById(R.id.textViewShowGameInfoGameId);
        textViewReceiverInfo = findViewById(R.id.textViewShowGameInfoReceiver);
        textViewReceiverWish = findViewById(R.id.textViewShowGameInfoReceiverWish);
        textViewWishInfo = findViewById(R.id.textViewShowGameInfoWish);
        textViewTextSendMessageToSanta = findViewById(R.id.textViewShowGameInfoSendMessage);

        editTextWish = findViewById(R.id.editTextShowGameInfoWish);

        buttonDeleteGame = findViewById(R.id.buttonDeleteGame);
        buttonDeleteGame.setOnClickListener(onClickListener);

        buttonBack = findViewById(R.id.buttonGoNext);
        buttonBack.setOnClickListener(onClickListener);

        buttonSendWish = findViewById(R.id.buttonSendWish);
        buttonSendWish.setOnClickListener(onClickListener);
    }


    public void addPersonGameInfo(String gameInfo) {
        textViewGameInfo.setText(gameInfo);
    }

    public void addWishInfo(String wishInfo) {
        if (wishInfo == null) {
            textViewWishInfo.setText(getResources().getString(R.string.title_wish_not_sent_earlier));
        } else {
            textViewWishInfo.setText(wishInfo + "\n");
            textViewTextSendMessageToSanta.setText(getResources().getString(R.string.title_wish_already_sent_earlier));
        }
    }

    public void addReceiverInfo(String receiverInfo) {
        textViewReceiverInfo.setText(receiverInfo);
    }

    public void addReceiverWishInfo(String receiverWishInfo) {
        textViewReceiverWish.setText(receiverWishInfo);
    }

    private final View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (v.getId() == buttonBack.getId()) {
                finish();
            }
            if (v.getId() == buttonSendWish.getId()) {
                checkInfoAndSetWishlistOrGetMessage();
            }
            if (v.getId() == buttonDeleteGame.getId()) {
                presenter.startDeleteGame();
                finish();
            }
        }
    };

    public void checkInfoAndSetWishlistOrGetMessage() {
        if (isAllInfoEntered()) {
            String wish = editTextWish.getText().toString();
            textViewWishInfo.setText(wish + "\n");
            presenter.startSetWishlist(wish);
        } else {
            showToastIncorrectInfo();
        }
    }


    public boolean isAllInfoEntered() {
        return (editTextWish.getText().toString().length() != 0);
    }

    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    public void showToast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    public void showToastServerProblem(String t) {
        String str = getResources().getString(R.string.title_server_problem) + "\n" + t;
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    public void showToastIncorrectInfo() {
        Toast.makeText(this, getResources().getString(R.string.title_incorrect_info), Toast.LENGTH_LONG).show();
    }


    public void showToastWishSend() {
        Toast.makeText(this, getResources().getString(R.string.title_wish_send), Toast.LENGTH_SHORT).show();
    }

    public void showToastGameDelete() {
        Toast.makeText(this, getResources().getString(R.string.title_game_delete), Toast.LENGTH_SHORT).show();
    }


}