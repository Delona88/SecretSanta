package com.example.secretsantaservi.androidrepository.repositoriesfactory;

import com.example.secretsantaservi.androidrepository.RepositoryInterface;
import secretsantamodel.Person;
import secretsantamodel.SecretSantaGame;

public interface RepositoriesFactory {
    RepositoryInterface<String, Person> createPersonsRepository();
    RepositoryInterface<Integer, SecretSantaGame> createGamesRepository();
}
