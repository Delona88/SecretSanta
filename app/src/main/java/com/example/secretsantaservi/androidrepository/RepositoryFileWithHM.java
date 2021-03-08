package com.example.secretsantaservi.androidrepository;

import android.content.Context;

import com.example.secretsantaservi.androidrepository.repositoriesfactory.fortests.IOFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class RepositoryFileWithHM<Key, Value> implements RepositoryInterface<Key, Value> {
    private final IOInterface IOFile;
    private final String fileName;
    private HashMap<Key, Value> hashMap = new HashMap<>();

    public RepositoryFileWithHM(String fileName, Context context) {
        this.fileName = fileName;
        IOFile = new IOFileAndroid(context);
        File file = new File(context.getFilesDir() + "/" + fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
                IOFile.writeInFile(hashMap, fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // For tests
    public RepositoryFileWithHM(String fileName) {
        this.IOFile = new IOFile();
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
        hashMap = (HashMap<Key, Value>) IOFile.readFromFile(fileName);
        hashMap.put(id, object);
        IOFile.writeInFile(hashMap, fileName);
    }

    @Override
    public Value getById(Key id) {
        hashMap = (HashMap<Key, Value>) IOFile.readFromFile(fileName);
        return hashMap.get(id);
    }

    @Override
    public void deleteById(Key id) {
        hashMap = (HashMap<Key, Value>) IOFile.readFromFile(fileName);
        hashMap.remove(id);
        IOFile.writeInFile(hashMap, fileName);
    }

    @Override
    public void update(Key id, Value object) {
        hashMap = (HashMap<Key, Value>) IOFile.readFromFile(fileName);
        hashMap.put(id, object);
        IOFile.writeInFile(hashMap, fileName);
    }

    @Override
    public HashMap<Key, Value> getAll() {
        hashMap = (HashMap<Key, Value>) IOFile.readFromFile(fileName);
        return hashMap;
    }

}
