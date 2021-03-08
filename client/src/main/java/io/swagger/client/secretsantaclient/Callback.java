package io.swagger.client.secretsantaclient;

public interface Callback<T> {
    void onResponse(T response) ;

    void onFailure(Throwable t) ;
}

