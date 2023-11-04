package edu.mq.simple.jms.session;

import edu.mq.simple.Utils;
import edu.mq.simple.jms.connection.SimpleMQConnection;
import edu.mq.simple.jms.consumer.SimpleMQMessageConsumer;
import edu.mq.simple.jms.destination.SimpleMQQueue;
import edu.mq.simple.jms.message.type.bytes.SimpleMQBytesMessage;
import edu.mq.simple.jms.message.type.map.SimpleMQMapMessage;
import edu.mq.simple.jms.message.type.text.SimpleMQTextMessage;
import edu.mq.simple.jms.producer.SimpleMQMessageProducer;
import edu.mq.simple.storage.Storage;
import jakarta.jms.*;
import lombok.Data;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class SimpleMQSession extends SimpleMQAbstractSession {
    private String id = Utils.generateId();
    private SimpleMQConnection connection;
    private Storage storage;

    public SimpleMQSession(
            @NonNull SimpleMQConnection connection,
            @NonNull Storage storage
    ) {
        this.connection = connection;
        this.storage = storage;
    }

    @Override
    public BytesMessage createBytesMessage() throws JMSException {
        log.debug("Session: {}: Creating new bytes message", id);
        return new SimpleMQBytesMessage();
    }

    @Override
    public TextMessage createTextMessage(String text) throws JMSException {
        log.debug("Session: {}: Creating new text message", id);
        return new SimpleMQTextMessage(text);
    }

    @Override
    public MapMessage createMapMessage() throws JMSException {
        log.debug("Session: {}: Creating new map message", id);
        return new SimpleMQMapMessage();
    }

    @Override
    public MessageProducer createProducer(Destination destination) throws JMSException {
        log.debug("Session: {}: Creating producer for destination '{}'", id, destination.toString());
        return new SimpleMQMessageProducer(this, destination);
    }

    @Override
    public MessageConsumer createConsumer(Destination destination) throws JMSException {
        log.debug("Session: {}: Creating consumer for destination '{}'", id, destination.toString());
        return new SimpleMQMessageConsumer(this, destination);
    }

    @Override
    public Queue createQueue(String queueName) throws JMSException {
        log.debug("Session: {}: Creating queue '{}'", id, queueName);
        return new SimpleMQQueue(queueName);
    }

    @Override
    public void close() throws JMSException {
        log.debug("Session: {}: Closing session", id);
    }

}
