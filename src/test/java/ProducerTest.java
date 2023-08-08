import edu.mq.simple.connection.SimpleMQConnectionFactory;
import jakarta.jms.*;
import org.junit.jupiter.api.Test;

public class ProducerTest {

    @Test
    public void producerTest() throws JMSException {
        final var connectionFactory = new SimpleMQConnectionFactory("./db");
        final var connection = connectionFactory.createConnection();
        final var session = connection.createSession();
        final var queue = session.createQueue("edu.queue.q1");
        final var producer = session.createProducer(queue);

        final var textMessage = session.createTextMessage("Hello World");
        producer.send(textMessage);

        producer.close();
        session.close();
        connection.close();
    }

}
