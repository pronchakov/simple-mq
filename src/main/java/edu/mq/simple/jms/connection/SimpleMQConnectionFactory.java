package edu.mq.simple.jms.connection;

import edu.mq.simple.storage.Storage;
import edu.mq.simple.storage.fs.FileSystemStorage;
import jakarta.jms.Connection;
import jakarta.jms.JMSException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class SimpleMQConnectionFactory extends SimpleMQAbstractConnectionFactory {

    private static final String BASE_PATH = "./db";

    @Override
    public Connection createConnection() throws JMSException {
        log.info("Creating connection with default file system storage in '{}'", BASE_PATH);
        final var defaultStorage = new FileSystemStorage(BASE_PATH);
        return new SimpleMQConnection(defaultStorage);
    }

    @Override
    public Connection createConnection(String userName, String password) throws JMSException {
        return createConnection();
    }

    public Connection createConnection(Storage storage) throws JMSException {
        if (storage == null) {
            throw new IllegalArgumentException("Storage must not be null");
        }
        log.info("Creating connection with custom storage '{}'", storage.getClass().getName());
        return new SimpleMQConnection(storage);
    }
}
