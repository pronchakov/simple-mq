package edu.mq.simple.jms.connection;

import edu.mq.simple.jms.connection.abstrct.SimpleMQAbstractConnectionFactory;
import jakarta.jms.Connection;
import jakarta.jms.JMSException;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Builder
@Data
public class SimpleMQConnectionFactory extends SimpleMQAbstractConnectionFactory {
    @NonNull
    private String baseDir;

    @Override
    public Connection createConnection() throws JMSException {
        return new SimpleMQConnection(baseDir);
    }
}
