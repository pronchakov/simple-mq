package edu.mq.simple.jms.connection;

import edu.mq.simple.jms.session.SimpleMQSession;
import edu.mq.simple.storage.Storage;
import jakarta.jms.JMSException;
import jakarta.jms.Session;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SimpleMQConnection extends SimpleMQAbstractConnection {
    private Storage storage;

    @Override
    public Session createSession() throws JMSException {
        return new SimpleMQSession(this, storage);
    }

    @Override
    public void close() throws JMSException {

    }
}
