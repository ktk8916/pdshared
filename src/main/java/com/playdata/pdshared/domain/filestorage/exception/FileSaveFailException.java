package com.playdata.pdshared.domain.filestorage.exception;

public class FileSaveFailException extends RuntimeException{
    public FileSaveFailException() {
        super("FILE SAVE FAIL");
    }

    public FileSaveFailException(String message) {
        super(message);
    }
}
