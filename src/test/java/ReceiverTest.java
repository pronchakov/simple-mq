import edu.mq.simple.connection.SimpleMQConnectionFactory;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.TextMessage;
import lombok.Cleanup;
import org.junit.jupiter.api.Test;

public class ReceiverTest {

    @Test
    public void producerTest() throws JMSException {
        final var connectionFactory = SimpleMQConnectionFactory.builder()
                .baseDir("./db")
                .build();
        @Cleanup final var connection = connectionFactory.createConnection();
        @Cleanup final var session = connection.createSession();
        final var queue = session.createQueue("edu.queue.q1");
        @Cleanup final var consumer = session.createConsumer(queue);

        final var message = consumer.receive();

        if (message instanceof TextMessage textMessage) {
            System.out.println(textMessage.getText());
        }
    }

}
