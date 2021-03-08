package com.example.secretsantaservi.androidrepository.repositoriesfactory;

import android.content.Context;

import com.example.secretsantaservi.androidrepository.RepositoryInterface;
import com.example.secretsantaservi.androidrepository.RepositorySQLite;
import secretsantamodel.*;

public class SQLiteRepositoriesFactory implements RepositoriesFactory {

    private final String fileNameDbGames;
    private final String fileNameDbPersons;

    private final Context context;

    public SQLiteRepositoriesFactory(Context context, String fileNameDbGames, String fileNameDbPersons) {
        this.context = context;
        this.fileNameDbGames = fileNameDbGames;
        this.fileNameDbPersons = fileNameDbPersons;
    }

    @Override
    public RepositoryInterface<String, Person> createPersonsRepository() {
        return new RepositorySQLite<>(context, fileNameDbPersons, String.class, Person.class);
    }

    @Override
    public RepositoryInterface<Integer, SecretSantaGame> createGamesRepository() {
        return new RepositorySQLite<>(context, fileNameDbGames, Integer.class, SecretSantaGame.class);
    }

}
