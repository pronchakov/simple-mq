package edu.mq.simple.connection;

import jakarta.jms.Connection;
import jakarta.jms.JMSContext;
import jakarta.jms.JMSException;

import java.io.File;

public class SimpleMQConnectionFactory extends SimpleMQAbstractConnectionFactory {
    private String baseDir;

    public SimpleMQConnectionFactory(String baseDir) {
        this.baseDir = baseDir;
    }

    @Override
    public Connection createConnection() throws JMSException {
        return new SimpleMQConnection(baseDir);
    }
}
