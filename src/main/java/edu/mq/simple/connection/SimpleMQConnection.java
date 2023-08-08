package edu.mq.simple.connection;

import edu.mq.simple.SimpleMQSession;
import jakarta.jms.*;

import java.io.File;

public class SimpleMQConnection extends SimpleMQAbstractConnection {
    private String baseDir;

    public SimpleMQConnection(String baseDir) {
        this.baseDir = baseDir;
        new File(baseDir).mkdirs();
    }

    @Override
    public Session createSession() throws JMSException {
        return new SimpleMQSession(baseDir);
    }

    @Override
    public void close() throws JMSException {

    }
}
