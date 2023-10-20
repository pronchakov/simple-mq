package edu.mq.simple.jms.connection;

import jakarta.jms.Connection;
import jakarta.jms.JMSException;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Builder
@Data
public class SimpleMQConnectionFactory extends SimpleMQAbstractConnectionFactory {
    @NonNull
    private String baseDir; // todo: remove basedir as it if only for FS storage

    @Override
    public Connection createConnection() throws JMSException {
        return new SimpleMQConnection(baseDir);
    }
}
