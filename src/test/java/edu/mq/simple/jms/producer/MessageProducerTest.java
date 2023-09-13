package edu.mq.simple.jms.producer;

import edu.mq.simple.jms.connection.SimpleMQConnectionFactory;
import edu.mq.simple.test.TestUtils;
import jakarta.jms.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MessageProducerTest {

    private SimpleMQConnectionFactory connectionFactory;
    private Connection connection;
    private Session session;
    private Queue queue;
    private MessageProducer producer;

    @BeforeEach
    @AfterEach
    public void before() throws JMSException {
        TestUtils.deleteQueue("./db", "edu.queue.q1");
        connectionFactory = SimpleMQConnectionFactory.builder()
                .baseDir("./db")
                .build();
        connection = connectionFactory.createConnection();
        session = connection.createSession();
        queue = session.createQueue("edu.queue.q1");
        producer = session.createProducer(queue);
    }

    @BeforeEach
    public void after() throws JMSException {
        producer.close();
        session.close();
        connection.close();
        TestUtils.deleteQueue("./db", "edu.queue.q1");
    }

    @Test
    public void testTextMessage() throws JMSException {
        final var textMessage = session.createTextMessage("Hello World");
        producer.send(textMessage);

        Assertions.assertEquals("""
                {
                  "headers" : null,
                  "bodyType" : "text",
                  "body" : "Hello World"
                }""", TestUtils.readMessage("./db", "edu.queue.q1"));
    }

    @Test
    public void testByteMessage() throws JMSException {
        final var bytesMessage = session.createBytesMessage();
        bytesMessage.writeBytes(new byte[]{0x01, 0x02, 0x03});
        producer.send(bytesMessage);

        Assertions.assertEquals("""
                {
                  "headers" : null,
                  "bodyType" : "bytes",
                  "body" : "AQID"
                }""", TestUtils.readMessage("./db", "edu.queue.q1"));
    }

}