package edu.mq.simple.storage;

import edu.mq.simple.jms.message.SimpleMQMessage;
import edu.mq.simple.storage.exception.CannotReadMessageException;
import edu.mq.simple.storage.exception.CannotWriteMessageException;

public interface Storage {
    SimpleMQMessage readMessage(String queueName) throws CannotReadMessageException;

    void writeMessage(String queueName, SimpleMQMessage message) throws CannotWriteMessageException;
}
