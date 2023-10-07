package edu.mq.simple.jms.producer;

import edu.mq.simple.jms.connection.SimpleMQConnectionFactory;
import edu.mq.simple.test.TestUtils;
import jakarta.jms.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.*;

public class ProducerTest {

    private SimpleMQConnectionFactory connectionFactory;
    private Connection connection;
    private Session session;
    private Queue queue;
    private MessageProducer producer;

    @BeforeEach
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

    @AfterEach
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

        Assertions.assertEquals(1, TestUtils.messageCount("./db", "edu.queue.q1"));

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

        Assertions.assertEquals(1, TestUtils.messageCount("./db", "edu.queue.q1"));

        Assertions.assertEquals("""
                {
                  "headers" : null,
                  "bodyType" : "bytes",
                  "body" : "AQID"
                }""", TestUtils.readMessage("./db", "edu.queue.q1"));
    }

    @Test
    public void testMapMessage() throws JMSException {
        final var mapMessage = session.createMapMessage();
        mapMessage.setBoolean("boolentry", true);
        mapMessage.setByte("byteentry", (byte) 0xFD);
        mapMessage.setBytes("bytesentry", new byte[] {0x01, 0x02, 0x03});
        mapMessage.setBytes("byteslimentry", new byte[] {0x01, 0x02, 0x03, 0x04}, 1, 2);
        mapMessage.setChar("charentry", 'c');
        mapMessage.setDouble("dblentry", Double.MAX_VALUE);
        mapMessage.setFloat("fltentry", Float.MAX_VALUE);
        mapMessage.setInt("intentry", Integer.MAX_VALUE);
        mapMessage.setLong("longentry", Long.MAX_VALUE);
        mapMessage.setShort("shortentry", Short.MAX_VALUE);
        mapMessage.setString("strentry", "hello world");
        mapMessage.setObject("objentry", new TestPoint(34, 76));
        producer.send(mapMessage);

        Assertions.assertEquals(1, TestUtils.messageCount("./db", "edu.queue.q1"));

        Assertions.assertEquals("""
                {
                  "headers" : null,
                  "bodyType" : "map",
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
                      "type" : "object[edu.mq.simple.jms.producer.ProducerTest.TestPoint]",
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
                }""", TestUtils.readMessage("./db", "edu.queue.q1"));
    }

    @Getter
    @Setter
    @AllArgsConstructor
    static class TestPoint {
        private int x;
        private int y;
    }

}
