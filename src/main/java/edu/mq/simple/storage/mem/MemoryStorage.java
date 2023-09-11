package edu.mq.simple.storage.mem;

import edu.mq.simple.entity.SimpleMQMessage;
import edu.mq.simple.storage.Storage;
import edu.mq.simple.storage.exception.CannotReadMessageException;
import edu.mq.simple.storage.exception.CannotWriteMessageException;

public class MemoryStorage implements Storage {
    @Override
    public SimpleMQMessage readMessage(String queueName) throws CannotReadMessageException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void writeMessage(String queueName, SimpleMQMessage message) throws CannotWriteMessageException {
        throw new RuntimeException("Not implemented");
    }
}
