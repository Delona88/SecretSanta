package com.example.secretsantaservi.API.repository.repositoriesfactory;

import com.example.secretsantaservi.API.repository.RepositoryInterface;

public interface RepositoriesFactory {
    RepositoryInterface createPersonsRepository();
    RepositoryInterface createGamesRepository();
}
