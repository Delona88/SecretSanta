package com.example.secretsantaservi.API;

public interface MyCallback<T> {
    void onResponse(T response) ;

    void onFailure(Throwable t) ;
}

