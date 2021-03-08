package com.example.secretsantaservi.androidrepository.repositoriesfactory.fortests;

import com.example.secretsantaservi.androidrepository.IOInterface;

import java.io.*;

public class IOFile implements IOInterface {

    public void writeInFile(Object object, String file) {
        FileOutputStream fileOutputStream;
        ObjectOutputStream fileOut;
        try {
            fileOutputStream = new FileOutputStream(file);
            fileOut = new ObjectOutputStream(fileOutputStream);
            fileOut.writeObject(object);
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object readFromFile(String file) {
        FileInputStream stream;
        ObjectInputStream inputStream;
        Object object;
        try {
            stream = new FileInputStream(file);
            inputStream = new ObjectInputStream(stream);
            object = inputStream.readObject();
            inputStream.close();
            return object;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


}
