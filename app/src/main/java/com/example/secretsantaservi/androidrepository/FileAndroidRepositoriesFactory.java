package com.example.secretsantaservi.androidrepository;

import repositoryapi.repositoriesfactory.RepositoriesFactory;
import repository.RepositoryInterface;
import android.content.Context;
import secretsantamodel.Person;
import secretsantamodel.SecretSantaGame;


public class FileAndroidRepositoriesFactory implements RepositoriesFactory {

    private final static String FILE_NAME_HM_GAMES = "HashMapGames.ser";
    private final static String FILE_NAME_HM_PERSONS = "HashMapPersons.ser";
    private final Context context;

    public FileAndroidRepositoriesFactory(Context context) {
        this.context = context;
    }

    @Override
    public RepositoryInterface<String, Person> createPersonsRepository() {
        return new RepositoryFileWithHM<>(FILE_NAME_HM_PERSONS, context);
    }

    @Override
    public RepositoryInterface<Integer, SecretSantaGame> createGamesRepository() {
        return new RepositoryFileWithHM<>(FILE_NAME_HM_GAMES, context);
    }
}
