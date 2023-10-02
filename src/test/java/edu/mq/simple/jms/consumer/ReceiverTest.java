package edu.mq.simple.jms.consumer;

import edu.mq.simple.jms.connection.SimpleMQConnectionFactory;
import edu.mq.simple.test.TestUtils;
import jakarta.jms.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ReceiverTest {

    private SimpleMQConnectionFactory connectionFactory;
    private Connection connection;
    private Session session;
    private Queue queue;
    private MessageConsumer consumer;

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
        consumer = session.createConsumer(queue);
    }

    @BeforeEach
    public void after() throws JMSException {
        consumer.close();
        session.close();
        connection.close();
        TestUtils.deleteQueue("./db", "edu.queue.q1");
    }

    @Test
    public void tesReceiveTextMessage() throws JMSException {
        TestUtils.putMessage("./db", "edu.queue.q1", """
                {
                  "headers" : null,
                  "bodyType" : "text",
                  "body" : "Hello World"
                }""");

        final var message = consumer.receive();
        Assertions.assertTrue(message instanceof TextMessage);
        final var textMessage = (TextMessage) message;
        Assertions.assertEquals("Hello World", textMessage.getText());

        Assertions.assertNull(consumer.receiveNoWait());
    }

    @Test
    public void tesReceiveBytesMessage() throws JMSException {
        TestUtils.putMessage("./db", "edu.queue.q1", """
                {
                  "headers" : null,
                  "bodyType" : "bytes",
                  "body" : "AQID"
                }""");

        final var message = consumer.receive();
        Assertions.assertTrue(message instanceof BytesMessage);
        final var bytesMessage = (BytesMessage) message;
        Assertions.assertEquals(3, bytesMessage.getBodyLength());
        final var bytesArray = new byte[3];
        bytesMessage.readBytes(bytesArray);
        Assertions.assertArrayEquals(new byte[]{0x01, 0x02, 0x03}, bytesArray);

        Assertions.assertNull(consumer.receiveNoWait());
    }

}
