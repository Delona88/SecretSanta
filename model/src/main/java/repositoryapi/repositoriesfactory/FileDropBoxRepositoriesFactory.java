package repositoryapi.repositoriesfactory;

import repository.IOFileDropBox;
import repository.RepositoryFileWithHM;

import repository.RepositoryInterface;
import secretsantamodel.Person;
import secretsantamodel.SecretSantaGame;

public class FileDropBoxRepositoriesFactory implements RepositoriesFactory {

    private final String FILE_NAME_HM_GAMES ;//= "HashMapGames.ser";
    private final String FILE_NAME_HM_PERSONS ;//= "HashMapPersons.ser";

    public FileDropBoxRepositoriesFactory(String fileNameHmGames, String fileNameHmPersons) {
        FILE_NAME_HM_GAMES = fileNameHmGames;
        FILE_NAME_HM_PERSONS = fileNameHmPersons;
    }

    @Override
    public repository.RepositoryInterface<String, Person> createPersonsRepository() {
        return new RepositoryFileWithHM<>(new IOFileDropBox(), FILE_NAME_HM_PERSONS);
    }

    @Override
    public RepositoryInterface<Integer, SecretSantaGame> createGamesRepository() {
        return new RepositoryFileWithHM<>(new IOFileDropBox(), FILE_NAME_HM_GAMES);
    }
}
