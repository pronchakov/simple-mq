package edu.mq.simple.jms.producer;

import edu.mq.simple.jms.message.SimpleMQJMSMessageConverter;
import edu.mq.simple.jms.message.abstrct.SimpleMQAbstractMessage;
import edu.mq.simple.jms.producer.abstrct.SimpleMQAbstractMessageProducer;
import edu.mq.simple.jms.session.SimpleMQSession;
import edu.mq.simple.storage.exception.CannotSendMessageException;
import jakarta.jms.Destination;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.Queue;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class SimpleMQMessageProducer extends SimpleMQAbstractMessageProducer {

    @NonNull
    private SimpleMQSession session;
    @NonNull
    private Destination destination;
    private SimpleMQJMSMessageConverter jmsMessageConverter = new SimpleMQJMSMessageConverter();

    @Override
    public void send(Message message) throws JMSException {
        if (!(message instanceof final SimpleMQAbstractMessage abstractMessage)) {
            throw new JMSException("Unknown message type", "SMQ003");
        }

        var queue = (Queue) destination;

        final var universalMessage = jmsMessageConverter.convert(abstractMessage);
        try {
            session.getStorage().sendMessage(queue.getQueueName(), universalMessage);
        } catch (CannotSendMessageException e) {
            throw new JMSException("Cannot save JMS message", "SMQ002", e);
        }
    }

    @Override
    public void close() throws JMSException {

    }
}
