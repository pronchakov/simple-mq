package edu.mq.simple.jms.connection;

import edu.mq.simple.storage.Storage;
import edu.mq.simple.storage.fs.FileSystemStorage;
import jakarta.jms.Connection;
import jakarta.jms.JMSException;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
public class SimpleMQConnectionFactory extends SimpleMQAbstractConnectionFactory {

    @Override
    public Connection createConnection() throws JMSException {
        final var defaultStorage = new FileSystemStorage("./db");
        return new SimpleMQConnection(defaultStorage);
    }

    @Override
    public Connection createConnection(String userName, String password) throws JMSException {
        return createConnection();
    }

    public Connection createConnection(Storage storage) throws JMSException {
        return new SimpleMQConnection(storage);
    }
}
