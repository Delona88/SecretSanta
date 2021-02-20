package com.example.secretsantaservi.API.repository.repositoriesfactory;

import com.example.secretsantaservi.API.repository.IOInterface;
import com.example.secretsantaservi.API.repository.RepositoryFileWithHM;
import com.example.secretsantaservi.API.repository.RepositoryInterface;

public class FileRepositoriesFactory implements RepositoriesFactory {

    private final static String FILE_NAME_HM_GAMES = "HashMapGames.ser";
    private final static String FILE_NAME_HM_PERSONS = "HashMapPersons.ser";

    @Override
    public RepositoryInterface createPersonsRepository() {
        return new RepositoryFileWithHM(FILE_NAME_HM_PERSONS);
    }

    @Override
    public RepositoryInterface createGamesRepository() {
        return new RepositoryFileWithHM(FILE_NAME_HM_GAMES);
    }
}
