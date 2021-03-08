# GamesApi

All URIs are relative to *http://localhost:8080/*

Method | HTTP request | Description
------------- | ------------- | -------------
[**addGame**](GamesApi.md#addGame) | **POST** personGames | Add personGame
[**addPersonInGame**](GamesApi.md#addPersonInGame) | **POST** personGames/{id}/participants/{email} | Add participant
[**deleteGame**](GamesApi.md#deleteGame) | **DELETE** personGames/{id} | Delete personGame
[**deletePersonFromGame**](GamesApi.md#deletePersonFromGame) | **DELETE** personGames/{id}/participants/{email} | Delete participant
[**getGameById**](GamesApi.md#getGameById) | **GET** personGames/{id} | Get personGame by id
[**getNewID**](GamesApi.md#getNewID) | **GET** personGames | Get new ID
[**getPersonsByGameId**](GamesApi.md#getPersonsByGameId) | **GET** personGames/{id}/participants | Get all persons by personGame ID
[**setGamePlayed**](GamesApi.md#setGamePlayed) | **POST** personGames/{id}/played | Set played
[**startToss**](GamesApi.md#startToss) | **POST** personGames/{id}/toss | Start toss

<a name="addGame"></a>
# **addGame**
> Void addGame(body)

Add personGame

Create new personGame

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.GamesApi;


GamesApi apiInstance = new GamesApi();
SecretSantaGame body = new SecretSantaGame(); // SecretSantaGame | personGame to add
try {
    Void result = apiInstance.addGame(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling GamesApi#addGame");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**SecretSantaGame**](SecretSantaGame.md)| personGame to add |

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

<a name="addPersonInGame"></a>
# **addPersonInGame**
> Void addPersonInGame(id, email)

Add participant

Add participant

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.GamesApi;


GamesApi apiInstance = new GamesApi();
Integer id = 56; // Integer | id
String email = "email_example"; // String | email
try {
    Void result = apiInstance.addPersonInGame(id, email);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling GamesApi#addPersonInGame");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **Integer**| id |
 **email** | **String**| email |

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

<a name="deleteGame"></a>
# **deleteGame**
> Void deleteGame(id)

Delete personGame

Delete personGame

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.GamesApi;


GamesApi apiInstance = new GamesApi();
Integer id = 56; // Integer | id
try {
    Void result = apiInstance.deleteGame(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling GamesApi#deleteGame");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **Integer**| id |

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

<a name="deletePersonFromGame"></a>
# **deletePersonFromGame**
> Void deletePersonFromGame(id, email)

Delete participant

Delete participant

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.GamesApi;


GamesApi apiInstance = new GamesApi();
Integer id = 56; // Integer | id
String email = "email_example"; // String | email
try {
    Void result = apiInstance.deletePersonFromGame(id, email);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling GamesApi#deletePersonFromGame");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **Integer**| id |
 **email** | **String**| email |

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

<a name="getGameById"></a>
# **getGameById**
> SecretSantaGame getGameById(id)

Get personGame by id

Get personGame by id

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.GamesApi;


GamesApi apiInstance = new GamesApi();
Integer id = 56; // Integer | to get personGame
try {
    SecretSantaGame result = apiInstance.getGameById(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling GamesApi#getGameById");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **Integer**| to get personGame |

### Return type

[**SecretSantaGame**](SecretSantaGame.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getNewID"></a>
# **getNewID**
> Integer getNewID()

Get new ID

Get new ID

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.GamesApi;


GamesApi apiInstance = new GamesApi();
try {
    Integer result = apiInstance.getNewID();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling GamesApi#getNewID");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

**Integer**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getPersonsByGameId"></a>
# **getPersonsByGameId**
> List&lt;Person&gt; getPersonsByGameId(id)

Get all persons by personGame ID

Get all persons by personGame ID

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.GamesApi;


GamesApi apiInstance = new GamesApi();
Integer id = 56; // Integer | id
try {
    List<Person> result = apiInstance.getPersonsByGameId(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling GamesApi#getPersonsByGameId");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **Integer**| id |

### Return type

[**List&lt;Person&gt;**](Person.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="setGamePlayed"></a>
# **setGamePlayed**
> Void setGamePlayed(id, played)

Set played

Set played

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.GamesApi;


GamesApi apiInstance = new GamesApi();
Integer id = 56; // Integer | id
Boolean played = true; // Boolean | played
try {
    Void result = apiInstance.setGamePlayed(id, played);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling GamesApi#setGamePlayed");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **Integer**| id |
 **played** | **Boolean**| played |

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

<a name="startToss"></a>
# **startToss**
> Void startToss(id)

Start toss

Start toss

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.GamesApi;


GamesApi apiInstance = new GamesApi();
Integer id = 56; // Integer | id
try {
    Void result = apiInstance.startToss(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling GamesApi#startToss");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **Integer**| id |

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

