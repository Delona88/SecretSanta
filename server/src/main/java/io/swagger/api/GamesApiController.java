package io.swagger.api;

import io.swagger.model.Person;
import io.swagger.model.SecretSantaGame;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.secretsantaservice.Converter;
import io.swagger.secretsantaservice.SecretSantaApiService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-02-16T11:27:20.662Z[GMT]")
@RestController
public class GamesApiController implements GamesApi {

    private static final Logger log = LoggerFactory.getLogger(GamesApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private Converter converter;
    private SecretSantaApiService service;


    @org.springframework.beans.factory.annotation.Autowired
    public GamesApiController(ObjectMapper objectMapper, HttpServletRequest request, SecretSantaApiService service) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.service = service;
        this.converter = new Converter();
    }

    public ResponseEntity<Void> addGame(@Parameter(in = ParameterIn.DEFAULT, description = "game to add", required=true, schema=@Schema()) @Valid @RequestBody SecretSantaGame body) {
        service.addGame(converter.convert(body));
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    public ResponseEntity<Void> addPersonInGame(@Parameter(in = ParameterIn.PATH, description = "id", required=true, schema=@Schema()) @PathVariable("id") Integer id,@Parameter(in = ParameterIn.PATH, description = "email", required=true, schema=@Schema()) @PathVariable("email") String email) {
        service.addPersonInGame(id, email);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteGame(@Parameter(in = ParameterIn.PATH, description = "id", required=true, schema=@Schema()) @PathVariable("id") Integer id) {
        service.deleteGame(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Void> deletePersonFromGame(@Parameter(in = ParameterIn.PATH, description = "id", required=true, schema=@Schema()) @PathVariable("id") Integer id,@Parameter(in = ParameterIn.PATH, description = "email", required=true, schema=@Schema()) @PathVariable("email") String email) {
        service.deleteEmailFromGame(id, email);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<SecretSantaGame> getGameById(@Parameter(in = ParameterIn.PATH, description = "to get game", required=true, schema=@Schema()) @PathVariable("id") Integer id) {

        return new ResponseEntity<SecretSantaGame>(converter.convert(service.getGameById(id)), HttpStatus.OK);
    }

    public ResponseEntity<Integer> getNewID() {
        return new ResponseEntity<Integer>(service.getNewID(), HttpStatus.OK);

    }

    public ResponseEntity<List<Person>> getPersonsByGameId(@Parameter(in = ParameterIn.PATH, description = "id", required=true, schema=@Schema()) @PathVariable("id") Integer id) {
        List<Person> personList = new ArrayList<>();
        HashMap<String, secretsantamodel.Person> personHashMap = service.getPersonsByGameId(id);
        for (secretsantamodel.Person person : personHashMap.values()) {
            personList.add(converter.convert(person));
        }
        return new ResponseEntity<List<Person>>(personList, HttpStatus.OK);
    }

    public ResponseEntity<Void> setGamePlayed(@Parameter(in = ParameterIn.PATH, description = "id", required=true, schema=@Schema()) @PathVariable("id") Integer id,@NotNull @Parameter(in = ParameterIn.QUERY, description = "played" ,required=true,schema=@Schema()) @Valid @RequestParam(value = "played", required = true) Boolean played) {
        service.setGamePlayed(id, played);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Void> startToss(@Parameter(in = ParameterIn.PATH, description = "id", required=true, schema=@Schema()) @PathVariable("id") Integer id) {
        try {
            service.startToss(id);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (secretsantamodel.BadConditionsException badConditions) {
            badConditions.printStackTrace();
            System.out.println("Организатор указал неверные ограничения, игра не может быть сформирована ");
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
    }

}
