package edu.mq.simple.jms.connection;

import jakarta.jms.Connection;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSContext;
import jakarta.jms.JMSException;

public abstract class SimpleMQAbstractConnectionFactory implements ConnectionFactory {

    @Override
    public Connection createConnection() throws JMSException {
        return null;
    }

    @Override
    public Connection createConnection(String userName, String password) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public JMSContext createContext() {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public JMSContext createContext(String userName, String password) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public JMSContext createContext(String userName, String password, int sessionMode) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public JMSContext createContext(int sessionMode) {
        throw new RuntimeException("Not implemented");
    }
}
