package com.playdata.pdshared.domain.group.exception;

public class ExistMemberException extends IllegalArgumentException{
    public ExistMemberException() {
        super("EXIST MEMBER");
    }

    public ExistMemberException(String s) {
        super(s);
    }
}
