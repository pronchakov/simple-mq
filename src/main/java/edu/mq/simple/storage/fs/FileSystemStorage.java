package edu.mq.simple.storage.fs;

import edu.mq.simple.entity.SimpleMQMessage;
import edu.mq.simple.storage.fs.json.CannotTransformMessageToJSONException;
import edu.mq.simple.storage.fs.json.JSONMessageMapper;
import edu.mq.simple.storage.Storage;
import edu.mq.simple.storage.exception.CannotReadMessageException;
import edu.mq.simple.storage.exception.CannotWriteMessageException;

import java.util.HashMap;
import java.util.Map;

public class FileSystemStorage implements Storage {

    private final String basePath;
    private JSONMessageMapper jsonMapper = new JSONMessageMapper();
    private Map<String, FileSystemQueue> queues = new HashMap<>();


    public FileSystemStorage(String basePath) {
        if (!basePath.endsWith("/")) {
            basePath = basePath + "/";
        }
        this.basePath = basePath;
    }

    @Override
    public SimpleMQMessage readMessage(String queueName) throws CannotReadMessageException {
        final var text = getFileSystemQueue(queueName).readFile();
        if (text == null) {
            return null;
        }
        final var simpleMQMessage = jsonMapper.transformMessage(text);
        return simpleMQMessage;
    }

    @Override
    public void writeMessage(String queueName, SimpleMQMessage message) throws CannotWriteMessageException {
        try {
            var text = jsonMapper.transformMessage(message);
            getFileSystemQueue(queueName).writeFile(text);
        } catch (CannotTransformMessageToJSONException e) {
            throw new CannotWriteMessageException("Cannot prepare JMS message for saving", e);
        }

    }

    private FileSystemQueue getFileSystemQueue(String queueName) {
        var fsQueue = queues.get(queueName);
        if (fsQueue == null) {
            fsQueue = new FileSystemQueue(basePath, queueName);
            queues.put(queueName, fsQueue);
        }
        return fsQueue;
    }
}
