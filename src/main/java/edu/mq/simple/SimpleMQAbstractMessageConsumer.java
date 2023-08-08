package edu.mq.simple;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageConsumer;
import jakarta.jms.MessageListener;

public class SimpleMQAbstractMessageConsumer implements MessageConsumer {
    @Override
    public String getMessageSelector() throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public MessageListener getMessageListener() throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void setMessageListener(MessageListener listener) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public Message receive() throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public Message receive(long timeout) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public Message receiveNoWait() throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void close() throws JMSException {
        throw new RuntimeException("Not implemented");
    }
}
