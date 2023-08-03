package com.playdata.pdshared.domain.group.exception;

public class TeamPermissionException extends IllegalArgumentException{

    public TeamPermissionException() {
    }

    public TeamPermissionException(String s) {
        super(s);
    }
}
