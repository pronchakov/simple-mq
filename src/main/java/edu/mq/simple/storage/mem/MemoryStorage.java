package edu.mq.simple.storage.mem;

import edu.mq.simple.jms.message.SimpleMQMessage;
import edu.mq.simple.storage.Storage;
import edu.mq.simple.storage.exception.CannotReadMessageException;
import edu.mq.simple.storage.exception.CannotWriteMessageException;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

public class MemoryStorage implements Storage {

    private final Map<String, Queue<SimpleMQMessage>> queueStorage = new TreeMap<>();

    private Queue<SimpleMQMessage> getQueue(final String queueName) { // todo: multithreading
        var queue = queueStorage.get(queueName);
        if (queue == null) {
            queue = new LinkedList<>();
        }
        return queue;
    }

    @Override
    public SimpleMQMessage readMessage(final String queueName) throws CannotReadMessageException {
        final var queue = getQueue(queueName);
        return queue.poll();
    }

    @Override
    public void writeMessage(final String queueName, final SimpleMQMessage message) throws CannotWriteMessageException {
        final var queue = getQueue(queueName);
        queue.add(message);
    }
}
