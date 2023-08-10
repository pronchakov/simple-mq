package edu.mq.simple;

import edu.mq.simple.connection.SimpleMQConnection;
import edu.mq.simple.storage.FileSystemStorage;
import edu.mq.simple.storage.Storage;
import edu.mq.simple.message.SimpleMQTextMessage;
import jakarta.jms.*;
import lombok.Data;
import lombok.NonNull;

@Data
public class SimpleMQSession extends SimpleMQAbstractSession {
    private SimpleMQConnection connection;
    private Storage storage;

    public SimpleMQSession(@NonNull SimpleMQConnection connection) {
        this.connection = connection;
        storage = new FileSystemStorage(connection.getBaseDir());
    }

    @Override
    public TextMessage createTextMessage(String text) throws JMSException {
        return new SimpleMQTextMessage(text);
    }

    @Override
    public MessageProducer createProducer(Destination destination) throws JMSException {
        return new SimpleMQMessageProducer(this, destination);
    }

    @Override
    public Queue createQueue(String queueName) throws JMSException {
        storage.createQueue(queueName);
        return new SimpleMQQueue(queueName);
    }

    @Override
    public void close() throws JMSException {

    }
}
