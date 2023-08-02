package com.playdata.pdshared.domain.group.exception;

public class TeamNotFoundException extends IllegalArgumentException{
    public TeamNotFoundException() {
        super("TEAM NOT FOUNT");
    }
}
