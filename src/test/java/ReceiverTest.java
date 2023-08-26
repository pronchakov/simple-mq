import edu.mq.simple.connection.SimpleMQConnectionFactory;
import jakarta.jms.BytesMessage;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.TextMessage;
import lombok.Cleanup;
import org.junit.jupiter.api.Test;

import java.util.HexFormat;

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

        for (int i = 0; i < 2; i++) {
            final var message = consumer.receive();

            if (message instanceof TextMessage textMessage) {
                System.out.println("Text message: " + textMessage.getText());
            } else if (message instanceof BytesMessage bytesMessage) {
                final var bodyLength = bytesMessage.getBodyLength();
                final var bytes = new byte[(int) bodyLength];
                bytesMessage.readBytes(bytes);
                System.out.println("Bytes message: " + HexFormat.of().withDelimiter(" ").withPrefix("0x").withUpperCase().formatHex(bytes));
            }
        }

    }

}