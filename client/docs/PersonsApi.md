# PersonsApi

All URIs are relative to *http://localhost:8080/*

Method | HTTP request | Description
------------- | ------------- | -------------
[**addPerson**](PersonsApi.md#addPerson) | **POST** persons | Add person
[**deletePerson**](PersonsApi.md#deletePerson) | **DELETE** persons/{email} | Delete person
[**getAllPersons**](PersonsApi.md#getAllPersons) | **GET** persons | Get all persons
[**getPersonById**](PersonsApi.md#getPersonById) | **GET** persons/{email} | Get person by id
[**replacePerson**](PersonsApi.md#replacePerson) | **PUT** persons/{email} | Replace person

<a name="addPerson"></a>
# **addPerson**
> Void addPerson(body)

Add person

Add person

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.PersonsApi;


PersonsApi apiInstance = new PersonsApi();
Person body = new Person(); // Person | Person
try {
    Void result = apiInstance.addPerson(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PersonsApi#addPerson");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**Person**](Person.md)| Person |

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

<a name="deletePerson"></a>
# **deletePerson**
> Void deletePerson(email)

Delete person

Delete person

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.PersonsApi;


PersonsApi apiInstance = new PersonsApi();
String email = "email_example"; // String | email
try {
    Void result = apiInstance.deletePerson(email);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PersonsApi#deletePerson");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **email** | **String**| email |

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

<a name="getAllPersons"></a>
# **getAllPersons**
> List&lt;Person&gt; getAllPersons()

Get all persons

Get all Persons

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.PersonsApi;


PersonsApi apiInstance = new PersonsApi();
try {
    List<Person> result = apiInstance.getAllPersons();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PersonsApi#getAllPersons");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;Person&gt;**](Person.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getPersonById"></a>
# **getPersonById**
> Person getPersonById(email)

Get person by id

Get person by id

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.PersonsApi;


PersonsApi apiInstance = new PersonsApi();
String email = "email_example"; // String | to get person
try {
    Person result = apiInstance.getPersonById(email);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PersonsApi#getPersonById");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **email** | **String**| to get person |

### Return type

[**Person**](Person.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="replacePerson"></a>
# **replacePerson**
> Void replacePerson(body, email)

Replace person

Replace person

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.PersonsApi;


PersonsApi apiInstance = new PersonsApi();
Person body = new Person(); // Person | Person
String email = "email_example"; // String | to get person
try {
    Void result = apiInstance.replacePerson(body, email);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PersonsApi#replacePerson");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**Person**](Person.md)| Person |
 **email** | **String**| to get person |

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

