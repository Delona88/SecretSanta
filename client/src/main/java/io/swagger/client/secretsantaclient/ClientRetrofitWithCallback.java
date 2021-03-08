package io.swagger.client.secretsantaclient;

import io.swagger.client.ApiClient;
import io.swagger.client.api.GamesApi;
import io.swagger.client.api.PersonGamesApi;
import io.swagger.client.api.PersonsApi;
import retrofit2.Call;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import secretsantamodel.*;


public class ClientRetrofitWithCallback implements ApiWithCallbackInterface {
    Converter converter;
    PersonsApi personsApi;
    GamesApi gamesApi;
    PersonGamesApi personGamesApi;

    public ClientRetrofitWithCallback() {
        converter = new Converter();
        personsApi = new ApiClient().createService(PersonsApi.class);
        gamesApi = new ApiClient().createService(GamesApi.class);
        personGamesApi = new ApiClient().createService(PersonGamesApi.class);
    }

    @Override
    public void getPersonById(String email, Callback<Person> myCallback) {
        personsApi.getPersonById(email).enqueue(new retrofit2.Callback<io.swagger.client.model.Person>() {
            @Override
            public void onResponse(Call<io.swagger.client.model.Person> call, Response<io.swagger.client.model.Person> response) {
                if (response.isSuccessful()) {
                    io.swagger.client.model.Person person = response.body();
                    if (person != null) {
                        myCallback.onResponse(converter.convert(person));
                    }
                } else {
                    myCallback.onResponse(null);
                }
            }

            @Override
            public void onFailure(Call<io.swagger.client.model.Person> call, Throwable t) {
                myCallback.onFailure(t);
            }
        });
    }

    @Override
    public void getAllPersons(Callback<HashMap<String, Person>> myCallback) {
        personsApi.getAllPersons().enqueue(new retrofit2.Callback<List<io.swagger.client.model.Person>>() {
            @Override
            public void onResponse(Call<List<io.swagger.client.model.Person>> call, Response<List<io.swagger.client.model.Person>> response) {
                HashMap<String, Person> hashMap = new HashMap<>();
                if (response.isSuccessful()) {
                    List<io.swagger.client.model.Person> list = response.body();
                    if (list != null) {
                        for (io.swagger.client.model.Person person : list) {
                            hashMap.put(person.getEmail(), converter.convert(person));
                        }
                    }
                    myCallback.onResponse(hashMap);
                } else {
                    myCallback.onResponse(hashMap);
                }
            }

            @Override
            public void onFailure(Call<List<io.swagger.client.model.Person>> call, Throwable t) {
                myCallback.onFailure(t);
            }
        });
    }

    @Override
    public void addPerson(Person person, Callback<Object> myCallback) {
        personsApi.addPerson(converter.convert(person)).enqueue(new retrofit2.Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    myCallback.onResponse(null);
                } else {
                    myCallback.onResponse(null);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                myCallback.onFailure(t);
            }
        });
    }

    @Override
    public void replacePerson(Person person, String email, Callback<Object> myCallback) {
        personsApi.replacePerson(converter.convert(person), email).enqueue(new retrofit2.Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    myCallback.onResponse(null);
                } else {
                    myCallback.onResponse(null);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                myCallback.onFailure(t);
            }
        });
    }

    @Override
    public void deletePerson(String email, Callback<Object> myCallback) {
        personsApi.deletePerson(email).enqueue(new retrofit2.Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    myCallback.onResponse(null);
                } else {
                    myCallback.onResponse(null);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                myCallback.onFailure(t);
            }
        });
    }


    @Override
    public void addPersonGameToPerson(String email, PersonGame personGame, Callback<Object> myCallback) {
        personGamesApi.addPersonGameToPerson(converter.convert(personGame), email).enqueue(new retrofit2.Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    myCallback.onResponse(null);
                } else {
                    myCallback.onResponse(null);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                myCallback.onFailure(t);
            }
        });
    }

    @Override
    public void deletePersonGameFromPerson(Integer gameId, String email, Callback<Object> myCallback) {
        personGamesApi.deletePersonGameFromPerson(email, gameId).enqueue(new retrofit2.Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    myCallback.onResponse(null);
                } else {
                    myCallback.onResponse(null);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                myCallback.onFailure(t);
            }
        });
    }

    @Override
    public void setNaughtyList(String email, Integer gameId, ArrayList<String> arraylist, Callback<Object> myCallback) {
        personGamesApi.setNaughtyList(arraylist, gameId, email).enqueue(new retrofit2.Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    myCallback.onResponse(null);
                } else {
                    myCallback.onResponse(null);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                myCallback.onFailure(t);
            }
        });
    }

    @Override
    public void setReceiver(String emailSanta, Integer gameId, String emailReceiver, Callback<Object> myCallback) {
        personGamesApi.setReceiver(gameId, emailSanta, emailReceiver).enqueue(new retrofit2.Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    myCallback.onResponse(null);
                } else {
                    myCallback.onResponse(null);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                myCallback.onFailure(t);
            }
        });
    }

    @Override
    public void setWhishlist(String email, Integer gameId, String wish, Callback<Object> myCallback) {
        personGamesApi.setWhishlist(gameId, email, wish).enqueue(new retrofit2.Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    myCallback.onResponse(null);
                } else {
                    myCallback.onResponse(null);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                myCallback.onFailure(t);
            }
        });
    }

    @Override
    public void setPersonGameActive(Integer gameId, String email, Boolean activity, Callback<Object> myCallback) {
        personGamesApi.setPersonGameActive(gameId, email, activity).enqueue(new retrofit2.Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    myCallback.onResponse(null);
                } else {
                    myCallback.onResponse(null);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                myCallback.onFailure(t);
            }
        });
    }

    @Override
    public void addGame(SecretSantaGame game, Callback<Object> myCallback) {
        gamesApi.addGame(converter.convert(game)).enqueue(new retrofit2.Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    myCallback.onResponse(null);
                } else {
                    myCallback.onResponse(null);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                myCallback.onFailure(t);
            }
        });
    }

    @Override
    public void getGameById(int id, Callback<SecretSantaGame> myCallback) {
        gamesApi.getGameById(id).enqueue(new retrofit2.Callback<io.swagger.client.model.SecretSantaGame>() {
            @Override
            public void onResponse(Call<io.swagger.client.model.SecretSantaGame> call, Response<io.swagger.client.model.SecretSantaGame> response) {
                if (response.isSuccessful()) {
                    io.swagger.client.model.SecretSantaGame game = response.body();
                    if (game != null) {
                        myCallback.onResponse(converter.convert(game));
                    }
                } else {
                    myCallback.onResponse(null);
                }
            }

            @Override
            public void onFailure(Call<io.swagger.client.model.SecretSantaGame> call, Throwable t) {
                myCallback.onFailure(t);
            }
        });
    }

    @Override
    public void deleteGame(Integer gameId, Callback<Object> myCallback) {
        gamesApi.deleteGame(gameId).enqueue(new retrofit2.Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    myCallback.onResponse(null);
                } else {
                    myCallback.onResponse(null);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                myCallback.onFailure(t);
            }
        });
    }

    @Override
    public void getNewID(Callback<Integer> myCallback) {
        gamesApi.getNewID().enqueue(new retrofit2.Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.isSuccessful()) {
                    myCallback.onResponse(response.body());
                } else {
                    myCallback.onResponse(null);
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                myCallback.onFailure(t);
            }
        });
    }


    @Override
    public void getHMWithPersonsInfo(Integer gameId, Callback<HashMap<String, String>> myCallback) {
        gamesApi.getPersonsByGameId(gameId).enqueue(new retrofit2.Callback<List<io.swagger.client.model.Person>>() {
            @Override
            public void onResponse(Call<List<io.swagger.client.model.Person>> call, Response<List<io.swagger.client.model.Person>> response) {

                HashMap<String, String> hashMapPersonsInfo = new HashMap<>();

                if (response.isSuccessful()) {
                    List<io.swagger.client.model.Person> list = response.body();
                    if (list != null) {
                        for (io.swagger.client.model.Person person : list) {
                            String str = person.getName() + " (" + person.getEmail() + ") ";
                            hashMapPersonsInfo.put(person.getEmail(), str);
                        }
                    }
                }
                myCallback.onResponse(hashMapPersonsInfo);
            }

            @Override
            public void onFailure(Call<List<io.swagger.client.model.Person>> call, Throwable t) {
                myCallback.onFailure(t);
            }
        });
    }

    @Override
    public void getPersonsByGameId(Integer gameId, Callback<HashMap<String, Person>> myCallback) {
        gamesApi.getPersonsByGameId(gameId).enqueue(new retrofit2.Callback<List<io.swagger.client.model.Person>>() {
            @Override
            public void onResponse(Call<List<io.swagger.client.model.Person>> call, Response<List<io.swagger.client.model.Person>> response) {

                HashMap<String, Person> hashMapPersons = new HashMap<>();

                if (response.isSuccessful()) {
                    List<io.swagger.client.model.Person> list = response.body();
                    if (list != null) {
                        for (io.swagger.client.model.Person person : list) {
                            hashMapPersons.put(person.getEmail(), converter.convert(person));
                        }
                    }
                }
                myCallback.onResponse(hashMapPersons);
            }

            @Override
            public void onFailure(Call<List<io.swagger.client.model.Person>> call, Throwable t) {
                myCallback.onFailure(t);
            }
        });
    }

    @Override
    public void addPersonInGame(Integer gameId, String email, Callback<Object> myCallback) {
        gamesApi.addPersonInGame(gameId, email).enqueue(new retrofit2.Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    myCallback.onResponse(null);
                } else {
                    myCallback.onResponse(null);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                myCallback.onFailure(t);
            }
        });
    }

    @Override
    public void deletePersonFromGame(Integer gameId, String email, Callback<Object> myCallback) {
        gamesApi.deletePersonFromGame(gameId, email).enqueue(new retrofit2.Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    myCallback.onResponse(null);
                } else {
                    myCallback.onResponse(null);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                myCallback.onFailure(t);
            }
        });
    }

    @Override
    public void setGamePlayed(Integer gameId, Boolean activity, Callback<Object> myCallback) {
        gamesApi.setGamePlayed(gameId, activity).enqueue(new retrofit2.Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    myCallback.onResponse(null);
                } else {
                    myCallback.onResponse(null);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                myCallback.onFailure(t);
            }
        });
    }

    @Override
    public void startToss(Integer gameId, Callback<Object> myCallback) {
        gamesApi.startToss(gameId).enqueue(new retrofit2.Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    myCallback.onResponse(null);
                } else {
                    myCallback.onResponse(null);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                myCallback.onFailure(t);
            }
        });
    }


}
