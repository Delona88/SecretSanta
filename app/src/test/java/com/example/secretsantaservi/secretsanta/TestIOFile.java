package com.example.secretsantaservi.secretsanta;

import com.example.secretsantaservi.API.repository.IOFile;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class TestIOFile {
    private IOFile ioFile;
    private static final String FILE_NAME = "TestFile.ser" ;
    private File file;

    @Before
    public void setUp(){
        ioFile = new IOFile();
    }

    @Test
    public void testReadFromFileWithNoFile(){
        System.out.println("FileNotFoundException expected");
        Object object = ioFile.readFromFile(FILE_NAME);
        System.out.println(object);
    }

    @Test
    public void testReadFromFileWithEmptyFile(){
        System.out.println("EOFException expected");
         file = new File(FILE_NAME);
         try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Object object = ioFile.readFromFile(FILE_NAME);
        System.out.println(object);
        System.out.println(file.delete());//не работает??
    }


}
