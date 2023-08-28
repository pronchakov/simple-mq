package edu.mq.simple;

import jakarta.jms.*;

public class SimpleMQAbstractMessageProducer implements MessageProducer {
    @Override
    public boolean getDisableMessageID() throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void setDisableMessageID(boolean value) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public boolean getDisableMessageTimestamp() throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void setDisableMessageTimestamp(boolean value) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public int getDeliveryMode() throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void setDeliveryMode(int deliveryMode) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public int getPriority() throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void setPriority(int defaultPriority) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public long getTimeToLive() throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void setTimeToLive(long timeToLive) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public long getDeliveryDelay() throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void setDeliveryDelay(long deliveryDelay) throws JMSException {

    }

    @Override
    public Destination getDestination() throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void close() throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void send(Message message) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void send(Message message, int deliveryMode, int priority, long timeToLive) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void send(Destination destination, Message message) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void send(Destination destination, Message message, int deliveryMode, int priority, long timeToLive) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void send(Message message, CompletionListener completionListener) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void send(Message message, int deliveryMode, int priority, long timeToLive, CompletionListener completionListener) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void send(Destination destination, Message message, CompletionListener completionListener) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void send(Destination destination, Message message, int deliveryMode, int priority, long timeToLive, CompletionListener completionListener) throws JMSException {
        throw new RuntimeException("Not implemented");
    }
}
