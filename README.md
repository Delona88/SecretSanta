# SecretSanta
<br>Client-server Android application for organizing the Secret Santa draw.
<br>The model describes the entities and API required for the draw, as well as the repositories. To work with the Secret Santa API, you need two repositories, which are created using the Abstract Factory. At the moment, the following repositories are implemented: HashMap (in-memory), a file with a HashMap and a file with a HashMap loaded from / to DropBox.
<br>The application offers a choice of Quick Play in-memory, a local version with saving to device memory (in a file or a simple SQLiteDataBase) and a network version using the Retrofit client.
<br>When developing the client-server part, Swagger was used. The API specification was written in YAML in the Swagger Editor. As described by OpenAPI, a Retrofit client and a Spring Boot server stub were generated using the Swagger Codegen.
<br>A facade has been added to the Retrofit client, which is an asynchronous API. Dependency on the facade, which is used to access the server, has been added to the application.
<br>Spring Boot server stub has been improved. Added service call implementation to controllers. The service delegates requests to the repositories described in the model.
