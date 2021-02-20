package com.example.secretsantaservi.API.repository.repositoriesfactory;

import com.example.secretsantaservi.API.repository.RepositoryHM;
import com.example.secretsantaservi.API.repository.RepositoryInterface;

public class HMRepositoriesFactory implements RepositoriesFactory {

    public RepositoryInterface createPersonsRepository(){
        return new RepositoryHM();
    }

    public RepositoryInterface createGamesRepository(){
        return new RepositoryHM();
    }
}
