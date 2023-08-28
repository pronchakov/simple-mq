package edu.mq.simple.storage;

public interface Storage {
    String getBasePath();

    void sendMessage(String queueName, String message) throws CannotSendMessageException;

    void createQueue(String queueName);

}
