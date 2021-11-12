package com.tilitili.schedule.exception;

public class UnLoginException extends RuntimeException {
    public UnLoginException() {
        super();
    }

    public UnLoginException(String s) {
        super(s);
    }

    public UnLoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnLoginException(Throwable cause) {
        super(cause);
    }

}
