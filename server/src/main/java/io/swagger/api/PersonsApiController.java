package io.swagger.api;

import io.swagger.model.Person;
import io.swagger.model.PersonGame;
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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-02-16T11:27:20.662Z[GMT]")
@RestController
public class PersonsApiController implements PersonsApi {

    private static final Logger log = LoggerFactory.getLogger(PersonsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private SecretSantaApiService service;
    private Converter converter;

    @org.springframework.beans.factory.annotation.Autowired
    public PersonsApiController(ObjectMapper objectMapper, HttpServletRequest request, SecretSantaApiService service) { //создается service помеченый @Service
        this.objectMapper = objectMapper;
        this.request = request;
        this.service = service;
        this.converter = new Converter();
    }

    public ResponseEntity<Void> addPerson(@Parameter(in = ParameterIn.DEFAULT, description = "Person", required = true, schema = @Schema()) @Valid @RequestBody Person body) {
        service.addPerson(converter.convert(body));
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    public ResponseEntity<Void> addPersonGameToPerson(@Parameter(in = ParameterIn.PATH, description = "email", required = true, schema = @Schema()) @PathVariable("email") String email, @Parameter(in = ParameterIn.DEFAULT, description = "Array emails", required = true, schema = @Schema()) @Valid @RequestBody PersonGame body) {
        service.addPersonGameToPerson(email, converter.convert(body));
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Void> deletePerson(@Parameter(in = ParameterIn.PATH, description = "email", required = true, schema = @Schema()) @PathVariable("email") String email) {
        service.deletePerson(email);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Void> deletePersonGameFromPerson(@Parameter(in = ParameterIn.PATH, description = "email", required = true, schema = @Schema()) @PathVariable("email") String email, @Parameter(in = ParameterIn.PATH, description = "id", required = true, schema = @Schema()) @PathVariable("id") Integer id) {
        service.deletePersonGameFromPerson(id, email);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<List<Person>> getAllPersons() {
        // stub
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Person>>(objectMapper.readValue("[ {\n  \"name\" : \"name\",\n  \"games\" : [ {\n    \"idGame\" : 0,\n    \"arrayNaughtyListEmail\" : [ \"arrayNaughtyListEmail\", \"arrayNaughtyListEmail\" ],\n    \"wishlist\" : \"wishlist\",\n    \"active\" : true,\n    \"receiverEmail\" : \"receiverEmail\"\n  }, {\n    \"idGame\" : 0,\n    \"arrayNaughtyListEmail\" : [ \"arrayNaughtyListEmail\", \"arrayNaughtyListEmail\" ],\n    \"wishlist\" : \"wishlist\",\n    \"active\" : true,\n    \"receiverEmail\" : \"receiverEmail\"\n  } ],\n  \"email\" : \"email\"\n}, {\n  \"name\" : \"name\",\n  \"games\" : [ {\n    \"idGame\" : 0,\n    \"arrayNaughtyListEmail\" : [ \"arrayNaughtyListEmail\", \"arrayNaughtyListEmail\" ],\n    \"wishlist\" : \"wishlist\",\n    \"active\" : true,\n    \"receiverEmail\" : \"receiverEmail\"\n  }, {\n    \"idGame\" : 0,\n    \"arrayNaughtyListEmail\" : [ \"arrayNaughtyListEmail\", \"arrayNaughtyListEmail\" ],\n    \"wishlist\" : \"wishlist\",\n    \"active\" : true,\n    \"receiverEmail\" : \"receiverEmail\"\n  } ],\n  \"email\" : \"email\"\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Person>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Person>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Person> getPersonById(@Parameter(in = ParameterIn.PATH, description = "to get person", required = true, schema = @Schema()) @PathVariable("email") String email) {
        secretsantamodel.Person person = service.getPersonById(email);
        if (person != null) {
            return new ResponseEntity<Person>(converter.convert(service.getPersonById(email)), HttpStatus.OK);
        } else {
            return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Void> replacePerson(@Parameter(in = ParameterIn.PATH, description = "to get person", required = true, schema = @Schema()) @PathVariable("email") String email, @Parameter(in = ParameterIn.DEFAULT, description = "Person", required = true, schema = @Schema()) @Valid @RequestBody Person body) {
        service.replacePerson(email, converter.convert(body));
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Void> setNaughtyList(@Parameter(in = ParameterIn.PATH, description = "id", required = true, schema = @Schema()) @PathVariable("id") Integer id, @Parameter(in = ParameterIn.PATH, description = "email", required = true, schema = @Schema()) @PathVariable("email") String email, @Parameter(in = ParameterIn.DEFAULT, description = "Person game", required = true, schema = @Schema()) @Valid @RequestBody List<String> body) {
        service.setNaughtyList(email, id, new ArrayList<>(body));
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Void> setPersonGameActive(@Parameter(in = ParameterIn.PATH, description = "id", required = true, schema = @Schema()) @PathVariable("id") Integer id, @Parameter(in = ParameterIn.PATH, description = "email", required = true, schema = @Schema()) @PathVariable("email") String email, @NotNull @Parameter(in = ParameterIn.QUERY, description = "active", required = true, schema = @Schema()) @Valid @RequestParam(value = "active", required = true) Boolean active) {
        service.setPersonGameActive(id, email, active);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Void> setReceiver(@Parameter(in = ParameterIn.PATH, description = "id", required = true, schema = @Schema()) @PathVariable("id") Integer id, @Parameter(in = ParameterIn.PATH, description = "email", required = true, schema = @Schema()) @PathVariable("email") String email, @NotNull @Parameter(in = ParameterIn.QUERY, description = "receiverEmail", required = true, schema = @Schema()) @Valid @RequestParam(value = "receiverEmail", required = true) String receiverEmail) {
        service.setReceiver(email, id, receiverEmail);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Void> setWhishlist(@Parameter(in = ParameterIn.PATH, description = "id", required = true, schema = @Schema()) @PathVariable("id") Integer id, @Parameter(in = ParameterIn.PATH, description = "email", required = true, schema = @Schema()) @PathVariable("email") String email, @NotNull @Parameter(in = ParameterIn.QUERY, description = "wishlist", required = true, schema = @Schema()) @Valid @RequestParam(value = "wish", required = true) String wish) {
        service.setWhishlist(email, id, wish);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
