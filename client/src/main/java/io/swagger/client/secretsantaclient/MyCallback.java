package io.swagger.client.secretsantaclient;

public interface MyCallback<T> {
    void onResponse(T response) ;

    void onFailure(Throwable t) ;
}

