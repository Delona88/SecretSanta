package com.example.secretsantaservi.androidrepository.repositoriesfactory;

import android.content.Context;
import com.example.secretsantaservi.androidrepository.RepositoryFileWithHM;
import com.example.secretsantaservi.androidrepository.RepositoryInterface;
import secretsantamodel.Person;
import secretsantamodel.SecretSantaGame;

public class FileAndroidRepositoriesFactory implements RepositoriesFactory {

    private final Context context;
    private final String fileNameHmGames;
    private final String fileNameHmPersons;

    public FileAndroidRepositoriesFactory(Context context, String fileNameHmGames, String fileNameHmPersons) {
        this.context = context;
        this.fileNameHmGames = fileNameHmGames;
        this.fileNameHmPersons = fileNameHmPersons;
    }

    @Override
    public RepositoryInterface<String, Person> createPersonsRepository() {
        return new RepositoryFileWithHM<>(fileNameHmPersons, context);
    }

    @Override
    public RepositoryInterface<Integer, SecretSantaGame> createGamesRepository() {
        return new RepositoryFileWithHM<>(fileNameHmGames, context);
    }
}
