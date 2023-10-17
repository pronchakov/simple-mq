package edu.mq.simple.storage.fs;

import edu.mq.simple.jms.message.SimpleMQMessage;
import edu.mq.simple.storage.Storage;
import edu.mq.simple.storage.exception.CannotReadMessageException;
import edu.mq.simple.storage.exception.CannotWriteMessageException;
import edu.mq.simple.storage.fs.json.JsonFileFormatter;

import java.util.HashMap;
import java.util.Map;

public class FileSystemStorage implements Storage {

    private final String basePath;
    private FileFormatter fileFormatter = new JsonFileFormatter();
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

        final var message = fileFormatter.toMessage(text);
        return message;
    }

    @Override
    public void writeMessage(String queueName, SimpleMQMessage message) throws CannotWriteMessageException {
        final var text = fileFormatter.toText(message);
        getFileSystemQueue(queueName).writeFile(text);
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
