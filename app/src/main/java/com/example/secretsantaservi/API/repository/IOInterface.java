package com.example.secretsantaservi.API.repository;


public interface IOInterface {
    void writeInFile(Object object, String fileName);
    Object readFromFile(String fileName);
}
