package com.example.secretsantaservi.androidrepository;

import android.content.Context;

import secretsantamodel.*;

import repositoryapi.repositoriesfactory.RepositoriesFactory;
import repository.RepositoryInterface;

public class SQLiteRepositoriesFactory implements RepositoriesFactory {

    private final String FILE_NAME_DB_GAMES;// = "Games.db";
    private final String FILE_NAME_DB_PERSONS;// = "Persons.db";

    private final Context context;

    public SQLiteRepositoriesFactory(Context context, String fileNameDbGames, String fileNameDbPersons) {
        this.context = context;
        FILE_NAME_DB_GAMES = fileNameDbGames;
        FILE_NAME_DB_PERSONS = fileNameDbPersons;
    }

    @Override
    public RepositoryInterface<String, Person> createPersonsRepository() {
        return new RepositorySQLite<>(context, FILE_NAME_DB_PERSONS, String.class, Person.class);
    }

    @Override
    public RepositoryInterface<Integer, SecretSantaGame> createGamesRepository() {
        return new RepositorySQLite<>(context, FILE_NAME_DB_GAMES, Integer.class, SecretSantaGame.class);
    }

}
