package repositoryapi.repositoriesfactory;

import repository.RepositoryInterface;
import secretsantamodel.Person;
import secretsantamodel.SecretSantaGame;

public interface RepositoriesFactory {
    RepositoryInterface<String, Person> createPersonsRepository();
    RepositoryInterface<Integer, SecretSantaGame> createGamesRepository();
}
