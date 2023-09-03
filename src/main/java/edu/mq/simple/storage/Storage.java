package edu.mq.simple.storage;

import edu.mq.simple.entity.SimpleMQMessage;
import edu.mq.simple.storage.exception.CannotReadMessageException;
import edu.mq.simple.storage.exception.CannotSendMessageException;

public interface Storage {
    SimpleMQMessage readMessage(String queueName) throws CannotReadMessageException;
    void sendMessage(String queueName, SimpleMQMessage message) throws CannotSendMessageException;
}
