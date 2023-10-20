package edu.mq.simple.jms.connection;

import edu.mq.simple.jms.session.SimpleMQSession;
import jakarta.jms.JMSException;
import jakarta.jms.Session;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SimpleMQConnection extends SimpleMQAbstractConnection {
    private String baseDir; // todo: remove basedir as it if only for FS storage

    @Override
    public Session createSession() throws JMSException {
        return new SimpleMQSession(this);
    }

    @Override
    public void close() throws JMSException {

    }
}
