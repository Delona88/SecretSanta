package io.swagger.secretsantaservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import repositoryapi.repositoriesfactory.FileDropBoxRepositoriesFactory;
import repositoryapi.repositoriesfactory.FileLocalRepositoriesFactory;
import repositoryapi.repositoriesfactory.RepositoriesFactory;

import static io.swagger.Swagger2SpringBoot.FILE_NAME_HM_GAMES;
import static io.swagger.Swagger2SpringBoot.FILE_NAME_HM_PERSONS;

@Configuration
public class SecretSantaServerConfiguration {
    /**
     * HMRepositoriesFactory - для хранения в памяти
     * FileDropBoxRepositoriesFactory для сохранения в файл DropBox
     * FileRepositoriesFactory с параметрами IOFileLocal или IOFileDropBox - для сохранения в файл (локальный или DropBox)
     */

    @Bean
    public RepositoriesFactory fileDropBoxRepositoriesFactory(){
        return new FileDropBoxRepositoriesFactory(FILE_NAME_HM_GAMES, FILE_NAME_HM_PERSONS);
    }

    /**
     *  2 вариант
     */

/*    @Bean
    public RepositoriesFactory fileRepositoriesFactory(){
        return new FileLocalRepositoriesFactory(FILE_NAME_HM_GAMES, FILE_NAME_HM_PERSONS);
    }*/


}
