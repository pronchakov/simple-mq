package edu.mq.simple.connection;

import jakarta.jms.Connection;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSContext;
import jakarta.jms.JMSException;

public class SimpleMQConnectionFactory implements ConnectionFactory {

    private String baseDir;

    public SimpleMQConnectionFactory(String baseDir) {
        this.baseDir = baseDir;
    }

    @Override
    public Connection createConnection() throws JMSException {
        return new SimpleMQConnection();
    }

    @Override
    public Connection createConnection(String userName, String password) throws JMSException {
        return createConnection();
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
