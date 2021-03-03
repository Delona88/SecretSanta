package repository;

import java.io.*;

public class IOFile implements IOInterface {

    public void writeInFile(Object object, String fileName) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName);
             ObjectOutputStream fileOut = new ObjectOutputStream(fileOutputStream)){
            fileOut.writeObject(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object readFromFile(String fileName) {

        try (FileInputStream stream = new FileInputStream(fileName);
             ObjectInputStream inputStream = new ObjectInputStream(stream)) {
            return inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


}
