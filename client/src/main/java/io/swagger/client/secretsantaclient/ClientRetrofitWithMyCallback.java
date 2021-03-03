package io.swagger.client.secretsantaclient;


import io.swagger.client.ApiClient;
import io.swagger.client.api.GamesApi;
import io.swagger.client.api.PersonGamesApi;
import io.swagger.client.api.PersonsApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import secretsantamodel.*;


public class ClientRetrofitWithMyCallback implements ApiWithMyCallbackInterface {
    Converter converter;
    PersonsApi personsApi;
    GamesApi gamesApi;
    PersonGamesApi personGamesApi;

    public ClientRetrofitWithMyCallback() {
        converter = new Converter();
        personsApi = new ApiClient().createService(PersonsApi.class);
        gamesApi = new ApiClient().createService(GamesApi.class);
        personGamesApi = new ApiClient().createService(PersonGamesApi.class);
    }

    @Override
    public void getPersonById(String email, MyCallback<Person> myCallback) {
        personsApi.getPersonById(email).enqueue(new Callback<io.swagger.client.model.Person>() {
            @Override
            public void onResponse(Call<io.swagger.client.model.Person> call, Response<io.swagger.client.model.Person> response) {
                if (response.isSuccessful()) {
                    myCallback.onResponse(converter.convert(response.body()));
                } else {
                    myCallback.onResponse(null);
                    //Log.d("getPersonById", "Code: " + response.code() + " Message: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<io.swagger.client.model.Person> call, Throwable t) {
                myCallback.onFailure(t);
                //t.printStackTrace();
            }
        });
    }

    @Override
    public void getAllPersons(MyCallback<HashMap<String, Person>> myCallback) {
        personsApi.getAllPersons().enqueue(new Callback<List<io.swagger.client.model.Person>>() {
            @Override
            public void onResponse(Call<List<io.swagger.client.model.Person>> call, Response<List<io.swagger.client.model.Person>> response) {
                HashMap<String, Person> hashMap = new HashMap<>();
                if (response.isSuccessful()) {
                    List<io.swagger.client.model.Person> list = response.body();
                    for (io.swagger.client.model.Person person : list) {
                        hashMap.put(person.getEmail(), converter.convert(person));
                    }
                    myCallback.onResponse(hashMap);
                } else {
                    myCallback.onResponse(hashMap); //пустая??
                    //Log.d("getPersonById", "Code: " + response.code() + " Message: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<io.swagger.client.model.Person>> call, Throwable t) {
                myCallback.onFailure(t);
                //t.printStackTrace();
            }
        });
    }

    @Override
    public void addPerson(Person person, MyCallback<Object> myCallback) {
        personsApi.addPerson(converter.convert(person)).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    myCallback.onResponse(null);
                } else {
                    myCallback.onResponse(null);
                    //Log.d("getPersonById", "Code: " + response.code() + " Message: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                myCallback.onFailure(t);
                //t.printStackTrace();
            }
        });
    }

    @Override
    public void replacePerson(Person person, String email, MyCallback<Object> myCallback) {
        //TODO подумать
        personsApi.replacePerson(converter.convert(person), email).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    myCallback.onResponse(null);
                } else {
                    myCallback.onResponse(null);
                    //Log.d("getPersonById", "Code: " + response.code() + " Message: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                myCallback.onFailure(t);
                //t.printStackTrace();
            }
        });
    }

    @Override
    public void deletePerson(String email, MyCallback<Object> myCallback) {
        personsApi.deletePerson(email).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    myCallback.onResponse(null);
                } else {
                    myCallback.onResponse(null);
                    //Log.d("getPersonById", "Code: " + response.code() + " Message: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                myCallback.onFailure(t);
                //t.printStackTrace();
            }
        });
    }


    @Override
    public void addPersonGameToPerson(String email, PersonGame personGame, MyCallback<Object> myCallback) {
        personGamesApi.addPersonGameToPerson(converter.convert(personGame), email).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    myCallback.onResponse(null);
                } else {
                    myCallback.onResponse(null);
                    //Log.d("getPersonById", "Code: " + response.code() + " Message: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                myCallback.onFailure(t);
                //t.printStackTrace();
            }
        });
    }

    @Override
    public void deletePersonGameFromPerson(Integer gameId, String email, MyCallback<Object> myCallback) {
        personGamesApi.deletePersonGameFromPerson(email, gameId).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    myCallback.onResponse(null);
                } else {
                    myCallback.onResponse(null);
                    //Log.d("getPersonById", "Code: " + response.code() + " Message: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                myCallback.onFailure(t);
                //t.printStackTrace();
            }
        });
    }

    @Override
    public void setNaughtyList(String email, Integer gameId, ArrayList<String> arraylist, MyCallback<Object> myCallback) {
        personGamesApi.setNaughtyList(arraylist, gameId, email).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    myCallback.onResponse(null);
                } else {
                    myCallback.onResponse(null);
                    //Log.d("getPersonById", "Code: " + response.code() + " Message: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                myCallback.onFailure(t);
                //t.printStackTrace();
            }
        });
    }

    @Override
    public void setReceiver(String emailSanta, Integer gameId, String emailReceiver, MyCallback<Object> myCallback) {
        personGamesApi.setReceiver(gameId, emailSanta, emailReceiver).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    myCallback.onResponse(null);
                } else {
                    myCallback.onResponse(null);
                    //Log.d("getPersonById", "Code: " + response.code() + " Message: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                myCallback.onFailure(t);
                //t.printStackTrace();
            }
        });
    }

    @Override
    public void setWhishlist(String email, Integer gameId, String wish, MyCallback<Object> myCallback) {
        personGamesApi.setWhishlist(gameId, email, wish).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    myCallback.onResponse(null);
                } else {
                    myCallback.onResponse(null);
                    //Log.d("getPersonById", "Code: " + response.code() + " Message: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                myCallback.onFailure(t);
                //t.printStackTrace();
            }
        });
    }

    @Override
    public void setPersonGameActive(Integer gameId, String email, Boolean activity, MyCallback<Object> myCallback) {
        personGamesApi.setPersonGameActive(gameId, email, activity).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    myCallback.onResponse(null);
                } else {
                    myCallback.onResponse(null);
                    //Log.d("getPersonById", "Code: " + response.code() + " Message: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                myCallback.onFailure(t);
                //t.printStackTrace();
            }
        });
    }

    @Override
    public void addGame(SecretSantaGame game, MyCallback<Object> myCallback) {
        gamesApi.addGame(converter.convert(game)).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    myCallback.onResponse(null);
                } else {
                    myCallback.onResponse(null);
                    //Log.d("getPersonById", "Code: " + response.code() + " Message: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                myCallback.onFailure(t);
                //t.printStackTrace();
            }
        });
    }

    @Override
    public void getGameById(int id, MyCallback<SecretSantaGame> myCallback) {
        gamesApi.getGameById(id).enqueue(new Callback<io.swagger.client.model.SecretSantaGame>() {
            @Override
            public void onResponse(Call<io.swagger.client.model.SecretSantaGame> call, Response<io.swagger.client.model.SecretSantaGame> response) {
                if (response.isSuccessful()) {
                    myCallback.onResponse(converter.convert(response.body()));
                } else {
                    myCallback.onResponse(null);
                    //Log.d("getPersonById", "Code: " + response.code() + " Message: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<io.swagger.client.model.SecretSantaGame> call, Throwable t) {
                myCallback.onFailure(t);
                //t.printStackTrace();
            }
        });
    }

    @Override
    public void deleteGame(Integer gameId, MyCallback<Object> myCallback) {
        gamesApi.deleteGame(gameId).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    myCallback.onResponse(null);
                } else {
                    myCallback.onResponse(null);
                    //Log.d("getPersonById", "Code: " + response.code() + " Message: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                myCallback.onFailure(t);
                //t.printStackTrace();
            }
        });
    }

    @Override
    public void getNewID(MyCallback<Integer> myCallback) {
        gamesApi.getNewID().enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.isSuccessful()) {
                    myCallback.onResponse(response.body());
                } else {
                    myCallback.onResponse(null);
                    //Log.d("getPersonById", "Code: " + response.code() + " Message: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                myCallback.onFailure(t);
                //t.printStackTrace();
            }
        });
    }


    @Override
    public void getHMWithPersonsInfo(Integer gameId, MyCallback<HashMap<String, String>> myCallback) {
        gamesApi.getPersonsByGameId(gameId).enqueue(new Callback<List<io.swagger.client.model.Person>>() {
            @Override
            public void onResponse(Call<List<io.swagger.client.model.Person>> call, Response<List<io.swagger.client.model.Person>> response) {

                HashMap<String, String> hashMapPersonsInfo = new HashMap<>();

                if (response.isSuccessful()) {
                    List<io.swagger.client.model.Person> list = response.body();
                    for (io.swagger.client.model.Person person : list) {
                        String str = person.getName() + " (" + person.getEmail() + ") ";
                        hashMapPersonsInfo.put(person.getEmail(), str);
                    }
                } else {
                    //Log.d("getPersonById", "Code: " + response.code() + " Message: " + response.message());
                }
                myCallback.onResponse(hashMapPersonsInfo); //пустая в случае ошибки?? или null
            }

            @Override
            public void onFailure(Call<List<io.swagger.client.model.Person>> call, Throwable t) {
                myCallback.onFailure(t);
                //t.printStackTrace();
            }
        });
    }

    @Override
    public void getPersonsByGameId(Integer gameId, MyCallback<HashMap<String, Person>> myCallback) {
        gamesApi.getPersonsByGameId(gameId).enqueue(new Callback<List<io.swagger.client.model.Person>>() {
            @Override
            public void onResponse(Call<List<io.swagger.client.model.Person>> call, Response<List<io.swagger.client.model.Person>> response) {

                HashMap<String, Person> hashMapPersons = new HashMap<>();

                if (response.isSuccessful()) {
                    List<io.swagger.client.model.Person> list = response.body();
                    for (io.swagger.client.model.Person person : list) {
                        hashMapPersons.put(person.getEmail(), converter.convert(person));
                    }
                } else {
                    //Log.d("getPersonById", "Code: " + response.code() + " Message: " + response.message());
                }
                myCallback.onResponse(hashMapPersons); //пустая в случае ошибки?? или null
            }

            @Override
            public void onFailure(Call<List<io.swagger.client.model.Person>> call, Throwable t) {
                myCallback.onFailure(t);
                //t.printStackTrace();
            }
        });
    }

    @Override
    public void addPersonInGame(Integer gameId, String email, MyCallback<Object> myCallback) {
        gamesApi.addPersonInGame(gameId, email).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    myCallback.onResponse(null);
                } else {
                    myCallback.onResponse(null);
                    //Log.d("getPersonById", "Code: " + response.code() + " Message: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                myCallback.onFailure(t);
                //t.printStackTrace();
            }
        });
    }

    @Override
    public void deletePersonFromGame(Integer gameId, String email, MyCallback<Object> myCallback) {
        gamesApi.deletePersonFromGame(gameId, email).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    myCallback.onResponse(null);
                } else {
                    myCallback.onResponse(null);
                    //Log.d("getPersonById", "Code: " + response.code() + " Message: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                myCallback.onFailure(t);
                //t.printStackTrace();
            }
        });
    }

    @Override
    public void setGamePlayed(Integer gameId, Boolean activity, MyCallback<Object> myCallback) {
        gamesApi.setGamePlayed(gameId, activity).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    myCallback.onResponse(null);
                } else {
                    myCallback.onResponse(null);
                    //Log.d("getPersonById", "Code: " + response.code() + " Message: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                myCallback.onFailure(t);
                //t.printStackTrace();
            }
        });
    }

    @Override
    public void startToss(Integer gameId, MyCallback<Object> myCallback) {
        gamesApi.startToss(gameId).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    myCallback.onResponse(response.code());
                } else {
                    myCallback.onResponse(response.code()); //ловим BAD_REQUEST
                    //Log.d("getPersonById", "Code: " + response.code() + " Message: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                myCallback.onFailure(t);
                //t.printStackTrace();
            }
        });
    }






    @Override
    public void setGamesByPersonId(String email, HashMap<Integer, PersonGame> games) {

    }

}
