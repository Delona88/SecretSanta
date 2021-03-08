package repositoryapi.repositoriesfactory;

import repository.IOFile;
import repository.RepositoryFileWithHM;

import repository.RepositoryInterface;
import secretsantamodel.Person;
import secretsantamodel.SecretSantaGame;

public class FileLocalRepositoriesFactory implements RepositoriesFactory {

    private final String fileNameHmGames;
    private final String fileNameHmPersons;

    public FileLocalRepositoriesFactory(String fileNameHmGames, String fileNameHmPersons) {
        this.fileNameHmGames = fileNameHmGames;
        this.fileNameHmPersons = fileNameHmPersons;
    }

    @Override
    public RepositoryInterface<String, Person> createPersonsRepository() {
        return new RepositoryFileWithHM<>(new IOFile(), fileNameHmPersons);
    }

    @Override
    public RepositoryInterface<Integer, SecretSantaGame> createGamesRepository() {
        return new RepositoryFileWithHM<>(new IOFile(), fileNameHmGames);
    }
}
