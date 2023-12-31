package edu.mq.simple.jms.producer;

import edu.mq.simple.Utils;
import edu.mq.simple.jms.message.SimpleMQMessage;
import edu.mq.simple.jms.session.SimpleMQSession;
import edu.mq.simple.storage.exception.CannotWriteMessageException;
import jakarta.jms.Destination;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.Queue;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@RequiredArgsConstructor
@Slf4j
public class SimpleMQMessageProducer extends SimpleMQAbstractMessageProducer {

    private String id = Utils.generateId();
    @NonNull
    private SimpleMQSession session;
    @NonNull
    private Destination destination;

    @Override
    public void send(Message message) throws JMSException {
        if (!(message instanceof final SimpleMQMessage simpleMQMessage)) {
            throw new JMSException("Unknown message type", "SMQ003");
        }

        var queue = (Queue) destination; // todo: can be topic
        try {
            session.getStorage().writeMessage(queue.getQueueName(), simpleMQMessage);
        } catch (CannotWriteMessageException e) {
            throw new JMSException("Cannot save JMS message", "SMQ002", e);
        }
    }

    @Override
    public void close() throws JMSException {
        log.debug("Producer: {}: Closing producer", id);
    }
}
