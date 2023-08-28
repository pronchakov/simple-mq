package edu.mq.simple.storage;

import edu.mq.simple.entity.UniversalMessage;

public interface Storage {
    String getBasePath();

    void sendMessage(String queueName, UniversalMessage message) throws CannotSendMessageException;

    void createQueue(String queueName);

}
