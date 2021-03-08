package repositoryapi.repositoriesfactory;

import repository.IOFileDropBox;
import repository.RepositoryFileWithHM;

import repository.RepositoryInterface;
import secretsantamodel.Person;
import secretsantamodel.SecretSantaGame;

public class FileDropBoxRepositoriesFactory implements RepositoriesFactory {

    private final String fileNameHmGames;
    private final String fileNameHmPersons;
    private final String accessToken;

    public FileDropBoxRepositoriesFactory(String accessToken, String fileNameHmGames, String fileNameHmPersons) {
        this.accessToken = accessToken;
        this.fileNameHmGames = fileNameHmGames;
        this.fileNameHmPersons = fileNameHmPersons;
    }

    @Override
    public RepositoryInterface<String, Person> createPersonsRepository() {
        return new RepositoryFileWithHM<>(new IOFileDropBox(accessToken), fileNameHmPersons);
    }

    @Override
    public RepositoryInterface<Integer, SecretSantaGame> createGamesRepository() {
        return new RepositoryFileWithHM<>(new IOFileDropBox(accessToken), fileNameHmGames);
    }
}
