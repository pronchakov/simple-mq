package edu.mq.simple.storage;

public class CannotSendMessageException extends Exception{
    public CannotSendMessageException(String message, Throwable cause) {
        super(message, cause);
    }
}
