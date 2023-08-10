import edu.mq.simple.connection.SimpleMQConnectionFactory;
import jakarta.jms.*;
import lombok.Cleanup;
import org.junit.jupiter.api.Test;

public class ProducerTest {

    @Test
    public void producerTest() throws JMSException {
        final var connectionFactory = SimpleMQConnectionFactory.builder()
                .baseDir("./db")
                .build();
        @Cleanup final var connection = connectionFactory.createConnection();
        @Cleanup final var session = connection.createSession();
        final var queue = session.createQueue("edu.queue.q1");
        @Cleanup final var producer = session.createProducer(queue);

        final var textMessage = session.createTextMessage("Hello World");
        producer.send(textMessage);

        final var bytesMessage = session.createBytesMessage();
        bytesMessage.writeBytes(new byte[]{0x01, 0x02, 0x03});
        producer.send(bytesMessage);
    }

}
