package edu.mq.simple.jms.connection;

import edu.mq.simple.Utils;
import edu.mq.simple.jms.session.SimpleMQSession;
import edu.mq.simple.storage.Storage;
import jakarta.jms.JMSException;
import jakarta.jms.Session;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;
import java.util.zip.CRC32;

@Data
@RequiredArgsConstructor
@Slf4j
public class SimpleMQConnection extends SimpleMQAbstractConnection {
    private String id = Utils.generateId();
    @NonNull private Storage storage;

    @Override
    public Session createSession() throws JMSException {
        log.info("Connection: {}: Creating new session", id);
        return new SimpleMQSession(this, storage);
    }

    @Override
    public void close() throws JMSException {
        log.info("Connection: {}: Closing connection", id);
    }
}
