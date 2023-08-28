package edu.mq.simple.connection;

import edu.mq.simple.SimpleMQSession;
import jakarta.jms.JMSException;
import jakarta.jms.Session;
import lombok.AllArgsConstructor;
import lombok.Data;

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
