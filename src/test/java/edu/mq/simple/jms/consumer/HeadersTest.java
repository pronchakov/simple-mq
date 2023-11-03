package edu.mq.simple.jms.consumer;

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
    private MessageConsumer consumer;

    @BeforeEach
    public void before() throws JMSException {
        TestUtils.deleteQueue("./db", "edu.queue.q1");
        connectionFactory = new SimpleMQConnectionFactory();
        connection = connectionFactory.createConnection();
        session = connection.createSession();
        queue = session.createQueue("edu.queue.q1");
        consumer = session.createConsumer(queue);
    }

    @AfterEach
    public void after() throws JMSException {
        consumer.close();
        session.close();
        connection.close();
        TestUtils.deleteQueue("./db", "edu.queue.q1");
    }

    @Test
    public void stringHeaderTest() throws Exception {
        TestUtils.putMessage("./db", "edu.queue.q1", """
                {
                  "headers" : [ {
                    "name" : "key1",
                    "type" : "string",
                    "value" : "value1"
                  } ],
                  "type" : "text",
                  "body" : "Hello World"
                }""");


        final var message = consumer.receiveNoWait();
        Assertions.assertTrue(message instanceof TextMessage);

        final var textMessage = (TextMessage) message;
        final var key1Value = textMessage.getStringProperty("key1");
        Assertions.assertEquals("value1", key1Value);
    }

}
