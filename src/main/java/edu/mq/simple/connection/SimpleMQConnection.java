package edu.mq.simple.connection;

import edu.mq.simple.SimpleMQSession;
import jakarta.jms.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.File;

@Data
@AllArgsConstructor
public class SimpleMQConnection extends SimpleMQAbstractConnection {
    private String baseDir;

    @Override
    public Session createSession() throws JMSException {
        return new SimpleMQSession(this);
    }

    @Override
    public void close() throws JMSException {

    }
}
