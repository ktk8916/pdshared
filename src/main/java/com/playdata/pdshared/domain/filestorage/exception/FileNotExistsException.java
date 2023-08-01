package com.playdata.pdshared.domain.filestorage.exception;

public class FileNotExistsException extends IllegalArgumentException{

    private String path;
    public FileNotExistsException(String path) {
        super("FILE NOT EXISTS");
        this.path = path;
    }
}
