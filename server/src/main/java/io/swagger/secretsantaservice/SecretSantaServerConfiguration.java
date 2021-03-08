package io.swagger.secretsantaservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import repositoryapi.repositoriesfactory.FileDropBoxRepositoriesFactory;
import repositoryapi.repositoriesfactory.FileLocalRepositoriesFactory;
import repositoryapi.repositoriesfactory.RepositoriesFactory;

import static io.swagger.Swagger2SpringBoot.*;

@Configuration
public class SecretSantaServerConfiguration {
    /**
     * HMRepositoriesFactory - in-memory
     * FileLocalRepositoriesFactory - for saving in local file
     * FileDropBoxRepositoriesFactory - for saving in file and load to DropBox
     */

/*    @Bean
    public RepositoriesFactory repositoriesFactory() {
        return new FileDropBoxRepositoriesFactory(ACCESS_TOKEN_DROPBOX, FILE_NAME_HM_GAMES, FILE_NAME_HM_PERSONS);
    }*/

    @Bean
    public RepositoriesFactory repositoriesFactory() {
        return new FileLocalRepositoriesFactory(FILE_NAME_HM_GAMES, FILE_NAME_HM_PERSONS);
    }

}
