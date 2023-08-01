package com.playdata.pdshared.domain.filestorage.exception;

public class FileStorageNotFoundException extends IllegalArgumentException{

    public FileStorageNotFoundException() {
        super("FILE STORAGE NOT FOUND");
    }

    public FileStorageNotFoundException(String s) {
        super(s);
    }
}
