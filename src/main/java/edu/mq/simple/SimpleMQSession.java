package edu.mq.simple;

import edu.mq.simple.message.SimpleMQTextMessage;
import jakarta.jms.*;

import java.io.File;

public class SimpleMQSession extends SimpleMQAbstractSession {

    private String baseDir;

    public SimpleMQSession(String baseDir) {
        this.baseDir = baseDir;
    }


    @Override
    public TextMessage createTextMessage(String text) throws JMSException {
        return new SimpleMQTextMessage(text);
    }

    @Override
    public MessageProducer createProducer(Destination destination) throws JMSException {
        return new SimpleMQMessageProducer(baseDir, destination);
    }

    @Override
    public Queue createQueue(String queueName) throws JMSException {
        new File(baseDir + "/" + queueName).mkdirs();
        return new SimpleMQQueue(queueName);
    }

    @Override
    public void close() throws JMSException {

    }
}
