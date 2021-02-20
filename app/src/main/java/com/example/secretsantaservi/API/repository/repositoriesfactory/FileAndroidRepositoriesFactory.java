package com.example.secretsantaservi.API.repository.repositoriesfactory;

import android.content.Context;
import com.example.secretsantaservi.API.repository.IOInterface;
import com.example.secretsantaservi.API.repository.RepositoryFileWithHM;
import com.example.secretsantaservi.API.repository.RepositoryInterface;

public class FileAndroidRepositoriesFactory implements RepositoriesFactory {

    private final static String FILE_NAME_HM_GAMES = "HashMapGames.ser";
    private final static String FILE_NAME_HM_PERSONS = "HashMapPersons.ser";
    private Context context;

    public FileAndroidRepositoriesFactory(Context context) {
        this.context = context;
    }

    @Override
    public RepositoryInterface createPersonsRepository() {
        return new RepositoryFileWithHM(FILE_NAME_HM_PERSONS, context);
    }

    @Override
    public RepositoryInterface createGamesRepository() {
        return new RepositoryFileWithHM(FILE_NAME_HM_GAMES, context);
    }
}
