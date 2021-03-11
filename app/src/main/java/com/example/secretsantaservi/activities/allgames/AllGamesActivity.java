package com.example.secretsantaservi.activities.allgames;

import android.content.Intent;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.secretsantaservi.*;
import com.example.secretsantaservi.activities.ConnectToGameActivity;
import com.example.secretsantaservi.activities.newgame.NewGameActivity;
import com.example.secretsantaservi.activities.PersonInfoActivity;
import com.example.secretsantaservi.activities.gameinfo.GameInfoActivity;

import java.util.ArrayList;

public class AllGamesActivity extends AppCompatActivity implements AllGamesView {
    private Button buttonGoBack;
    private Button buttonCreateGame;
    private Button buttonConnect;
    private Button buttonPersonInfo;
    private ProgressBar progressBar;
    private RecyclerView recyclerViewAllGames;

    private SecretSantaApplication secretSantaApplication;

    private AllGamesPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_games);

        secretSantaApplication = (SecretSantaApplication) getApplicationContext();

        presenter = new AllGamesPresenter(this, (SecretSantaApplication) getApplicationContext());
        presenter.init();

    }


    public void buildGUI() {

        recyclerViewAllGames = findViewById(R.id.all_games_recycler_view);
        recyclerViewAllGames.setLayoutManager(new LinearLayoutManager(this));

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        buttonPersonInfo = findViewById(R.id.buttonPersonInfo);
        buttonPersonInfo.setOnClickListener(onClickListener);

        buttonGoBack = findViewById(R.id.buttonGoNext);
        buttonGoBack.setOnClickListener(onClickListener);

        buttonCreateGame = findViewById(R.id.buttonCreateGame);
        buttonCreateGame.setOnClickListener(onClickListener);

        buttonConnect = findViewById(R.id.buttonConnect);
        buttonConnect.setOnClickListener(onClickListener);
    }

    public void createGamesList(ArrayList<Integer> gamesId) {
        AllGamesRecyclerViewAdapter adapter1 = new AllGamesRecyclerViewAdapter(gamesId);
        adapter1.setOnItemClickListener(onItemClickListener1);
        recyclerViewAllGames.setAdapter(adapter1);
    }

    private AllGamesRecyclerViewAdapter.OnItemClickListener onItemClickListener1= new AllGamesRecyclerViewAdapter.OnItemClickListener(){

        @Override
        public void onItemClick(View view, int position, String gameId) {
            Integer gamesId = Integer.parseInt(gameId);
            secretSantaApplication.setCurrentGameId(gamesId);
            goToGameInfo();
        }
    };

    private final View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            if (v.getId() == buttonGoBack.getId()) {
                finish();
            }
            if (v.getId() == buttonPersonInfo.getId()) {
                goToPersonInfo();
            }
            if (v.getId() == buttonCreateGame.getId()) {
                presenter.startGetNewGameID();
            }
            if (v.getId() == buttonConnect.getId()) {
                goToConnectToGame();
            }
        }
    };

    private void goToConnectToGame() {
        Intent intent = new Intent(AllGamesActivity.this, ConnectToGameActivity.class);
        startActivity(intent);
    }

    private void goToPersonInfo() {
        Intent intent = new Intent(AllGamesActivity.this, PersonInfoActivity.class);
        startActivity(intent);
    }

    private void goToGameInfo() {
        Intent intent = new Intent(AllGamesActivity.this, GameInfoActivity.class);
        startActivity(intent);
    }

    public void goToFillInfo() {
        Intent intent = new Intent(AllGamesActivity.this, NewGameActivity.class);
        startActivity(intent);
    }

    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    public void showToastServerProblem(String t) {
        String str = getString(R.string.title_server_problem) + "\n" + t;
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        presenter.restart();
    }
}