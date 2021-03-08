package repository;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

public class RepositoryFileWithHM<Key, Value> implements RepositoryInterface<Key, Value> {

    private IOInterface IOFile;
    private ConcurrentHashMap<Key, Value> hashMap = new ConcurrentHashMap<>();
    private String fileName;

    public RepositoryFileWithHM(String fileName) {
        this.fileName = fileName;
        IOFile = new IOFile();
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
                IOFile.writeInFile(hashMap, fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public RepositoryFileWithHM(IOInterface IOFile, String fileName) {
        this.IOFile = IOFile;
        this.fileName = fileName;
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
                IOFile.writeInFile(hashMap, fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void add(Key id, Value object) {
        hashMap = (ConcurrentHashMap<Key, Value>) IOFile.readFromFile(fileName);
        hashMap.put(id, object);
        IOFile.writeInFile(hashMap, fileName);
    }

    @Override
    public Value getById(Key id) {
        hashMap = (ConcurrentHashMap<Key, Value>) IOFile.readFromFile(fileName);
        return hashMap.get(id);
    }

    @Override
    public void deleteById(Key id) {
        hashMap = (ConcurrentHashMap<Key, Value>) IOFile.readFromFile(fileName);
        hashMap.remove(id);
        IOFile.writeInFile(hashMap, fileName);
    }

    @Override
    public void update(Key id, Value object) {
        hashMap = (ConcurrentHashMap<Key, Value>) IOFile.readFromFile(fileName);
        hashMap.put(id, object);
        IOFile.writeInFile(hashMap, fileName);
    }

    @Override
    public ConcurrentHashMap<Key, Value> getAll() {
        hashMap = (ConcurrentHashMap<Key, Value>) IOFile.readFromFile(fileName);
        return hashMap;
    }

}
