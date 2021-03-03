package com.example.secretsantaservi.androidrepository;

import android.content.Context;

import java.io.*;

public class IOFileAndroid implements IOInterface {

    Context context; //для записи и чтения файла в Android нужен контекст

    public IOFileAndroid(Context context) {
        this.context = context;
    }

    public void writeInFile(Object object, String fileName) {
        FileOutputStream fileOutputStream;
        ObjectOutputStream objectOutputStream;
        try {
            fileOutputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(object);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object readFromFile(String fileName) {
        FileInputStream fileInputStream;
        ObjectInputStream objectInputStream;
        Object object = null;
        try {
            fileInputStream = context.openFileInput(fileName);
            objectInputStream = new ObjectInputStream(fileInputStream);
            object = objectInputStream.readObject();
            objectInputStream.close();
            return object;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return object;
    }
}
