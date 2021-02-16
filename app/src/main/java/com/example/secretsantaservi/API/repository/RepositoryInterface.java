package com.example.secretsantaservi.API.repository;

import java.util.HashMap;

public interface RepositoryInterface<Key, Value> {
    void add(Key id, Value object);

    Value getById(Key id);

    void deleteById(Key id);

    void update(Key id, Value object);

    HashMap<Key, Value> getAll();
}
