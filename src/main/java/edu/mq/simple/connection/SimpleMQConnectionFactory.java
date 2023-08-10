package edu.mq.simple.connection;

import jakarta.jms.Connection;
import jakarta.jms.JMSException;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.io.File;

@Builder
@Data
public class SimpleMQConnectionFactory extends SimpleMQAbstractConnectionFactory {
    @NonNull private String baseDir;

    @Override
    public Connection createConnection() throws JMSException {
        return new SimpleMQConnection(baseDir);
    }
}
