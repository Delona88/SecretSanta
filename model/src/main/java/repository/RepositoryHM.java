package repository;

import java.util.concurrent.ConcurrentHashMap;

public class RepositoryHM<Key, Value> implements RepositoryInterface<Key, Value> {
    private ConcurrentHashMap<Key, Value> hashMap = new ConcurrentHashMap<>();

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
    public ConcurrentHashMap<Key, Value> getAll() {
        return hashMap;
    }

}
