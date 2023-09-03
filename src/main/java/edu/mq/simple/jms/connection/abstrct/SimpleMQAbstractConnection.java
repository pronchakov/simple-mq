package edu.mq.simple.jms.connection.abstrct;

import jakarta.jms.*;

public abstract class SimpleMQAbstractConnection implements Connection {
    @Override
    public Session createSession(boolean transacted, int acknowledgeMode) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public Session createSession(int sessionMode) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public Session createSession() throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public String getClientID() throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void setClientID(String clientID) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public ConnectionMetaData getMetaData() throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public ExceptionListener getExceptionListener() throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void setExceptionListener(ExceptionListener listener) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void start() throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void stop() throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void close() throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public ConnectionConsumer createConnectionConsumer(Destination destination, String messageSelector, ServerSessionPool sessionPool, int maxMessages) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public ConnectionConsumer createSharedConnectionConsumer(Topic topic, String subscriptionName, String messageSelector, ServerSessionPool sessionPool, int maxMessages) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public ConnectionConsumer createDurableConnectionConsumer(Topic topic, String subscriptionName, String messageSelector, ServerSessionPool sessionPool, int maxMessages) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public ConnectionConsumer createSharedDurableConnectionConsumer(Topic topic, String subscriptionName, String messageSelector, ServerSessionPool sessionPool, int maxMessages) throws JMSException {
        throw new RuntimeException("Not implemented");
    }
}
