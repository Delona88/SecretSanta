package repository;

import java.util.HashMap;

public class RepositoryHM<Key, Value> implements RepositoryInterface<Key, Value> {
    private HashMap<Key, Value> hashMap = new HashMap<>();

    @Override
    public void add(Key id, Value object) {
        hashMap.put(id, object);
    }

    @Override
    public Value getById(Key id) {
        return hashMap.get(id);
    }

    @Override
    public void deleteById(Key id) {
        hashMap.remove(id);
    }

    @Override
    public void update(Key id, Value object) {
        hashMap.put(id, object);
    }

    @Override
    public HashMap<Key, Value> getAll() {
        return hashMap;
    }

}
