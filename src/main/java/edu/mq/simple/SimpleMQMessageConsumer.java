package edu.mq.simple;

import edu.mq.simple.json.JsonToMessageMapper;
import edu.mq.simple.message.SimpleMQBytesMessage;
import edu.mq.simple.message.SimpleMQTextMessage;
import jakarta.jms.Destination;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.Queue;
import lombok.Cleanup;
import lombok.Data;
import lombok.NonNull;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

@Data
public class SimpleMQMessageConsumer extends SimpleMQAbstractMessageConsumer {

    private final File[] files;
    private int nextFileIndex = 0;
    @NonNull
    private SimpleMQSession session;
    @NonNull
    private Destination destination;
    private JsonToMessageMapper mapper = new JsonToMessageMapper();

    public SimpleMQMessageConsumer(@NonNull SimpleMQSession session, @NonNull Destination destination) throws JMSException {
        this.session = session;
        this.destination = destination;

        var queue = (Queue) destination;
        final var storage = session.getStorage();
        final var dir = new File(storage.getBasePath() + queue.getQueueName());
        files = dir.listFiles((dir1, name) -> name.endsWith(".mq"));
    }

    @Override
    public Message receive() throws JMSException {

        try {
            @Cleanup final FileReader fileReader = new FileReader(files[nextFileIndex]);
            final var bytes = IOUtils.toByteArray(fileReader);
            final var message = mapper.transformMessage(bytes);

            var jmsMessage = switch (message.getBodyType()) {
                case "text" -> new SimpleMQTextMessage(message.getBody());
                case "bytes" -> new SimpleMQBytesMessage();
                default -> null;
            };

            nextFileIndex++;
            return jmsMessage;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void close() throws JMSException {

    }
}
