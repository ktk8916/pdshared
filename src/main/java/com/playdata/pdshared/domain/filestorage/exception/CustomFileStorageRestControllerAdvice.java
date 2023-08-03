package com.playdata.pdshared.domain.filestorage.exception;

import com.playdata.pdshared.global.exception.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomFileStorageRestControllerAdvice {
    @ExceptionHandler(FileStorageNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse fileStorageNotFoundException(FileStorageNotFoundException e){
        return new ExceptionResponse(e.getMessage());
    }

    @ExceptionHandler(FileNotExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse fileNotExistsException(FileNotExistsException e){
        return new ExceptionResponse(e.getMessage());
    }

    @ExceptionHandler(FileSaveFailException.class)
    @ResponseStatus(HttpStatus.INSUFFICIENT_STORAGE)
    public ExceptionResponse fileSaveFailException(FileSaveFailException e){
        return new ExceptionResponse(e.getMessage());
    }
}
