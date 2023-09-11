package edu.mq.simple.jms.consumer;

import edu.mq.simple.jms.connection.SimpleMQConnectionFactory;
import edu.mq.simple.test.TestUtils;
import jakarta.jms.JMSException;
import jakarta.jms.TextMessage;
import lombok.Cleanup;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ConsumeProducedMessagesTest {

    @Test
    public void consumeProducedMessages() throws JMSException {
        TestUtils.deleteQueue("./db", "edu.queue.consume-produced");

        final var connectionFactory = SimpleMQConnectionFactory.builder()
                .baseDir("./db")
                .build();
        @Cleanup final var connection = connectionFactory.createConnection();
        @Cleanup final var session = connection.createSession();
        final var queue = session.createQueue("edu.queue.consume-produced");
        @Cleanup final var producer = session.createProducer(queue);

        producer.send(session.createTextMessage("hello-1"));
        producer.send(session.createTextMessage("hello-2"));
        producer.send(session.createTextMessage("hello-3"));

        final var consumer = session.createConsumer(queue);
        Assertions.assertEquals("hello-1", ((TextMessage) consumer.receive()).getText());
        Assertions.assertEquals("hello-2", ((TextMessage) consumer.receive()).getText());
        Assertions.assertEquals("hello-3", ((TextMessage) consumer.receive()).getText());

        TestUtils.deleteQueue("./db", "edu.queue.consume-produced");
    }

}
