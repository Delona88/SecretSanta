package com.example.secretsantaservi.API.repository.repositoriesfactory;

import android.content.Context;
import com.example.secretsantaservi.API.repository.IOInterface;
import com.example.secretsantaservi.API.repository.RepositoryFileWithHM;
import com.example.secretsantaservi.API.repository.RepositoryInterface;
import com.example.secretsantaservi.API.repository.RepositorySQLite;
import com.example.secretsantaservi.secretsanta.Person;
import com.example.secretsantaservi.secretsanta.SecretSantaGame;

public class SQLiteRepositoriesFactory implements RepositoriesFactory {

    private final static String FILE_NAME_DB_GAMES = "Games.db";
    private final static String FILE_NAME_DB_PERSONS = "Persons.db";

    private Context context;

    public SQLiteRepositoriesFactory(Context context) {
        this.context = context;
    }

    @Override
    public RepositoryInterface createPersonsRepository() {
        return new RepositorySQLite(context, FILE_NAME_DB_PERSONS, String.class, Person.class);
    }

    @Override
    public RepositoryInterface createGamesRepository() {
        return new RepositorySQLite(context, FILE_NAME_DB_GAMES, Integer.class, SecretSantaGame.class);
    }

}
