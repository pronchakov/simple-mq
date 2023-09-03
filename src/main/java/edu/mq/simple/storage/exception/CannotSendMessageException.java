package edu.mq.simple.storage.exception;

public class CannotSendMessageException extends Exception {
    public CannotSendMessageException(String message, Throwable cause) {
        super(message, cause);
    }
}
