package com.example.secretsantaservi.androidrepository.repositoriesfactory.fortests;

import com.example.secretsantaservi.androidrepository.RepositoryInterface;
import com.example.secretsantaservi.androidrepository.repositoriesfactory.RepositoriesFactory;
import secretsantamodel.Person;
import secretsantamodel.SecretSantaGame;


public class HMRepositoriesFactory implements RepositoriesFactory {

    public RepositoryInterface<String, Person> createPersonsRepository(){
        return new RepositoryHM<>();
    }

    public RepositoryInterface<Integer, SecretSantaGame> createGamesRepository(){
        return new RepositoryHM<>();
    }
}
