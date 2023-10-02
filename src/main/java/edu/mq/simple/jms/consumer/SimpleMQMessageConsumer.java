package edu.mq.simple.jms.consumer;

import edu.mq.simple.entity.SimpleMQMessage;
import edu.mq.simple.jms.consumer.abstrct.SimpleMQAbstractMessageConsumer;
import edu.mq.simple.jms.message.SimpleMQJMSMessageConverter;
import edu.mq.simple.jms.message.UnknownTypeException;
import edu.mq.simple.jms.message.abstrct.SimpleMQAbstractMessage;
import edu.mq.simple.jms.session.SimpleMQSession;
import edu.mq.simple.storage.exception.CannotReadMessageException;
import jakarta.jms.Destination;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.Queue;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class SimpleMQMessageConsumer extends SimpleMQAbstractMessageConsumer {
    @NonNull
    private SimpleMQSession session;
    @NonNull
    private Destination destination;

    private SimpleMQJMSMessageConverter jmsMessageConverter = new SimpleMQJMSMessageConverter();

    @Override
    public Message receive() throws JMSException { // TODO: wrong implementation. add waiting
        final SimpleMQMessage simpleMQMessage;
        try {
            simpleMQMessage = session.getStorage().readMessage(((Queue) destination).getQueueName());
            if (simpleMQMessage == null) {
                return null;
            }
        } catch (CannotReadMessageException e) {
            throw new RuntimeException(e); // todo:
        }
        final SimpleMQAbstractMessage jmsMessage;
        try {
            jmsMessage = jmsMessageConverter.convert(simpleMQMessage);
        } catch (UnknownTypeException e) {
            throw new RuntimeException(e); // todo:
        }
        return  jmsMessage;
    }

    @Override
    public Message receiveNoWait() throws JMSException {
        final SimpleMQMessage simpleMQMessage;
        try {
            simpleMQMessage = session.getStorage().readMessage(((Queue) destination).getQueueName());
            if (simpleMQMessage == null) {
                return null;
            }
        } catch (CannotReadMessageException e) {
            throw new RuntimeException(e); // todo:
        }
        final SimpleMQAbstractMessage jmsMessage;
        try {
            jmsMessage = jmsMessageConverter.convert(simpleMQMessage);
        } catch (UnknownTypeException e) {
            throw new RuntimeException(e); // todo:
        }
        return  jmsMessage;
    }

    @Override
    public void close() throws JMSException {

    }
}
