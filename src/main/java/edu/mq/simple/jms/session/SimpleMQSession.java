package edu.mq.simple.jms.session;

import edu.mq.simple.jms.connection.SimpleMQConnection;
import edu.mq.simple.jms.consumer.SimpleMQMessageConsumer;
import edu.mq.simple.jms.destination.SimpleMQQueue;
import edu.mq.simple.jms.message.SimpleMQBytesMessage;
import edu.mq.simple.jms.message.SimpleMQTextMessage;
import edu.mq.simple.jms.producer.SimpleMQMessageProducer;
import edu.mq.simple.storage.Storage;
import edu.mq.simple.storage.fs.FileSystemStorage;
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
    public BytesMessage createBytesMessage() throws JMSException {
        return SimpleMQBytesMessage.forSend();
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
    public MessageConsumer createConsumer(Destination destination) throws JMSException {
        return new SimpleMQMessageConsumer(this, destination);
    }

    @Override
    public Queue createQueue(String queueName) throws JMSException {
        return new SimpleMQQueue(queueName);
    }

    @Override
    public void close() throws JMSException {

    }


}
