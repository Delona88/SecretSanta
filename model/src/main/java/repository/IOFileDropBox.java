package repository;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.WriteMode;

import java.io.*;

public class IOFileDropBox implements IOInterface{

    private final DbxClientV2 client;

    public IOFileDropBox(String accessToken) {
        DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
        client = new DbxClientV2(config, accessToken);
    }

    public void writeInFile(Object object, String fileName) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName);
             ObjectOutputStream fileOut = new ObjectOutputStream(fileOutputStream)){
            fileOut.writeObject(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
        loadInDropBox(fileName);
    }

    public void loadInDropBox(String fileName) {
        try (InputStream in = new FileInputStream(fileName)) {
            client.files().uploadBuilder("/" + fileName).withMode(WriteMode.OVERWRITE)
                    .uploadAndFinish(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object readFromFile(String fileName) {
        downloadFromDropBox(fileName);
        try (FileInputStream stream = new FileInputStream(fileName);
             ObjectInputStream inputStream = new ObjectInputStream(stream)) {
            return inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void downloadFromDropBox(String fileName) {
        try (FileOutputStream outputStream = new FileOutputStream(fileName)){
            client.files().download("/" + fileName).download(outputStream);
        } catch (DbxException | IOException e) {
            e.printStackTrace();
        }
    }
}
