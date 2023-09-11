package edu.mq.simple.storage.exception;

public class CannotWriteMessageException extends Exception {
    public CannotWriteMessageException(String message, Throwable cause) {
        super(message, cause);
    }
}
