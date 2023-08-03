package com.playdata.pdshared.domain.group.exception;

import com.playdata.pdshared.global.exception.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomGroupRestControllerAdvice {

    @ExceptionHandler(TeamNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse teamNotFoundExceptionHandler(TeamNotFoundException e){
        return new ExceptionResponse(e.getMessage());
    }

    @ExceptionHandler(TeamPermissionException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ExceptionResponse teamPermissionException(TeamPermissionException e){
        return new ExceptionResponse(e.getMessage());
    }

    @ExceptionHandler(ExistMemberException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse existMemberException(ExistMemberException e){
        return new ExceptionResponse(e.getMessage());
    }
}
