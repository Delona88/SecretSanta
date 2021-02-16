# PersonGamesApi

All URIs are relative to *http://localhost:8080/*

Method | HTTP request | Description
------------- | ------------- | -------------
[**addPersonGameToPerson**](PersonGamesApi.md#addPersonGameToPerson) | **POST** persons/{email}/persongame | Add person game
[**deletePersonGameFromPerson**](PersonGamesApi.md#deletePersonGameFromPerson) | **DELETE** persons/{email}/persongame/{id} | Delete person game
[**setNaughtyList**](PersonGamesApi.md#setNaughtyList) | **POST** persons/{email}/persongame/{id}/naughtylist | Set naughty list
[**setPersonGameActive**](PersonGamesApi.md#setPersonGameActive) | **POST** persons/{email}/persongame/{id}/active | Set active
[**setReceiver**](PersonGamesApi.md#setReceiver) | **POST** persons/{email}/persongame/{id}/receiver | Set receiver
[**setWhishlist**](PersonGamesApi.md#setWhishlist) | **POST** persons/{email}/persongame/{id}/wishlist | Set whishlist

<a name="addPersonGameToPerson"></a>
# **addPersonGameToPerson**
> Void addPersonGameToPerson(body, email)

Add person game

Add person game

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.PersonGamesApi;


PersonGamesApi apiInstance = new PersonGamesApi();
PersonGame body = new PersonGame(); // PersonGame | Array emails
String email = "email_example"; // String | email
try {
    Void result = apiInstance.addPersonGameToPerson(body, email);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PersonGamesApi#addPersonGameToPerson");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**PersonGame**](PersonGame.md)| Array emails |
 **email** | **String**| email |

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

<a name="deletePersonGameFromPerson"></a>
# **deletePersonGameFromPerson**
> Void deletePersonGameFromPerson(email, id)

Delete person game

Delete person game

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.PersonGamesApi;


PersonGamesApi apiInstance = new PersonGamesApi();
String email = "email_example"; // String | email
Integer id = 56; // Integer | id
try {
    Void result = apiInstance.deletePersonGameFromPerson(email, id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PersonGamesApi#deletePersonGameFromPerson");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **email** | **String**| email |
 **id** | **Integer**| id |

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

<a name="setNaughtyList"></a>
# **setNaughtyList**
> Void setNaughtyList(body, id, email)

Set naughty list

Set naughty list

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.PersonGamesApi;


PersonGamesApi apiInstance = new PersonGamesApi();
List<String> body = Arrays.asList("body_example"); // List<String> | Person game
Integer id = 56; // Integer | id
String email = "email_example"; // String | email
try {
    Void result = apiInstance.setNaughtyList(body, id, email);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PersonGamesApi#setNaughtyList");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**List&lt;String&gt;**](String.md)| Person game |
 **id** | **Integer**| id |
 **email** | **String**| email |

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

<a name="setPersonGameActive"></a>
# **setPersonGameActive**
> Void setPersonGameActive(id, email, active)

Set active

Set active

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.PersonGamesApi;


PersonGamesApi apiInstance = new PersonGamesApi();
Integer id = 56; // Integer | id
String email = "email_example"; // String | email
Boolean active = true; // Boolean | active
try {
    Void result = apiInstance.setPersonGameActive(id, email, active);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PersonGamesApi#setPersonGameActive");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **Integer**| id |
 **email** | **String**| email |
 **active** | **Boolean**| active |

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

<a name="setReceiver"></a>
# **setReceiver**
> Void setReceiver(id, email, receiverEmail)

Set receiver

Set receiver

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.PersonGamesApi;


PersonGamesApi apiInstance = new PersonGamesApi();
Integer id = 56; // Integer | id
String email = "email_example"; // String | email
String receiverEmail = "receiverEmail_example"; // String | receiverEmail
try {
    Void result = apiInstance.setReceiver(id, email, receiverEmail);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PersonGamesApi#setReceiver");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **Integer**| id |
 **email** | **String**| email |
 **receiverEmail** | **String**| receiverEmail |

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

<a name="setWhishlist"></a>
# **setWhishlist**
> Void setWhishlist(id, email, wish)

Set whishlist

Set whishlist

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.PersonGamesApi;


PersonGamesApi apiInstance = new PersonGamesApi();
Integer id = 56; // Integer | id
String email = "email_example"; // String | email
String wish = "wish_example"; // String | wishlist
try {
    Void result = apiInstance.setWhishlist(id, email, wish);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PersonGamesApi#setWhishlist");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **Integer**| id |
 **email** | **String**| email |
 **wish** | **String**| wishlist |

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

