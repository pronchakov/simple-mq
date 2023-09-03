package edu.mq.simple.jms.connection;

import edu.mq.simple.jms.connection.abstrct.SimpleMQAbstractConnection;
import edu.mq.simple.jms.session.SimpleMQSession;
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
