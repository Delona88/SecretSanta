package repository;

import java.util.concurrent.ConcurrentHashMap;

public interface RepositoryInterface<Key, Value> {
    void add(Key id, Value object);

    Value getById(Key id);

    void update(Key id, Value object);

    void deleteById(Key id);

    ConcurrentHashMap<Key, Value> getAll();

}
