package io.swagger;

import repositoryapi.repositoriesfactory.RepositoriesFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import repositoryapi.repositoriesfactory.FileDropBoxRepositoriesFactory;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@EnableOpenApi
@ComponentScan(basePackages = { "io.swagger", "io.swagger.api" , "io.swagger.configuration"})
public class Swagger2SpringBoot implements CommandLineRunner {

    public final static String FILE_NAME_HM_GAMES = "HashMapGames.ser";
    public final static String FILE_NAME_HM_PERSONS = "HashMapPersons.ser";

    //public static final RepositoriesFactory REPO_FACTORY = new FileDropBoxRepositoriesFactory(FILE_NAME_HM_GAMES, FILE_NAME_HM_PERSONS);

    @Override
    public void run(String... arg0) throws Exception {
        if (arg0.length > 0 && arg0[0].equals("exitcode")) {
            throw new ExitException();
        }
    }

    public static void main(String[] args) throws Exception {
        new SpringApplication(Swagger2SpringBoot.class).run(args);
    }

    class ExitException extends RuntimeException implements ExitCodeGenerator {
        private static final long serialVersionUID = 1L;

        @Override
        public int getExitCode() {
            return 10;
        }

    }
}
