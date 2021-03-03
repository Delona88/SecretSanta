package repository;

import java.util.HashMap;

public interface RepositoryInterface<Key, Value> {
    void add(Key id, Value object);

    Value getById(Key id);

    void update(Key id, Value object);

    void deleteById(Key id);

    HashMap<Key, Value> getAll();

}
