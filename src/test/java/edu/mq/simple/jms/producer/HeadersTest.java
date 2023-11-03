package edu.mq.simple.jms.producer;

import edu.mq.simple.jms.connection.SimpleMQConnectionFactory;
import edu.mq.simple.test.TestUtils;
import jakarta.jms.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HeadersTest {

    private SimpleMQConnectionFactory connectionFactory;
    private Connection connection;
    private Session session;
    private Queue queue;
    private MessageProducer producer;

    @BeforeEach
    public void before() throws JMSException {
        TestUtils.deleteQueue("./db", "edu.queue.q1");
        connectionFactory = new SimpleMQConnectionFactory();
        connection = connectionFactory.createConnection();
        session = connection.createSession();
        queue = session.createQueue("edu.queue.q1");
        producer = session.createProducer(queue);
    }

    @AfterEach
    public void after() throws JMSException {
        producer.close();
        session.close();
        connection.close();
        TestUtils.deleteQueue("./db", "edu.queue.q1");
    }

    @Test
    public void stringHeaderTest() throws Exception {
        final var textMessage = session.createTextMessage("Hello World");
        textMessage.setStringProperty("key1", "value1");
        producer.send(textMessage);

        Assertions.assertEquals(1, TestUtils.messageCount("./db", "edu.queue.q1"));

        Assertions.assertEquals("""
                {
                  "headers" : [ {
                    "name" : "key1",
                    "type" : "string",
                    "value" : "value1"
                  } ],
                  "type" : "text",
                  "body" : "Hello World"
                }""", TestUtils.readMessage("./db", "edu.queue.q1"));
    }

}
