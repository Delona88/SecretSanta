package repositoryapi.repositoriesfactory;


import repository.RepositoryHM;

import repository.RepositoryInterface;
import secretsantamodel.Person;
import secretsantamodel.SecretSantaGame;


public class HMRepositoriesFactory implements RepositoriesFactory {

    public repository.RepositoryInterface<String, Person> createPersonsRepository(){
        return new RepositoryHM<>();
    }

    public RepositoryInterface<Integer, SecretSantaGame> createGamesRepository(){
        return new RepositoryHM<>();
    }
}
