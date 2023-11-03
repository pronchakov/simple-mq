package edu.mq.simple.jms.consumer;

import edu.mq.simple.jms.connection.SimpleMQConnectionFactory;
import edu.mq.simple.test.TestUtils;
import edu.mq.simple.util.TestPoint;
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
        connectionFactory = new SimpleMQConnectionFactory();
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
                  "headers" : [ ],
                  "type" : "text",
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
                  "headers" : [ ],
                  "type" : "bytes",
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

    @Test
    public void tesReceiveMapMessage() throws JMSException {
        TestUtils.putMessage("./db", "edu.queue.q1", """
                {
                  "headers" : [ ],
                  "type" : "map",
                  "body" : {
                    "boolentry" : {
                      "type" : "boolean",
                      "value" : true
                    },
                    "byteentry" : {
                      "type" : "byte",
                      "value" : "/Q=="
                    },
                    "bytesentry" : {
                      "type" : "bytes",
                      "value" : "AQID"
                    },
                    "byteslimentry" : {
                      "type" : "bytes",
                      "value" : "AgM="
                    },
                    "charentry" : {
                      "type" : "character",
                      "value" : "c"
                    },
                    "dblentry" : {
                      "type" : "double",
                      "value" : 1.7976931348623157E308
                    },
                    "fltentry" : {
                      "type" : "float",
                      "value" : 3.4028235E38
                    },
                    "intentry" : {
                      "type" : "integer",
                      "value" : 2147483647
                    },
                    "longentry" : {
                      "type" : "long",
                      "value" : 9223372036854775807
                    },
                    "objentry" : {
                      "type" : "object[edu.mq.simple.util.TestPoint]",
                      "value" : {
                        "x" : 34,
                        "y" : 76
                      }
                    },
                    "shortentry" : {
                      "type" : "short",
                      "value" : 32767
                    },
                    "strentry" : {
                      "type" : "string",
                      "value" : "hello world"
                    }
                  }
                }""");

        final var message = consumer.receive();
        Assertions.assertTrue(message instanceof MapMessage);
        final var mapMessage = (MapMessage) message;

        Assertions.assertEquals(true, mapMessage.getBoolean("boolentry"));
        Assertions.assertEquals((byte) 0xFD, mapMessage.getByte("byteentry"));
        Assertions.assertArrayEquals(new byte[]{0x01, 0x02, 0x03}, mapMessage.getBytes("bytesentry"));
        Assertions.assertArrayEquals(new byte[]{0x02, 0x03}, mapMessage.getBytes("byteslimentry"));
        Assertions.assertEquals('c', mapMessage.getChar("charentry"));
        Assertions.assertEquals(Double.MAX_VALUE, mapMessage.getDouble("dblentry"));
        Assertions.assertEquals(Float.MAX_VALUE, mapMessage.getFloat("fltentry"));
        Assertions.assertEquals(Integer.MAX_VALUE, mapMessage.getInt("intentry"));
        Assertions.assertEquals(Long.MAX_VALUE, mapMessage.getLong("longentry"));
        Assertions.assertEquals(Short.MAX_VALUE, mapMessage.getShort("shortentry"));
        Assertions.assertEquals("hello world", mapMessage.getString("strentry"));
        Assertions.assertEquals(new TestPoint(34, 76), mapMessage.getObject("objentry"));

        Assertions.assertNull(consumer.receiveNoWait());
    }

}
