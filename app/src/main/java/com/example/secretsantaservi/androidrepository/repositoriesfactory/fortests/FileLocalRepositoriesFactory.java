package com.example.secretsantaservi.androidrepository.repositoriesfactory.fortests;

import com.example.secretsantaservi.androidrepository.RepositoryFileWithHM;
import com.example.secretsantaservi.androidrepository.RepositoryInterface;
import com.example.secretsantaservi.androidrepository.repositoriesfactory.RepositoriesFactory;
import secretsantamodel.Person;
import secretsantamodel.SecretSantaGame;

public class FileLocalRepositoriesFactory implements RepositoriesFactory {

    private final  String FILE_NAME_HM_GAMES;
    private final  String FILE_NAME_HM_PERSONS;

    public FileLocalRepositoriesFactory(String fileNameHmGames, String fileNameHmPersons) {
        FILE_NAME_HM_GAMES = fileNameHmGames;
        FILE_NAME_HM_PERSONS = fileNameHmPersons;
    }

    @Override
    public RepositoryInterface<String, Person> createPersonsRepository() {
        return new RepositoryFileWithHM<>(FILE_NAME_HM_PERSONS);
    }

    @Override
    public RepositoryInterface<Integer, SecretSantaGame> createGamesRepository() {
        return new RepositoryFileWithHM<>(FILE_NAME_HM_GAMES);
    }
}
